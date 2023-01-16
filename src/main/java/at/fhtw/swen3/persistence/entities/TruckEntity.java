package at.fhtw.swen3.persistence.entities;

import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@NoArgsConstructor
public class TruckEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String regionGeoJson;
    private String numberPlate;

    public TruckEntity(String regionGeoJson, String numberPlate, String hopType, String code, String description, Integer processingDelayMins, String locationName, GeoCoordinateEntity locationCoordinates) {
        super(hopType, code, description, processingDelayMins, locationName, locationCoordinates);
        this.regionGeoJson = regionGeoJson;
        this.numberPlate = numberPlate;
    }
}
