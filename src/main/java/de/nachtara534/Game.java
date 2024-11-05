package de.nachtara534;

import static java.lang.System.out;

import java.util.Scanner;

import de.nachtara534.exceptions.PositionAlreadyUsedException;

public class Game {

    private final Validator validator = new Validator();

    public String ansi_RESET = "\u001B[0m";

    public String ansi_BLUE = "\u001B[34m";

    public String ansi_RED = "\u001B[31m";


    public Token[][] initBoard() {
        Token[][] board = new Token[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = null;
            }
        }
        return board;
    }

    public String userInput() {
        boolean valid = false;
        String input = "";
        while (!valid) {
            input = new Scanner(System.in).next();
            try {
                if (validator.inputValidation(input)) {
                    valid = true;
                }
            } catch (Exception e) {
                out.println(e.getMessage());
            }
        }
        return input;
    }

    public TokenPosition splitInput(String input) {
        int y = (input.charAt(0) - 65);
        int x = Integer.parseInt(String.valueOf(input.charAt(1)));
        return new TokenPosition(x, y);
    }

    public void placeToken(Token[][] board, final TokenPosition position, final Token token) throws PositionAlreadyUsedException {

        if (validator.positionValidation(board, position)) {
            board[position.y][position.x] = token;
        }

    }


    //Prints the Field with Stones
    public void printField(Token[][] gameBoard) {
        for (int y = gameBoard[0].length - 1; y >= 0; y--) {
            out.print(Character.toChars(y + 65));
            out.print("|");

            for (Token[] tokens : gameBoard) {

                if (tokens[y] == Token.BLUE) {
                    out.print(ansi_BLUE + " O" + ansi_RESET + "|");
                } else if (tokens[y] == Token.RED) {
                    out.print(ansi_RED + " X" + ansi_RESET + "|");
                } else {
                    out.print("  |");
                }
            }
            out.println();
        }
        for (int i = 0; i < gameBoard.length; i++) {

            if (i == 0) {
                out.print("   " + i);
            } else {
                out.print("  " + i);
            }
        }
        out.println();
    }

}
