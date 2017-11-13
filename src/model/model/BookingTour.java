package model.model;

public class BookingTour {
    private Tour tour;

    BookingTour(Tour tour) {
        this.tour = tour;
    }

    public Tour getTour() {
        return tour;
    }

}
