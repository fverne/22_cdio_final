import Fields.Field;
import Fields.NotOwnable.ChanceField;
import Fields.Ownable.Property;
import Fields.Ownerable;
import GUI.FieldProperties.Chancecards;
import GUI.GUIController;
import Calculation.Calculator;
import model.Player;

public class TurnController {

    private GUIController guiController;
    private MovementController movementController;
    private Calculator calculator;

    public void startGame() {
        guiController = new GUIController();
        guiController.initGUIPlayers();
        movementController = new MovementController(guiController.getNumberOfPlayers());
        calculator = new Calculator();
    }

    public void playGame() {
        int winCondition = 0;
        for (int turnTimer = 0; winCondition == 0; turnTimer++) {
            guiController.rollButtonGUI();
            guiController.displayRollGUI(movementController.getLatestRoll()[0].getFaceValue(), movementController.getLatestRoll()[1].getFaceValue());
            model.Player player = movementController.makeMove(turnTimer);

            guiController.displayGUIMsg(movementController.passedStart(movementController.getLatestPosition(player), player.getPosition()));
            guiController.movePlayerGUI(turnTimer, player.getPosition(), movementController.getLatestPosition(player));

            int fieldNumber = player.getPosition();
            Fields.Field plField = calculator.getField(fieldNumber);

            //hvis købbart felt
            if (plField instanceof Fields.Ownerable) {

                //Hvis feltet ikke er ejet, købes det
                if ((((Fields.Ownerable) plField).getOwnedBy() == null)) {
                    buyField(turnTimer, player, fieldNumber, plField);

                    //Hvis feltet ejes og du er ejer, kan du byg hus
                } else if (plField instanceof Fields.Ownable.Property && player.equals(((Fields.Ownerable) plField).getOwnedBy())
                        && ((Fields.Ownable.Property) plField).isCanBuild()) {

                    if (calculator.isBuildable(fieldNumber)) {
                        buildHouse(turnTimer, player, fieldNumber, (Property) plField);
                    }

                    //ellers betal husleje
                } else {
                    this.calculator.payRent(player, fieldNumber, this.movementController.getLatestRoll());
                }
            }

            //chancekort
            if (plField instanceof Fields.NotOwnable.ChanceField) {
                landOnChancecard(player);
            }
            guiController.updatePlayerBalanceGUI(turnTimer, player.getBalance());

            if (turnTimer == guiController.getNumberOfPlayers() - 1) {
                turnTimer = -1;
            }
        }
    }

    private void landOnChancecard(Player player) {
        Chancecards card = ChanceField.getRandomCard();
        guiController.displayChancecardGUI(card.getMessage());
        player.deposit(card.getReward());
    }

    private void buyField(int turnTimer, Player player, int fieldNumber, Field plField) {
        if (guiController.yesOrNo("Vil du købe grunden? prisen er: " + ((Ownerable) plField).getCost() + " kr.").equals("ja")) {
            calculator.buyField(player, fieldNumber);

            if (plField instanceof Property) {
                ((Property) plField).setCanBuild(true);
            }
            guiController.setFieldBorderGUI(fieldNumber, turnTimer);
        }
    }

    private void buildHouse(int turnTimer, Player player, int fieldNumber, Property plField) {
        int amount;
        int numberOfHousesAlreadyPlaced = plField.getHouseAmount();

        //IMPLEMENTER MAKS 4 HUSE
        if (plField.getHouseAmount() == 4) {
            if (guiController.yesOrNo("Vil du bygge et hotel?, prisen pr. stk er: " + " kr.").equals("ja"))
                plField.setHotelAmount(1);
            guiController.addHotelToGUI(fieldNumber);
        }

        if (guiController.yesOrNo("Vil du bygge?, prisen pr. hus er: " + plField.getHouseCost() + " kr.").equals("ja")) {
            do {
                amount = guiController.amountOfHousesToBuyGUI();

                if ((amount + numberOfHousesAlreadyPlaced) > 4) {
                    guiController.displayGUIMsg("Du kan højst bygge 4 huse pr. felt.");
                } else if (plField.getHouseAmount() < 4) {
                    calculator.buyHouse(player, fieldNumber, amount);
                    guiController.addHouseToGUI(fieldNumber, amount, numberOfHousesAlreadyPlaced);
                    guiController.setFieldBorderGUI(fieldNumber, turnTimer);
                    plField.setHouseAmount(amount);
                }
            }
            while ((amount + numberOfHousesAlreadyPlaced) > 4);
        }
    }
}


