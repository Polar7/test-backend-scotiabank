package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IEmployeeDtoMapper;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
import co.com.scotiabank.backendtest.domain.usecase.IPositionEmployeeUsecasePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Adapter for Employee service
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceAdapter implements IEmployeeServicePort {

    private final IEmployeeDtoMapper employeeDtoMapper;

    private final IEmployeeUsecasePort employeeUsecase;

    private final IPositionEmployeeUsecasePort positionEmployeeUsecase;

    /**
     * Get all the employees
     * @return Employee list found
     */
    @Override
    public List<EmployeeDtoResponse> getAllEmployees() {
        return employeeDtoMapper.toEmployeeDtoList(employeeUsecase.getAllEmployees(), positionEmployeeUsecase.getAllPositionEmployee());
    }

    /**
     * Save a new employee
     * @param newEmployee New employee to save
     * @return Dto with confirmation of operation
     */
    @Override
    public GenericDtoResponse saveNewEmployee(SaveEmployeeDtoRequest newEmployee) {
        employeeUsecase.saveEmployee(employeeDtoMapper.toEmployeeFromSaveDtoRequest(newEmployee));
        return GenericDtoResponse.builder().code(0).message("Employee saved succesfully").build();
    }
}
