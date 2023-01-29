package at.fhtw.swen3.services.exception;

import at.fhtw.swen3.services.BLException;

public class BLIllegalArgumentException extends BLException {
    public BLIllegalArgumentException(String message) {
        super(message);
    }
}
