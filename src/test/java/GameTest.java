import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import de.nachtara534.Game;
import de.nachtara534.Token;
import de.nachtara534.TokenPosition;
import de.nachtara534.exceptions.PositionAlreadyUsedException;

public class GameTest {

    @Test
    void test_initBoard() {
        Game game = new Game();
        Token[][] board = game.initBoard();
        assertThat(board.length).isEqualTo(3);
        assertThat(board[1].length).isEqualTo(3);
        assertThat(board[2][2]).isNull();
    }

    @Test
    void test_printField() {

        Token[][] board = new Token[3][3];
        Game game = new Game();

        game.printField(board);
    }

    @Test
    void test_splitInput() {
        Game game = new Game();
        String input1 = "A0";
        String input2 = "A1";
        String input3 = "B1";

        TokenPosition tokenPosition1 = game.splitInput(input1);
        TokenPosition tokenPosition2 = game.splitInput(input2);
        TokenPosition tokenPosition3 = game.splitInput(input3);

        assertThat(tokenPosition1).isEqualTo(new TokenPosition(0, 0));
        assertThat(tokenPosition2).isEqualTo(new TokenPosition(1, 0));
        assertThat(tokenPosition3).isEqualTo(new TokenPosition(1, 1));

    }

    @Test
    void test_placeToken() throws PositionAlreadyUsedException {
        Token[][] board = new Token[3][3];
        Game game = new Game();

        game.placeToken(board, new TokenPosition(0, 0), Token.RED);

        assertThat(board[0][0]).isEqualTo(Token.RED);
    }


}
