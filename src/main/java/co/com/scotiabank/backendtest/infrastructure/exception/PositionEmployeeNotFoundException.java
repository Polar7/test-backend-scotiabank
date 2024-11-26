package co.com.scotiabank.backendtest.infrastructure.exception;

/**
 * Exception when does not exist an PositionEmployee
 */
public class PositionEmployeeNotFoundException extends RuntimeException {

    public PositionEmployeeNotFoundException() {
        super("No PositionEmployee was found with that ID employee");
    }
}
