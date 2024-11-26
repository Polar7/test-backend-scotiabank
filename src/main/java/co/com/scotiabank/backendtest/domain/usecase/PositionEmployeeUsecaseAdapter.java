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

    @Override
    public Optional<PositionEmployee> getPositionEmployeeByIdEmployee(Long id) {
        return positionEmployeePersistence.getPositionEmployeeByIdEmployee(id);
    }

    @Override
    public PositionEmployee savePositionEmployee(PositionEmployee positionEmployee) {
        return positionEmployeePersistence.savePositionEmployee(positionEmployee);
    }
}
