package model.model;

import java.time.LocalDate;

public class BookingTour {
    private LocalDate date;
    private Tour tour;

    BookingTour(Tour tour, LocalDate date) {
        this.date = date;
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

    public LocalDate getDate() {
        return date;
    }
}
