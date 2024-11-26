package co.com.scotiabank.backendtest.domain.persistance;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;

import java.util.List;
import java.util.Optional;

/**
 * Port for PositionEmployee Persistance
 */
public interface IPositionEmployeePersistencePort {

    /**
     * Get all the positionEmployee
     * @return Positions list found
     */
    List<PositionEmployee> getAllPositionEmployee();

    Optional<PositionEmployee> getPositionEmployeeByIdEmployee(Long id);

    PositionEmployee savePositionEmployee(PositionEmployee positionEmployee);
}
