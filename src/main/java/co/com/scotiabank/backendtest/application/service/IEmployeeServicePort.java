package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;

import java.util.List;

/**
 * Port for Employee service
 */
public interface IEmployeeServicePort {

    /**
     * Get all the employees
     * @return Employee list found
     */
    List<EmployeeDtoResponse> getAllEmployees();
}
