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

    public Booking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker) {
        this.wifi = wifi;
        this.breakfast = breaktast;
        this.shower = shower;
        this.isSpeaker = isSpeaker;
    }

    public float calcTotalPrice() {
        // TODO
        return -1;
    }

    public float calcHotelPrice() {
        // TODO
        return -1;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Companion getCompanion() {
        return companion;
    }

    public Companion createCompanion(String name) {
        Companion companion = new Companion(name);
        return companion;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }
}
