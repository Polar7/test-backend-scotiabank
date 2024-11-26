package co.com.scotiabank.backendtest.infrastructure.persistance.jpa.mapper;

import co.com.scotiabank.backendtest.domain.model.PositionEmployee;
import co.com.scotiabank.backendtest.infrastructure.persistance.jpa.entity.PositionEmployeeEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper for PositionEmployee to PositionEmployeeEntity
 */
@Mapper(componentModel = "spring")
public interface IPositionEmployeeEntityMapper {

    PositionEmployee toPosition(PositionEmployeeEntity entity);

    PositionEmployeeEntity toEntity(PositionEmployee position);

    List<PositionEmployee> toPositionList(List<PositionEmployeeEntity> entities);
}
