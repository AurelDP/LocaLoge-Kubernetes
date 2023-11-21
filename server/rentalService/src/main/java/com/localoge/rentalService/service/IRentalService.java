package com.localoge.rentalService.service;

import com.localoge.rentalService.exceptions.Exceptions;
import com.localoge.rentalService.model.Rental;

import java.util.List;

public interface IRentalService {
    Rental createRental(Rental rental) throws Exception;
    void deleteRental(Integer id) throws Exception;
    Rental getRental(Integer id) throws Exceptions.RentalNotFoundException;
    List<Rental> getAllRentals() throws Exceptions.RentalNotFoundException;
    Rental getRentalByPropertyId(Integer id) throws Exceptions.RentalNotFoundException;
}
