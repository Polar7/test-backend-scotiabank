package co.com.scotiabank.backendtest.infrastructure.exception;

public class PositionDateBeforeHireDateException extends RuntimeException {

    public PositionDateBeforeHireDateException() {
        super("Position date is before hire date");
    }
}
