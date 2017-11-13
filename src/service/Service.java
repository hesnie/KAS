package service;

import model.ConferenceType;
import model.Hotel;
import model.Location;
import model.Participant;
import model.Tour;

public class Service {

    public void initContent() {

        // participants

        Participant p1 = new Participant("Finn Madsen", "Adelgade 1", (short) 123);
        Participant p2 = new Participant("Niels Petersen", "Adelgade 2", (short) 1234);
        Participant p3 = new Participant("Peter Sommer", "Adelgade 3", (short) 12345);
        Participant p4 = new Participant("Lone Jensen", "Adelgade 4", (short) 123456);

        // Ny conference
        ConferenceType c1 = new ConferenceType("Comic con", "Confernce for comics");

        // Ny beligenhed
        Location l1 = new Location("Musikhuset Aarhus", "Der hvor det ligger?", (short) 100, "Det her er et flot sted");

        // Nyt hotel
        Hotel h1 = new Hotel("hilton", "Midt i byen", 500, 800, true, true, true, 50, 100, 20);
        Hotel h2 = new Hotel("Hostel", "Ved havnen", 200, 300);

        // Ny Tour
        Tour t1 = new Tour("tur til havnen", "Vi går en tur ned på havnen", 150, (short) 15);
        Tour t2 = new Tour("Aros", "En lækker tur til Aros", 150, (short) 10);

        // Tilføj hotel
        l1.addHotel(h1);

    }
}
