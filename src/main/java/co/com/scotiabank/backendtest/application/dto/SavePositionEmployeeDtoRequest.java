package co.com.scotiabank.backendtest.application.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for endpoint to save a new PositionEmployee
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SavePositionEmployeeDtoRequest {

    @NotNull(message = "EmployeeId cannot be null")
    private Long employeeId;

    @NotEmpty(message = "PositionTitle cannot be empty")
    private String positionTitle;

    @NotEmpty(message = "Email cannot be empty")
    private String email;

    @NotNull(message = "Salary cannot be null")
    private Double salary;

    @NotNull(message = "PositionDate cannot be null")
    private LocalDate positionDate;
}
