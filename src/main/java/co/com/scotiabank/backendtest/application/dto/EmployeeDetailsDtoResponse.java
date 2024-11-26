package co.com.scotiabank.backendtest.application.dto;

import co.com.scotiabank.backendtest.domain.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto for endpoint to get details of an employee
 */
@Getter
@Setter
public class EmployeeDetailsDtoResponse {

    private Employee employee;

    private PositionEmployeeDetailsDtoResponse positionEmployee;
}
