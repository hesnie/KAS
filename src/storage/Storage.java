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
    private static ArrayList<Conference> conferences = new ArrayList<>();
    private static ArrayList<ConferenceType> conferenceTypes = new ArrayList<>();
    private static ArrayList<Location> locations = new ArrayList<>();
    private static ArrayList<TourType> tourTypes = new ArrayList<>();
    private static ArrayList<Tour> tours = new ArrayList<>();
    private static ArrayList<Hotel> hotels = new ArrayList<>();
    private static ArrayList<Participant> participants = new ArrayList<>();
    private static ArrayList<Company> companies = new ArrayList<>();

    /**
     * Getters
     */

    public static ArrayList<Conference> getConferences() {
        return new ArrayList<>(conferences);
    }

    public static ArrayList<ConferenceType> getConferenceTypes() {
        return new ArrayList<>(conferenceTypes);
    }

    public static ArrayList<Location> getLocations() {
        return new ArrayList<>(locations);
    }

    public static ArrayList<TourType> getTourTypes() {
        return new ArrayList<>(tourTypes);
    }

    public static ArrayList<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public static ArrayList<Hotel> getHotels() {
        return new ArrayList<>(hotels);
    }

    public static ArrayList<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public static ArrayList<Company> getCompanies() {
        return new ArrayList<>(companies);
    }

    /**
     * Adders
     */

    public static void addConference(Conference conference) {
        conferences.add(conference);
    }

    public static void addConferenceType(ConferenceType conferenceType) {
        conferenceTypes.add(conferenceType);
    }

    public static void addLocation(Location location) {
        locations.add(location);
    }

    public static void addTourType(TourType tourType) {
        tourTypes.add(tourType);
    }

    public static void addTour(Tour tour) {
        tours.add(tour);
    }

    public static void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public static void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public static void addCompany(Company company) {
        companies.add(company);
    }

    /**
     * Removers
     */

    public static void removeConference(Conference conference) {
        conferences.remove(conference);
    }

    public static void removeConferenceType(ConferenceType conferenceType) {
        conferenceTypes.remove(conferenceType);
    }

    public static void removeLocation(Location location) {
        locations.remove(location);
    }

    public static void removeTourType(TourType tourType) {
        tourTypes.remove(tourType);
    }

    public static void removeTour(Tour tour) {
        tours.remove(tour);
    }

    public static void removeHotel(Hotel hotel) {
        hotels.remove(hotel);
    }

    public static void removeParticipant(Participant participant) {
        participants.remove(participant);
    }

    public static void removeCompany(Company company) {
        companies.remove(company);
    }
}
