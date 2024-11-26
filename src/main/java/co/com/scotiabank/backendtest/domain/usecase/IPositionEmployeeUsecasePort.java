package co.com.scotiabank.backendtest.domain.usecase;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;

import java.util.List;
import java.util.Optional;

/**
 * Port for PositionEmployee UseCase
 */
public interface IPositionEmployeeUsecasePort {

    /**
     * Get all the positionEmployee
     * @return Positions list found
     */
    List<PositionEmployee> getAllPositionEmployee();

    Optional<PositionEmployee> getPositionEmployeeByIdEmployee(Long id);

    PositionEmployee savePositionEmployee(PositionEmployee positionEmployee);

}
