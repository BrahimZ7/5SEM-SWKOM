package at.fhtw.swen3.services.impl;

import at.fhtw.swen3.gps.service.GeoEncodingService;
import at.fhtw.swen3.gps.service.impl.OpenStreetMapsProxy;
import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.repositories.TruckRepository;
import at.fhtw.swen3.persistence.repositories.WarehouseRepository;
import at.fhtw.swen3.services.RouteService;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;
import at.fhtw.swen3.services.dto.Truck;
import at.fhtw.swen3.services.exception.BLNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {
    final TruckRepository truckRepository;
    final WarehouseRepository warehouseRepository;
    final GeoEncodingService geoEncodingService = new OpenStreetMapsProxy();

    @Override
    public List<HopArrival> calculateRoute(Recipient from, Recipient to) throws IOException, InterruptedException {
        var fromCoordinates = geoEncodingService.encodeAddress(from.getStreet() + " " + from.getPostalCode() + " " + from.getCity());
        var toCoordinates = geoEncodingService.encodeAddress(to.getStreet() + " " + to.getPostalCode() + " " + to.getCity());

        var source = new Point(fromCoordinates.getLat().intValue(), fromCoordinates.getLon().intValue());
        var destination = new Point(toCoordinates.getLat().intValue(), toCoordinates.getLon().intValue());

        var truck = truckRepository.getAll();
        if (truck.isEmpty()) {
            throw new BLNotFoundException("No truck found");
        }
        var warehouse = warehouseRepository.getFirstByLevel(0);

        if (warehouse == null) {
            throw new BLNotFoundException("No warehouse found");
        }

        var nearestTruckToSource = truck.get(0);

        for (int i = 0; i < truck.size(); i++) {
            if (new Point(nearestTruckToSource.getLocationCoordinates().getLat().intValue(), nearestTruckToSource.getLocationCoordinates().getLon().intValue()).distance(source) > new Point(truck.get(i).getLocationCoordinates().getLat().intValue(), truck.get(i).getLocationCoordinates().getLon().intValue()).distance(destination)) {
                nearestTruckToSource = truck.get(i);
            }
        }

        var nearestTruckToDestination = truck.get(0);

        for (int i = 0; i < truck.size(); i++) {
            if (new Point(nearestTruckToDestination.getLocationCoordinates().getLat().intValue(), nearestTruckToDestination.getLocationCoordinates().getLon().intValue()).distance(source) > new Point(truck.get(i).getLocationCoordinates().getLat().intValue(), truck.get(i).getLocationCoordinates().getLon().intValue()).distance(source)) {
                nearestTruckToDestination = truck.get(i);
            }
        }


        var nearestHopArrivalToSource = new HopArrival();
        nearestHopArrivalToSource.setCode(nearestTruckToSource.getCode());
        nearestHopArrivalToSource.setDescription(nearestTruckToSource.getDescription());

        var warehouseHop = new HopArrival();
        warehouseHop.setCode(warehouse.getCode());
        warehouseHop.setDescription(warehouse.getDescription());

        var nearestHopArrivalToDestination = new HopArrival();
        nearestHopArrivalToDestination.setCode(nearestTruckToDestination.getCode());
        nearestHopArrivalToDestination.setDescription(nearestTruckToDestination.getDescription());

        return new ArrayList<HopArrival>(List.of(nearestHopArrivalToSource, warehouseHop, nearestHopArrivalToDestination));
    }
}
