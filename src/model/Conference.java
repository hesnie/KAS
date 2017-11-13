package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Conference {

    private LocalDate dateStart;
    private short duration;
    private double price;
    private ConferenceType conferenceType;
    private Location location;
    private ArrayList<Booking> bookings;

    public Conference(LocalDate dateStart, short duration, float price, ConferenceType conferenceType,
            Location location) {
        this.dateStart = dateStart;
        this.duration = duration;
        this.price = price;
        this.conferenceType = conferenceType;
        this.location = location;
    }

    public void createBooking(Participant participant) {
        Booking booking = new Booking(false, false, false, false, participant);
        booking.setHotel(null);
        booking.setCompany(null);
        booking.setConference(this);

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
}
