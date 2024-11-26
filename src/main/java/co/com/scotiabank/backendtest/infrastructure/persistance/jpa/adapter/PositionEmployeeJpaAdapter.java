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

    @Override
    public List<PositionEmployee> getAllPositionEmployee() {
        return positionEmployeeEntityMapper.toPositionList(positionEmployeeCrudRepository.findAll());
    }

    @Override
    public Optional<PositionEmployee> getPositionEmployeeByIdEmployee(Long id) {
        return positionEmployeeCrudRepository
                .findByEmployeeId(id)
                .map(positionEmployeeEntityMapper::toPosition);
    }

    @Override
    public PositionEmployee savePositionEmployee(PositionEmployee positionEmployee) {
        return positionEmployeeEntityMapper.toPosition(
                positionEmployeeCrudRepository.save(positionEmployeeEntityMapper.toEntity(positionEmployee))
        );
    }
}
