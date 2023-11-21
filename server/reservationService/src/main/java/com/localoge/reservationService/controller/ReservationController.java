package com.localoge.reservationService.controller;

import com.localoge.reservationService.exceptions.Exceptions;
import com.localoge.reservationService.model.Reservation;
import com.localoge.reservationService.service.IReservationService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @ApiOperation(value = "Create a reservation", response = Reservation.class)
    @ApiParam(name = "reservation", value = "The reservation to create", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully created the reservation"),
            @ApiResponse(code = 404, message = "Rental not found"),
            @ApiResponse(code = 409, message = "The reservation already exists"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/create")
    public Reservation createReservation(@RequestBody Reservation reservation) throws Exception {
        return reservationService.createReservation(reservation);
    }

    @ApiOperation(value = "Delete a reservation", response = Reservation.class)
    @ApiParam(name = "id", value = "Reservation id", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully deleted the reservation"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/delete")
    public void deleteReservation(@RequestParam Integer id) throws Exceptions.ReservationNotFoundException {
        reservationService.deleteReservation(id);
    }

    @ApiOperation(value = "Get a reservation", response = Reservation.class)
    @ApiParam(name = "id", value = "Reservation id", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get the reservation"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/get")
    public Reservation getReservation(@RequestParam Integer id) throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservation(id);
    }

    @ApiOperation(value = "Get reservation by rentalId", response = Reservation.class)
    @ApiParam(name = "rentalId", value = "Rental id", required = true)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get the reservation by the rental id"),
            @ApiResponse(code = 404, message = "Reservation not found"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/rental")
    public Reservation getReservationByRentalId(@RequestParam Integer rentalId) throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservationByRentalId(rentalId);
    }

    @ApiOperation(value = "Get all reservations", response = Reservation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successfully get all reservations"),
            @ApiResponse(code = 500, message = "Internal server error"),
            @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/getall")
    public List<Reservation> getReservations() throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservations();
    }


}
