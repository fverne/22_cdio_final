import model.*;
import controllers.MovementController;
import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @org.junit.jupiter.api.Test
    void addToBalance(){
        Player testPlayer = new Player();
        testPlayer.deposit(-35000);
        assertEquals(0, testPlayer.getBalance());

        testPlayer.deposit(355);
        assertEquals(355, testPlayer.getBalance());
    }

    @org.junit.jupiter.api.Test
    void setOwner2() {
        Player testPlayer = new Player();
        testPlayer.setOwnership(3);
        testPlayer.setOwnership(24);
        testPlayer.setOwnership(8);

        assertTrue(testPlayer.getOwnership(3));
        assertTrue(testPlayer.getOwnership(24));
        assertTrue(testPlayer.getOwnership(8));
        assertFalse(testPlayer.getOwnership(14));
    }

    @org.junit.jupiter.api.Test
    void setPosition() {
       Player testPlayer = new Player();
       MovementController movementController = new MovementController(1);
       movementController.setPosition(testPlayer,34);
       //testPlayer.setPosition(34);
       assertEquals(34,testPlayer.getPosition());

       movementController.setPosition(testPlayer,7);
       //testPlayer.setPosition(7);
       assertEquals(1,testPlayer.getPosition());
    }
}