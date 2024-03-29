package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopEntity;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.mapper.impl.HopMapperImplementation;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
@DecoratedWith(HopMapperImplementation.class)
public interface HopMapper {
    HopMapper INSTANCE = Mappers.getMapper(HopMapper.class);

    Hop entityToDto(HopEntity hopEntity);

    HopEntity dtoToEntity(Hop hop);
}
