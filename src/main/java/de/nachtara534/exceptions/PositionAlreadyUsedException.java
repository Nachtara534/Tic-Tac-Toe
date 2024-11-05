package de.nachtara534.exceptions;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PositionAlreadyUsedException extends Exception {
    String meassage;
}
