package model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Conference {

    private LocalDate dateStart;
    private short duration;
    private double price;
    private ConferenceType conferenceType;
    private Location location;
    private ArrayList<Booking> bookings = new ArrayList<>();

    public Conference(LocalDate dateStart, short duration, double price, ConferenceType conferenceType,
            Location location) {
        this.dateStart = dateStart;
        this.duration = duration;
        this.price = price;
        this.conferenceType = conferenceType;
        this.location = location;
    }

    public void createBooking(Participant participant) {
        Booking booking = new Booking(false, false, false, false, participant, this);
        booking.setHotel(null);
        booking.setCompany(null);
        bookings.add(booking);
    }

    public void setHotelServices(int index, boolean wifi, boolean breakfast, boolean shower) {
        bookings.get(index).setWifi(wifi);
        bookings.get(index).setBreakfast(breakfast);
        bookings.get(index).setShower(shower);
    }

    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public double getPrice() {
        return price;
    }

    public short getDuration() {
        return duration;
    }

    public Location getLocation() {
        return location;
    }
}
