package at.fhtw.swen3.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;

import java.io.IOException;

public interface GeoEncodingService {

    GeoCoordinateEntity encodeAddress(String address) throws IOException, InterruptedException;
}
