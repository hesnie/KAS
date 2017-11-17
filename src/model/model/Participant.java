package model.model;

public class Participant {
    private String name;
    private String adress;
    private String town;
    private String country;
    private int phoneNumber;

    public Participant(String name, String adress, int phoneNumber, String town, String country) {
        this.name = name;
        this.adress = adress;
        this.town = town;
        this.country = country;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return name;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public int getPhoneNumber() {
        return phoneNumber;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }
}