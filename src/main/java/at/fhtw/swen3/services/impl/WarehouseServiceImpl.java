package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.exception.BLNotFoundException;
import at.fhtw.swen3.services.mapper.WarehouseMapperContext;
import at.fhtw.swen3.services.mapper.WarehouseMapper;
import at.fhtw.swen3.services.validation.ValidatorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl implements WarehouseService {
    final WarehouseRepository warehouseRepository;
    final ValidatorService validatorService;

    @Override
    public void importWarehouses(Warehouse warehouse) {
        var warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse, new WarehouseMapperContext());

        validatorService.validate(warehouseEntity);
        warehouseRepository.deleteAll();
        warehouseRepository.save(warehouseEntity);
    }

    @Override
    public Warehouse exportWarehouses() {
        var warehouseEntity = warehouseRepository.getFirstByLevel(0);

        if (warehouseEntity == null) {
            throw new BLNotFoundException("No Warehouses found");
        }

        return WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
    }

    @Override
    public Hop getWarehouse(String code) {
        var warehouseEntity = warehouseRepository.findFirstByCode(code);

        if (warehouseEntity == null) {
            throw new BLNotFoundException("No Warehouse found with code " + code);
        }

        return WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
    }
}
