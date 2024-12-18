package co.com.scotiabank.backendtest.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Dto for endpoint to get all employees
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDtoResponse {

    private Long employeeId;

    private String firstName;

    private String lastName;

    private String positionTitle;

    private LocalDate arrivalDate;

    private String status;
}
