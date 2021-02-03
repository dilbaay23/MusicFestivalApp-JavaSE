package be.btep.teamcappucino.model;

import java.time.LocalDate;

public class Customer extends Person {

    private Long id;
    private String password;
    private boolean isManager;

    public Customer() {
        super();
    }

    public Customer(String name, String surname, Gender gender, LocalDate dob, String address, String email, Long id, String password) {
        super(name, surname, gender, dob, address, email);
        this.id= id;
        this.password= password;
    }

    public boolean isManager() {
        return isManager;
    }

    public void setManager(boolean manager) {
        isManager = manager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return super.toString() + "Customer{" +
                "id=" + id +
                ", password='" + password + '\'' +
                '}';
    }
}
