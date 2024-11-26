package co.com.scotiabank.backendtest.domain.persistance;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;

import java.util.List;
import java.util.Optional;

/**
 * Port for PositionEmployee Persistance
 */
public interface IPositionEmployeePersistencePort {

    /**
     * Get all the positions of the employees
     * @return Positions list found
     */
    List<PositionEmployee> getAllPositionEmployee();

    /**
     * Get the actual position employee (with status Active)
     * @param employeeId Employee id to search for
     * @return Optional with position found
     */
    Optional<PositionEmployee> getPositionEmployeeActualByIdEmployee(Long employeeId);

    /**
     * Save a new position for an employee
     * @param positionEmployee New position to save
     * @return Position saved
     */
    PositionEmployee savePositionEmployee(PositionEmployee positionEmployee);
}
