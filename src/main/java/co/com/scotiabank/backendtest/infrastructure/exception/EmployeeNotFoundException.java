package co.com.scotiabank.backendtest.infrastructure.exception;

/**
 * Exception when does not exist an Employee
 */
public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException() {
        super("No Employee was found with that ID");
    }
}
