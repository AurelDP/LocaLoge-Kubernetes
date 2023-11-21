package com.localoge.reservationService.service;

import com.localoge.reservationService.exceptions.Exceptions;
import com.localoge.reservationService.model.Reservation;

import java.util.List;

public interface IReservationService {

    Reservation createReservation(Reservation reservation) throws Exception;
    void deleteReservation(Integer id) throws Exceptions.ReservationNotFoundException;
    Reservation getReservation(Integer id) throws Exceptions.ReservationNotFoundException;
    Reservation getReservationByRentalId(Integer rentalId) throws Exceptions.ReservationNotFoundException;
    List<Reservation> getReservations() throws Exceptions.ReservationNotFoundException;

}
