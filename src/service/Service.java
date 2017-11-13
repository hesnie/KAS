package service;

import java.time.LocalDate;
import model.Booking;
import java.util.ArrayList;

import model.Conference;
import model.ConferenceType;
import model.Hotel;
import model.Location;
import model.Participant;
import model.Tour;

public class Service {

    public ArrayList<String> ListToursAndCompanions(Conference conference) {
        ArrayList<String> listToursAndCompanions = new ArrayList<>();
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
        Tour t1 = new Tour("tur til havnen", "Vi går en tur ned på havnen", 150, (short) 15);
        Tour t2 = new Tour("Aros", "En lækker tur til Aros", 150, (short) 20);
        Tour t3 = new Tour("Sejltur", "Sejltur på vandet", 200, (short) 10);

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

        // Booking
        c1.createBooking(p1);
        c1.createBooking(p2);

        c1.createCompanionForBooking(0, "Henrik");
        c1.createCompanionForBooking(1, "Mathias");
    }
}
