package model.model;

public class Company {
    private String name;
    private int phoneNumber;

    public Company(String name, int phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name + " (" + phoneNumber + ")";
    }
}
