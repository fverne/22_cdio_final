import Controller.MovementController;
import model.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class JailTest {

   @Test
    void testlandOnJailField(){
      MovementController testmovementcontroller = new MovementController(1);
      Player testplayer = testmovementcontroller.landOnJailField(0);
      assertTrue(testplayer.getInJail());
      assertEquals(testplayer.getPosition(),10);
   }
}
