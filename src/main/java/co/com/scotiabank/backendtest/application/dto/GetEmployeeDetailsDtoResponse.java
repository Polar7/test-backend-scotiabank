package co.com.scotiabank.backendtest.application.dto;

import co.com.scotiabank.backendtest.domain.model.Employee;
import lombok.Getter;
import lombok.Setter;

/**
 * Dto for endpoint to get details of an employee
 */
@Getter
@Setter
public class GetEmployeeDetailsDtoResponse {

    private Employee employee;

    private GetPositionEmployeeDetailsDtoResponse positionEmployee;
}
