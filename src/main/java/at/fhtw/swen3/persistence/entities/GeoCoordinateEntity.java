package at.fhtw.swen3.persistence.entities;

import lombok.*;
import org.locationtech.jts.geom.Point;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity(name = "GEO_COORDINATE")
public class GeoCoordinateEntity {
    @Id
    @GeneratedValue()
    private Long id;

    private Double lat;
    private Double lon;
}
