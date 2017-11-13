package model.model;

import java.util.ArrayList;

public class Location {

    private String name;
    private String adress;
    private short maxParticipants;
    private String description;
    private ArrayList<Hotel> hotels;
    private ArrayList<Tour> tours;

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

    public ArrayList<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
    }
}
