package storage;

import java.util.ArrayList;
import model.model.Company;
import model.model.Conference;
import model.model.ConferenceType;
import model.model.Hotel;
import model.model.Location;
import model.model.Participant;
import model.model.Tour;
import model.model.TourType;

public class Storage {
    private ArrayList<Conference> conferences = new ArrayList<>();
    private ArrayList<ConferenceType> conferenceTypes = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<TourType> tourTypes = new ArrayList<>();
    private ArrayList<Tour> tours = new ArrayList<>();
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Participant> participants = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();

    /**
     * Getters
     */

    public ArrayList<Conference> getConferences() {
        return new ArrayList<>(conferences);
    }

    public ArrayList<ConferenceType> getConferenceTypes() {
        return new ArrayList<>(conferenceTypes);
    }

    public ArrayList<Location> getLocations() {
        return new ArrayList<>(locations);
    }

    public ArrayList<TourType> getTourTypes() {
        return new ArrayList<>(tourTypes);
    }

    public ArrayList<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public ArrayList<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public ArrayList<Company> getCompanies() {
        return new ArrayList<>(companies);
    }

    /**
     * Adders
     */

    public void addConference(Conference conference) {
        conferences.add(conference);
    }

    public void addConferenceType(ConferenceType conferenceType) {
        conferenceTypes.add(conferenceType);
    }

    public void addLocation(Location location) {
        locations.add(location);
    }

    public void addTourType(TourType tourType) {
        tourTypes.add(tourType);
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public void addCompany(Company company) {
        companies.add(company);
    }

    /**
     * Removers
     */

    public void removeConference(Conference conference) {
        conferences.remove(conference);
    }

    public void removeConferenceType(ConferenceType conferenceType) {
        conferenceTypes.remove(conferenceType);
    }

    public void removeLocation(Location location) {
        locations.remove(location);
    }

    public void removeTourType(TourType tourType) {
        tourTypes.remove(tourType);
    }

    public void removeTour(Tour tour) {
        tours.remove(tour);
    }

    public void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
    }

    public void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public void removeCompany(Company company) {
        companies.remove(company);
    }
}
