package com.localoge.rentalService.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.localoge.rentalService.exceptions.Exceptions;
import com.localoge.rentalService.model.Rental;
import com.localoge.rentalService.repository.RentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service // Spring annotation to mark this class as a service
public class RentalService implements IRentalService {

    @Autowired // JPA annotation to mark this field as an autowired dependency
    private RentalRepository rentalRepository;

    @Value("${housing.service.url}") // Spring annotation to inject the value of the property housing.service.url from application.properties
    private String housingServiceUrl;

    @Value("${reservation.service.url}")
    private String reservationServiceUrl;

    RestTemplate restTemplate = new RestTemplate();

    @Override
    public Rental createRental(Rental rental) throws Exception {
        // Verify that all fields are present
        if (rental.getPropertyId() == null || rental.getDurationInDays() == null || rental.getDailyPrice() == null || rental.getDescription() == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        // Verify if the property exists in the housing service by making a GET request to the housing service
        String url = housingServiceUrl + "/housings/get?id=" + rental.getPropertyId();
        boolean isProperty;
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);
            isProperty = responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                isProperty = false;
            } else {
                throw new Exception("Error while checking if the property exists", e);
            }
        } catch (Exception e) {
            throw new Exception("Error while checking if the property exists", e);
        }
        if (!isProperty) {
            throw new Exceptions.PropertyNotFoundException("Property does not exist");
        }

        // Verify if the rental already exists in the database (if there is an object with the same propertyId)
        if (rentalRepository.existsByPropertyId(rental.getPropertyId())) {
            throw new Exceptions.RentalAlreadyExistsException("Rental already exists");
        }

        // Save the rental in the database
        return rentalRepository.save(rental);
    }

    @Override
    public void deleteRental(Integer id) throws Exception {
        // Verify if the rental exists in the database
        if (!rentalRepository.existsById(id)) {
            throw new Exceptions.RentalNotFoundException("Rental does not exist");
        }

        // Verify if the rental is not reserved by making a GET request to the reservation service
        String url = reservationServiceUrl + "/reservations/rental?rentalId=" + id;
        boolean isReserved;
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);
            isReserved = responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                isReserved = false;
            } else {
                throw new Exception("Error while checking reservation status", e);
            }
        } catch (Exception e) {
            throw new Exception("Error while checking reservation status", e);
        }
        if (isReserved) {
            throw new Exceptions.RentalIsReservedException("Rental is reserved");
        }

        // Delete the rental from the database
        rentalRepository.deleteById(id);
    }

    @Override
    public Rental getRental(Integer id) throws Exceptions.RentalNotFoundException {
        // Verify if the rental exists in the database
        if (!rentalRepository.existsById(id)) {
            throw new Exceptions.RentalNotFoundException("Rental does not exist");
        }

        // Get the rental from the database
        return rentalRepository.findById(id).get();
    }

    @Override
    public List<Rental> getAllRentals() throws Exceptions.RentalNotFoundException {
        if (rentalRepository.count() == 0) {
            throw new Exceptions.RentalNotFoundException("No rentals found");
        }
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalByPropertyId(Integer propertyId) throws Exceptions.RentalNotFoundException {
        if (!rentalRepository.existsByPropertyId(propertyId)) {
            throw new Exceptions.RentalNotFoundException("Rental does not exist");
        }
        return rentalRepository.findByPropertyId(propertyId);
    }
}
