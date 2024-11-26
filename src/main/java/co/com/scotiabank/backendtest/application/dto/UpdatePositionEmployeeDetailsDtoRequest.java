package co.com.scotiabank.backendtest.application.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for a position of {@link UpdateEmployeeDtoRequest}
 */
@Getter
@Setter
public class UpdatePositionEmployeeDetailsDtoRequest {

    @NotEmpty(message = "PositionTitle cannot be empty")
    private String positionTitle;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Salary cannot be null")
    private Double salary;

    @NotNull(message = "PositionDate cannot be null")
    private LocalDate positionDate;
}
