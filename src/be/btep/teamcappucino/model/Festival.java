package be.btep.teamcappucino.model;

import java.math.BigDecimal;

public class Festival {
    private String name;
    private FestivalDay[] days;
    private Venue venue;
    private BigDecimal dayPrice;
    private Genre genre;

    private int soldCombiTicket;


    public Festival() {
    }

    public Festival(String name, FestivalDay[] days, Venue venue, BigDecimal dayPrice, Genre genre, int soldCombiTicket) {
        this.name = name;
        this.days = days;
        this.venue = venue;
        this.dayPrice = dayPrice;
        this.genre = genre;
        this.soldCombiTicket = soldCombiTicket;
    }


    public  int totalLeftticket(){
        int totalLeftTicket=0;
        for (FestivalDay fd : days ) {
            totalLeftTicket += (fd.getTicketsLeft() - soldCombiTicket);
        }
        return totalLeftTicket;
    }


    public int getSoldCombiTicket() {
        return soldCombiTicket;
    }

    public void setSoldCombiTicket(int soldCombiTicket) {
        this.soldCombiTicket = soldCombiTicket;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FestivalDay[] getDays() {
        return days;
    }

    public void setDays(FestivalDay[] days) {
        this.days = days;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public BigDecimal getDayPrice() {
        return dayPrice;
    }

    public void setDayPrice(BigDecimal dayPrice) {
        this.dayPrice = dayPrice;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
