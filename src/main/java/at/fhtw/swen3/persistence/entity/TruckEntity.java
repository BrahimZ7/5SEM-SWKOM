package at.fhtw.swen3.persistence.entity;

import at.fhtw.swen3.services.dto.GeoCoordinate;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
public class TruckEntity extends HopEntity {
    @Id
    private Long id;
    private String regionGeoJson;
    private String numberPlate;

    public TruckEntity(String regionGeoJson, String numberPlate, String hopType, String code, String description, Integer processingDelayMins, String locationName, GeoCoordinateEntity locationCoordinates) {
        super(hopType, code, description, processingDelayMins, locationName, locationCoordinates);
        this.regionGeoJson = regionGeoJson;
        this.numberPlate = numberPlate;
    }
}
