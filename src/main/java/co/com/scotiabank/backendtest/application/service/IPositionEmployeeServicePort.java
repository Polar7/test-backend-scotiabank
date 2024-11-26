package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;

import java.util.List;

/**
 * Port for PositionEmployee service
 */
public interface IPositionEmployeeServicePort {

    /**
     * Get all the positions of the employees
     * @return Positions list found
     */
    List<PositionEmployee> getAllPositionEmployee();

    /**
     * Save a new position for an employee
     * @param newPositionEmployee New position to add
     * @return Dto with confirmation of operation
     */
    GenericDtoResponse savePositionEmployee(SavePositionEmployeeDtoRequest newPositionEmployee);
}
