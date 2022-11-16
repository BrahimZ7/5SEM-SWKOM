package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "geo_coordinate")
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private Double latitude;
    private Double longitude;

    public GeoCoordinateEntity(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
