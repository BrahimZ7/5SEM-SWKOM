package at.fhtw.swen3.services.impl;

import java.util.ArrayList;
import java.util.UUID;
import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.TrackingInformation.StateEnum;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;

    @Override
    public TrackingInformation trackParcel(String trackingId) {
        log.info("Tracking parcel with id {}", trackingId);

        var parcel = parcelRepository.findByTrackingId(trackingId);

        return null;
    }

    @Override
    public void reportParcelHop(String trackingId, String code) {
        log.info("Reporting parcel hop for parcel with id {} and code {}", trackingId, code);
    }

    @Override
    public void reportParcelDelivery(String trackingId) {
        var parcelEntity = parcelRepository.findByTrackingId(trackingId);

        parcelEntity.setState(StateEnum.DELIVERED);
        parcelEntity.getVisitedHops().addAll(parcelEntity.getFutureHops());
        parcelEntity.setFutureHops(new ArrayList<HopArrivalEntity>());

        parcelRepository.save(parcelEntity);
    }

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) {
        // inputValidator.validate(parcel);

        var trackingID = createTrackingID();

        var parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        parcelEntity.setTrackingId(trackingID);
        parcelEntity.setState(StateEnum.PICKUP);

        return new NewParcelInfo().trackingId(trackingID);
    }

    @Override
    public NewParcelInfo transitionParcel(Parcel parcel, String trackingId) {
        return null;
    }

    private String createTrackingID() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 8);
    }
}
