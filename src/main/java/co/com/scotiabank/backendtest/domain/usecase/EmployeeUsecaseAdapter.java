package co.com.scotiabank.backendtest.domain.usecase;

import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.persistance.IEmployeePersistencePort;

import java.util.List;
import java.util.Optional;

/**
 * Adapter for EmployeeUseCase
 */
public class EmployeeUsecaseAdapter implements IEmployeeUsecasePort {

    private final IEmployeePersistencePort employeePersistencePort;

    public EmployeeUsecaseAdapter(IEmployeePersistencePort employeePersistencePort) {
        this.employeePersistencePort = employeePersistencePort;
    }

    /**
     * Get all the employees
     * @return Employee list found
     */
    @Override
    public List<Employee> getAllEmployees() {
        return employeePersistencePort.getAllEmployees();
    }

    /**
     * Find an employee by ID
     * @param id Employee id to search for
     * @return Optional with Employee found
     */
    @Override
    public Optional<Employee> getEmployeeById(Long id) {
        return employeePersistencePort.getEmployeeById(id);
    }

    /**
     * Save a new employee
     * @param employee New employee to save
     * @return Employee saved
     */
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeePersistencePort.saveEmployee(employee);
    }
}
