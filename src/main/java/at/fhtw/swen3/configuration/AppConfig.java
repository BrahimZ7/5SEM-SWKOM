package at.fhtw.swen3.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.WarehouseService;
import at.fhtw.swen3.services.impl.ParcelServiceImpl;
import at.fhtw.swen3.services.impl.WarehouseServiceImpl;

@Configuration
public class AppConfig {

    @Bean
    public ParcelService parcelServivce(ParcelRepository parcelRepository) {
        return new ParcelServiceImpl(parcelRepository);
    }

    @Bean
    public WarehouseService warehouseService(WarehouseRepository warehouseRepository) {
        return new WarehouseServiceImpl(warehouseRepository);
    }
}
