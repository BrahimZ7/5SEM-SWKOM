package at.fhtw.swen3.configuration.service;

import at.fhtw.swen3.persistence.entities.GeoCoordinateEntity;
import at.fhtw.swen3.services.dto.GeoCoordinate;

import java.io.IOException;

public interface GeoEncodingService {
    GeoCoordinateEntity encodeAddress(String address) throws IOException, InterruptedException;
}
