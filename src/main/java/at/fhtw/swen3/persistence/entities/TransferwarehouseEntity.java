package at.fhtw.swen3.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "TRANSFER_WAREHOUSE")
public class TransferwarehouseEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private String regionGeoJson;
    private String logisticsPartner;
    private String logisticsPartnerUrl;
}
