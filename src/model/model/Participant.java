package model.model;

public class Participant {
    private String name;
    private String adress;
    private String town;
    private String country;
    private int phoneNumber;

    public Participant(String name, String adress, int phoneNumber) {
        this.name = name;
        this.adress = adress;
        this.town = "";
        this.country = "";
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public int getPhoneNUmber() {
        return phoneNumber;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }
}
