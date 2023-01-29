package at.fhtw.swen3.persistence.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;

public interface HopArrivalRepository extends JpaRepository<HopArrivalEntity, Long> {
}
