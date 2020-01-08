import model.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementControllerTest {

    @Test
    void makeMove() {
        MovementController movementController = new MovementController(1);
        Player player = movementController.makeMove(0);
        assertEquals(movementController.getLatestRoll(), player.getPosition());
    }
}