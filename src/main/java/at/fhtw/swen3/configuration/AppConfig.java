package at.fhtw.swen3.configuration;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.OpenStreetMapsProxy;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.services.RouteService;
import at.fhtw.swen3.services.impl.RouteServiceImpl;
import at.fhtw.swen3.services.validation.ValidatorService;
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
    public RouteService routeService(TruckRepository truckRepository, WarehouseRepository warehouseRepository){
        return new RouteServiceImpl(truckRepository, warehouseRepository);
    }

    @Bean
    public ParcelService parcelServivce(ParcelRepository parcelRepository, ValidatorService validatorService, RouteService routeService) {
        return new ParcelServiceImpl(parcelRepository, validatorService, routeService);
    }

    @Bean
    public WarehouseService warehouseService(WarehouseRepository warehouseRepository, ValidatorService validatorService) {
        return new WarehouseServiceImpl(warehouseRepository, validatorService);
    }
}
