package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.PositionEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IEmployeeDtoMapper;
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
    public EmployeeDetailsDtoResponse getEmployeeDetails(Long employeeId) {

        Employee employee = employeeUsecase.getEmployeeById(employeeId).orElseThrow(EmployeeNotFoundException::new);

        PositionEmployee positionEmployee = positionEmployeeUsecase.getPositionEmployeeByIdEmployee(employee.getId()).orElseThrow(PositionEmployeeNotFoundException::new);

        PositionEmployeeDetailsDtoResponse positionEmployeeDetailsDtoResponse = new PositionEmployeeDetailsDtoResponse();
        positionEmployeeDetailsDtoResponse.setPositionTitle(positionEmployee.getPositionTitle());
        positionEmployeeDetailsDtoResponse.setEmail(positionEmployee.getEmail());
        positionEmployeeDetailsDtoResponse.setSalary(positionEmployee.getSalary());
        positionEmployeeDetailsDtoResponse.setPositionDate(positionEmployee.getPositionDate());

        Period period = Period.between(LocalDate.now(), positionEmployee.getPositionDate());
        int totalMonths = period.getYears() * 12 + period.getMonths();
        positionEmployeeDetailsDtoResponse.setTimePosition("The employee has been in this position for " + totalMonths + " months.");

        EmployeeDetailsDtoResponse employeeDetailsDtoResponse = new EmployeeDetailsDtoResponse();
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
}
