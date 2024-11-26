package co.com.scotiabank.backendtest.infrastructure.exception;

public class InvalidPhoneNumberException extends RuntimeException {

    public InvalidPhoneNumberException() {
        super("The telephone number must not have decimals.");
    }
}
