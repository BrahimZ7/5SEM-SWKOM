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
@ToString
@Builder
@Table(name = "PARCEL")
public class ParcelEntity {
    @Id
    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @DecimalMin("0.0")
    private Float weight;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "RECIPIENT_ID")
    private RecipientEntity recipient;

    @NotNull
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SENDER_ID")
    private RecipientEntity sender;

    private TrackingInformation.StateEnum state;

    @OneToMany(cascade = CascadeType.ALL)
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<HopArrivalEntity> futureHops = new ArrayList<>();
}
