package at.fhtw.swen3.services.mapper.impl;

import at.fhtw.swen3.persistence.entities.*;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Transferwarehouse;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.dto.Warehouse;
import at.fhtw.swen3.services.mapper.*;

public class HopMapperImplementation implements HopMapper {
    private final HopMapper delegate;

    protected HopMapperImplementation(HopMapper delegate) {
        this.delegate = delegate;
    }

    @Override
    public HopEntity dtoToEntity(Hop hop) {
        if (hop instanceof Transferwarehouse) {
            return TransferwarehouseMapper.INSTANCE.dtoToEntity((Transferwarehouse) hop);
        } else if (hop instanceof Truck) {
            return TruckMapper.INSTANCE.dtoToEntity((Truck) hop);
        } else if (hop instanceof Warehouse) {
            return WarehouseMapper.INSTANCE.dtoToEntity((Warehouse) hop, new WarehouseMapperContext());
        }
        return delegate.dtoToEntity(hop);
    }

    @Override
    public Hop entityToDto(HopEntity hop) {
        if (hop instanceof TransferwarehouseEntity) {
            return TransferwarehouseMapper.INSTANCE.entityToDto((TransferwarehouseEntity) hop);
        } else if (hop instanceof TruckEntity) {
            return TruckMapper.INSTANCE.entityToDto((TruckEntity) hop);
        } else if (hop instanceof WarehouseEntity) {
            return WarehouseMapper.INSTANCE.entityToDto((WarehouseEntity) hop);
        }
        return delegate.entityToDto(hop);
    }
}
