package be.btep.teamcappucino.database;


import be.btep.teamcappucino.model.*;

import java.math.BigDecimal;
import java.time.*;

public class FakeDatabase {


    private UserFakeDao userFakeDao;

    public void setUserFakeDao(UserFakeDao userFakeDao) {
        this.userFakeDao = userFakeDao;
    }

    private Act[] acts1;
    private Act[] acts2;
    private Act[] acts3;

    public LocalTime[] freeTimesForfestivalDay1 = {LocalTime.of(17, 0), LocalTime.of(22, 30), LocalTime.of(22, 0)};
    public LocalTime[] freeTimesForfestivalDay2 = {LocalTime.of(17, 0), LocalTime.of(19, 30), LocalTime.of(22, 0)};
    public LocalTime[] freeTimesForfestivalDay3 = {LocalTime.of(17, 0), LocalTime.of(19, 30), LocalTime.of(22, 0)};


    public void setActs1(Act[] acts1) {
        this.acts1 = acts1;
    }

    public void setActs2(Act[] acts2) {
        this.acts2 = acts2;
    }

    public void setActs3(Act[] acts3) {
        this.acts3 = acts3;
    }

    public Act[] getActs1() {
        if (acts1 == null)
            setActs1(getAllActsForFestival1());
        return acts1;
    }

    public Act[] getActs2() {
        if (acts2 == null)
            setActs2(getAllActsForFestival2());
        return acts2;
    }

    public Act[] getActs3() {
        if (acts3 == null)
            setActs3(getAllActsForFestival3());
        return acts3;
    }

    public Festival[] getAllFestivals(UserFakeDao userFakeDao) {
        setUserFakeDao(userFakeDao);
        Festival festival1 = new Festival();
        Festival festival2 = new Festival();
        Festival festival3 = new Festival();

        Venue venue1 = new Venue("VenueAdress1", 2000);
        Venue venue2 = new Venue("VenueAdress2", 2500);
        Venue venue3 = new Venue("VenueAdress3", 2000);


        festival1.setName("Tommorowland 2021");
        festival1.setDays(getAllFestivalDaysForFestival1());
        festival1.setDayPrice(new BigDecimal(100));
        festival1.setGenre(Genre.TECHNO);
        festival1.setVenue(venue1);

        festival2.setName("Pukkelpop 2021");
        festival2.setDays(getAllFestivalDaysForFestival2());
        festival2.setDayPrice(new BigDecimal(90));
        festival2.setGenre(Genre.POP);
        festival2.setVenue(venue2);

        festival3.setName("Rock Werchter 2021");
        festival3.setDays(getAllFestivalDaysForFestival3());
        festival3.setDayPrice(new BigDecimal(80));
        festival3.setGenre(Genre.ROCK);
        festival3.setVenue(venue3);

        Festival[] festivals = {festival1, festival2, festival3};
        return festivals;
    }

    public int calculateIncomesFestival1() {

        int soldDailyTicketIncomes = (getAllFestivals(userFakeDao)[1].getVenue().getCapacity() - getAllFestivals(userFakeDao)[1].totalLeftticket()) * 100;

        int combiTicketIncomes = getAllFestivals(userFakeDao)[1].getSoldCombiTicket() * 3 * (getAllFestivals(userFakeDao)[1].getDayPrice().intValue() * 60 / 100);

        return soldDailyTicketIncomes + combiTicketIncomes;

    }
    public int calculateIncomesFestival2() {

        int soldDailyTicketIncomes = (getAllFestivals(userFakeDao)[0].getVenue().getCapacity() - getAllFestivals(userFakeDao)[0].totalLeftticket()) * 100;

        int combiTicketIncomes = getAllFestivals(userFakeDao)[0].getSoldCombiTicket() * 3 * (getAllFestivals(userFakeDao)[0].getDayPrice().intValue() * 60 / 100);

        return soldDailyTicketIncomes + combiTicketIncomes;

    }
    public int calculateIncomesFestival3() {

        int soldDailyTicketIncomes = (getAllFestivals(userFakeDao)[2].getVenue().getCapacity() - getAllFestivals(userFakeDao)[2].totalLeftticket()) * 100;

        int combiTicketIncomes = getAllFestivals(userFakeDao)[2].getSoldCombiTicket() * 3 * (getAllFestivals(userFakeDao)[2].getDayPrice().intValue() * 60 / 100);

        return soldDailyTicketIncomes + combiTicketIncomes;

    }


