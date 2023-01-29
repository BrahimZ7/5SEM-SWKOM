package at.fhtw.swen3.services.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.UUID;

import at.fhtw.swen3.persistence.entities.HopArrivalEntity;
import at.fhtw.swen3.persistence.repositories.ParcelRepository;
import at.fhtw.swen3.services.ParcelService;
import at.fhtw.swen3.services.RouteService;
import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;
import at.fhtw.swen3.services.dto.TrackingInformation.StateEnum;
import at.fhtw.swen3.services.exception.BLIllegalArgumentException;
import at.fhtw.swen3.services.exception.BLNotFoundException;
import at.fhtw.swen3.services.mapper.HopArrivalMapper;
import at.fhtw.swen3.services.mapper.HopMapper;
import at.fhtw.swen3.services.mapper.ParcelMapper;
import at.fhtw.swen3.services.validation.ValidatorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ParcelServiceImpl implements ParcelService {

    private final ParcelRepository parcelRepository;
    private final ValidatorService validatorService;
    private final RouteService routeService;

    @Override
    public TrackingInformation trackParcel(String trackingId) {
        log.info("Tracking parcel with id {}", trackingId);

        var parcelEntity = parcelRepository.findByTrackingId(trackingId);

        var trackingInformation = new TrackingInformation();

        trackingInformation.setState(parcelEntity.getState());
        trackingInformation.setVisitedHops(HopArrivalMapper.INSTANCE.entityToDto(parcelEntity.getVisitedHops()));
        trackingInformation.setFutureHops(HopArrivalMapper.INSTANCE.entityToDto(parcelEntity.getFutureHops()));

        return trackingInformation;
    }

    @Override
    public void reportParcelHop(String trackingId, String code) {
        log.info("Reporting parcel hop for parcel with id {} and code {}", trackingId, code);

        var parcelEntity = parcelRepository.findByTrackingId(trackingId);

        if (parcelEntity == null) {
            throw new BLNotFoundException("No parcel found with id " + trackingId);
        }

        var hopArrivalEntity = parcelEntity.getFutureHops().stream().findFirst();

        if (hopArrivalEntity.isPresent()) {
            parcelEntity.getVisitedHops().add(hopArrivalEntity.get());
            parcelEntity.getFutureHops().remove(hopArrivalEntity.get());
        } else {
            throw new BLIllegalArgumentException(String.format("No future hop found for parcel with id {} and code {}", trackingId, code));
        }

        parcelRepository.save(parcelEntity);
    }

    @Override
    public void reportParcelDelivery(String trackingId) {
        var parcelEntity = parcelRepository.findByTrackingId(trackingId);

        if (parcelEntity == null) {
            throw new BLNotFoundException("No parcel found with id " + trackingId);
        }

        parcelEntity.setState(StateEnum.DELIVERED);
        parcelEntity.getVisitedHops().addAll(parcelEntity.getFutureHops());
        parcelEntity.setFutureHops(new ArrayList<HopArrivalEntity>());

        parcelRepository.save(parcelEntity);
    }

    @Override
    public NewParcelInfo submitParcel(Parcel parcel) throws IOException, InterruptedException {
        var trackingID = createTrackingID();
        var parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        parcelEntity.setTrackingId(trackingID);
        parcelEntity.setState(StateEnum.PICKUP);
        parcelEntity.setVisitedHops(new ArrayList<>());

        var route = routeService.calculateRoute(parcel.getSender(), parcel.getRecipient());
        parcelEntity.setFutureHops(HopArrivalMapper.INSTANCE.dtoToEntity(route));

        System.out.println(parcelEntity.toString());
        validatorService.validate(parcelEntity);

        parcelRepository.save(parcelEntity);

        return new NewParcelInfo().trackingId(trackingID);
    }

    @Override
    public NewParcelInfo transitionParcel(Parcel parcel, String trackingId) {
        var parcelEntity = ParcelMapper.INSTANCE.dtoToEntity(parcel);

        validatorService.validate(parcelEntity);

        var parcelEntityFromDB = parcelRepository.findByTrackingId(trackingId);

        if (parcelEntityFromDB == null) {
            throw new BLNotFoundException("No parcel found with id " + trackingId);
        }

        parcelEntityFromDB.setVisitedHops(parcelEntity.getVisitedHops());
        parcelEntityFromDB.setFutureHops(parcelEntity.getFutureHops());
        parcelEntityFromDB.setState(parcelEntity.getState());
        parcelEntityFromDB.setRecipient(parcelEntity.getRecipient());
        parcelEntityFromDB.setSender(parcelEntity.getSender());
        parcelEntityFromDB.setWeight(parcelEntity.getWeight());

        parcelRepository.save(parcelEntityFromDB);

        return new NewParcelInfo().trackingId(trackingId);
    }

    private String createTrackingID() {
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 9).toUpperCase();
    }
}
