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

    // Skal slettes, kun til test
    private Conference c1;

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
        TourType tT1 = new TourType("tur til havnen", "Vi går en tur ned på havnen", 150, (short) 15);
        TourType tT2 = new TourType("Aros", "En lækker tur til Aros", 150, (short) 20);
        TourType tT3 = new TourType("Sejltur", "Sejltur på vandet", 200, (short) 10);

        // Tilføj hotel
        l1.addHotel(h1);
        l1.addHotel(h2);
        l1.addTour(tT1);
        l1.addTour(tT2);

        l2.addHotel(h2);
        l2.addHotel(h3);
        l2.addTour(tT2);
        l2.addTour(tT3);

        // Ny conference
        c1 = new Conference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);

        // Ny tour
        Tour t1 = new Tour(LocalDate.of(2017, 12, 6), tT1);

        // booking
        c1.createBooking(p1);
        c1.createBooking(p2);
        c1.createBooking(p3);

        // companions and companions tours
        c1.getBookings().get(0).createCompanion("Henrik");
        c1.getBookings().get(0).getCompanion().createBookingTour(t1);

        c1.getBookings().get(1).createCompanion("Mathias");
        c1.getBookings().get(1).getCompanion().createBookingTour(t1);

        // set hotels for participants
        c1.getBookings().get(0).setHotel(h1);
        c1.getBookings().get(1).setHotel(h2);
        c1.getBookings().get(2).setHotel(h1);
        c1.setHotelServices(0, true, true, true);
        c1.setHotelServices(1, true, false, false);
        c1.setHotelServices(2, true, true, true);
    }

    public void printTest() {
        for (int i = 0; i < c1.getBookings().size(); i++) {
            System.out.println(c1.getBookings().get(i).calcTotalPrice());
        }
    }

}
