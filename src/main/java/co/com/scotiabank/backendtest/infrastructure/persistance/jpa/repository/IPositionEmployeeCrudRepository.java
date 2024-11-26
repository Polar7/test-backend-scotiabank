package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository;

import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.entity.PositionEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Implementation JpaRepository for PositionEmployee
 */
public interface IPositionEmployeeCrudRepository extends JpaRepository<PositionEmployeeEntity, Long> {

    Optional<PositionEmployeeEntity> findByEmployeeId(Long employeeId);

}
