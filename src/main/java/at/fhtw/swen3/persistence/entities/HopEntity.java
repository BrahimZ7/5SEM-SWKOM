package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Getter
@Setter
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "HOP")
public class HopEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String hopType;
    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;
    private String description;
    private Integer processingDelayMins;
    private String locationName;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "GEO_COORDINATE_ID", referencedColumnName = "ID")
    private GeoCoordinateEntity locationCoordinates;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "WAREHOUSE_NEXT_HOPS_ID", referencedColumnName = "ID")
    private WarehouseNextHopsEntity warehouseNextHops;

}
