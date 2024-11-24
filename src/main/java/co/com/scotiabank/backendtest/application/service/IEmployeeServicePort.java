package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;

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

    /**
     * Save a new employee
     * @param newEmployee New employee to save
     * @return Dto with confirmation of operation
     */
    GenericDtoResponse saveNewEmployee(SaveEmployeeDtoRequest newEmployee);
}
