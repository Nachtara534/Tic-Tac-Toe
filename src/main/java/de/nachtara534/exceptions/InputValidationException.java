package de.nachtara534.exceptions;


import lombok.AllArgsConstructor;

@AllArgsConstructor
public class InputValidationException extends Exception {
    String message;
}
