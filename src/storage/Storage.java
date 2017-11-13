package storage;

import java.util.ArrayList;

import model.Companion;
import model.Conference;
import model.ConferenceType;
import model.Hotel;
import model.Location;
import model.Participant;
import model.Tour;

public class Storage {
    private ArrayList<Conference> conferences = new ArrayList<>();
    private ArrayList<ConferenceType> conferenceTypes = new ArrayList<>();
    private ArrayList<Location> locations = new ArrayList<>();
    private ArrayList<Tour> tours = new ArrayList<>();
    private ArrayList<Hotel> hotels = new ArrayList<>();
    private ArrayList<Participant> participants = new ArrayList<>();
    private ArrayList<Company> companies = new ArrayList<>();

    /**
     * Getters
     */

    public ArrayList<Conference> getConferences() {
        return new ArrayList(conferences);
    }

    public ArrayList<ConferenceType> getConferenceTypes() {
        return new ArrayList(conferenceTypes);
    }

    public ArrayList<Location> getLocations() {
        return new ArrayList(locations);
    }

    public ArrayList<Tour> getTours() {
        return new ArrayList(tours);
    }

    public ArrayList<Hotel> getHotels() {
        return new ArrayList(hotels);
    }

    public ArrayList<Participant> getParticipants() {
        return new ArrayList(participants);
    }

    public ArrayList<Company> getCompanies() {
        return new ArrayList(companies);
    }

}
