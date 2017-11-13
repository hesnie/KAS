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
    private Conference c2;

    public ArrayList<String> ListToursAndCompanions(Conference conference) {
        ArrayList<String> listToursAndCompanions = new ArrayList<>();
        ArrayList<TourType> listToursOnConference = conference.getLocation().getTours();
        ArrayList<Booking> listBookingsOnConference = conference.getBookings();
        for (int i = 0; i < listToursOnConference.size(); i++) {
            String s = listToursOnConference.get(i).getName() + ": ";
            for (int j = 0; j < listBookingsOnConference.size(); j++) {
                Companion c = listBookingsOnConference.get(j).getCompanion();
                if (c != null && c.getBookingTours().contains(listToursOnConference.get(i))) {
                    s += listBookingsOnConference.get(j).getCompanion().getName();
                }
            }
            listToursAndCompanions.add(s);
        }
        return listToursAndCompanions;
    }

    public ArrayList<String> ListHotelsAndParticipants(Conference conference) {
        ArrayList<String> listHotelsAndParticipants = new ArrayList<>();
        for (int i = 0; i < conference.getLocation().getHotels().size(); i++) {
            listHotelsAndParticipants.add(conference.getLocation().getHotels().get(i).getName() + ":");
            for (int j = 0; j < conference.getBookings().size(); j++) {
                if (conference.getBookings().get(j).getHotel() == conference.getLocation().getHotels().get(i)) {
                    listHotelsAndParticipants.add(conference.getBookings().get(j).getParticipant().getName());
                }
            }
        }
        return listHotelsAndParticipants;
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
        Hotel h1 = new Hotel("Den hvide svane", "Midt i byen", 1050, 1250, true, true, false, 50, 0, 0);
        Hotel h2 = new Hotel("Hostel", "Ved havnen", 200, 300);
        Hotel h3 = new Hotel("det dyre", "trøjborg", 300, 400);
        h3.setHasWifi(true);
        h3.setWifiPrice(20);
        h3.setHasShower(true);
        h3.setShowerPrice(50);

        // Ny Tour
        TourType tT1 = new TourType("Egeskov", "Tur til egeskov", 75, (short) 15);
        TourType tT2 = new TourType("Byrundttur i Odense", "En lækker tur i Odense", 125, (short) 20);
        TourType tT3 = new TourType("Trapholt", "Tur til trapholt", 200, (short) 10);

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
        c1 = new Conference(LocalDate.of(2017, 12, 5), (short) 3, 500, ct1, l1);

        // test
        c2 = new Conference(LocalDate.of(2017, 11, 20), (short) 3, 1500, ct2, l1);

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

<<<<<<< HEAD
    public void printTest2() {
        // for (int i = 0; i < c2.getLocation().getHotels().size(); i++) {
        // System.out.println(c2.getLocation().getHotels().get(i).getName() + ":");
        // for (int j = 0; j < c2.getBookings().size(); j++) {
        // if (c2.getBookings().get(j).getHotel() ==
        // c2.getLocation().getHotels().get(i)) {
        // System.out.println(c2.getBookings().get(j).getParticipant().getName());
        // }
        // }
        // }
        System.out.println(ListHotelsAndParticipants(c2));
    }

=======
    public void printTest3() {
        for (String s : ListToursAndCompanions(c2)) {
            System.out.println(s);
        }
        for (Booking b : c1.getBookings()) {
            System.out.println(b.getParticipant());
        }

    }
>>>>>>> 6a3243bbe76303716edd0b3f2a12167e894b91a9
}
