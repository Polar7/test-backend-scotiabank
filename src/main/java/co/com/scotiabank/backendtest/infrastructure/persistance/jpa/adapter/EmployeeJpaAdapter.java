package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.adapter;

import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.persistance.IEmployeePersistencePort;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper.IEmployeeEntityMapper;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.repository.IEmployeeCrudRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    /**
     * Find an employee by ID
     * @param id Employee id to search for
     * @return Optional with Employee found
     */
    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeCrudRepository.findById(id)
                .map(employeeEntityMapper::toEmployee);
    }

    /**
     * Save a new employee
     * @param employee New employee to save
     * @return Employee saved
     */
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeEntityMapper.toEmployee(
                employeeCrudRepository.save(employeeEntityMapper.toEntity(employee))
        );
    }
}
