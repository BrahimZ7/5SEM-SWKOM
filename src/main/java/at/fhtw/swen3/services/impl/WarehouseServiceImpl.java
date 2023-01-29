package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.services.mapper.WarehouseMapper;
import org.springframework.stereotype.Service;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.dto.Hop;
import at.fhtw.swen3.services.dto.Warehouse;

@Service
public class WarehouseServiceImpl implements WarehouseService {
    final WarehouseRepository warehouseRepository;


    public WarehouseServiceImpl(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void importWarehouses(Warehouse warehouse) {

        var warehouseEntity = WarehouseMapper.INSTANCE.dtoToEntity(warehouse);

        warehouseRepository.deleteAll();
        warehouseRepository.save(warehouseEntity);
    }

    @Override
    public Warehouse exportWarehouses() {
        var warehouseEntity = warehouseRepository.getFirstByLevel(0);

        return WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
    }

    @Override
    public Hop getWarehouse(String code) {
        var warehouseEntity = warehouseRepository.findFirstByCode(code);

        return WarehouseMapper.INSTANCE.entityToDto(warehouseEntity);
    }
}
