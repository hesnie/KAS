package model.service;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.scene.control.DatePicker;
import model.model.Booking;
import model.model.BookingTour;
import model.model.Companion;
import model.model.Company;
import model.model.Conference;
import model.model.ConferenceType;
import model.model.Hotel;
import model.model.Location;
import model.model.Participant;
import model.model.Tour;
import model.model.TourType;
import storage.Storage;

public class Service {

    // Skal slettes, kun til test
    private static Conference c1;
    private static Conference c2;

    public static ArrayList<String> listToursAndCompanions(Conference conference) {
        ArrayList<String> listToursAndCompanions = new ArrayList<>();
        ArrayList<TourType> listToursOnConference = conference.getLocation().getToursTypes();
        ArrayList<Booking> listBookingsOnConference = conference.getBookings();
        for (int i = 0; i < listToursOnConference.size(); i++) {
            String s = listToursOnConference.get(i).getName() + ": ";
            for (int j = 0; j < listBookingsOnConference.size(); j++) {
                Booking b = listBookingsOnConference.get(j);
                if (b.getCompanion() != null) {
                    for (int l = 0; l < b.getCompanion().getBookingTours().size(); l++) {
                        if (b.getCompanion().getBookingTours().get(l).getTour().getTourType() == listToursOnConference
                                .get(i)) {
                            s += b.getCompanion().getName() + "(" + b.getParticipant().getName() + ")" + ", ";
                        }
                    }
                }
            }
            listToursAndCompanions.add(s);
        }
        return listToursAndCompanions;

    }

    public static ArrayList<String> listHotelsAndParticipants(Conference conference) {
        ArrayList<String> listHotelsAndParticipants = new ArrayList<>();
        String h;
        for (int i = 0; i < conference.getLocation().getHotels().size(); i++) {
            h = conference.getLocation().getHotels().get(i).getName() + ": ";
            for (int j = 0; j < conference.getBookings().size(); j++) {
                if (conference.getBookings().get(j).getHotel() == conference.getLocation().getHotels().get(i)) {
                    h += conference.getBookings().get(j).getParticipant().getName();
                    if (conference.getBookings().get(j).getCompanion() != null) {
                        h += " - " + conference.getBookings().get(j).getCompanion().getName();
                    }
                    h += ", ";
                }
            }
            listHotelsAndParticipants.add(h);
        }
        return listHotelsAndParticipants;
    }

    public static ArrayList<String> listBookingsOnConference(Conference conference) {
        ArrayList<String> listBookings = new ArrayList<>();
        ArrayList<Booking> bookings = conference.getBookings();
        listBookings.add("Deltagere på \"" + conference.getConferenceType().getName() + "\":");
        for (Booking b : bookings) {
            String toAdd = b.getParticipant().getName();
            if (b.isSpeaker()) {
                toAdd += "(Foredragsholder)";
            }
            listBookings.add(toAdd);
        }

        return listBookings;
    }

    // =================================================================================================

    public static void tourTypeSet(TourType tourType, String name, String description, short maxParticipants,
            double price) {
        tourType.setDescription(description);
        tourType.setMaxParticipants(maxParticipants);
        tourType.setName(name);
        tourType.setPrice(price);
    }

    public static void hotelSet(Hotel hotel, String name, String adress, double priceSingle, double priceDouble,
            boolean hasWifi, boolean hasBreakfast, boolean hasShower, double wifiPrice, double breakfastPrice,
            double showerPrice) {
        hotel.setName(name);
        hotel.setAdress(adress);
        hotel.setSinglePice(priceSingle);
        hotel.setDoublePrice(priceDouble);
        hotel.setHasWifi(hasWifi);
        hotel.setHasBreakfast(hasBreakfast);
        hotel.setHasShower(hasShower);
        hotel.setWifiPrice(wifiPrice);
        hotel.setBreakfastPrice(breakfastPrice);
        hotel.setShowerPrice(showerPrice);

    }

    // add tour to Conference
    public static Tour addTourToConference(Tour tour, Conference conference) {
        conference.addTour(tour);
        return tour;
    }

    // create participant
    public static Participant createParticipant(String name, String adress, short phoneNumber) {
        Participant p = new Participant(name, adress, phoneNumber);
        Storage.addParticipant(p);
        return p;
    }

    // Create companion
    public static Companion createCompanion(String name, Booking booking) {
        Companion c = booking.createCompanion(name);
        return c;
    }

    public static void addTourToCompanion(Tour tour, Booking booking) {
        booking.getCompanion().createBookingTour(tour);
    }

    // Add hotel
    public static Hotel addParticipantToHotel(Hotel hotel, Booking booking) {
        booking.setHotel(hotel);
        return hotel;

    }

