package co.com.scotiabank.backendtest.domain.usecase;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.persistance.IPositionEmployeePersistencePort;

import java.util.List;
import java.util.Optional;

/**
 * Adapter for EmployeeUseCase
 */
public class PositionEmployeeUsecaseAdapter implements IPositionEmployeeUsecasePort {

    private final IPositionEmployeePersistencePort positionEmployeePersistence;

    public PositionEmployeeUsecaseAdapter(IPositionEmployeePersistencePort positionEmployeePersistence) {
        this.positionEmployeePersistence = positionEmployeePersistence;
    }

    /**
     * Get all the positionEmployee
     * @return Positions list found
     */
    @Override
    public List<PositionEmployee> getAllPositionEmployee() {
        return positionEmployeePersistence.getAllPositionEmployee();
    }

    /**
     * Get the actual position employee (with status Active)
     * @param employeeId Employee id to search for
     * @return Optional with position found
     */
    @Override
    public Optional<PositionEmployee> getPositionEmployeeActualByIdEmployee(Long employeeId) {
        return positionEmployeePersistence.getPositionEmployeeActualByIdEmployee(employeeId);
    }

    /**
     * Save a new position for an employee
     * @param positionEmployee New position to save
     * @return Position saved
     */
    @Override
    public PositionEmployee savePositionEmployee(PositionEmployee positionEmployee) {
        return positionEmployeePersistence.savePositionEmployee(positionEmployee);
    }
}
