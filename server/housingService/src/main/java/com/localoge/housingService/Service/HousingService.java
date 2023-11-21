package com.localoge.housingService.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.localoge.housingService.DAO.HousingDAO;
import com.localoge.housingService.Exceptions.Exceptions;
import com.localoge.housingService.Model.Housing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class HousingService implements IHousingService{
    @Autowired // JPA annotation to mark this field as an autowired dependency
    private HousingDAO housingDAO;

    @Value("${rental.service.url}")
    private String rentalServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate(); // Spring's RestTemplate to make HTTP requests

    @Override
    public Housing createHousing(Housing housing) {
        // Verify that all fields are present
        if (housing.getType() == null || housing.getLocation() == null || housing.getOwnerId() == null) {
            throw new IllegalArgumentException("All fields are required");
        }

        // Save the housing in the database
        return housingDAO.save(housing);
    }

    @Override
    public void deleteHousing(Integer id) throws Exception {
        // Verify if the housing exists in the database
        if (!housingDAO.existsById(id)) {
            throw new Exceptions.HousingNotFoundException("Housing does not exist");
        }

        // Verify if the housing is not rented by making a GET request to the rental service
        String url = rentalServiceUrl + "/rentals/housing?propertyId=" + id;
        boolean isRented;
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);
            isRented = responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                isRented = false;
            } else {
                throw new Exception("Error while checking housing status", e);
            }
        } catch (Exception e) {
            throw new Exception("Error while checking housing status", e);
        }
        if (isRented) {
            throw new Exceptions.HousingIsRentedException("Housing is rented");
        }

        // Delete the housing from the database
        housingDAO.deleteById(id);
    }

    @Override
    public Housing getHousing(Integer id) throws Exceptions.HousingNotFoundException {
        // Verify if the housing exists in the database
        if (!housingDAO.existsById(id)) {
            throw new Exceptions.HousingNotFoundException("Housing does not exist");
        }

        // Get the housing from the database
        return housingDAO.findById(id).get();
    }

    @Override
    public List<Housing> getAllHousings() throws Exceptions.HousingNotFoundException {
        if (housingDAO.count() == 0) {
            throw new Exceptions.HousingNotFoundException("No housings found");
        }
        return housingDAO.findAll();
    }
}
