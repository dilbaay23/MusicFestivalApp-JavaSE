package be.btep.teamcappucino.model;

public class SoloArtist extends Talent {

    private Artist artist;

    public SoloArtist(String name, Artist artist) {
        super(name);
        this.artist = artist;
    }



    public SoloArtist(String name) {
        super(name);
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String toString() {
        return "SoloArtist{" +
                "artist=" + artist +
                '}';
    }
}
