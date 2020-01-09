package GUI;

import model.Player;

public abstract class Fields {
    private String name;
    protected boolean isJail;

    Fields(String name, boolean isJail) {
        this.name = name;
        this.isJail = isJail;
    }

    Fields(){
    }

    public abstract Fields landOnField(Player player);

    public String getFieldName(int fieldNumber){
        return name;
    }
}
