package at.fhtw.swen3.persistence.entities;

import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor
public class WarehouseNextHopsEntity {
    @Id
    @GeneratedValue()
    private long id;
    private Integer traveltimeMins;

    @NotNull
    @OneToOne
    private HopEntity hop;

    public WarehouseNextHopsEntity(Integer traveltimeMins, HopEntity hop) {
        this.traveltimeMins = traveltimeMins;
        this.hop = hop;
    }
}
