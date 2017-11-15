package model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import model.model.Booking;
import model.model.Companion;
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

    public ArrayList<String> ListToursAndCompanions(Conference conference) {
        ArrayList<String> listToursAndCompanions = new ArrayList<>();
        ArrayList<TourType> listToursOnConference = conference.getLocation().getTours();
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

    public ArrayList<String> ListHotelsAndParticipants(Conference conference) {
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

    public ArrayList<String> ListBookingsOnConference(Conference conference) {
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
    public void CreateParticipant(String name, String adress, short phoneNumber) {
        Participant p = new Participant(name, adress, phoneNumber);
        s1.addParticipant(p);
    }

    // Create companion
    public Companion Createcompanion(String name) {
        Companion c = b1.createCompanion(name);
        return c;
    }

    // Add hotel
    public Hotel addParticipantToHotel(Hotel hotel) {
        b1.setHotel(hotel);
        return hotel;

    }

    // create booking
    public Booking createBooking(boolean wifi, boolean breaktast, boolean shower, boolean isSpeaker,
            Participant participant, Conference conference, boolean companion, Hotel hotel, String companionName) {

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

    public TourType createTourType(String name, String description, double price, short maxParticipants) {
        TourType tt = new TourType(name, description, price, maxParticipants);
        s1.addTourType(tt);
        return tt;
    }

    public Tour createTour(LocalDate date, TourType tourType) {
        Tour t = new Tour(date, tourType);
        s1.addTour(t);
        return t;
    }

    public void initContent() {

        // participants

        Participant p1 = CreateParticipant("Finn Madsen", "Adelgade 1", (short) 123);
        Participant p2 = new Participant("Niels Petersen", "Adelgade 2", (short) 1234);
        Participant p3 = new Participant("Peter Sommer", "Adelgade 3", (short) 12345);
        Participant p4 = new Participant("Lone Jensen", "Adelgade 4", (short) 123456);

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

        h3.setHasWifi(true);
        h3.setWifiPrice(20);
        h3.setHasShower(true);
        h3.setShowerPrice(50);

        // Ny Tour type
        TourType tT1 = createTourType("Egeskov", "Tur til egeskov", 75, (short) 15);
        TourType tT2 = createTourType("Byrundttur i Odense", "En lækker tur i Odense", 125, (short) 20);
        TourType tT3 = createTourType("Trapholt", "Tur til trapholt", 200, (short) 10);

        // Tilføj hotel
        l1.addHotel(h1);
        l1.addHotel(h2);
        l1.addTour(tT1);
        l1.addTour(tT2);
        l1.addTour(tT3);

        l2.addHotel(h2);
        l2.addHotel(h3);
        l2.addTour(tT2);
        l2.addTour(tT3);

        // Ny conference
        Conference c1 = createConference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);
        Conference c2 = createConference(LocalDate.of(2017, 11, 20), (short) 3, 1500, ct2, l1);

        // Ny tour
        Tour t1 = new Tour(LocalDate.of(2017, 11, 21), tT1);
        Tour t2 = new Tour(LocalDate.of(2017, 11, 22), tT2);
        Tour t3 = new Tour(LocalDate.of(2017, 11, 23), tT3);

        // booking
        c1.createBooking(p1, false);
        c1.createBooking(p2, false);
        c1.createBooking(p3, false);

        // test
        c2.createBooking(p1, false);
        c2.createBooking(p2, false);
        c2.createBooking(p3, false);
        c2.createBooking(p4, true);

        // companions and companions tours
        c1.getBookings().get(0).createCompanion("Henrik");
        c1.getBookings().get(0).getCompanion().createBookingTour(t1);

        c1.getBookings().get(1).createCompanion("Mathias");
        c1.getBookings().get(1).getCompanion().createBookingTour(t1);

        // test
        c2.getBookings().get(2).createCompanion("Mie Sommer");
        c2.getBookings().get(2).getCompanion().createBookingTour(t1);
        c2.getBookings().get(2).getCompanion().createBookingTour(t3);

        c2.getBookings().get(3).createCompanion("Jan Madsen");
        c2.getBookings().get(3).getCompanion().createBookingTour(t1);
        c2.getBookings().get(3).getCompanion().createBookingTour(t2);

        // set hotels for participants
        c1.getBookings().get(0).setHotel(h1);
        c1.getBookings().get(1).setHotel(h2);
        c1.getBookings().get(2).setHotel(h1);
        c1.setHotelServices(0, true, true, true);
        c1.setHotelServices(1, true, false, false);
        c1.setHotelServices(2, true, true, true);

        // test
        c2.getBookings().get(1).setHotel(h1);
        c2.getBookings().get(2).setHotel(h1);
        c2.setHotelServices(2, true, false, false);

        c2.getBookings().get(3).setHotel(h1);
        c2.setHotelServices(3, true, false, false);
    }

    public void printTest() {
        for (int i = 0; i < c2.getBookings().size(); i++) {
            System.out.println(c2.getBookings().get(i).calcTotalPrice());
        }
    }

    public void printTest2() {
        for (int i = 0; i < ListHotelsAndParticipants(c2).size(); i++) {
            System.out.println(ListHotelsAndParticipants(c2).get(i));
        }

    }

    public void printTest3() {
        for (String s : ListToursAndCompanions(c2)) {
            System.out.println(s);
        }
    }

    public void printTest4() {
        for (String s : ListBookingsOnConference(c2)) {
            System.out.println(s);
        }
    }
}
