package de.nachtara534;

public enum Token {
    BLUE,
    RED;

    public Token change(Token toChange){
        if (toChange == BLUE) {
            toChange = RED;
        } else {
            toChange = BLUE;
        }
        return toChange;
    }
}
