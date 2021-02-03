package be.btep.teamcappucino.utility;

import be.btep.teamcappucino.exception.IllegalDateException;
import be.btep.teamcappucino.exception.IllegalEmailException;
import be.btep.teamcappucino.model.Gender;

import java.time.*;
import java.util.Scanner;

public abstract class KeyboardUtility {
    private static final String INVALID_MSG = "Invalid input! Please try again...";
    public static Scanner KEYBOARD;
    static {KEYBOARD = new Scanner(System.in);}

    public static int askForInt(String message){
        while (true) {
            String input = ask(message);
            try {
                return Integer.parseInt(input);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
            }
        }
    }

    public static boolean askYorN(String message){
        while (true) {
            String input = ask(message + "(y/n)");
            char firstLetter = '0';  // yes no y n other YES NO Y ohyes
            try {
                firstLetter = input.toLowerCase().charAt(0);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
                continue;
            }
            switch (firstLetter) {
                case 'y':
                    return true;
                case 'n':
                    return false;
                default:
                    break;
            }
        }
    }

    public static String ask(String message){
        System.out.println(message);
        return KEYBOARD.nextLine();
    }

    public static Gender askForGender(String message){
        while (true) {
            String input = ask(message + "(m/f/o)");
            char firstLetter = '0';
            try {
                firstLetter = input.toLowerCase().charAt(0);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
                continue;
            }
            switch (firstLetter) {
                case 'm':
                    return Gender.MALE;
                case 'f':
                    return Gender.FEMALE;
                case 'o':
                    return Gender.OTHER;
                default:
                    System.out.println(INVALID_MSG);
                    break;
            }
        }

    }

    public static LocalDate askForFullDate(String datum){
        while (true) {
            System.out.println(datum);
            int inputYear = askForInt("Year:");
            try {
                if (inputYear < 1900 || inputYear > LocalDate.now().getYear()) {
                    throw new IllegalDateException("Year must be >1900 and before this year");
                }
                int inputMonth = askForInt("Month (1-12):");
                if (inputMonth > 12 || inputMonth < 1) {
                    throw new IllegalDateException("Month must be between 1 and 12");
                }
                int daysInMonth = Month.of(inputMonth).length(Year.isLeap(inputYear));
                int inputDay = askForInt("Day (1-" + daysInMonth + "):");
                if (inputDay > daysInMonth || inputDay < 1) {
                    throw new IllegalDateException("Day must be between 1 and " + daysInMonth + " for " + Month.of(inputMonth));
                }
                return LocalDate.of(inputYear, inputMonth, inputDay);
            } catch (IllegalDateException ide) {
                System.out.println(INVALID_MSG);
                System.out.println(ide.getMessage());
            }
        }
    }
    public static LocalDateTime askForFullDateTime(String datum){
        while (true) {
            System.out.println(datum);
            int inputYear = askForInt("Year:");
            try {
                if (inputYear < 1900 || inputYear > LocalDate.now().getYear()) {
                    throw new IllegalDateException("Year must be >1900 and before this year");
                }
                int inputMonth = askForInt("Month (1-12):");
                if (inputMonth > 12 || inputMonth < 1) {
                    throw new IllegalDateException("Month must be between 1 and 12");
                }
                int daysInMonth = Month.of(inputMonth).length(Year.isLeap(inputYear));
                int inputDay = askForInt("Day (1-" + daysInMonth + "):");
                if (inputDay > daysInMonth || inputDay < 1) {
                    throw new IllegalDateException("Day must be between 1 and " + daysInMonth + " for " + Month.of(inputMonth));
                }

                int inputHour = askForInt("Hour:");
                if (inputHour < 0 || inputHour > 23) {
                    throw new IllegalDateException("Hour must be between 0 and 23.59");
                }
                int inputMinutes = askForInt("Minutes");
                if (inputMinutes > 59 || inputMinutes< 0) {
                    throw new IllegalDateException("Minutes must be between 0 and 59");
                }
                return LocalDateTime.of(inputYear, inputMonth, inputDay,inputHour,inputMinutes);
            } catch (IllegalDateException ide) {
                System.out.println(INVALID_MSG);
                System.out.println(ide.getMessage());
            }
        }
    }
    public static LocalTime askForFullTime(String message){
        while (true) {
            System.out.println(message);

            try {
                int inputHour = askForInt("Hour:");
                if (inputHour < 0 || inputHour > 23) {
                    throw new IllegalDateException("Hour must be between 0 and 23.59");
                }
                int inputMinutes = askForInt("Minutes");
                if (inputMinutes > 59 || inputMinutes< 0) {
                    throw new IllegalDateException("Minutes must be between 0 and 59");
                }
                return LocalTime.of(inputHour, inputMinutes);
            } catch (IllegalDateException ide) {
                System.out.println(INVALID_MSG);
                System.out.println(ide.getMessage());
            }
        }
    }


    public static String askForEmail(String message){
        while (true) {
            try {
                String input = ask(message);  //null
                final String EMAIL_PATTERN = "^[A-Za-z0-9+_.-]+@(.+)$";
                if (input.matches(EMAIL_PATTERN)) {
                    return input;
                } else {
                    throw new IllegalEmailException("Email must be of pattern: user@domain.com");
                }
            } catch (IllegalEmailException iee) {
                System.out.println(INVALID_MSG);
                System.out.println(iee.getMessage());
            }
        }
    }


    public static double askForDouble(String message){
        while (true) {
            String input = ask(message);
            try {
                return Double.parseDouble(input);
            } catch (Exception e) {
                System.out.println(INVALID_MSG);
            }
        }
    }

    public static int askForChoice(String message, String[] options){
        System.out.println(message);
        return askForChoice(options);
    }

    public static int askForChoice(String[] options){
        while (true) {
            for (int i = 0; i < options.length; i++) {
                System.out.printf("%d. %s%n", i + 1, options[i]);

            }
            int chosenIdx = askForInt(String.format("Enter your choice (1-%d):", options.length)) - 1;
            if (chosenIdx < 0 || chosenIdx >= options.length){
                System.out.println(INVALID_MSG);
                System.out.println("Please enter a choice in the valid range");
            } else {
                return chosenIdx;
            }
        }
    }


}


