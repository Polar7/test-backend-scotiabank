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
     * Get all the positionEmployee
     * @return Positions list found
     */
    List<PositionEmployee> getAllPositionEmployee();

    GenericDtoResponse savePositionEmployee(SavePositionEmployeeDtoRequest employee);
}
