package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "TRANSFER_WAREHOUSE")
public class TransferwarehouseEntity extends HopEntity {
    @Id
    @GeneratedValue()
    private Long id;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    private String regionGeoJson;
    private String logisticsPartner;
    private String logisticsPartnerUrl;
}
