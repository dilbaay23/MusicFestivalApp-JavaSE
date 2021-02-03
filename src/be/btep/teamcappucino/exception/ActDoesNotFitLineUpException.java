package be.btep.teamcappucino.exception;

public class ActDoesNotFitLineUpException extends RuntimeException{


    private final static String MESSAGE = "I have an error for..";

    public ActDoesNotFitLineUpException() {
        super(MESSAGE);
    }
}