    public double calculateExpenseFestival1() {

        double hour;

        double totalCostOfFestival = 0;

        Act[] acts = getActs2();
        for (Act act : acts) {
            double totalCostOfAct = 0;
            String nameTalent = act.getTalent().getName();
            hour = act.getDuration().toHours();

            SoloArtist[] soloArtists = userFakeDao.getSoloArtists();


            for (SoloArtist soloArtist : soloArtists) {
                if (soloArtist != null && soloArtist.getName().equals(nameTalent)) {
                    int hwOfArtist = soloArtist.getArtist().getHourlyWage().intValue();
                    totalCostOfAct += hour * hwOfArtist;
                }
            }

            Band[] bands = userFakeDao.getBands();
            for (Band band : bands) {

                if (band != null && band.getName().equals(nameTalent))
                    for (Artist member : band.getMembers()) {
                        int hwOfArtist = member.getHourlyWage().intValue();
                        totalCostOfAct += hour * hwOfArtist;
                    }
            }
            totalCostOfFestival += totalCostOfAct;

        }

        return totalCostOfFestival;
    }

    public double calculateExpenseFestival2() {
        double hour;

        double totalCostOfFestival = 0;

        Act[] acts = getActs1();
        for (Act act : acts) {
            double totalCostOfAct = 0;
            String nameTalent = act.getTalent().getName();
            hour = act.getDuration().toHours();

            SoloArtist[] soloArtists = userFakeDao.getSoloArtists();


            for (SoloArtist soloArtist : soloArtists) {
                if (soloArtist != null && soloArtist.getName().equals(nameTalent)) {
                    int hwOfArtist = soloArtist.getArtist().getHourlyWage().intValue();
                    totalCostOfAct += hour * hwOfArtist;
                }
            }

            Band[] bands = userFakeDao.getBands();
            for (Band band : bands) {

                if (band != null && band.getName().equals(nameTalent))
                    for (Artist member : band.getMembers()) {
                        int hwOfArtist = member.getHourlyWage().intValue();
                        totalCostOfAct += hour * hwOfArtist;
                    }
            }
            totalCostOfFestival += totalCostOfAct;

        }

        return totalCostOfFestival;
    }

    public double calculateExpenseFestival3() {
        double hour;

        double totalCostOfFestival = 0;

        Act[] acts = getActs3();
        for (Act act : acts) {
            double totalCostOfAct = 0;
            String nameTalent = act.getTalent().getName();
            hour = act.getDuration().toHours();

            SoloArtist[] soloArtists = userFakeDao.getSoloArtists();


            for (SoloArtist soloArtist : soloArtists) {
                if (soloArtist != null && soloArtist.getName().equals(nameTalent)) {
                    int hwOfArtist = soloArtist.getArtist().getHourlyWage().intValue();
                    totalCostOfAct += hour * hwOfArtist;
                }
            }

            Band[] bands = userFakeDao.getBands();
            for (Band band : bands) {

                if (band != null && band.getName().equals(nameTalent))
                    for (Artist member : band.getMembers()) {
                        int hwOfArtist = member.getHourlyWage().intValue();
                        totalCostOfAct += hour * hwOfArtist;
                    }
            }
            totalCostOfFestival += totalCostOfAct;

        }

        return totalCostOfFestival;
    }


    public FestivalDay[] getAllFestivalDaysForFestival1() {
        FestivalDay festivalDay1 = new FestivalDay(LocalDate.of(2021, 7, 5), LocalTime.of(06, 0), 300);
        FestivalDay festivalDay2 = new FestivalDay(LocalDate.of(2021, 7, 6), LocalTime.of(06, 0), 370);
        FestivalDay festivalDay3 = new FestivalDay(LocalDate.of(2021, 7, 7), LocalTime.of(06, 0), 325);
        FestivalDay[] festivalDays = {festivalDay1, festivalDay2, festivalDay3};
        return festivalDays;
    }

