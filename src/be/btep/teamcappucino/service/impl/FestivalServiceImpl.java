package be.btep.teamcappucino.service.impl;

import be.btep.teamcappucino.database.FakeDatabase;
import be.btep.teamcappucino.database.UserFakeDao;
import be.btep.teamcappucino.model.*;
import be.btep.teamcappucino.service.FestivalService;

import java.math.BigDecimal;
import java.time.*;

import static be.btep.teamcappucino.utility.KeyboardUtility.*;

import static be.btep.teamcappucino.utility.MenuUtility.*;


public class FestivalServiceImpl implements FestivalService {


    private final static String teamName = " by TEAM CAPPUCINO";
    private final static String projectName = "FESTIVAL MANAGER";
    private final static String wantToContinue = "Do you want to continue? ";

    private UserFakeDao userFakeDao;

    public void setUserFakeDao(UserFakeDao userFakeDao) {
        this.userFakeDao = userFakeDao;
    }

    private FestivalDay chooseDay() {
        return null;
    }

    @Override
    public void registerNewAct( int festivalIndex, FakeDatabase fakeDatabase, UserFakeDao userFakeDao) {
        setUserFakeDao(userFakeDao);
        boolean registering = askYorN("Do you want to register for a new act?");

        String message = "The Days Of The Festivals are ...";
        String[] options = showFestivalDays(festivalIndex, fakeDatabase);
        int index = askForChoice(message, options);
        Festival[] festivals = fakeDatabase.getAllFestivals(userFakeDao);
        Festival[] sortedFestivals = sortFestivals(festivals);
        Festival sortedFestival = sortedFestivals[festivalIndex];

        int year = sortedFestival.getDays()[index].getDate().getYear();
        int month = sortedFestival.getDays()[index].getDate().getMonthValue();
        int day = sortedFestival.getDays()[index].getDate().getDayOfMonth();

        if (registering) {
            String[] talentOptions = {"SoloArtist", "Band"};
            int chooseBandOrSoloArtist = askForChoice("Which Sort of Talent are you?", talentOptions);
            String talentName = ask("what is your talent name?");

            boolean isTalentExist = userFakeDao.checkTalentName(chooseBandOrSoloArtist, talentName);

            if (!isTalentExist) {

                if (chooseBandOrSoloArtist == 0) {

                    Artist artist = registerArtistForAct(fakeDatabase);
                    userFakeDao.addArtist(artist);

                    SoloArtist soloArtist = new SoloArtist(talentName);
                    soloArtist.setArtist(artist);
                    userFakeDao.addSoloArtist(soloArtist);

                    System.out.println();
                } else {
                    int personNumberOfBand = askForInt("How many Artist are there in your group ?");
                    Band band = new Band(talentName);
                    Artist[] artists = new Artist[personNumberOfBand];
                    for (int i = 0; i < personNumberOfBand; i++) {
                        Artist artist1 = registerArtistForAct(fakeDatabase);
                        userFakeDao.addArtist(artist1);
                        System.out.println(thinLine());
                        System.out.println(thinLine());
                        System.out.println(thinLine());
                        if( i < (personNumberOfBand-1))
                            System.out.println("Please enter the next Artist's information ");
                        System.out.println(thinLine());
                        artists[i] = artist1;
                    }
                    band.setMembers(artists);
                    this.userFakeDao.addBand(band);
                }
            }

            createActForTalent(index, festivalIndex, year, month, day, talentName, fakeDatabase);

        }

    }

