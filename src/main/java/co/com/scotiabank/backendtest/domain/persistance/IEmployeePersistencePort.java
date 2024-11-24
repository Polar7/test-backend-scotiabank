package co.com.scotiabank.backendtest.domain.persistance;

import co.com.scotiabank.backendtest.domain.model.Employee;

import java.util.List;

/**
 * Port for Employee Persistance
 */
public interface IEmployeePersistencePort {

    /**
     * Get all the employees from database
     * @return Employee list found
     */
    List<Employee> getAllEmployees();
}
