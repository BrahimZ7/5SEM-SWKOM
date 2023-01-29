package at.fhtw.swen3.services.mapper;

import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.entities.WarehouseNextHopsEntity;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.dto.WarehouseNextHops;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {GeoCoordinateMapper.class, HopMapper.class}, builder = @Builder(disableBuilder = true))
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

    Warehouse entityToDto(WarehouseEntity warehouse);

    WarehouseEntity dtoToEntity(Warehouse warehouse, @Context WarehouseMapperContext warehouseMapperContext);

    WarehouseNextHops entityToDto(WarehouseNextHopsEntity warehouseNextHops);

    @Mapping(target = "warehouse", ignore = true)
    WarehouseNextHopsEntity dtoToEntity(WarehouseNextHops warehouseNextHops, @Context WarehouseMapperContext warehouseMapperContext);
}
