package com.localoge.reservationService.repository;

import com.localoge.reservationService.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    boolean existsByRentalId(Integer rentalId);
    Reservation findByRentalId(Integer rentalId);
}
