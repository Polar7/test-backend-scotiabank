package co.com.scotiabank.backendtest.application.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto for endpoint to update details of an employee
 */
@Getter
@Setter
public class UpdateEmployeeDtoRequest {

    @NotNull(message = "employeeId cannot be null")
    private Long employeeId;

    @Valid
    private SaveEmployeeDtoRequest employee;

    @Valid
    private UpdatePositionEmployeeDetailsDtoRequest position;
}
