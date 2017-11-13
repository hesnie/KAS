package model.model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Companion {
    private String name;
    private ArrayList<BookingTour> bookingTours = new ArrayList<>();

    Companion(String name) {
        this.name = name;
    }

    public BookingTour createBookingTour(Tour tour, LocalDate date) {
        BookingTour b = new BookingTour(tour, date);
        bookingTours.add(b);
        return b;
    }

    public ArrayList<BookingTour> getBookingTours() {
        return new ArrayList(bookingTours);
    }

    public double calcPrice() {
        double price = 0;
        for (int i = 0; i < bookingTours.size(); i++) {
            price += bookingTours.get(i).getTour().getPrice();
        }
        return price;
    }

    public String getName() {
        return name;
    }
}
