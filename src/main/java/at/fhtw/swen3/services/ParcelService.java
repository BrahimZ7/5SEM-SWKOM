package at.fhtw.swen3.services;

import at.fhtw.swen3.services.dto.NewParcelInfo;
import at.fhtw.swen3.services.dto.Parcel;
import at.fhtw.swen3.services.dto.TrackingInformation;

import java.io.IOException;

public interface ParcelService {
    TrackingInformation trackParcel(String trackingId);

    void reportParcelHop(String trackingId, String code);

    void reportParcelDelivery(String trackingId);

    NewParcelInfo submitParcel(Parcel parcel) throws IOException, InterruptedException;

    NewParcelInfo transitionParcel(Parcel parcel, String trackingId);
}
