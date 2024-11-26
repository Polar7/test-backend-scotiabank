package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.GetEmployeeDetailsDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdateEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdatePositionEmployeeDetailsDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IEmployeeDtoMapper;
import co.com.scotiabank.backendtest.application.mapper.IPositionEmployeeDtoMapper;
import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.usecase.IEmployeeUsecasePort;
import co.com.scotiabank.backendtest.domain.usecase.IPositionEmployeeUsecasePort;
import co.com.scotiabank.backendtest.infrastructure.exception.EmployeeNotFoundException;
import co.com.scotiabank.backendtest.infrastructure.exception.PositionEmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class EmployeeServiceAdapterTest {

    @InjectMocks
    private EmployeeServiceAdapter employeeServiceAdapter;

    @Mock
    private IEmployeeDtoMapper employeeDtoMapper;

    @Mock
    private IPositionEmployeeDtoMapper positionEmployeeDtoMapper;

    @Mock
    private IEmployeeUsecasePort employeeUsecase;

    @Mock
    private IPositionEmployeeUsecasePort positionEmployeeUsecase;

    @Test
    void getAllEmployees_success() {
        Employee employee1 = new Employee();
        employee1.setId(1L);
        employee1.setFirstName("Jean");

        Employee employee2 = new Employee();
        employee2.setId(2L);
        employee2.setFirstName("Michael");

        PositionEmployee positionEmployee1 = new PositionEmployee();
        positionEmployee1.setEmployeeId(1L);
        positionEmployee1.setPositionTitle("Developer");

        PositionEmployee positionEmployee2 = new PositionEmployee();
        positionEmployee2.setEmployeeId(2L);
        positionEmployee2.setPositionTitle("QA");

        EmployeeDtoResponse dto1 = new EmployeeDtoResponse();
        dto1.setFirstName("Jean");
        dto1.setPositionTitle("Developer");

        EmployeeDtoResponse dto2 = new EmployeeDtoResponse();
        dto2.setFirstName("Michael");
        dto2.setPositionTitle("QA");

        when(employeeUsecase.getAllEmployees()).thenReturn(List.of(employee1, employee2));
        when(positionEmployeeUsecase.getAllPositionEmployee()).thenReturn(List.of(positionEmployee1, positionEmployee2));
        when(employeeDtoMapper.toEmployeeDtoList(anyList(), anyList())).thenReturn(List.of(dto1, dto2));

        List<EmployeeDtoResponse> result = employeeServiceAdapter.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Jean", result.get(0).getFirstName());
        assertEquals("Developer", result.get(0).getPositionTitle());
        assertEquals("Michael", result.get(1).getFirstName());
        assertEquals("QA", result.get(1).getPositionTitle());

        verify(employeeUsecase).getAllEmployees();
        verify(positionEmployeeUsecase).getAllPositionEmployee();
        verify(employeeDtoMapper).toEmployeeDtoList(anyList(), anyList());
    }

    @Test
    void getEmployeeDetails_success() {
        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        employee.setFirstName("Jean");

        PositionEmployee positionEmployee = new PositionEmployee();
        positionEmployee.setEmployeeId(employeeId);
        positionEmployee.setPositionTitle("Backend Engineer");
        positionEmployee.setEmail("jean.lozano@scotiabank.com");
        positionEmployee.setPositionDate(LocalDate.now().minusMonths(12));

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(employee));
        when(positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeId))
                .thenReturn(Optional.of(positionEmployee));

        GetEmployeeDetailsDtoResponse result = employeeServiceAdapter.getEmployeeDetails(employeeId);

        assertNotNull(result);
        assertEquals(employee.getFirstName(), result.getEmployee().getFirstName());
        assertEquals(positionEmployee.getPositionTitle(), result.getPositionEmployee().getPositionTitle());
        assertEquals("The employee has been in this position for 12 months to date.",
                result.getPositionEmployee().getTimePosition());

        verify(employeeUsecase).getEmployeeById(employeeId);
        verify(positionEmployeeUsecase).getPositionEmployeeActualByIdEmployee(employeeId);
    }

    @Test
    void getEmployeeDetails_employeeNotFound() {
        Long employeeId = 1L;
        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceAdapter.getEmployeeDetails(employeeId));
        verify(employeeUsecase).getEmployeeById(employeeId);
        verifyNoInteractions(positionEmployeeUsecase);
    }

    @Test
    void saveNewEmployee_success() {
        SaveEmployeeDtoRequest newEmployee = new SaveEmployeeDtoRequest();
        Employee employee = new Employee();
        when(employeeDtoMapper.toEmployeeFromSaveDtoRequest(newEmployee)).thenReturn(employee);

        GenericDtoResponse result = employeeServiceAdapter.saveNewEmployee(newEmployee);

        assertNotNull(result);
        assertEquals(0, result.getCode());
        assertEquals("Employee saved succesfully", result.getMessage());
        verify(employeeUsecase).saveEmployee(employee);
    }

    @Test
    void updateEmployee_success() {
        Long employeeId = 1L;
        UpdateEmployeeDtoRequest updateEmployeeDtoRequest = new UpdateEmployeeDtoRequest();
        updateEmployeeDtoRequest.setEmployeeId(employeeId);
        SaveEmployeeDtoRequest employeeDtoRequest = new SaveEmployeeDtoRequest();
        employeeDtoRequest.setFirstName("Updated Name");
        updateEmployeeDtoRequest.setEmployee(employeeDtoRequest);
        UpdatePositionEmployeeDetailsDtoRequest positionDtoRequest = new UpdatePositionEmployeeDetailsDtoRequest();
        positionDtoRequest.setPositionTitle("Updated Position");
        updateEmployeeDtoRequest.setPosition(positionDtoRequest);

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setFirstName("Original Name");
        PositionEmployee existingPositionEmployee = new PositionEmployee();
        existingPositionEmployee.setId(2L);
        existingPositionEmployee.setEmployeeId(employeeId);
        existingPositionEmployee.setPositionTitle("Original Position");

        Employee updatedEmployee = new Employee();
        updatedEmployee.setId(employeeId);
        updatedEmployee.setFirstName("Updated Name");
        PositionEmployee updatedPositionEmployee = new PositionEmployee();
        updatedPositionEmployee.setId(2L);
        updatedPositionEmployee.setEmployeeId(employeeId);
        updatedPositionEmployee.setPositionTitle("Updated Position");

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeId)).thenReturn(Optional.of(existingPositionEmployee));
        when(employeeDtoMapper.toEmployeeFromUpdateDtoRequest(employeeDtoRequest)).thenReturn(updatedEmployee);
        when(positionEmployeeDtoMapper.toPositionEmployeeFromUpdateDtoRequest(positionDtoRequest)).thenReturn(updatedPositionEmployee);

        GenericDtoResponse response = employeeServiceAdapter.updateEmployee(updateEmployeeDtoRequest);

        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals("Employee updated succesfully", response.getMessage());

        verify(employeeUsecase).getEmployeeById(employeeId);
        verify(positionEmployeeUsecase).getPositionEmployeeActualByIdEmployee(employeeId);
        verify(employeeDtoMapper).toEmployeeFromUpdateDtoRequest(employeeDtoRequest);
        verify(positionEmployeeDtoMapper).toPositionEmployeeFromUpdateDtoRequest(positionDtoRequest);
        verify(employeeUsecase).saveEmployee(updatedEmployee);
        verify(positionEmployeeUsecase).savePositionEmployee(updatedPositionEmployee);
    }

    @Test
    void updateEmployee_employeeNotFound() {
        Long employeeId = 1L;
        UpdateEmployeeDtoRequest updateEmployeeDtoRequest = new UpdateEmployeeDtoRequest();
        updateEmployeeDtoRequest.setEmployeeId(employeeId);

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> employeeServiceAdapter.updateEmployee(updateEmployeeDtoRequest));

        verify(employeeUsecase).getEmployeeById(employeeId);
        verifyNoInteractions(positionEmployeeUsecase, employeeDtoMapper, positionEmployeeDtoMapper);
    }

    @Test
    void updateEmployee_positionNotFound() {
        Long employeeId = 1L;
        UpdateEmployeeDtoRequest updateEmployeeDtoRequest = new UpdateEmployeeDtoRequest();
        updateEmployeeDtoRequest.setEmployeeId(employeeId);

        Employee existingEmployee = new Employee();
        existingEmployee.setId(employeeId);
        existingEmployee.setFirstName("Original Name");

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(existingEmployee));
        when(positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeId)).thenReturn(Optional.empty());

        assertThrows(PositionEmployeeNotFoundException.class, () -> employeeServiceAdapter.updateEmployee(updateEmployeeDtoRequest));

        verify(employeeUsecase).getEmployeeById(employeeId);
        verify(positionEmployeeUsecase).getPositionEmployeeActualByIdEmployee(employeeId);
        verifyNoInteractions(employeeDtoMapper, positionEmployeeDtoMapper);
    }

}