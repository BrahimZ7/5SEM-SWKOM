package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface HopArrivalMapper {
    HopArrivalMapper INSTANCE = Mappers.getMapper(HopArrivalMapper.class);

    HopArrival entityToDto(HopArrivalEntity hopArrival);
    List<HopArrival> entityToDto(List<HopArrivalEntity> hopArrival);

    List<HopArrivalEntity> dtoToEntity(List<HopArrival> hopArrival);
    HopArrivalEntity dtoToEntity(HopArrival hopArrival);
}
