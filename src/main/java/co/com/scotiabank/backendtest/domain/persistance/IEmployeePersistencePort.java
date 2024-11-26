package co.com.scotiabank.backendtest.domain.persistance;

import co.com.scotiabank.backendtest.domain.model.Employee;

import java.util.List;
import java.util.Optional;

/**
 * Port for Employee Persistance
 */
public interface IEmployeePersistencePort {

    /**
     * Get all the employees from database
     * @return Employee list found
     */
    List<Employee> getAllEmployees();

    /**
     * Find an employee by ID
     * @param id Employee id to search for
     * @return Optional with Employee found
     */
    Optional<Employee> getEmployeeById(Long id);

    /**
     * Save a new employee
     * @param employee New employee to save
     * @return Employee saved
     */
    Employee saveEmployee(Employee employee);
}
