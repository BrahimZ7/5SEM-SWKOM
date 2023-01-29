package at.fhtw.swen3.services.exception;

import at.fhtw.swen3.services.BLException;
import lombok.experimental.SuperBuilder;

public class BLValidationException extends BLException {
    public BLValidationException(String message) {
        super(message);
    }
}
