package com.localoge.rentalService.controller;

import com.localoge.rentalService.exceptions.Exceptions;
import com.localoge.rentalService.model.Rental;
import com.localoge.rentalService.service.IRentalService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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

    @ApiOperation(value = "Create a rental", response = Rental.class) // Swagger annotation to document this method
    @ApiParam(name = "rental", value = "Rental object", required = true) // Swagger annotation to document this method
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully created rental"),
        @ApiResponse(code = 404, message = "Property not found"),
        @ApiResponse(code = 409, message = "Property already exists"),
        @ApiResponse(code = 500, message = "Internal server error")
    }) // Swagger annotation to document the possible responses
    @PostMapping("/create")
    public Rental createRental(@RequestBody Rental rental) throws Exception {
        return rentalService.createRental(rental);
    }

    @ApiOperation(value = "Delete a rental", response = void.class)
    @ApiParam(name = "id", value = "Rental id", required = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully deleted rental"),
        @ApiResponse(code = 404, message = "Rental not found"),
        @ApiResponse(code = 409, message = "Rental is reserved"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @PostMapping("/delete")
    public void deleteRental(@RequestParam Integer id) throws Exception {
        rentalService.deleteRental(id);
    }

    @ApiOperation(value = "Get a rental", response = Rental.class)
    @ApiParam(name = "id", value = "Rental id", required = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved rental"),
        @ApiResponse(code = 404, message = "Rental not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/get")
    public Rental getRental(@RequestParam Integer id) throws Exceptions.RentalNotFoundException {
        return rentalService.getRental(id);
    }

    @ApiOperation(value = "Get all rentals", response = List.class)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved all rentals"),
        @ApiResponse(code = 404, message = "No rentals found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping
    public List<Rental> getAllRentals() throws Exceptions.RentalNotFoundException {
        return rentalService.getAllRentals();
    }

    @ApiOperation(value = "Get rental by property id", response = Rental.class)
    @ApiParam(name = "propertyId", value = "Property id", required = true)
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved rental"),
        @ApiResponse(code = 404, message = "Rental not found"),
        @ApiResponse(code = 500, message = "Internal server error")
    })
    @GetMapping("/housing")
    public Rental getRentalByPropertyId(@RequestParam Integer propertyId) throws Exceptions.RentalNotFoundException {
        return rentalService.getRentalByPropertyId(propertyId);
    }
}
