package com.localoge.rentalService.controller;

import com.localoge.rentalService.exceptions.Exceptions;
import com.localoge.rentalService.model.Rental;
import com.localoge.rentalService.service.IRentalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // This annotation tells Spring that this class is a controller
@RequestMapping("/rentals")
public class RentalController {

    @Autowired
    private IRentalService rentalService;

    // Exceptions are handled by the ExceptionsHandler class
    // When an exception is thrown, the ExceptionsHandler class will handle it and return a response

    @Operation(summary = "Create a rental", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully created rental", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "404", description = "Property not found"),
            @ApiResponse(responseCode = "409", description = "Property already exists"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @PostMapping
    public Rental createRental(@Parameter(description = "Rental object", required = true) @RequestBody Rental rental) throws Exception {
        return rentalService.createRental(rental);
    }

    @Operation(summary = "Delete a rental", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted rental", content = @Content(schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "409", description = "Rental is reserved"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @DeleteMapping("/{id}")
    public void deleteRental(@PathVariable Integer id) throws Exception {
        rentalService.deleteRental(id);
    }

    @Operation(summary = "Get a rental", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved rental", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @GetMapping("/{id}")
    public Rental getRental(@PathVariable Integer id) throws Exceptions.RentalNotFoundException {
        return rentalService.getRental(id);
    }

    @Operation(summary = "Get all rentals", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved all rentals", content = @Content(schema = @Schema(implementation = List.class))),
            @ApiResponse(responseCode = "404", description = "No rentals found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @GetMapping
    public List<Rental> getAllRentals() throws Exceptions.RentalNotFoundException {
        return rentalService.getAllRentals();
    }

    @Operation(summary = "Get rental by property id", responses = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved rental", content = @Content(schema = @Schema(implementation = Rental.class))),
            @ApiResponse(responseCode = "404", description = "Rental not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @GetMapping("/housing/{propertyId}")
    public Rental getRentalByPropertyId(@PathVariable Integer propertyId) throws Exceptions.RentalNotFoundException {
        return rentalService.getRentalByPropertyId(propertyId);
    }
}
