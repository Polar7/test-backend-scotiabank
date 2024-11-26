package co.com.scotiabank.backendtest.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for a position of {@link GetEmployeeDetailsDtoResponse}
 */
@Getter
@Setter
public class GetPositionEmployeeDetailsDtoResponse {

    private String positionTitle;

    private String email;

    private Double salary;

    private LocalDate positionDate;

    private String timePosition;
}
