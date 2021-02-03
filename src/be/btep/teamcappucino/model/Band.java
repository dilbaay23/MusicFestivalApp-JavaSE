package be.btep.teamcappucino.model;

public class Band extends Talent {
    private Artist[] members;

    public Band(String name, Artist[] members) {
        super(name);
        this.members = members;
    }

    public Band(String name) {
        super(name);
    }


    public Artist[] getMembers() {
        return members;
    }

    public void setMembers(Artist[] members) {
        this.members = members;
    }
}
