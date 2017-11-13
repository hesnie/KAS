package model.model;

import java.time.LocalDate;

public class Tour {
    private LocalDate date;
    private TourType tourType;

    public Tour(LocalDate date, TourType tourType) {
        this.date = date;
        this.tourType = tourType;
    }

    public LocalDate getDate() {
        return date;
    }

    public TourType getTourType() {
        return tourType;
    }

}
