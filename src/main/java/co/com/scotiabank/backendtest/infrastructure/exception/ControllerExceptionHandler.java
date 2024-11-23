package co.com.scotiabank.backendtest.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller for handling exceptions globally
 */
@RestControllerAdvice
public class ControllerExceptionHandler {

    /**
     * Catches exceptions based on business logic
     * @param e Exception thrown
     * @return ProblemDetail with CONFLICT status code and its message
     */
    @ExceptionHandler({})
    public ProblemDetail functionalException(RuntimeException e) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT, e.getMessage());
    }

    /**
     * Catches exceptions thrown by Validation when the JSON received in the controller does not meet the correct conditions
     * @param e Exception thrown
     * @return ProblemDetail with the list of the fields where there is error
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail methodArgumentNotValidException(MethodArgumentNotValidException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Error with fields in the JSON sent.");
        Map<String, Object> error = new HashMap<>();

        Map<String, String> errorsValidation = new HashMap<>();
        e.getFieldErrors().forEach(x -> errorsValidation.put(x.getField(), x.getDefaultMessage()));

        error.put("validations", errorsValidation);
        problemDetail.setProperties(error);

        return problemDetail;
    }
}
