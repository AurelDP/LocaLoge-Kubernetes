package com.localoge.rentalService.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // This annotation tells Spring that this class is a controller advice linked to the controller
public class ExceptionsHandler {

    // Handle exceptions

    // Raise a 404 error if the property is not found
    @ExceptionHandler(value = {Exceptions.PropertyNotFoundException.class, Exceptions.RentalNotFoundException.class})
    public ResponseEntity<String> handleNotFoundRequest(Exception e) {
        return ResponseEntity.notFound().build();
    }

    // Raise a 409 error if the property already exists
    @ExceptionHandler(value = {Exceptions.RentalAlreadyExistsException.class, Exceptions.RentalIsReservedException.class})
    public ResponseEntity<String> handleConflict(Exception e) {
        return ResponseEntity.status(409).body(e.getMessage());
    }

    // Raise a 500 error if an internal server error occurs
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleInternalServerError(Exception e) {
        return ResponseEntity.internalServerError().body(e.getMessage());
    }
}
