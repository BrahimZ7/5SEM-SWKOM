package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@SuperBuilder
@Table(name = "WAREHOUSE")
public class WarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue()
    @Column(name = "ID")
    private Long id;

    private Integer level;

    @NotNull
    @Singular
    @Valid
    @OneToMany(targetEntity = WarehouseNextHopsEntity.class, mappedBy = "warehouse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<WarehouseNextHopsEntity> nextHops = new ArrayList<>();
}
