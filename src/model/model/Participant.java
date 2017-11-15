package model.model;

public class Participant {
    private String name;
    private String adress;
    private String town;
    private String country;
    private short phoneNUmber;

    public Participant(String name, String adress, short phoneNumber) {
        this.name = name;
        this.adress = adress;
        this.town = "";
        this.country = "";
        this.phoneNUmber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public String getAdress() {
        return adress;
    }

    public short getPhoneNUmber() {
        return phoneNUmber;
    }

    public String getTown() {
        return town;
    }

    public String getCountry() {
        return country;
    }
}
