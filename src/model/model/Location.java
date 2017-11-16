package model.model;

import java.util.ArrayList;

public class Location {

    private String name;
    private String adress;
    private short maxParticipants;
    private String description;
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<TourType> toursType = new ArrayList<>();

    public Location(String name, String adress, short maxParticipants, String description) {
        this.name = name;
        this.adress = adress;
        this.maxParticipants = maxParticipants;
        this.description = description;
    }

    public ArrayList<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
    }

    public ArrayList<TourType> getToursTypes() {
        return new ArrayList<>(toursType);
    }

    public void addTourType(TourType tourType) {
        toursType.add(tourType);
    }

    public void removeTourType(TourType tourType) {
        toursType.remove(tourType);
    }

    @Override
    public String toString() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
