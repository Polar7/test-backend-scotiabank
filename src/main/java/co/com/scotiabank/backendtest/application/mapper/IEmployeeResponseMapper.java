package co.com.scotiabank.backendtest.application.mapper;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for EmployeeDtoResponse
 */
@Mapper(componentModel = "spring")
public interface IEmployeeResponseMapper {

    default List<EmployeeDtoResponse> toEmployeeDtoList(List<Employee> employees, List<PositionEmployee> positionsEmployee) {
        return employees.stream().map(employee -> {
            EmployeeDtoResponse employeeDtoResponse = new EmployeeDtoResponse();
            employeeDtoResponse.setFirstName(employee.getFirstName());
            employeeDtoResponse.setLastName(employee.getLastName());
           // employeeDtoResponse.setPositionTitle(positionsEmployee.stream().filter(position -> position.getEmployeeId().equals(employee.getId())).findFirst().orElse(new PositionEmployee()).getPositionTitle());
            employeeDtoResponse.setArrivalDate(employee.getArrivalDate());
            employeeDtoResponse.setStatus(employeeDtoResponse.getStatus());
            return employeeDtoResponse;
        }).toList();
    }
}
