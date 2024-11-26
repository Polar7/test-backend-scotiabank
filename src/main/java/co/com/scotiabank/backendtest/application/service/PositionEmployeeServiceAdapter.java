package co.com.scotiabank.backendtest.application.service;

import co.com.scotiabank.backendtest.application.dto.GenericDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.mapper.IPositionEmployeeDtoMapper;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.usecase.IPositionEmployeeUsecasePort;
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

    private final IPositionEmployeeDtoMapper employeeDtoMapper;

    @Override
    public List<PositionEmployee> getAllPositionEmployee() {
        return positionEmployeeUsecase.getAllPositionEmployee();
    }

    @Override
    public GenericDtoResponse savePositionEmployee(SavePositionEmployeeDtoRequest employeeDto) {
        positionEmployeeUsecase.savePositionEmployee(employeeDtoMapper.toPositionEmployeeFromSaveDtoRequest(employeeDto));
        return GenericDtoResponse.builder().code(0).message("PositionEmployee saved succesfully").build();
    }

}