    private void createActForTalent(int index, int festivalIndex, int year, int month, int day, String talentName, FakeDatabase fakeDatabase) {
        LocalDate localDate = LocalDate.of(year, month, day);

        int minDuration;
        do {
            minDuration = askForInt("What is the duration of your Act?(Please give a duration which is smaller than 121 min!...");

        } while (minDuration > 120);

        Duration duration = Duration.ofMinutes(minDuration);

        Talent talent = fakeDatabase.getTalentByName(talentName);

        LocalTime newActTime = null;
        if (index == 0) {
            for (int i = 0; i < fakeDatabase.freeTimesForfestivalDay1.length; i++) {
                if (fakeDatabase.freeTimesForfestivalDay1[i] != null) {
                    newActTime = fakeDatabase.freeTimesForfestivalDay1[i];
                    fakeDatabase.freeTimesForfestivalDay1[i] = null;
                    break;
                }

            }

            if (newActTime != null) {
                LocalDateTime newActLocalDateTime = LocalDateTime.of(localDate, newActTime);
                Act act = new Act(newActLocalDateTime, duration, talent);
                registerAct(act, festivalIndex, fakeDatabase);
            } else {
                System.out.println("We have no open time slot...");
                boolean choose = askYorN("Do you want to choose another day...");
                if (choose) registerNewAct( festivalIndex, fakeDatabase, userFakeDao);
                else {
                    quit();
                }

            }

        } else if (index == 1) {
            for (int i = 0; i < fakeDatabase.freeTimesForfestivalDay2.length; i++) {
                if (fakeDatabase.freeTimesForfestivalDay2[i] != null) {
                    newActTime = fakeDatabase.freeTimesForfestivalDay2[i];
                    fakeDatabase.freeTimesForfestivalDay2[i] = null;
                    break;
                }

            }
            if (newActTime != null) {
                LocalDateTime newActLocalDateTime = LocalDateTime.of(localDate, newActTime);
                Act act = new Act(newActLocalDateTime, duration, talent);
                registerAct(act, festivalIndex, fakeDatabase);
            } else {
                System.out.println("We have no open time slot...");
                boolean choose = askYorN("Do you want to choose another day...");
                if (choose) registerNewAct( festivalIndex, fakeDatabase, userFakeDao);
                else {
                    quit();
                }
            }

        } else if (index == 2) {
            for (int i = 0; i < fakeDatabase.freeTimesForfestivalDay3.length; i++) {
                if (fakeDatabase.freeTimesForfestivalDay3[i] != null) {
                    newActTime = fakeDatabase.freeTimesForfestivalDay3[i];
                    fakeDatabase.freeTimesForfestivalDay3[i] = null;
                    break;
                }

            }
            if (newActTime != null) {
                LocalDateTime newActLocalDateTime = LocalDateTime.of(localDate, newActTime);
                Act act = new Act(newActLocalDateTime, duration, talent);
                registerAct(act, festivalIndex, fakeDatabase);
            } else {
                System.out.println("We have no open time slot...");
                boolean choose = askYorN("Do you want to choose another day...");
                if (choose) registerNewAct(festivalIndex, fakeDatabase, userFakeDao);
                else {

                }

            }

        }
    }

    private Artist registerArtistForAct(FakeDatabase fakeDatabase) {
        Artist artist = new Artist();
        System.out.println(thickLine());
        System.out.println(thinLine());
        System.out.println(center("Welcome REGISTER ARTIST Page"));
        System.out.println(thinLine());
        System.out.println(thickLine());

        artist.setName(ask("Enter your name: "));
        artist.setSurname(ask("Enter your surname: "));
        artist.setGender(askForGender("Enter your gender: "));
        artist.setDob(askForFullDate("Enter your birthday:"));
        artist.setAddress(ask("Enter your address: "));
        artist.setEmail(askForEmail("Enter your email address: "));
        double hourlyWage = askForDouble("Enter Your hourlyWage");
        BigDecimal hw = BigDecimal.valueOf(hourlyWage);
        artist.setHourlyWage(hw);

        return artist;
    }

    public String[] showFestivalDays(int festivalIndex, FakeDatabase fakeDatabase) {
        String[] dayMonth = new String[3];
        if (festivalIndex == 0) {
            int i = 0;
            FestivalDay[] getFestivalDays = fakeDatabase.getAllFestivalDaysForFestival2();
            for (FestivalDay getFestivalDay : getFestivalDays) {
                String month = getFestivalDay.getDate().getMonth().toString();
                int day = getFestivalDay.getDate().getDayOfMonth();
                int year = getFestivalDay.getDate().getYear();
                dayMonth[i] = day + " " + month + " " + year;
                i++;
            }
        } else if (festivalIndex == 1) {
            int i = 0;
            FestivalDay[] getFestivalDays = fakeDatabase.getAllFestivalDaysForFestival1();
            for (FestivalDay getFestivalDay : getFestivalDays) {
                String month = getFestivalDay.getDate().getMonth().toString();
                int day = getFestivalDay.getDate().getDayOfMonth();
                int year = getFestivalDay.getDate().getYear();
                dayMonth[i] = day + " " + month + " " + year;
                i++;
            }
        } else {
            FestivalDay[] getFestivalDays = fakeDatabase.getAllFestivalDaysForFestival3();
            int i = 0;
            for (FestivalDay getFestivalDay : getFestivalDays) {
                String month = getFestivalDay.getDate().getMonth().toString();
                int day = getFestivalDay.getDate().getDayOfMonth();
                int year = getFestivalDay.getDate().getYear();
                dayMonth[i] = day + " " + month + " " + year;
                i++;
            }
        }
        return dayMonth;


    }

