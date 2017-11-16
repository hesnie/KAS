package gui;

import model.model.Booking;
import model.model.Company;
import model.model.Conference;
import model.model.Hotel;
import model.model.Participant;
import model.model.Tour;
import model.model.TourType;
import model.service.Service;
import storage.Storage;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookingFormWindow extends Stage {

    public BookingFormWindow(String title, Conference selectedConference) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);
        lblHeadline.setText("Tilmelding til: " + selectedConference.getConferenceType().getName());
        lblHeadline.setFont(Font.font(32));
        this.selectedConference = selectedConference;

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    // -----------------------------------------------------------------
    private Label lblHeadline = new Label();
    private Label lblHotelDescription = new Label();
    private TextField txfName, txfAdress, txfCity, txfCountry, txfPhoneNr, txfCompanionName;
    private CheckBox cbxSpeaker, cbxCompanion, cbxHotel, cbxCompany;
    private ComboBox<Company> cbbCompanies;
    private ComboBox<Hotel> cbbHotels;
    private Button btnSignUp = new Button("Tilmeld");
    CheckBox cbxShower;
    CheckBox cbxBreakfast;
    CheckBox cbxWifi;

    private Conference selectedConference;
    private int rowCount = 2;
    ArrayList<Tour> bookedTours = new ArrayList<>();

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        txfName = new TextField();
        txfAdress = new TextField();
        txfCity = new TextField();
        txfCountry = new TextField();
        txfPhoneNr = new TextField();

        Label lblName = new Label("Navn");
        Label lblAdress = new Label("Adresse");
        Label lblCity = new Label("By");
        Label lblCountry = new Label("Land");
        Label lblPhoneNr = new Label("Telefonnr.");

        cbxSpeaker = new CheckBox("Foredragsholder?");
        cbxCompanion = new CheckBox("Medtager ledsager");
        cbxHotel = new CheckBox("Ønsker overnatning");
        cbxCompany = new CheckBox("Firmabetaling");

        cbbCompanies = new ComboBox<>();
        cbbCompanies.getItems().addAll(Service.getCompaniesFromStorage());

        btnSignUp.setOnAction(event -> createBooking());

        VBox rowOne = new VBox();
        VBox rowTwo = new VBox();

        HBox lineOneOne = new HBox();
        lineOneOne.getChildren().add(lblName);
        lineOneOne.getChildren().add(txfName);

        HBox lineTwoOne = new HBox();
        lineTwoOne.getChildren().add(lblAdress);
        lineTwoOne.getChildren().add(txfAdress);

        HBox lineThreeOne = new HBox();
        lineThreeOne.getChildren().add(lblCity);
        lineThreeOne.getChildren().add(txfCity);

        HBox lineFourOne = new HBox();
        lineFourOne.getChildren().add(cbxCompany);
        lineFourOne.getChildren().add(cbbCompanies);

        HBox lineFiveOne = new HBox();
        lineFiveOne.getChildren().add(cbxCompanion);
        lineFiveOne.getChildren().add(cbxHotel);

        HBox lineOneTwo = new HBox();
        lineOneTwo.getChildren().add(cbxSpeaker);

        HBox lineTwoTwo = new HBox();
        lineTwoTwo.getChildren().add(lblPhoneNr);
        lineTwoTwo.getChildren().add(txfPhoneNr);

        HBox lineThreeTwo = new HBox();
        lineThreeTwo.getChildren().add(lblCountry);
        lineThreeTwo.getChildren().add(txfCountry);

        rowOne.setMinWidth(300);
        rowTwo.setMinWidth(300);

        Label lblHead = new Label("Deltagerinformation");
        lblHead.setFont(Font.font(24));

        rowOne.getChildren().add(lblHead);
        rowOne.getChildren().add(lineOneOne);
        rowOne.getChildren().add(lineTwoOne);
        rowOne.getChildren().add(lineThreeOne);
        rowOne.getChildren().add(lineFourOne);
        rowOne.getChildren().add(lineFiveOne);

        rowTwo.getChildren().add(new Label(""));
        rowTwo.getChildren().add(lineOneTwo);
        rowTwo.getChildren().add(lineTwoTwo);
        rowTwo.getChildren().add(lineThreeTwo);

        cbxCompanion.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                companionFields(pane, newValue);
            }
        });
        cbxHotel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                hotelFields(pane, newValue);
            }
        });

        pane.add(lblHeadline, 0, 0);
        pane.add(rowOne, 0, 1);
        pane.add(rowTwo, 1, 1);
        pane.add(btnSignUp, 3, 0);
    }

    private void companionFields(GridPane pane, boolean show) {
        Label lblCompanionHeadline = new Label("Program for ledsagere");
        lblCompanionHeadline.setFont(Font.font(24));
        HBox companion = new HBox();
        txfCompanionName = new TextField();
        companion.getChildren().add(new Label("Ledsagernavn:"));
        companion.getChildren().add(txfCompanionName);
        Label lblTourName;
        Label lblTourDate;
        Label lblPriceAndDescription;
        Button btnAddTour;
        HBox companionHbox;

        if (show) {
            pane.add(lblCompanionHeadline, 0, rowCount, 2, 1);
            pane.add(companion, 0, rowCount + 1, 2, 1);
            rowCount += 2;
            for (int i = 0; i < selectedConference.getTours().size(); i++) {
                companionHbox = new HBox();
                int finalI = i;
                lblTourName = new Label(selectedConference.getTours().get(i).getTourType().getName());
                lblTourDate = new Label(selectedConference.getTours().get(i).getDate().toString());
                lblPriceAndDescription = new Label("Beskrivelse... ");
                btnAddTour = new Button("Tilmeld");
                btnAddTour.setOnAction(event -> saveTourBooking(selectedConference.getTours().get(finalI)));
                companionHbox.getChildren().addAll(lblTourName, lblTourDate, lblPriceAndDescription, btnAddTour);
                pane.add(companionHbox, 0, rowCount + i);
                rowCount++;
            }
            rowCount += 2;
            sizeToScene();
        } else if (!show) {
            // remove all content from companion from pane...
            // pane.getChildren().remove(lblCompanionHeadline);
            sizeToScene();
        }
    }

    private void hotelFields(GridPane pane, boolean show) {

        Label lblHotelHeadline = new Label("Overnatningsønsker");
        lblHotelHeadline.setFont(Font.font(24));
        cbbHotels = new ComboBox<>();
        cbbHotels.getItems().addAll(Service.getHotelsFromStorage(selectedConference));
        cbbHotels.setOnAction(event -> updateHotelDescription());

        if (show) {
            pane.add(lblHotelHeadline, 0, rowCount, 2, 1);
            HBox headlinesHBox = new HBox();
            HBox hotelHBox = new HBox();
            cbxShower = new CheckBox();
            cbxBreakfast = new CheckBox();
            cbxWifi = new CheckBox();

            headlinesHBox.getChildren().addAll(new Label("Hotel"), new Label("Priser: (dobbelt/enkelt)"),
                    new Label("Bad"), new Label("Mad"), new Label("Wifi"));
            hotelHBox.getChildren().addAll(cbbHotels, lblHotelDescription, cbxShower, cbxBreakfast, cbxWifi);
            pane.add(headlinesHBox, 0, rowCount + 1);
            pane.add(hotelHBox, 0, rowCount + 2);

            sizeToScene();
        } else if (!show) {
            // remove all content from hotel from pane...
            // pane.getChildren().remove(lblHotelHeadline);
            sizeToScene();
        }
    }

    private void updateHotelDescription() {
        lblHotelDescription.setText(cbbHotels.getSelectionModel().getSelectedItem().getPriceDouble() + "/"
                + cbbHotels.getSelectionModel().getSelectedItem().getPriceSingle());
    }

    private void createBooking() {
        Participant p = Service.createParticipant(txfName.getText(), txfAdress.getText(),
                Integer.parseInt(txfPhoneNr.getText()));
        Hotel h = cbbHotels.getSelectionModel().getSelectedItem();
        Booking b = Service.createBooking(cbxWifi.isSelected(), cbxBreakfast.isSelected(), cbxShower.isSelected(),
                cbxSpeaker.isSelected(), p, selectedConference, h, cbxCompanion.isSelected(),
                txfCompanionName.getText());
        addTourBookingsToCompanion(b);
    }

    private void saveTourBooking(Tour tour) {
        bookedTours.add(tour);
    }

    private void addTourBookingsToCompanion(Booking booking) {

        for (Tour t : bookedTours) {
            Service.addTourToCompanion(t, booking);
        }
    }
}
