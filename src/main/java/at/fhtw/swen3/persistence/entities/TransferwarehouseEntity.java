package at.fhtw.swen3.persistence.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class TransferwarehouseEntity {
    @Id
    @GeneratedValue()
    private Long id;
    private String regionGeoJson;
    private String logisticsPartner;
    private String logisticsPartnerUrl;

    public TransferwarehouseEntity(String regionGeoJson, String logisticsPartner, String logisticsPartnerUrl) {
        this.regionGeoJson = regionGeoJson;
        this.logisticsPartner = logisticsPartner;
        this.logisticsPartnerUrl = logisticsPartnerUrl;
    }
}
