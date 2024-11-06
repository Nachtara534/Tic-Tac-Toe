package de.nachtara534;

public enum Token {
    BLUE,
    RED;

    public Token change() {
        return this == BLUE ? RED : BLUE;
    }
}
