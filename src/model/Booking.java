package model;

public class Booking {
    private boolean wifi;
    private boolean breakfast;
    private boolean shower;
    private boolean isSpeaker;
    private Company company;
    private Hotel hotel;
    private Participant participant;
    private Companion companion;
    private Conference conference;

    Booking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker, Participant participant) {
        this.wifi = wifi;
        this.breakfast = breaktast;
        this.shower = shower;
        this.isSpeaker = isSpeaker;
        this.participant = participant;
    }

    double calcTotalPrice() {
        double price = ((calcHotelPrice() + conference.getPrice()) * conference.getDuration()) + companion.calcPrice();
        return price;
    }

    double calcHotelPrice() {
        double price = 0.0;

        if (wifi) {
            price += hotel.getWifiPrice();
        }
        if (breakfast) {
            price += hotel.getBreakfastPrice();
        }
        if (shower) {
            price += hotel.getShowerPrice();
        }

        if (getCompanion() == null) {
            price += hotel.getPriceSingle();
        } else {
            price += hotel.getPriceDouble();
        }

        return price;
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

    public Conference getConference() {
        return conference;
    }

    public void setConference(Conference conference) {
        this.conference = conference;
    }
}
