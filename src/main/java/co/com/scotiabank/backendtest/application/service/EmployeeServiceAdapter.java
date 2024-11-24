package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IEmployeeResponseMapper;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
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

    private final IEmployeeResponseMapper employeeResponseMapper;

    private final IEmployeeUsecasePort employeeUsecase;

    /**
     * Get all the employees
     * @return Employee list found
     */
    @Override
    public List<EmployeeDtoResponse> getAllEmployees() {
        return employeeResponseMapper.toEmployeeDtoList(employeeUsecase.getAllEmployees(), null);
    }

    /**
     * Save a new employee
     * @param newEmployee New employee to save
     * @return Dto with confirmation of operation
     */
    @Override
    public GenericDtoResponse saveNewEmployee(SaveEmployeeDtoRequest newEmployee) {
        employeeUsecase.saveEmployee(employeeResponseMapper.toEmployeeFromSaveDtoRequest(newEmployee));
        return GenericDtoResponse.builder().code(0).message("Employee saved succesfully").build();
    }
}
