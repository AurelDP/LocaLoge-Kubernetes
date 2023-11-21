package com.localoge.rentalService.exceptions;

public class Exceptions {

    // Exceptions classes
    public static class RentalNotFoundException extends Exception {
        public RentalNotFoundException(String message) {
            super(message);
        }
    }

    public static class RentalIsReservedException extends Exception {
        public RentalIsReservedException(String message) {
            super(message);
        }
    }

    public static class RentalAlreadyExistsException extends Exception {
        public RentalAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class PropertyNotFoundException extends Exception {
        public PropertyNotFoundException(String message) {
            super(message);
        }
    }
}
