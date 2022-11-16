package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.TrackingInformation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
@NoArgsConstructor
public class ParcelEntity {
    @Id
    private Long id;

    @Pattern(regexp = "^[A-Z0-9]{9}$")
    private String trackingId;

    @DecimalMin("0.0")
    private Float weight;

    @NotNull
    @OneToOne
    private RecipientEntity recipient;

    @NotNull
    @OneToOne
    private RecipientEntity sender;

    private TrackingInformation.StateEnum state;

    @NotNull
    @OneToMany
    private List<HopArrivalEntity> visitedHops = new ArrayList<>();

    @NotNull
    @OneToMany
    private List<HopArrivalEntity> futureHops = new ArrayList<>();

    public ParcelEntity(
            String trackingId,
            Float weight,
            RecipientEntity recipient,
            RecipientEntity sender,
            TrackingInformation.StateEnum state,
            List<HopArrivalEntity> visitedHops,
            List<HopArrivalEntity> futureHops
    ) {
        this.trackingId = trackingId;
        this.weight = weight;
        this.recipient = recipient;
        this.sender = sender;
        this.state = state;
        this.visitedHops = visitedHops;
        this.futureHops = futureHops;
    }


}
