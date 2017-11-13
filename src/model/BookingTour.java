package model;

import java.time.LocalDate;

public class BookingTour {
    private LocalDate date;
    private Tour tour;

    BookingTour(Tour tour, LocalDate date) {
        this.date = date;
        this.tour = tour;
    }

    Tour getTour() {
        return tour;
    }
}
