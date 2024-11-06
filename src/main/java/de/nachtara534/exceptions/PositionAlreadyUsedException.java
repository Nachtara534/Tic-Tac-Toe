package de.nachtara534.exceptions;

import lombok.ToString;

@ToString
public class PositionAlreadyUsedException extends Exception {
    public PositionAlreadyUsedException() {
    }

    public PositionAlreadyUsedException(String message) {
        super(message);
    }
}
