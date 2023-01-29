package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "PARCEL")
public class ParcelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @DecimalMin("0.0")
    private Float weight;

    @NotNull
    @OneToOne
    @JoinColumn
    private RecipientEntity recipient;

    @NotNull
    @OneToOne
    @JoinColumn
    private RecipientEntity sender;

    private TrackingInformation.StateEnum state;

    @NotNull
    @OneToMany
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<HopArrivalEntity> futureHops = new ArrayList<>();
}
