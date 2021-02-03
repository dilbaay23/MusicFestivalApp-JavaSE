package be.btep.teamcappucino.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Employee extends Person{
    private BigDecimal hourlyWage;

    public Employee(String name, String surname, Gender gender, LocalDate dob, String address, String email, BigDecimal hourlyWage) {
        super(name, surname, gender, dob, address, email);
        this.hourlyWage =hourlyWage;
    }

    public Employee() {
    }

    public BigDecimal getHourlyWage() {
        return hourlyWage;
    }

    public void setHourlyWage(BigDecimal hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    @Override
    public String toString() {
        return super.toString() +"Employee{" +
                "hourlyWage=" + hourlyWage +
                '}';
    }
}
