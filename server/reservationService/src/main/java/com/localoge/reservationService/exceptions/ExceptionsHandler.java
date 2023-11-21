package com.localoge.reservationService.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionsHandler {

    @ExceptionHandler(value = {Exceptions.RentalNotFoundException.class, Exceptions.ReservationNotFoundException.class})
    public ResponseEntity<String> handleNotFoundRequest(Exception e){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(value = {Exceptions.ReservationAlreadyExistsException.class})
    public ResponseEntity<String> handleConflict(Exception e){
        return ResponseEntity.status(409).body(e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<String> handleBadRequest(Exception e){
        return ResponseEntity.internalServerError().body(e.getMessage());
    }

}