    /*private boolean isFree(LocalDateTime askForDate, int minDuration, int festivalIndex) {
        if (festivalIndex == 0) {
            Act[] sortedActs = fakeDatabase.getAllActsForFestival1();
            int newDay = askForDate.getDayOfMonth();

            int count = 0;
            for (Act sortedAct : sortedActs) {
                if (newDay == sortedAct.getStartTime().getDayOfMonth()) {
                    count++;
                }
            }

            Act[] selectedDayActs = new Act[count];
            for (int i = 0; i < sortedActs.length; i++) {
                if (newDay == sortedActs[i].getStartTime().getDayOfMonth()) {
                    selectedDayActs[i] = sortedActs[i];
                }
            }
            Act[] sortedActsOfDay = sortActs(selectedDayActs);

            int i = count - 1;

            int year = sortedActsOfDay[i].getStartTime().getYear();
            int newYear = askForDate.getYear();
            if (year != newYear) {
                return false;
            }

            int month = sortedActsOfDay[i].getStartTime().getMonthValue();
            int newMonth = askForDate.getMonthValue();
            if (month != newMonth) {
                return false;
            }

            int newStartHour = askForDate.getHour();
            int newStartMin = askForDate.getMinute();

            int startHour = sortedActsOfDay[i].getStartTime().getHour();

            LocalDateTime endOfAct = getEndTimeOfAct(sortedActsOfDay[i]);
            int endHour = endOfAct.getHour();
            int endMin = endOfAct.getMinute();

            LocalDateTime askForDateEnd = askForDate.plusMinutes(minDuration);
            int endHourNew = askForDateEnd.getHour();
            int endHourMin = askForDateEnd.getMinute();


            if (newStartHour < endHour) {
                return false;
            } else if (newStartHour == endHour) {
                if (newStartMin < endMin) {
                    return false;
                } else
                    return true;

            } else {
                if (endHourNew < 24 && endHourNew > startHour) {
                    return true;
                }
            }

        }

        return false;
    }*/


    @Override
    public void registerAct(Act act, int festivalIndex, FakeDatabase fakeDatabase) {
        if (festivalIndex == 0) {
            Act[] acts = fakeDatabase.updatedActArrayForFestival1(act);
            Act[] sortedActs = sortActs(acts);
            showDetailsOfActs(sortedActs, fakeDatabase);
        } else if (festivalIndex == 1) {
            Act[] acts = fakeDatabase.updatedActArrayForFestival2(act);
            Act[] sortedActs = sortActs(acts);
            showDetailsOfActs(sortedActs, fakeDatabase);
        } else {
            Act[] acts = fakeDatabase.updatedActArrayForFestival3(act);
            Act[] sortedActs = sortActs(acts);
            showDetailsOfActs(sortedActs, fakeDatabase);
        }

    }

    @Override
    public void setFestival(Festival festival, FakeDatabase fakeDatabase) {

    }


    @Override
    public void buyTickets(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao) {
        setUserFakeDao(userFakeDao);
    }

