package co.com.scotiabank.backendtest.domain.usecase;

import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.persistance.IEmployeePersistencePort;

import java.util.List;

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
     * Save a new employee
     * @param employee New employee to save
     * @return Employee saved
     */
    @Override
    public Employee saveEmployee(Employee employee) {
        return employeePersistencePort.saveEmployee(employee);
    }
}
