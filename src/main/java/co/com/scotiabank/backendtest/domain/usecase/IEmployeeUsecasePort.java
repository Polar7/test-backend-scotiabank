package co.com.scotiabank.backendtest.domain.usecase;

import co.com.scotiabank.backendtest.domain.model.Employee;

import java.util.List;

/**
 * Port for Employee UseCase
 */
public interface IEmployeeUsecasePort {

    /**
     * Get all the employees
     * @return Employee list found
     */
    List<Employee> getAllEmployees();
}
