package co.com.scotiabank.backendtest.infrastructure.exception;

public class EmailFormatInvalidException extends RuntimeException {

    public EmailFormatInvalidException() {
        super("Email format is invalid");
    }
}