    public FestivalDay[] getAllFestivalDaysForFestival2() {
        FestivalDay festivalDay1 = new FestivalDay(LocalDate.of(2021, 6, 5), LocalTime.of(6, 0), 360);
        FestivalDay festivalDay2 = new FestivalDay(LocalDate.of(2021, 6, 6), LocalTime.of(6, 0), 230);
        FestivalDay festivalDay3 = new FestivalDay(LocalDate.of(2021, 6, 7), LocalTime.of(6, 0), 180);
        FestivalDay[] festivalDays = {festivalDay1, festivalDay2, festivalDay3};
        return festivalDays;
    }

    public FestivalDay[] getAllFestivalDaysForFestival3() {
        FestivalDay festivalDay1 = new FestivalDay(LocalDate.of(2021, 8, 5), LocalTime.of(8, 0), 270);
        FestivalDay festivalDay2 = new FestivalDay(LocalDate.of(2021, 8, 6), LocalTime.of(8, 0), 300);
        FestivalDay festivalDay3 = new FestivalDay(LocalDate.of(2021, 8, 7), LocalTime.of(8, 0), 290);
        FestivalDay[] festivalDays = {festivalDay1, festivalDay2, festivalDay3};
        return festivalDays;
    }

    public String[] serveFestivalDetails(UserFakeDao userFakeDao) {
        String[] festivalDetails = {"View Line Up", "Register new act", "Buy tickets", "View balance sheet", "Quit"};
        return festivalDetails;
    }

    public Talent[] getALlTalents() {

        Talent[] soloArtists = userFakeDao.getSoloArtists();
        Talent[] bands = userFakeDao.getBands();
        Talent[] talents = new Talent[bands.length + soloArtists.length];
        int index = 0;
        for (int i = 0; i < soloArtists.length; i++) {
            if (soloArtists[i] != null) {
                talents[index++] = soloArtists[i];

            }
        }

        for (int i = 0; i < bands.length; i++) {
            if (bands[i] != null)
                talents[index++] = bands[i];
        }

        return talents;
    }

    public Talent getTalentByName(String talentName) {
        for (Talent talent : getALlTalents()) {
            if (talent != null && talent.getName().equals(talentName)) {
                return talent;
            }
        }
        return null;
    }

    public Act[] getAllActsForFestival1() {
        Act act1 = new Act(LocalDateTime.of(2021, 6, 5, 10, 30, 00), Duration.ofMinutes(90), getALlTalents()[0]);
        Act act2 = new Act(LocalDateTime.of(2021, 6, 5, 12, 30, 00), Duration.ofMinutes(120), getALlTalents()[1]);
        Act act3 = new Act(LocalDateTime.of(2021, 6, 5, 15, 30, 00), Duration.ofMinutes(60), getALlTalents()[2]);

        Act act4 = new Act(LocalDateTime.of(2021, 6, 6, 10, 00, 00), Duration.ofMinutes(90), getALlTalents()[3]);
        Act act5 = new Act(LocalDateTime.of(2021, 6, 6, 12, 30, 00), Duration.ofMinutes(120), getALlTalents()[2]);
        Act act6 = new Act(LocalDateTime.of(2021, 6, 6, 15, 30, 00), Duration.ofMinutes(60), getALlTalents()[4]);

        Act act7 = new Act(LocalDateTime.of(2021, 6, 7, 10, 30, 00), Duration.ofMinutes(120), getALlTalents()[5]);
        Act act8 = new Act(LocalDateTime.of(2021, 6, 7, 13, 00, 00), Duration.ofMinutes(100), getALlTalents()[1]);
        Act[] acts = {act1, act2, act3, act4, act5, act6, act7, act8};
        setActs1(acts);
        return getActs1();
    }

