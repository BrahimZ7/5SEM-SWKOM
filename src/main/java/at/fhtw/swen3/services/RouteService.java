package at.fhtw.swen3.services;

import at.fhtw.swen3.persistence.entities.ParcelEntity;
import at.fhtw.swen3.services.dto.HopArrival;
import at.fhtw.swen3.services.dto.Recipient;

import java.io.IOException;
import java.util.List;

public interface RouteService {
    List<HopArrival> calculateRoute(Recipient from, Recipient to) throws IOException, InterruptedException;
}
