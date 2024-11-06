package de.nachtara534;

import static java.lang.System.out;

import java.util.Scanner;

import de.nachtara534.exceptions.PositionAlreadyUsedException;

public class Game {

    private final Validator validator = new Validator();

    public String ansi_RESET = "\u001B[0m";

    public String ansi_BLUE = "\u001B[34m";

    public String ansi_RED = "\u001B[31m";


    public void play() {
        boolean win = false;

        Token[][] board = initBoard();

        Token turn = Token.RED;
        int turnCounter = 0;
        while (!win) {
            turnCounter++;
            out.println(turn + " ist am Zug");

            boolean valid = false;
            while (!valid) {
                printField(board);
                try {
                    TokenPosition positionToPlace = splitInput(userInput());
                    placeToken(board, positionToPlace, turn);
                    valid = true;
                } catch (PositionAlreadyUsedException e) {
                    out.println(e.getMessage());
                }
            }

            if (turnCounter >= 5) {
                win = winConditions(board, turn);
            }

            if (!win) {
                turn = turn.change();
            }
        }

        out.println(turn + " hat nach " + turnCounter + " zügen gewonnen!");

    }

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
            out.println("Wähle eine Position");
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
        int y = (input.toUpperCase().charAt(0) - 65);
        int x = Integer.parseInt(String.valueOf(input.charAt(1)));
        return new TokenPosition(x, y);
    }

    public void placeToken(Token[][] board, final TokenPosition position, final Token token) throws PositionAlreadyUsedException {

        if (validator.positionValidation(board, position)) {
            board[position.x][position.y] = token;
        }

    }

    private boolean winConditions(Token[][] board, Token turn) {
        if (checkWinDiagonal(board, turn)) {
            return true;
        }
        if (checkWinHorizontal(board, turn)) {
            return true;
        }
        return checkWinVertical(board, turn);
    }

    public boolean checkWinHorizontal(final Token[][] board, Token playerColor) {
        for (int y = 0; y < board.length; y++) {
            if (board[0][y] == playerColor && board[1][y] == playerColor && board[2][y] == playerColor) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWinVertical(final Token[][] board, Token playerColor) {
        for (Token[] tokens : board) {
            if (tokens[0] == playerColor && tokens[1] == playerColor && tokens[2] == playerColor) {
                return true;
            }
        }
        return false;
    }

    public boolean checkWinDiagonal(final Token[][] board, Token playerColor) {
        if (board[0][0] == playerColor && board[1][1] == playerColor && board[2][2] == playerColor) {
            return true;
        }
        return board[0][2] == playerColor && board[1][1] == playerColor && board[2][0] == playerColor;
    }

    public TokenPosition ai(Token[][] board, int depth, int maxDepth) {
        if(depth == maxDepth) {
            return null;
        }
        return null;
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
