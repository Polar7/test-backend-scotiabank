package co.com.scotiabank.backendtest.application.mapper;

import co.com.scotiabank.backendtest.application.dto.EmployeeDtoResponse;
import co.com.scotiabank.backendtest.application.dto.SaveEmployeeDtoRequest;
import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * Mapper for EmployeeDtos
 */
@Mapper(componentModel = "spring")
public interface IEmployeeDtoMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "status", constant = "Active")
    Employee toEmployeeFromSaveDtoRequest(SaveEmployeeDtoRequest saveEmployeeDto);

    default List<EmployeeDtoResponse> toEmployeeDtoList(List<Employee> employees, List<PositionEmployee> positionsEmployee) {
        return employees.stream().map(employee -> {
            EmployeeDtoResponse employeeDtoResponse = new EmployeeDtoResponse();
            employeeDtoResponse.setEmployeeId(employee.getId());
            employeeDtoResponse.setFirstName(employee.getFirstName());
            employeeDtoResponse.setLastName(employee.getLastName());

            employeeDtoResponse.setPositionTitle(
                    positionsEmployee.stream()
                            .filter(position -> position.getEmployeeId().equals(employee.getId()) && position.getStatus().equals("Active"))
                            .findFirst().orElse(new PositionEmployee())
                            .getPositionTitle());

            employeeDtoResponse.setArrivalDate(employee.getHireDate());
            employeeDtoResponse.setStatus(employee.getStatus());
            return employeeDtoResponse;
        }).toList();
    }
}
