package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Generated;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "TRUCK")
public class TruckEntity extends HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String regionGeoJson;
    private String numberPlate;

}
