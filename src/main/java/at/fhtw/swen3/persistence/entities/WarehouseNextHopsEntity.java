package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "WAREHOUSE_ID")
    private WarehouseEntity warehouse;
}
