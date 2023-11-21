package com.localoge.reservationService.exceptions;

public class Exceptions {

    public static class ReservationNotFoundException extends Exception {
        public ReservationNotFoundException(String message) {
            super(message);
        }
    }

    public static class ReservationAlreadyExistsException extends Exception {
        public ReservationAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class RentalNotFoundException extends Exception {
        public RentalNotFoundException(String message) {
            super(message);
        }
    }
}
