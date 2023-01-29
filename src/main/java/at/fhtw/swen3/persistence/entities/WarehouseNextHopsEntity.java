package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@Setter
@Getter
@Table(name = "WAREHOUSE_NEXT_HOPS")
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue()
    private long id;
    private Integer traveltimeMins;

    @NotNull
    @Valid
    @OneToOne(mappedBy = "warehouseNextHops", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HopEntity hop;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WarehouseEntity warehouse;

    public void setHop(HopEntity hop) {
        this.hop = hop;
        hop.setWarehouseNextHops(this);
    }
}
