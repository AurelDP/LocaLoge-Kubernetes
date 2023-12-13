package com.localoge.reservationService.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.localoge.reservationService.exceptions.Exceptions;
import com.localoge.reservationService.model.Reservation;
import com.localoge.reservationService.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
@Service
public class ReservationService implements IReservationService{

    @Autowired
    private ReservationRepository reservationRepository;

    @Value("${rental.service.url}")
    private String rentalServiceUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Reservation createReservation(Reservation reservation) throws Exception {

        if(reservation.getName() == null || reservation.getEmail() == null || reservation.getPhone() == null)
            throw new IllegalArgumentException("All fields must be filled");

        String url = rentalServiceUrl + "/rentals/" + reservation.getRentalId();
        boolean isRental;
        try {
            ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(url, JsonNode.class);
            isRental = responseEntity.getStatusCode().is2xxSuccessful();
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                isRental = false;
            } else {
                throw new Exception("Error while checking if the rental exists");
            }
        } catch (Exception e) {
            throw new Exception("Error while checking if the rental exists");
        }
        if(!isRental)
            throw new Exceptions.RentalNotFoundException("The rental does not exist");

        if (reservationRepository.existsByRentalId(reservation.getRentalId()))
            throw new Exceptions.ReservationAlreadyExistsException("The reservation already exists");

        return reservationRepository.save(reservation);
    }

    @Override
    public void deleteReservation(Integer id) throws Exceptions.ReservationNotFoundException {

        if(!reservationRepository.existsById(id))
            throw new Exceptions.ReservationNotFoundException("The reservation does not exist");

        reservationRepository.deleteById(id);
    }

    @Override
    public Reservation getReservation(Integer id) throws Exceptions.ReservationNotFoundException {
        if(!reservationRepository.existsById(id))
            throw new Exceptions.ReservationNotFoundException("The reservation does not exist");
        return reservationRepository.findById(id).get();
    }

    @Override
    public Reservation getReservationByRentalId(Integer rentalId) throws Exceptions.ReservationNotFoundException {
        if(!reservationRepository.existsByRentalId(rentalId))
            throw new Exceptions.ReservationNotFoundException("The reservation does not exist");
        return reservationRepository.findByRentalId(rentalId);
    }

    @Override
    public List<Reservation> getReservations() throws Exceptions.ReservationNotFoundException {
        if (reservationRepository.count() == 0) {
            throw new Exceptions.ReservationNotFoundException("The reservation does not exist");
        }
        return reservationRepository.findAll();
    }
}
