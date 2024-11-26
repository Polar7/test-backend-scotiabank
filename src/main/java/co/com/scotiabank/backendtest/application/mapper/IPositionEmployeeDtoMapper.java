package co.com.scotiabank.backendtest.application.mapper;

import co.com.scotiabank.backendtest.application.dto.SavePositionEmployeeDtoRequest;
import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper for PositionEmployeeDtos
 */
@Mapper(componentModel = "spring")
public interface IPositionEmployeeDtoMapper {

    @Mapping(target = "status", constant = "Active")
    PositionEmployee toPositionEmployeeFromSaveDtoRequest(SavePositionEmployeeDtoRequest dtoRequest);
}
