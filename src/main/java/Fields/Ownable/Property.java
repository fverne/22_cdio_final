package Fields.Ownable;

import Fields.Ownerable;
import model.Player;

public class Property extends Ownerable {
    private boolean canBuild = false;
    private int pairType;
    private int houseCost = 4000;
    private int[] rentList = new int[6]; // indholder de 6 forskellige rent priser der er, afhængig af antal huse
    private int houseAmount = 0;

    public int getHouseAmount() {
        return houseAmount;
    }
    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
        this.setRent(this.houseAmount);
    }
    public void changeHouseAmount(int amount, Player pl){
        if(pl == this.getOwnedBy() && amount >= this.houseAmount){
            setHouseAmount(houseAmount - amount);
        }
    }


    public Property(String name, String text, int cost, int[] rentList, int pairType,int houseCost) {
        super(name, text, cost, rentList[0]);
        this.rentList = rentList;
        this.pairType = pairType;
        this.houseCost = houseCost;
    }


}