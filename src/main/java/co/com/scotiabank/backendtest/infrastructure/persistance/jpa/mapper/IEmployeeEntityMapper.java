package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper;

import co.com.scotiabank.backendtest.domain.model.Employee;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.entity.EmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for Employee to EmployeeEntity
 */
@Mapper(componentModel = "spring")
public interface IEmployeeEntityMapper {

    Employee toEmployee(EmployeeEntity employeeEntity);

    EmployeeEntity toEntity(Employee employee);

    List<Employee> toEmployeeList(List<EmployeeEntity> employeeEntityList);

}
