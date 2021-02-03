package be.btep.teamcappucino.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class FestivalDay {
    private LocalDate date;
    private LocalTime startTime;
    private int ticketsLeft;
    private Act[] lineup;

    public FestivalDay(LocalDate date, LocalTime startTime, int ticketsLeft, Act[] lineup) {
        this.date = date;
        this.startTime = startTime;
        this.ticketsLeft = ticketsLeft;
        this.lineup = lineup;
    }

    public FestivalDay(LocalDate date, LocalTime startTime, int ticketsLeft) {
        this.date = date;
        this.startTime = startTime;
        this.ticketsLeft = ticketsLeft;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public int getTicketsLeft() {
        return ticketsLeft;
    }

    public void setTicketsLeft(int ticketsLeft) {
        this.ticketsLeft = ticketsLeft;
    }

    public Act[] getLineup() {
        return lineup;
    }

    public void setLineup(Act[] lineup) {
        this.lineup = lineup;
    }
}
