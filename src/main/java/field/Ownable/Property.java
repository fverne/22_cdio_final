package field.Ownable;

import model.Player;

public class Property extends Ownable {
    private int houseCost = 4000;
    private int[] rentList; // indholder de 6 forskellige rent priser der er, afhængig af antal huse
    private int houseAmount = 0;
    private int hotelCost;

    public int getHouseAmount() {
        return houseAmount;
    }

    public void setHouseAmount(int houseAmount) {
        this.houseAmount = houseAmount;
        this.setRent(rentList[this.houseAmount]);
    }

    public void changeHouseAmount(int amount, Player pl) {
        if (pl == this.getOwnedBy() && amount >= this.houseAmount) {
            setHouseAmount(houseAmount + amount);
        }
    }

    public int getHouseCost() {
        return this.houseCost;
    }

    public int getHotelCost() {
        return this.hotelCost;
    }

    public Property(String name, String text, int cost, int[] rentList, int[] category, int houseCost, int hotelCost) {
        super(name, text, cost, rentList[0], category);
        this.rentList = rentList;
        this.houseCost = houseCost;
        this.hotelCost = hotelCost;
    }
}