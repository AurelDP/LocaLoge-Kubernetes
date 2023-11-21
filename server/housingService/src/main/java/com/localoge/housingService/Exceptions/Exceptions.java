package com.localoge.housingService.Exceptions;

public class Exceptions {

    // Exceptions classes
    public static class HousingNotFoundException extends Exception {
        public HousingNotFoundException(String message) {
            super(message);
        }
    }

    public static class HousingIsRentedException extends Exception {
        public HousingIsRentedException(String message) {
            super(message);
        }
    }
}
