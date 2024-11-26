package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.adapter;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.domain.persistance.IPositionEmployeePersistencePort;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper.IPositionEmployeeEntityMapper;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository.IPositionEmployeeCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Adapter for PositionEmployee Persistance
 */
@Repository
@RequiredArgsConstructor
public class PositionEmployeeJpaAdapter implements IPositionEmployeePersistencePort {

    private final IPositionEmployeeCrudRepository positionEmployeeCrudRepository;

    private final IPositionEmployeeEntityMapper positionEmployeeEntityMapper;

    /**
     * Get all the positions of the employees
     * @return Positions list found
     */
    @Override
    public List<PositionEmployee> getAllPositionEmployee() {
        return positionEmployeeEntityMapper.toPositionList(positionEmployeeCrudRepository.findAll());
    }

    /**
     * Get the actual position employee (with status Active)
     * @param employeeId Employee id to search for
     * @return Optional with position found
     */
    @Override
    public Optional<PositionEmployee> getPositionEmployeeActualByIdEmployee(Long employeeId) {
        return positionEmployeeCrudRepository
                .findByEmployeeIdAndStatus(employeeId, "Active")
                .map(positionEmployeeEntityMapper::toPosition);
    }

    /**
     * Save a new position for an employee
     * @param positionEmployee New position to save
     * @return Position saved
     */
    @Override
    public PositionEmployee savePositionEmployee(PositionEmployee positionEmployee) {
        return positionEmployeeEntityMapper.toPosition(
                positionEmployeeCrudRepository.save(positionEmployeeEntityMapper.toEntity(positionEmployee))
        );
    }
}
