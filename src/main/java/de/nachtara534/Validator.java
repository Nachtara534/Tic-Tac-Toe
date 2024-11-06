package de.nachtara534;

import java.util.Arrays;
import java.util.regex.Pattern;

import de.nachtara534.exceptions.InputValidationException;
import de.nachtara534.exceptions.PositionAlreadyUsedException;

public class Validator {

    public boolean inputValidation(final String input) throws InputValidationException {

        Pattern validationPattern = Pattern.compile("[a-cA-C]{1}[0-2]{1}");

        if (validationPattern.matcher(input).find()) {
            return true;
        }
        throw new InputValidationException("The input was invalid");
    }

    /**
     * Method to check if a field on a 2D Field is used.
     *
     * @param board Game Board
     * @param position Position to validate
     * @return false if the position is used. False if unused
     * @throws PositionAlreadyUsedException if the field is already used.
     */
    public boolean positionValidation(final Token[][] board, final TokenPosition position) throws PositionAlreadyUsedException {
        if (board[position.x][position.y] == null) {
            return true;
        }
        throw new PositionAlreadyUsedException(
                "Das feld an der Position " + Character.toString(position.x + 65)  + position.y + " ist bereits belegt!");
    }
}
