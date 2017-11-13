package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Companion {
    private String name;
    private ArrayList<BookingTour> bookingTours = new ArrayList<>();

    Companion(String name) {
        this.name = name;
    }

    BookingTour createBookingTour(Tour tour, LocalDate date) {
        BookingTour b = new BookingTour(tour, date);
        bookingTours.add(b);
        return b;
    }

    ArrayList<BookingTour> getBookingTours() {
        return new ArrayList(bookingTours);
    }

    float calcPrice() {
        float price = 0;
        for (int i = 0; i < bookingTours.size(); i++) {
            price += bookingTours.get(i).getTour().getPrice();
        }
        return price;
    }

    String getName() {
        return name;
    }

}
