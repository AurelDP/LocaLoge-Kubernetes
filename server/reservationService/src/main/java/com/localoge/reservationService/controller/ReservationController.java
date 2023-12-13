package com.localoge.reservationService.controller;

import com.localoge.reservationService.exceptions.Exceptions;
import com.localoge.reservationService.model.Reservation;
import com.localoge.reservationService.service.IReservationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    @Autowired
    private IReservationService reservationService;

    @Operation(summary = "Create a reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created reservation", content = @Content(schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "409", description = "Reservation already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping
    public Reservation createReservation(@Parameter(description = "The reservation to create", required = true) @RequestBody Reservation reservation) throws Exception {
        return reservationService.createReservation(reservation);
    }

    @Operation(summary = "Delete a reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted reservation", content = @Content(schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/{id}")
    public void deleteReservation(@PathVariable Integer id) throws Exceptions.ReservationNotFoundException {
        reservationService.deleteReservation(id);
    }

    @Operation(summary = "Get a reservation", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved reservation", content = @Content(schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/{id}")
    public Reservation getReservation(@PathVariable Integer id) throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservation(id);
    }

    @Operation(summary = "Get reservation by rentalId", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully get the reservation by the rental id", content = @Content(schema = @Schema(implementation = Reservation.class))),
            @ApiResponse(responseCode = "404", description = "Reservation not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/rental/{rentalId}")
    public Reservation getReservationByRentalId(@PathVariable Integer rentalId) throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservationByRentalId(rentalId);
    }

    @Operation(summary = "Get all reservations", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all reservations", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No reservations found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<Reservation> getReservations() throws Exceptions.ReservationNotFoundException {
        return reservationService.getReservations();
    }
}
