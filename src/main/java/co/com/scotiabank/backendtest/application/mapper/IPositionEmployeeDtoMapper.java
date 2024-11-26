package co.com.scotiabank.backendtest.application.mapper;

import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.application.dto.UpdatePositionEmployeeDetailsDtoRequest;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.infrastructure.util.UtilValidator;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for PositionEmployeeDtos
 */
@Mapper(componentModel = "spring", imports = UtilValidator.class)
public interface IPositionEmployeeDtoMapper {

    @Mapping(target = "status", constant = "Active")
    @Mapping(target = "email", expression = "java(UtilValidator.isValidEmail(dtoRequest.getEmail()))")
    PositionEmployee toPositionEmployeeFromSaveDtoRequest(SavePositionEmployeeDtoRequest dtoRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employeeId", ignore = true)
    @Mapping(target = "status", ignore = true)
    @Mapping(target = "email", expression = "java(UtilValidator.isValidEmail(dtoRequest.getEmail()))")
    PositionEmployee toPositionEmployeeFromUpdateDtoRequest(UpdatePositionEmployeeDetailsDtoRequest dtoRequest);

}
