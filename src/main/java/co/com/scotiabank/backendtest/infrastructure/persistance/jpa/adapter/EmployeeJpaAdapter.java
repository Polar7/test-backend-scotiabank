package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.adapter;

import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.persistance.IEmployeePersistencePort;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper.IEmployeeEntityMapper;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository.IEmployeeCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Adapter for Employee Persistance
 */
@Repository
@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IEmployeeCrudRepository employeeCrudRepository;

    private final IEmployeeEntityMapper employeeEntityMapper;

    /**
     * Get all the employees from database
     * @return Employee list found
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeeEntityMapper.toEmployeeList(employeeCrudRepository.findAll());
    }
}
