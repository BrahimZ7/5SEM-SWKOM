package at.fhtw.swen3.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import at.fhtw.swen3.persistence.entities.WarehouseEntity;

public interface WarehouseRepository extends JpaRepository<WarehouseEntity, Long> {
    WarehouseEntity getFirstByLevel(Integer level);

    WarehouseEntity findFirstByCode(String code);

    void deleteAll();
}