    @Override
    public void viewBalanceSheet(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao) {
        setUserFakeDao(userFakeDao);
        if (isManager) {
            if (index == 0) {
                double expense = fakeDatabase.calculateExpenseFestival1();
                double incomes = fakeDatabase.calculateIncomesFestival1();
                System.out.println(tildaLine());
                System.out.println(tildaLine());

                System.out.println("The expense of the selected Festival is = " + expense);
                System.out.println(thinLine());
                System.out.println("The incomes of the selected Festival is = " + incomes);
                System.out.println(thinLine());
                System.out.println("The PROFIT of the selected Festival is = " + (incomes - expense));
                System.out.println(tildaLine());
                System.out.println(tildaLine());

            } else if (index == 1) {
                double expense = fakeDatabase.calculateExpenseFestival2();
                double incomes = fakeDatabase.calculateIncomesFestival2();
                System.out.println(tildaLine());
                System.out.println(tildaLine());
                System.out.println("The EXPENSE of the selected Festival is = " + expense);
                System.out.println(thinLine());
                System.out.println("The INCOMES of the selected Festival is = " + incomes);
                System.out.println(thinLine());
                System.out.println("The PROFIT of the selected Festival is = " + (incomes - expense));
                System.out.println(tildaLine());
                System.out.println(tildaLine());

            } else {
                double expense = fakeDatabase.calculateExpenseFestival3();
                double incomes = fakeDatabase.calculateIncomesFestival3();
                System.out.println(tildaLine());
                System.out.println(tildaLine());
                System.out.println("The expense of the selected Festival is = " + expense);
                System.out.println(thinLine());
                System.out.println("The incomes of the selected Festival is = " + incomes);
                System.out.println(thinLine());
                System.out.println("The PROFIT of the selected Festival is = " + (incomes - expense));
                System.out.println(tildaLine());
                System.out.println(tildaLine());

            }
        } else {
            System.out.println(tildaLine());
            System.out.println(tildaLine());
            System.out.println(center("403 FORBIDDEN"));
            System.out.println(center("Only The Event Manager can see this Page!!!"));
            System.out.println(tildaLine());
            System.out.println(tildaLine());
        }


    }

    @Override
    public void displayLineUp(boolean isManager, int index, FakeDatabase fakeDatabase, UserFakeDao userFakeDao) {
        setUserFakeDao(userFakeDao);
        if (index == 0) {

            Act[] acts = fakeDatabase.getActs2();
            thickLine();
            showDetailsOfActs(acts, fakeDatabase);

        } else if (index == 1) {

            Act[] acts = fakeDatabase.getActs1();
            thickLine();
            showDetailsOfActs(acts, fakeDatabase);

        } else {

            Act[] acts = fakeDatabase.getActs3();
            thickLine();
            showDetailsOfActs(acts, fakeDatabase);

        }
    }


    public LocalDateTime getEndTimeOfAct(Act act) {

        Long duration = act.getDuration().toMinutes();
        LocalDateTime finishTime = act.getStartTime().plusMinutes(duration);
        return finishTime;
    }

    public void showDetailsOfActs(Act[] acts, FakeDatabase fakeDatabase) {

        String firstDay = "";
        for (int i = 0; i < acts.length; i++) {

            String day = acts[i].getStartTime().getDayOfWeek().toString();
            if (day != firstDay) {
                System.out.println(thickLine());
                System.out.println(center(day));
                firstDay = day;
            }

            System.out.println(thinLine());
            LocalDateTime ldt = getEndTimeOfAct(acts[i]);
            int startHour = acts[i].getStartTime().getHour();
            int startMin = acts[i].getStartTime().getMinute();
            int endHour = ldt.getHour();
            int endMin = ldt.getMinute();
            String talentName = acts[i].getTalent().getName();

            System.out.println("[" + startHour + ":" + startMin + "-" + endHour + ":" + endMin + "] " + talentName);
            System.out.println(thinLine());

        }
    }

    public Act[] sortActs(Act[] acts) {
        Act[] sortedActs = new Act[acts.length];
        int a;
        for (int i = 0; i < acts.length; i++) {
            int count = 0;
            LocalDateTime ldt = acts[i].getStartTime();
            for (int j = 0; j < acts.length; j++) {
                LocalDateTime ldt1 = acts[j].getStartTime();
                a = ldt.compareTo(ldt1);
                if (a > 0) {
                    count++;
                }
            }
            sortedActs[count] = acts[i];
        }
        return sortedActs;
    }

    public Festival[] sortFestivals(Festival[] festivals) {
        Festival[] sortedFestivals = new Festival[festivals.length];
        int a;

        for (int i = 0; i < festivals.length; i++) {
            int count = 0;
            LocalDate ld = festivals[i].getDays()[0].getDate();
            for (int j = 0; j < festivals.length; j++) {
                LocalDate ld1 = festivals[j].getDays()[0].getDate();
                a = ld.compareTo(ld1);
                if (a > 0) {
                    count++;
                }
            }
            sortedFestivals[count] = festivals[i];
        }
        return sortedFestivals;
    }

    public void quit() {
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(center(projectName));
        System.out.println(center(teamName));
        System.out.println(center("*** GOODBYE :) ***"));
        System.out.println(center("*** SEE YOU LATER ***"));
        System.out.println(tildaLine());
        System.out.println(tildaLine());
        System.out.println(tildaLine());

    }

}
