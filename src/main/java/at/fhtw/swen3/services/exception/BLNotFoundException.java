package at.fhtw.swen3.services.exception;

import at.fhtw.swen3.services.BLException;
import lombok.experimental.SuperBuilder;

public class BLNotFoundException extends BLException {
    public BLNotFoundException(String message) {
        super(message);
    }
}
