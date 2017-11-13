package model;

public class Booking {
    private boolean wifi;
    private boolean breakfast;
    private boolean shower;
    private boolean isSpeaker;
    private Company company;
    private Hotel hotel;
    private Companion companion;
    private Participant participant;

    Booking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker) {
        this.wifi = wifi;
        this.breakfast = breaktast;
        this.shower = shower;
        this.isSpeaker = isSpeaker;
    }

    float calcTotalPrice() {
        // TODO
        return -1;
    }

    float calcHotelPrice() {
        // TODO
        return -1;
    }

    Company getCompany() {
        return company;
    }

    void setCompany(Company company) {
        this.company = company;
    }

    Hotel getHotel() {
        return hotel;
    }

    void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    Companion getCompanion() {
        return companion;
    }

    Companion createCompanion(String name) {
        Companion companion = new Companion(name);
        return companion;
    }

    String getCompanionName() {
        return companion.getName();
    }

    Participant getParticipant() {
        return participant;
    }

    void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
