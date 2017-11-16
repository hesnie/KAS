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
    private ArrayList<Tour> tours = new ArrayList<>();

    public Conference(LocalDate dateStart, short duration, double price, ConferenceType conferenceType,
            Location location) {
        this.dateStart = dateStart;
        this.duration = duration;
        this.price = price;
        this.conferenceType = conferenceType;
        this.location = location;
    }

    public Booking createBooking(Participant participant, boolean isSpeaker) {
        Booking booking = new Booking(false, false, false, isSpeaker, participant, this);
        booking.setHotel(null);
        booking.setCompany(null);
        bookings.add(booking);
        return booking;
    }

    public Tour addTour(Tour tour) {
        tours.add(tour);
        return tour;
    }

    public ArrayList<Tour> getTours() {
        return new ArrayList(tours);
    }

    public void setHotelServices(Booking booking, boolean wifi, boolean breakfast, boolean shower) {
        booking.setWifi(wifi);
        booking.setBreakfast(breakfast);
        booking.setShower(shower);
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

    public ConferenceType getConferenceType() {
        return conferenceType;
    }

    public void setConferenceType(ConferenceType conferenceType) {
        this.conferenceType = conferenceType;
    }

    @Override
    public String toString() {
        return conferenceType.getName() + " (" + dateStart + ")";
    }
}
