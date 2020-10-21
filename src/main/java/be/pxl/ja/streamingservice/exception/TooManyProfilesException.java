package be.pxl.ja.streamingservice.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class TooManyProfilesException extends RuntimeException {
    public TooManyProfilesException(String message) {
        super(message);
    }
}