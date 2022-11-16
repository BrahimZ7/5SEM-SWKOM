package at.fhtw.swen3.persistence.entity;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
public class WarehouseEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private Integer level;

    @NotNull
    @OneToMany
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();

    public WarehouseEntity(Integer level, List<WarehouseNextHopsEntity> nextHops) {
        this.level = level;
        this.nextHops = nextHops;
    }
}
