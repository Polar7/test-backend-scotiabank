package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IPositionEmployeeDtoMapper;
import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
import co.com.scotiabank.backendtest.domain.usecase.IPositionEmployeeUsecasePort;
import co.com.scotiabank.backendtest.infrastructure.exception.EmployeeNotFoundException;
import co.com.scotiabank.backendtest.infrastructure.exception.PositionDateBeforeHireDateException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Adapter for PositionEmployee service
 */
@Service
@Transactional
@RequiredArgsConstructor
public class PositionEmployeeServiceAdapter implements IPositionEmployeeServicePort {

    private final IPositionEmployeeUsecasePort positionEmployeeUsecase;

    private final IPositionEmployeeDtoMapper positionEmployeeDtoMapper;

    private final IEmployeeUsecasePort employeeUsecase;

    /**
     * Get all the positions of the employees
     * @return Positions list found
     */
    @Override
    public List<PositionEmployee> getAllPositionEmployee() {
        return positionEmployeeUsecase.getAllPositionEmployee();
    }

    /**
     * Save a new position for an employee
     * @param newPositionEmployeeDto New position to add
     * @return Dto with confirmation of operation
     * @exception PositionDateBeforeHireDateException If the positionDate is before to hire date of employee
     */
    @Override
    public GenericDtoResponse savePositionEmployee(SavePositionEmployeeDtoRequest newPositionEmployeeDto) {

        Employee employee = employeeUsecase.getEmployeeById(newPositionEmployeeDto.getEmployeeId()).orElseThrow(EmployeeNotFoundException::new);

        if (newPositionEmployeeDto.getPositionDate().isBefore(employee.getHireDate())) {
            throw new PositionDateBeforeHireDateException();
        }

        positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(newPositionEmployeeDto.getEmployeeId())
                .ifPresent(positionEmployee -> {
                    positionEmployee.setStatus("Inactive");
                    positionEmployeeUsecase.savePositionEmployee(positionEmployee);
                });

        positionEmployeeUsecase.savePositionEmployee(positionEmployeeDtoMapper.toPositionEmployeeFromSaveDtoRequest(newPositionEmployeeDto));
        return GenericDtoResponse.builder().code(0).message("PositionEmployee saved succesfully").build();
    }

}
