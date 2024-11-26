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
class PositionEmployeeServiceAdapterTest {

    @InjectMocks
    private PositionEmployeeServiceAdapter positionEmployeeServiceAdapter;

    @Mock
    private IPositionEmployeeUsecasePort positionEmployeeUsecase;

    @Mock
    private IPositionEmployeeDtoMapper positionEmployeeDtoMapper;

    @Mock
    private IEmployeeUsecasePort employeeUsecase;

    @Test
    void getAllPositionEmployee_success() {
        List<PositionEmployee> mockPositions = List.of(new PositionEmployee(), new PositionEmployee());
        when(positionEmployeeUsecase.getAllPositionEmployee()).thenReturn(mockPositions);

        List<PositionEmployee> result = positionEmployeeServiceAdapter.getAllPositionEmployee();

        assertNotNull(result);
        assertEquals(mockPositions.size(), result.size());
        verify(positionEmployeeUsecase).getAllPositionEmployee();
    }

    @Test
    void savePositionEmployee_success() {
        // Arrange
        Long employeeId = 1L;
        SavePositionEmployeeDtoRequest newPositionEmployeeDto = new SavePositionEmployeeDtoRequest();
        newPositionEmployeeDto.setEmployeeId(employeeId);
        newPositionEmployeeDto.setPositionDate(LocalDate.now());

        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setHireDate(LocalDate.of(2020, 1, 1));

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(mockEmployee));
        when(positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeId)).thenReturn(Optional.empty());

        GenericDtoResponse response = positionEmployeeServiceAdapter.savePositionEmployee(newPositionEmployeeDto);

        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals("PositionEmployee saved succesfully", response.getMessage());

        verify(employeeUsecase).getEmployeeById(employeeId);
        verify(positionEmployeeUsecase).savePositionEmployee(positionEmployeeDtoMapper.toPositionEmployeeFromSaveDtoRequest(newPositionEmployeeDto));
    }

    @Test
    void savePositionEmployee_employeeNotFound() {
        Long employeeId = 1L;
        SavePositionEmployeeDtoRequest newPositionEmployeeDto = new SavePositionEmployeeDtoRequest();
        newPositionEmployeeDto.setEmployeeId(employeeId);

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.empty());

        assertThrows(EmployeeNotFoundException.class, () -> positionEmployeeServiceAdapter.savePositionEmployee(newPositionEmployeeDto));

        verify(employeeUsecase).getEmployeeById(employeeId);
        verifyNoInteractions(positionEmployeeDtoMapper, positionEmployeeUsecase);
    }

    @Test
    void savePositionEmployee_positionDateBeforeHireDate() {
        Long employeeId = 1L;
        SavePositionEmployeeDtoRequest newPositionEmployeeDto = new SavePositionEmployeeDtoRequest();
        newPositionEmployeeDto.setEmployeeId(employeeId);
        newPositionEmployeeDto.setPositionDate(LocalDate.of(2019, 12, 31));

        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setHireDate(LocalDate.of(2020, 1, 1));

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(mockEmployee));

        // Act & Assert
        assertThrows(PositionDateBeforeHireDateException.class, () -> positionEmployeeServiceAdapter.savePositionEmployee(newPositionEmployeeDto));

        verify(employeeUsecase).getEmployeeById(employeeId);
        verifyNoInteractions(positionEmployeeDtoMapper, positionEmployeeUsecase);
    }

    @Test
    void savePositionEmployee_withExistingActivePosition() {
        Long employeeId = 1L;
        SavePositionEmployeeDtoRequest newPositionEmployeeDto = new SavePositionEmployeeDtoRequest();
        newPositionEmployeeDto.setEmployeeId(employeeId);
        newPositionEmployeeDto.setPositionDate(LocalDate.now());

        Employee mockEmployee = new Employee();
        mockEmployee.setId(employeeId);
        mockEmployee.setHireDate(LocalDate.of(2020, 1, 1));

        PositionEmployee existingPosition = new PositionEmployee();
        existingPosition.setEmployeeId(1L);
        existingPosition.setStatus("Active");

        when(employeeUsecase.getEmployeeById(employeeId)).thenReturn(Optional.of(mockEmployee));
        when(positionEmployeeUsecase.getPositionEmployeeActualByIdEmployee(employeeId)).thenReturn(Optional.of(existingPosition));

        GenericDtoResponse response = positionEmployeeServiceAdapter.savePositionEmployee(newPositionEmployeeDto);

        assertNotNull(response);
        assertEquals(0, response.getCode());
        assertEquals("PositionEmployee saved succesfully", response.getMessage());

        verify(employeeUsecase).getEmployeeById(employeeId);
        verify(positionEmployeeUsecase).getPositionEmployeeActualByIdEmployee(employeeId);
        verify(positionEmployeeUsecase, times(1)).savePositionEmployee(existingPosition);
        verify(positionEmployeeUsecase, times(1)).savePositionEmployee(any(PositionEmployee.class));
    }
}