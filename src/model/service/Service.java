package model.service;

import java.time.LocalDate;
import java.util.ArrayList;
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

    private Storage s1;
    private Booking b1;

    // Skal slettes, kun til test
    private Conference c1;
    private Conference c2;

    public ArrayList<String> listToursAndCompanions(Conference conference) {
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

    public ArrayList<String> listHotelsAndParticipants(Conference conference) {
        ArrayList<String> listHotelsAndParticipants = new ArrayList<>();
        for (int i = 0; i < conference.getLocation().getHotels().size(); i++) {
            String h = conference.getLocation().getHotels().get(i).getName() + ": ";
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

    public ArrayList<String> listBookingsOnConference(Conference conference) {
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

    // create participant
    public Participant createParticipant(String name, String adress, short phoneNumber) {
        Participant p = new Participant(name, adress, phoneNumber);
        s1.addParticipant(p);
        return p;
    }

    // Create companion
    public Companion createCompanion(String name) {
        Companion c = b1.createCompanion(name);
        return c;
    }

    public void addTourToCompanion(Tour tour, Booking booking) {
        booking.getCompanion().createBookingTour(tour);
    }

    // Add hotel
    public Hotel addParticipantToHotel(Hotel hotel) {
        b1.setHotel(hotel);
        return hotel;

    }

    // create booking
    public Booking createBooking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker,
            Participant participant, Conference conference, Hotel hotel, String companionName) {

        Booking b = conference.createBooking(participant, isSpeaker);

        if (hotel != null) {
            conference.setHotelServices(b, wifi, breaktast, shower);
            b.setHotel(hotel);
        }
        if (companionName != "" || companionName != null) {
            b.createCompanion(companionName);

        }
        return b;
    }

    public ConferenceType createConferenceType(String name, String description) {
        ConferenceType ct = new ConferenceType(name, description);
        s1.addConferenceType(ct);
        return ct;
    }

    public Conference createConference(LocalDate dateStart, short duration, double price, ConferenceType conferenceType,
            Location location) {
        Conference c = new Conference(dateStart, duration, price, conferenceType, location);
        s1.addConference(c);
        return c;
    }

    public Hotel createHotel(String name, String adress, double priceSingle, double priceDouble, boolean hasWifi,
            boolean hasBreakfast, boolean hasShower, double wifiPrice, double breakfastPrice, double showerPrice) {
        Hotel h = new Hotel(name, adress, priceSingle, priceDouble, hasWifi, hasBreakfast, hasShower, wifiPrice,
                breakfastPrice, showerPrice);
        s1.addHotel(h);
        return h;
    }

    public Hotel createHotel(String name, String adress, double priceSingle, double priceDouble) {
        Hotel h = new Hotel(name, adress, priceSingle, priceDouble);
        s1.addHotel(h);
        return h;
    }

    public Location createLocation(String name, String adress, short maxParticipants, String description) {
        Location l = new Location(name, adress, maxParticipants, description);
        s1.addLocation(l);
        return l;
    }

    public void addHotelToLocation(Hotel hotel, Location location) {
        location.addHotel(hotel);
    }

    public void addTourToLocation(TourType tourType, Location location) {
        location.addTour(tourType);
    }

    public TourType createTourType(String name, String description, double price, short maxParticipants) {
        TourType tt = new TourType(name, description, price, maxParticipants);
        s1.addTourType(tt);
        return tt;
    }

<<<<<<< HEAD
    public Company createCompany(String name, short phoneNumber) {
        Company c = new Company(name, phoneNumber);
        s1.addCompany(c);
        return c;
    }

    public Company addCompanyToBooking(Booking booking, Company company) {
        booking.setCompany(company);

        return company;
    }

    // ====================================================================================================

=======
    public Tour createTour(LocalDate date, TourType tourType) {
        Tour t = new Tour(date, tourType);
        s1.addTour(t);
        return t;
    }

<<<<<<< HEAD
    public void setHotelServices(Hotel hotel, boolean wifi, boolean breakfast, boolean shower, double wifiPrice,
            double breakfastPrice, double showerPrice) {
        hotel.setHasWifi(wifi);
        hotel.setWifiPrice(wifiPrice);
        hotel.setHasBreakfast(breakfast);
        hotel.setBreakfastPrice(breakfastPrice);
        hotel.setHasShower(shower);
        hotel.setShowerPrice(showerPrice);
    }

=======
>>>>>>> 565ab38617acb60870c31dc700cbd8048f7e8483
>>>>>>> 1167acac4f6d40308083570885bf782e34356ece
    public void initContent() {

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
        Conference c1 = createConference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);
        Conference c2 = createConference(LocalDate.of(2017, 11, 20), (short) 3, 1500, ct2, l1);

        // Ny tour
        Tour t1 = createTour(LocalDate.of(2017, 11, 21), tT1);
        Tour t2 = createTour(LocalDate.of(2017, 11, 22), tT2);
        Tour t3 = createTour(LocalDate.of(2017, 11, 23), tT3);

        // booking
        Booking b1 = createBooking(true, true, true, false, p1, c1, h1, "Henrik");
        Booking b2 = createBooking(true, false, false, false, p2, c1, h2, "Mathias");
        Booking b3 = createBooking(true, true, true, false, p3, c1, h1, null);

        Booking b4 = createBooking(true, true, true, false, p1, c2, h1, null);
        Booking b5 = createBooking(true, true, true, false, p2, c2, h1, null);
        Booking b6 = createBooking(true, false, false, false, p3, c2, h1, "Mie Sommer");
        Booking b7 = createBooking(true, false, false, true, p4, c2, h1, "Jan Madsen");

        // companions tours
        addTourToCompanion(t1, b1);
        addTourToCompanion(t1, b2);

        addTourToCompanion(t1, b6);
        addTourToCompanion(t3, b6);
        addTourToCompanion(t1, b7);
        addTourToCompanion(t2, b7);

        // // test
        // c2.getBookings().get(1).setHotel(h1);
        // c2.getBookings().get(2).setHotel(h1);
        // c2.setHotelServices(2, true, false, false);
        //
        // c2.getBookings().get(3).setHotel(h1);
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
