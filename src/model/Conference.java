package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Conference {

    private LocalDate dateStart;
    private short duration;
    private float price;
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

    public void createBooking() {
        Booking booking = new Booking(false, false, false, false);
        booking.setHotel(null);
        booking.setCompany(null);
    }

    public void deleteBooking(Booking booking) {
        bookings.remove(booking);
    }

    public ArrayList<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }
}
