package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GetEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdateEmployeeDtoRequest;

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
     * Get the details of an employee
     * @param employeeId Employee id to search for
     * @return Details found
     */
    GetEmployeeDetailsDtoResponse getEmployeeDetails(Long employeeId);

    /**
     * Save a new employee
     * @param newEmployee New employee to save
     * @return Dto with confirmation of operation
     */
    GenericDtoResponse saveNewEmployee(SaveEmployeeDtoRequest newEmployee);

    /**
     * Update the details of an employee and his position
     * @param updateEmployee Dto of employee to update
     * @return Dto with confirmation of operation
     */
    GenericDtoResponse updateEmployee(UpdateEmployeeDtoRequest updateEmployee);
}
