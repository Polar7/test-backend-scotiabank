package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository;

import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.entity.PositionEmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Implementation JpaRepository for PositionEmployee
 */
public interface IPositionEmployeeCrudRepository extends JpaRepository<PositionEmployeeEntity, Long> {

    /**
     * Get the actual position employee (with status Active)
     * @param employeeId Employee id to search for
     * @return Optional with position found
     */
    Optional<PositionEmployeeEntity> findByEmployeeIdAndStatus(Long employeeId, String status);

}
