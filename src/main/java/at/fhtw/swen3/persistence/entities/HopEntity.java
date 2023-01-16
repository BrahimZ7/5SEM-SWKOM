package at.fhtw.swen3.persistence.entities;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
public class HopEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private String hopType;
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;
    private String description;
    private Integer processingDelayMins;
    private String locationName;

    @OneToOne
    @NotNull
    private GeoCoordinateEntity locationCoordinates;

    public HopEntity(
        String hopType,
        String code,
        String description,
        Integer processingDelayMins,
        String locationName,
        GeoCoordinateEntity locationCoordinates
    ) {
        this.hopType = hopType;
        this.code = code;
        this.description = description;
        this.processingDelayMins = processingDelayMins;
        this.locationName = locationName;
        this.locationCoordinates = locationCoordinates;
    }
}