    public Act[] getAllActsForFestival2() {
        Act act1 = new Act(LocalDateTime.of(2021, 7, 5, 8, 30, 00), Duration.ofMinutes(120), getALlTalents()[0]);
        Act act2 = new Act(LocalDateTime.of(2021, 7, 5, 11, 30, 00), Duration.ofMinutes(90), getALlTalents()[1]);
        Act act3 = new Act(LocalDateTime.of(2021, 7, 5, 14, 00, 00), Duration.ofMinutes(120), getALlTalents()[2]);

        Act act4 = new Act(LocalDateTime.of(2021, 7, 6, 10, 30, 00), Duration.ofMinutes(60), getALlTalents()[3]);
        Act act5 = new Act(LocalDateTime.of(2021, 7, 6, 13, 30, 00), Duration.ofMinutes(120), getALlTalents()[4]);

        Act act6 = new Act(LocalDateTime.of(2021, 7, 7, 10, 30, 00), Duration.ofMinutes(100), getALlTalents()[1]);
        Act act7 = new Act(LocalDateTime.of(2021, 7, 7, 13, 30, 00), Duration.ofMinutes(90), getALlTalents()[5]);
        Act act8 = new Act(LocalDateTime.of(2021, 7, 7, 15, 30, 00), Duration.ofMinutes(60), getALlTalents()[3]);
        Act[] acts = {act1, act2, act3, act4, act5, act6, act7, act8};
        setActs2(acts);
        return getActs2();
    }

    public Act[] getAllActsForFestival3() {
        Act act1 = new Act(LocalDateTime.of(2021, 8, 5, 9, 30, 00), Duration.ofMinutes(120), getALlTalents()[0]);
        Act act2 = new Act(LocalDateTime.of(2021, 8, 5, 12, 0, 00), Duration.ofMinutes(90), getALlTalents()[1]);
        Act act3 = new Act(LocalDateTime.of(2021, 8, 5, 14, 30, 00), Duration.ofMinutes(120), getALlTalents()[2]);

        Act act4 = new Act(LocalDateTime.of(2021, 8, 6, 10, 30, 00), Duration.ofMinutes(60), getALlTalents()[5]);
        Act act5 = new Act(LocalDateTime.of(2021, 8, 6, 12, 0, 00), Duration.ofMinutes(90), getALlTalents()[4]);
        Act act6 = new Act(LocalDateTime.of(2021, 8, 6, 14, 0, 00), Duration.ofMinutes(100), getALlTalents()[2]);

        Act act7 = new Act(LocalDateTime.of(2021, 8, 7, 10, 30, 00), Duration.ofMinutes(90), getALlTalents()[5]);
        Act act8 = new Act(LocalDateTime.of(2021, 8, 7, 12, 30, 00), Duration.ofMinutes(120), getALlTalents()[3]);
        Act[] acts = {act1, act2, act3, act4, act5, act6, act7, act8};
        setActs3(acts);
        return getActs3();
    }

    public Act[] updatedActArrayForFestival1(Act act) {
        Act[] acts = getActs1();
        Act[] updatedArray = new Act[(acts.length + 1)];
        for (int i = 0; i < acts.length; i++) {
            updatedArray[i] = acts[i];
        }
        updatedArray[updatedArray.length - 1] = act;
        setActs1(updatedArray);
        return getActs1();
    }

    public Act[] updatedActArrayForFestival2(Act act) {
        Act[] acts = getActs2();
        Act[] updatedArray = new Act[acts.length + 1];
        for (int i = 0; i < acts.length; i++) {
            updatedArray[i] = acts[i];
        }
        updatedArray[updatedArray.length - 1] = act;
        setActs2(updatedArray);
        return getActs2();
    }

    public Act[] updatedActArrayForFestival3(Act act) {
        Act[] acts = getActs3();
        Act[] updatedArray = new Act[acts.length + 1];
        for (int i = 0; i < acts.length; i++) {
            updatedArray[i] = acts[i];
        }
        updatedArray[updatedArray.length - 1] = act;
        setActs3(updatedArray);
        return getActs3();
    }


}
