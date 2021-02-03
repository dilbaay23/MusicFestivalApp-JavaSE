package be.btep.teamcappucino.model;

public abstract class Talent {
    private String name;

    public Talent() {
    }

    public Talent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
