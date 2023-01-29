package at.fhtw.swen3.services;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class BLException extends RuntimeException {
    final String message;
}
