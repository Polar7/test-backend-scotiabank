package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository;

import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Implementation JpaRepository for Employee
 */
public interface IEmployeeCrudRepository extends JpaRepository<EmployeeEntity, Long> {
}
