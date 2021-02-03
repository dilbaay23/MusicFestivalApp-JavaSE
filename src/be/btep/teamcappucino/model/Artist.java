package be.btep.teamcappucino.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Artist extends Employee {
    private String instrument;

    public Artist(String name, String surname, Gender gender, LocalDate dob, String address, String email, BigDecimal hourlyWage) {
        super(name, surname, gender, dob, address, email, hourlyWage);
    }

    public Artist() {
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
}
