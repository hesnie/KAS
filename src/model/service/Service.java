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

public class Service {

    public ArrayList<String> ListToursAndCompanions(Conference conference) {
        ArrayList<String> listToursAndCompanions = new ArrayList<>();
        ArrayList<Companion> totalCompanionsOnTour = new ArrayList<>();
        for (int i = 0; i < conference.getLocation().getTours().size(); i++) {
            String string = conference.getLocation().getTours().get(i).getName();
            listToursAndCompanions.add(string);
        }
        return listToursAndCompanions;
    }

    public void initContent() {

        // participants

        Participant p1 = new Participant("Finn Madsen", "Adelgade 1", (short) 123);
        Participant p2 = new Participant("Niels Petersen", "Adelgade 2", (short) 1234);
        Participant p3 = new Participant("Peter Sommer", "Adelgade 3", (short) 12345);
        Participant p4 = new Participant("Lone Jensen", "Adelgade 4", (short) 123456);

        // Ny conference
        ConferenceType ct1 = new ConferenceType("Comic con", "Confernce for comics");
        ConferenceType ct2 = new ConferenceType("Hav og Himmel", "noget med mad");

        // Ny beligenhed
        Location l1 = new Location("Musikhuset Aarhus", "Der hvor det ligger?", (short) 100, "Det her er et flot sted");
        Location l2 = new Location("Kongresscenter Aarhus", "lige på sin plads", (short) 200, "Meget højt til loftet");

        // Nyt hotel
        Hotel h1 = new Hotel("hilton", "Midt i byen", 500, 800, true, true, true, 50, 100, 20);
        Hotel h2 = new Hotel("Hostel", "Ved havnen", 200, 300);
        Hotel h3 = new Hotel("det dyre", "trøjborg", 300, 400);
        h3.setHasWifi(true);
        h3.setWifiPrice(20);
        h3.setHasShower(true);
        h3.setShowerPrice(50);

        // Ny Tour
        TourType t1 = new TourType("tur til havnen", "Vi går en tur ned på havnen", 150, (short) 15);
        TourType t2 = new TourType("Aros", "En lækker tur til Aros", 150, (short) 20);
        TourType t3 = new TourType("Sejltur", "Sejltur på vandet", 200, (short) 10);

        // Tilføj hotel
        l1.addHotel(h1);
        l1.addHotel(h2);
        l1.addTour(t1);
        l1.addTour(t2);

        l2.addHotel(h2);
        l2.addHotel(h3);
        l2.addTour(t2);
        l2.addTour(t3);

        // Ny conference
        Conference c1 = new Conference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);

        // booking
        c1.createBooking(p1);
        c1.createBooking(p2);

        c1.getBookings().get(0).createCompanion("Henrik");
        c1.getBookings().get(0).getCompanion().createBookingTour(t1);

        c1.getBookings().get(1).createCompanion("Mathias");

        c1.getBookings().get(0).setHotel(h1);
        c1.getBookings().get(1).setHotel(h2);
        c1.setHotelServices(0, true, true, true);
        c1.setHotelServices(1, true, false, false);
    }
}
