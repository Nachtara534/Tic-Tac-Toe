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
    void test_printFieldWithToken() {

        Token[][] board = new Token[3][3];


        board[0][0] = Token.RED;
        board[1][0] = Token.BLUE;

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

    @Test
    void test_horizontalWinConditionFirstRow() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[0][0] = Token.RED;
        board[1][0] = Token.RED;
        board[2][0] = Token.RED;

        assertThat(game.checkWinHorizontal(board, Token.RED)).isTrue();
    }

    @Test
    void test_horizontalWinConditionLastRow() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[0][2] = Token.BLUE;
        board[1][2] = Token.BLUE;
        board[2][2] = Token.BLUE;

        assertThat(game.checkWinHorizontal(board, Token.BLUE)).isTrue();
    }

    @Test
    void test_verticalWinConditionFirstColumn() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[0][0] = Token.RED;
        board[0][1] = Token.RED;
        board[0][2] = Token.RED;

        assertThat(game.checkWinVertical(board, Token.RED)).isTrue();
    }

    @Test
    void test_verticalWinConditionLastColumn() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[2][0] = Token.BLUE;
        board[2][1] = Token.BLUE;
        board[2][2] = Token.BLUE;

        assertThat(game.checkWinVertical(board, Token.BLUE)).isTrue();
    }

    @Test
    void test_diagonalWinConditionLeftToRight() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[0][0] = Token.RED;
        board[1][1] = Token.RED;
        board[2][2] = Token.RED;

        assertThat(game.checkWinDiagonal(board, Token.RED)).isTrue();
    }

    @Test
    void test_diagonalWinConditionRightToLeft() {
        Token[][] board = new Token[3][3];
        Game game = new Game();
//      board[x][y]
        board[0][2] = Token.RED;
        board[1][1] = Token.RED;
        board[2][0] = Token.RED;

        assertThat(game.checkWinDiagonal(board, Token.RED)).isTrue();
    }

    @Test
    void test_TokenPlacementAfterSplit() throws PositionAlreadyUsedException {
        Game game = new Game();
        Token[][] board = new Token[3][3];
        String input1 = "A0";
        String input2 = "C2";
        String input3 = "C1";

        game.placeToken(board, game.splitInput(input1), Token.RED);
        game.placeToken(board, game.splitInput(input2), Token.BLUE);
        game.placeToken(board, game.splitInput(input3), Token.BLUE);

        assertThat(board[0][0]).isEqualTo(Token.RED);
        assertThat(board[2][2]).isEqualTo(Token.BLUE);
        assertThat(board[1][2]).isEqualTo(Token.BLUE);

    }
}
