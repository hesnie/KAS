package model.model;

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

    Booking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker, Participant participant,
            Conference conference) {
        this.wifi = wifi;
        this.breakfast = breaktast;
        this.shower = shower;
        this.isSpeaker = isSpeaker;
        this.participant = participant;
        this.conference = conference;
    }

    public double calcTotalPrice() {
        double price = 0.0;
        int duration = conference.getDuration();
        double hotelPricePrDay;

        if (companion != null) {
            price += companion.calcPrice();
        }
        if (isSpeaker) {
            if (hotel != null) {
                hotelPricePrDay = calcHotelPricePrDay();
                price += hotelPricePrDay * (duration - 1);
            }
        } else {
            if (hotel != null) {
                hotelPricePrDay = calcHotelPricePrDay();
                price += hotelPricePrDay * (duration - 1);
            }
            price += conference.getPrice() * duration;
        }
        return price;
    }

    public double calcHotelPricePrDay() {
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
        this.companion = companion;
        return companion;
    }

    public Participant getParticipant() {
        return participant;
    }

    public void setParticipant(Participant participant) {
        this.participant = participant;
    }

    void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    void setBreakfast(boolean breakfast) {
        this.breakfast = breakfast;
    }

    void setShower(boolean shower) {
        this.shower = shower;
    }

    public boolean isSpeaker() {
        return isSpeaker;
    }

    public void setSpeaker(boolean isSpeaker) {
        this.isSpeaker = isSpeaker;
    }
}
