import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import de.nachtara534.Game;
import de.nachtara534.Token;
import de.nachtara534.TokenPosition;
import de.nachtara534.Validator;
import de.nachtara534.exceptions.InputValidationException;
import de.nachtara534.exceptions.PositionAlreadyUsedException;

public class ValidatorTest {

    @Test
    void test_inputValidation() throws InputValidationException {

        Validator validator = new Validator();

        assertThat(validator.inputValidation("A0")).isTrue();
    }

    @Test
    void test_inputValidationFalse() {
        Validator validator = new Validator();

        assertThatThrownBy(() -> validator.inputValidation("d0")).isInstanceOf(InputValidationException.class);
        assertThatThrownBy(() -> validator.inputValidation("A3")).isInstanceOf(InputValidationException.class);
    }

    //POSITION VALIDATION

    @Test
    void test_positionIsNotUsed() throws PositionAlreadyUsedException {
        Game game = new Game();
        Token[][] board = game.initBoard();

        Validator validator = new Validator();

        TokenPosition position = new TokenPosition(0, 0);

        boolean result = validator.positionValidation(board, position);

        assertThat(result).isTrue();
    }

    @Test
    void test_positionIsUsed() {
        Game game = new Game();
        Token[][] board = game.initBoard();

        Validator validator = new Validator();

        TokenPosition position = new TokenPosition(0, 0);

        board[0][0] = Token.RED;

        assertThatThrownBy(() -> validator.positionValidation(board, position));
    }
}
