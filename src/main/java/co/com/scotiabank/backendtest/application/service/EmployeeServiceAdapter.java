package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.GetEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GetPositionEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdateEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IEmployeeDtoMapper;
import co.com.scotiabank.backendtest.application.mapper.IPositionEmployeeDtoMapper;
import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
import co.com.scotiabank.backendtest.domain.usecase.IPositionEmployeeUsecasePort;
import co.com.scotiabank.backendtest.infrastructure.exception.EmployeeNotFoundException;
import co.com.scotiabank.backendtest.infrastructure.exception.PositionEmployeeNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

/**
 * Adapter for Employee service
 */
@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeServiceAdapter implements IEmployeeServicePort {

    private final IEmployeeDtoMapper employeeDtoMapper;

    private final IPositionEmployeeDtoMapper positionEmployeeDtoMapper;

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
     * Get the details of an employee
     * @param employeeId Employee id to search for
     * @return Details found
     * @exception EmployeeNotFoundException If an employee does not exist
     * @exception PositionEmployeeNotFoundException If a PositionEmployee does not exist
     */
    @Override
    public GetEmployeeDetailsDtoResponse getEmployeeDetails(Long employeeId) {

        Employee employee = employeeUsecase.getEmployeeById(employeeId).orElseThrow(EmployeeNotFoundException::new);

        PositionEmployee positionEmployee = positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employee.getId()).orElseThrow(PositionEmployeeNotFoundException::new);

        GetPositionEmployeeDetailsDtoResponse positionEmployeeDetailsDtoResponse = new GetPositionEmployeeDetailsDtoResponse();
        positionEmployeeDetailsDtoResponse.setPositionTitle(positionEmployee.getPositionTitle());
        positionEmployeeDetailsDtoResponse.setEmail(positionEmployee.getEmail());
        positionEmployeeDetailsDtoResponse.setSalary(positionEmployee.getSalary());
        positionEmployeeDetailsDtoResponse.setPositionDate(positionEmployee.getPositionDate());

        Period period = Period.between(positionEmployee.getPositionDate(), LocalDate.now());
        int timeInMonthsPosition = period.isNegative() ? period.getMonths() : period.getYears() * 12 + period.getMonths();
        positionEmployeeDetailsDtoResponse.setTimePosition("The employee has been in this position for " + timeInMonthsPosition + " months to date.");

        GetEmployeeDetailsDtoResponse employeeDetailsDtoResponse = new GetEmployeeDetailsDtoResponse();
        employeeDetailsDtoResponse.setEmployee(employee);
        employeeDetailsDtoResponse.setPositionEmployee(positionEmployeeDetailsDtoResponse);
        return employeeDetailsDtoResponse;
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

    /**
     * Update the details of an employee and his position
     * @param updateEmployee Dto of employee to update
     * @return Dto with confirmation of operation
     * @exception EmployeeNotFoundException If an employee does not exist
     * @exception PositionEmployeeNotFoundException If a PositionEmployee does not exist
     */
    @Override
    public GenericDtoResponse updateEmployee(UpdateEmployeeDtoRequest updateEmployee) {

        Employee employeeActual = employeeUsecase.getEmployeeById(updateEmployee.getEmployeeId()).orElseThrow(EmployeeNotFoundException::new);
        PositionEmployee positionEmployeeActual = positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeActual.getId()).orElseThrow(PositionEmployeeNotFoundException::new);

        Employee employeeToUpdate = employeeDtoMapper.toEmployeeFromUpdateDtoRequest(updateEmployee.getEmployee());
        employeeToUpdate.setId(employeeActual.getId());
        employeeToUpdate.setStatus(employeeActual.getStatus());

        PositionEmployee positionEmployeeToUpdate = positionEmployeeDtoMapper.toPositionEmployeeFromUpdateDtoRequest(updateEmployee.getPosition());
        positionEmployeeToUpdate.setId(positionEmployeeActual.getId());
        positionEmployeeToUpdate.setEmployeeId(positionEmployeeActual.getEmployeeId());
        positionEmployeeToUpdate.setStatus(positionEmployeeActual.getStatus());

        employeeUsecase.saveEmployee(employeeToUpdate);
        positionEmployeeUsecase.savePositionEmployee(positionEmployeeToUpdate);

        return GenericDtoResponse.builder().code(0).message("Employee updated succesfully").build();
    }
}
