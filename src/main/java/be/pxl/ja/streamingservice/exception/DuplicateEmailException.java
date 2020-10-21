package be.pxl.ja.streamingservice.exception;

public class DuplicateEmailException extends RuntimeException {
    public DuplicateEmailException() {
        super("Email is al reeds toegevoegd");
    }
}
