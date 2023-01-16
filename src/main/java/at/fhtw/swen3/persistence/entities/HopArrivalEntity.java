package at.fhtw.swen3.persistence.entities;

import at.fhtw.swen3.services.dto.Hop;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class HopArrivalEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]{4}\\d{1,4}$")
    private String code;
    private String description;

    @NotNull
    @OneToOne
    @JoinColumn
    private ParcelEntity parcel;

    @NotNull
    private OffsetDateTime dateTime;

    public HopArrivalEntity(String code, String description, OffsetDateTime dateTime) {
        this.code = code;
        this.description = description;
        this.dateTime = dateTime;
    }

    public static Hop builder() {
        return null;
    }
}
