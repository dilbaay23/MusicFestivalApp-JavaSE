package be.btep.teamcappucino.exception;

public class IllegalEmailException extends RuntimeException{
    public IllegalEmailException(String message) {
        super(message);
    }
}

