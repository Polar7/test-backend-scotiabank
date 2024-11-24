package co.com.scotiabank.backendtest.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Generic dto for controller responses
 */
@Getter
@Setter
@Builder
public class GenericDtoResponse {

    /**
     * Status code
     */
    private int code;

    /**
     * Response message
     */
    private String message;
}