    // create booking
    public static Booking createBooking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker,
            Participant participant, Conference conference, Hotel hotel, boolean companion, String companionName) {

        Booking b = conference.createBooking(participant, isSpeaker);

        if (hotel != null) {
            conference.setHotelServices(b, wifi, breaktast, shower);
            b.setHotel(hotel);
        }
        if (companion) {
            b.createCompanion(companionName);

        }
        return b;
    }

    public static ConferenceType createConferenceType(String name, String description) {
        ConferenceType ct = new ConferenceType(name, description);
        Storage.addConferenceType(ct);
        return ct;
    }

    public static Conference createConference(LocalDate dateStart, short duration, double price,
            ConferenceType conferenceType, Location location) {
        Conference c = new Conference(dateStart, duration, price, conferenceType, location);
        Storage.addConference(c);
        return c;
    }

    public static Hotel createHotel(String name, String adress, double priceSingle, double priceDouble, boolean hasWifi,
            boolean hasBreakfast, boolean hasShower, double wifiPrice, double breakfastPrice, double showerPrice) {
        Hotel h = new Hotel(name, adress, priceSingle, priceDouble, hasWifi, hasBreakfast, hasShower, wifiPrice,
                breakfastPrice, showerPrice);
        Storage.addHotel(h);
        return h;
    }

    public static Hotel createHotel(String name, String adress, double priceSingle, double priceDouble) {
        Hotel h = new Hotel(name, adress, priceSingle, priceDouble);
        Storage.addHotel(h);
        return h;
    }

    public static Location createLocation(String name, String adress, short maxParticipants, String description) {
        Location l = new Location(name, adress, maxParticipants, description);
        Storage.addLocation(l);
        return l;
    }

    public static void addHotelToLocation(Hotel hotel, Location location) {
        location.addHotel(hotel);
    }

    public static void addTourToLocation(TourType tourType, Location location) {
        location.addTourType(tourType);
    }

    public static TourType createTourType(String name, String description, double price, short maxParticipants) {
        TourType tt = new TourType(name, description, price, maxParticipants);
        Storage.addTourType(tt);
        return tt;
    }

    public static Company createCompany(String name, short phoneNumber) {
        Company c = new Company(name, phoneNumber);
        Storage.addCompany(c);
        return c;
    }

    public Company addCompanyToBooking(Booking booking, Company company) {
        booking.setCompany(company);

        return company;
    }

    // =====================================================================================================
    public static ArrayList<Conference> getConferencesFromStorage() {
        return Storage.getConferences();
    }

    public static ArrayList<ConferenceType> getConferenceTypesFromStorage() {
        return Storage.getConferenceTypes();
    }

    public static ArrayList<Location> getLocationsFromStorage() {
        return Storage.getLocations();
    }

    public static ArrayList<TourType> getTourTypesFromStorage() {
        return Storage.getTourTypes();
    }

    public static ArrayList<Tour> getToursFromStorage() {
        return Storage.getTours();
    }

    public static ArrayList<Hotel> getHotelsFromStorage() {
        return Storage.getHotels();
    }

    public static ArrayList<Participant> getParticipantsFromStorage() {
        return Storage.getParticipants();
    }

    public static ArrayList<Company> getCompaniesFromStorage() {
        return Storage.getCompanies();
    }

    // ====================================================================================================

    public static Tour createTour(LocalDate date, TourType tourType) {
        Tour t = new Tour(date, tourType);
        Storage.addTour(t);
        return t;
    }

    private static void createNewCompany(String name, int phoneNumber) {
        Company c = new Company(name, phoneNumber);
        Storage.addCompany(c);
    }

    public static void setHotelServices(Hotel hotel, boolean wifi, boolean breakfast, boolean shower, double wifiPrice,
            double breakfastPrice, double showerPrice) {
        hotel.setHasWifi(wifi);
        hotel.setWifiPrice(wifiPrice);
        hotel.setHasBreakfast(breakfast);
        hotel.setBreakfastPrice(breakfastPrice);
        hotel.setHasShower(shower);
        hotel.setShowerPrice(showerPrice);
    }

    public static void initContent() {
        // participants

        Participant p1 = createParticipant("Finn Madsen", "Adelgade 1", (short) 123);
        Participant p2 = createParticipant("Niels Petersen", "Adelgade 2", (short) 1234);
        Participant p3 = createParticipant("Peter Sommer", "Adelgade 3", (short) 12345);
        Participant p4 = createParticipant("Lone Jensen", "Adelgade 4", (short) 123456);

        // Ny conference
        ConferenceType ct1 = createConferenceType("Comic con", "Confernce for comics");
        ConferenceType ct2 = createConferenceType("Hav og Himmel", "noget med mad");

        // Ny beligenhed
        Location l1 = createLocation("Musikhuset Aarhus", "Der hvor det ligger?", (short) 100,
                "Det her er et flot sted");
        Location l2 = createLocation("Kongresscenter Aarhus", "lige på sin plads", (short) 200,
                "Meget højt til loftet");

        // Nyt hotel
        Hotel h1 = createHotel("Den hvide svane", "Midt i byen", 1050, 1250, true, true, false, 50, 0, 0);
        Hotel h2 = createHotel("Hostel", "Ved havnen", 200, 300);
        Hotel h3 = createHotel("det dyre", "trøjborg", 300, 400);

        setHotelServices(h3, true, false, true, 20, 0, 50);

        // Ny Tour type
        TourType tT1 = createTourType("Egeskov", "Tur til egeskov", 75, (short) 15);
        TourType tT2 = createTourType("Byrundttur i Odense", "En lækker tur i Odense", 125, (short) 20);
        TourType tT3 = createTourType("Trapholt", "Tur til trapholt", 200, (short) 10);

        // Tilføj firmaer

        createNewCompany("Google", 12341234);
        createNewCompany("IBM", 94839483);
        createNewCompany("Aarstiderne", 28382838);
        createNewCompany("EAAA", 34563456);

        // Tilføj hotel

        addHotelToLocation(h1, l1);
        addHotelToLocation(h2, l1);
        addTourToLocation(tT1, l1);
        addTourToLocation(tT2, l1);
        addTourToLocation(tT3, l1);

        addHotelToLocation(h2, l2);
        addHotelToLocation(h3, l2);
        addTourToLocation(tT2, l2);
        addTourToLocation(tT3, l2);

        // Ny conference
        c1 = createConference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);
        c2 = createConference(LocalDate.of(2017, 11, 20), (short) 3, 1500, ct2, l1);

        // Ny tour
        Tour t1 = createTour(LocalDate.of(2017, 11, 21), tT1);
        Tour t2 = createTour(LocalDate.of(2017, 11, 22), tT2);
        Tour t3 = createTour(LocalDate.of(2017, 11, 23), tT3);

        // tilføj tour til konference
        addTourToConference(t1, c1);
        addTourToConference(t2, c1);
        addTourToConference(t3, c1);
        addTourToConference(t1, c2);
        addTourToConference(t2, c2);

        // booking
        Booking b1 = createBooking(true, true, true, false, p1, c1, h1, true, "Henrik");
        Booking b2 = createBooking(true, false, false, false, p2, c1, h2, true, "Mathias");
        Booking b3 = createBooking(true, true, true, false, p3, c1, h1, false, null);

        Booking b4 = createBooking(true, true, true, false, p1, c2, h1, false, null);
        Booking b5 = createBooking(true, true, true, false, p2, c2, h1, false, null);
        Booking b6 = createBooking(true, false, false, false, p3, c2, h1, true, "Mie Sommer");
        Booking b7 = createBooking(true, false, false, true, p4, c2, h1, true, "Jan Madsen");

        // companions tours
        addTourToCompanion(t1, b1);
        addTourToCompanion(t1, b2);

        addTourToCompanion(t1, b6);
        addTourToCompanion(t3, b6);
        addTourToCompanion(t1, b7);
        addTourToCompanion(t2, b7);
    }

    public static String locationOutputTextForLocationWindow(Location location) {
        String s;
        s = location.getName() + "\n" + location.getDescription() + ":" + "\n" + "\n";
        s += "Udflugter: " + "\n";
        for (int i = 0; i < location.getToursTypes().size(); i++) {
            s += location.getToursTypes().get(i).getName() + "\n";
        }
        s += "\n" + "Hoteller: " + "\n";
        for (int i = 0; i < location.getHotels().size(); i++) {
            s += location.getHotels().get(i).getName() + "\n";
        }

        return s;
    }

    public static String ConferenceOutputTextForConferenceWindow(Conference conference) {
        String s;
        s = conference.getConferenceType().getName() + "\n";
        for (int i = 0; i < conference.getTours().size(); i++) {
            s += conference.getTours().get(i).getTourType().getName() + "(" + conference.getTours().get(i).getDate()
                    + ")" + "\n";
        }
        return s;
    }

    public static void updateTourType(TourType tourType, String name, String Descriprion, double price,
            short macParticipants) {

    }

    public void printTest() {
        for (int i = 0; i < c2.getBookings().size(); i++) {
            System.out.println(c2.getBookings().get(i).calcTotalPrice());
        }
    }

    public void printTest2() {
        for (int i = 0; i < listHotelsAndParticipants(c2).size(); i++) {
            System.out.println(listHotelsAndParticipants(c2).get(i));
        }

    }

    public void printTest3() {
        for (String s : listToursAndCompanions(c2)) {
            System.out.println(s);
        }
    }

    public void printTest4() {
        for (String s : listBookingsOnConference(c2)) {
            System.out.println(s);
        }
    }
}
