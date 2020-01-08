package Fields;


public abstract class Field {

    private String name;
    private String text;

    public Field(String name, String text){
        this.name = name;
        this.text = text;
    }/*
    public void landOn(Player pl){

    }*/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
