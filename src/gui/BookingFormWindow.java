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

import java.nio.charset.IllegalCharsetNameException;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        this.selectedConference = selectedConference;
        GridPane pane = new GridPane();
        initContent(pane);
        lblHeadline.setText("Tilmelding til: " + this.selectedConference.getConferenceType().getName() + " ("
                + this.selectedConference.getDuration() + " dages varighed)");
        lblHeadline.setFont(Font.font(32));

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    // -----------------------------------------------------------------
    private Label lblHeadline = new Label();
    private Label lblHotelDescription = new Label();
    private TextField txfName, txfAdress, txfCity, txfContry, txfPhoneNr, txfCompanionName;
    private CheckBox cbxSpeaker, cbxCompanion, cbxHotel, cbxCompany;
    private ComboBox<Company> cbbCompanies;
    private ComboBox<Hotel> cbbHotels;
    private ComboBox<Participant> cbbParticipants;
    private Button btnSignUp = new Button("Tilmeld");
    private Label lblTotalPrice = new Label();
    private Label lblCompanionPrice = new Label();
    private Label lblHotelPrice = new Label("Total pris : 0.0 kr.");
    private CheckBox cbxShower;
    private CheckBox cbxBreakfast;
    private CheckBox cbxWifi;
    private VBox companionInfo;
    private VBox hotelInfo;

    private Conference selectedConference;
    private int rowCount = 3;
    private double totalPrice = 0.0;
    ArrayList<Tour> bookedTours = new ArrayList<>();

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        companionInfo = new VBox();
        companionInfo.setSpacing(10);
        hotelInfo = new VBox();
        hotelInfo.setSpacing(10);

        txfName = new TextField();
        txfAdress = new TextField();
        txfCity = new TextField();
        txfContry = new TextField();
        txfPhoneNr = new TextField();

        Label lblName = new Label("Navn");
        Label lblAdress = new Label("Adresse");
        Label lblCity = new Label("By");
        Label lblCountry = new Label("Land");
        Label lblPhoneNr = new Label("Telefonnr.");

        ArrayList<Label> labelsParticipants = new ArrayList<>();
        labelsParticipants.addAll(Arrays.asList(lblName, lblAdress, lblCity, lblCountry, lblPhoneNr, lblTotalPrice,
                lblCompanionPrice, lblHotelPrice));

        for (Label l : labelsParticipants) {
            l.setMinWidth(100);
        }

        cbxSpeaker = new CheckBox("Foredragsholder?");
        cbxCompanion = new CheckBox("Medtager ledsager");
        cbxHotel = new CheckBox("Ønsker overnatning");
        cbxCompany = new CheckBox("Firmabetaling");

        cbbCompanies = new ComboBox<>();
        cbbCompanies.getItems().addAll(Service.getCompaniesFromStorage());

        cbbParticipants = new ComboBox<>();
        cbbParticipants.getItems().addAll(Service.getParticipantsFromStorage());
        HBox participantsAndLabel = new HBox();
        participantsAndLabel.getChildren().add(new Label("Allerede oprettet?"));
        participantsAndLabel.getChildren().add(cbbParticipants);
        cbbParticipants.setOnAction(event -> fillParticipantInfo());
        btnSignUp.setOnAction(event -> createBooking());
        cbbCompanies.setDisable(true);

        VBox rowOne = new VBox();
        rowOne.setSpacing(10);
        VBox rowTwo = new VBox();
        rowTwo.setSpacing(10);
        VBox rowThree = new VBox();
        rowThree.setSpacing(10);

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
        lineFourOne.setSpacing(10);
        lineFourOne.getChildren().add(cbxCompany);
        lineFourOne.getChildren().add(cbbCompanies);

        HBox lineOneTwo = new HBox();
        lineOneTwo.getChildren().add(cbxSpeaker);

        HBox lineTwoTwo = new HBox();
        lineTwoTwo.getChildren().add(lblPhoneNr);
        lineTwoTwo.getChildren().add(txfPhoneNr);

        HBox lineThreeTwo = new HBox();
        lineThreeTwo.getChildren().add(lblCountry);
        lineThreeTwo.getChildren().add(txfContry);

        HBox lineFourTwo = new HBox();
        lineFourTwo.setSpacing(10);
        lineFourTwo.getChildren().add(cbxCompanion);
        lineFourTwo.getChildren().add(cbxHotel);

        rowOne.setMinWidth(300);
        rowTwo.setMinWidth(300);

        Label lblHead = new Label("Deltagerinformation");
        lblHead.setFont(Font.font(24));

        rowOne.getChildren().add(lineOneOne);
        rowOne.getChildren().add(lineTwoOne);
        rowOne.getChildren().add(lineThreeOne);
        rowOne.getChildren().add(lineFourOne);

        rowTwo.getChildren().add(lineOneTwo);
        rowTwo.getChildren().add(lineTwoTwo);
        rowTwo.getChildren().add(lineThreeTwo);
        rowTwo.getChildren().add(lineFourTwo);

        rowThree.getChildren().add(btnSignUp);
        rowThree.getChildren().add(participantsAndLabel);
        rowThree.getChildren().add(lblTotalPrice);

        ArrayList<HBox> Hboxs = new ArrayList<>();
        Hboxs.addAll(Arrays.asList(lineOneOne, lineTwoOne, lineThreeOne, lineFourOne, lineOneTwo, lineTwoTwo,
                lineThreeTwo, lineFourTwo));

        for (HBox h : Hboxs) {
            h.setMinHeight(30);
            h.setAlignment(Pos.CENTER_LEFT);
        }

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

        cbxSpeaker.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                updatePriceFields();
            }
        });

        cbxCompany.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                cbbCompanies.setDisable(!cbxCompany.isSelected());
            }
        });

        pane.add(lblHeadline, 0, 0, 3, 1);
        pane.add(lblHead, 0, 1, 2, 1);
        pane.add(rowOne, 0, 2);
        pane.add(rowTwo, 1, 2);
        pane.add(rowThree, 2, 2);
        pane.add(companionInfo, 0, 3, 3, 1);
        pane.add(hotelInfo, 0, 4, 3, 1);
        pane.add(lblTotalPrice, 2, 5);
        updatePriceFields();
    }

    private void fillParticipantInfo() {
        Participant p = cbbParticipants.getSelectionModel().getSelectedItem();
        txfName.setText(p.getName());
        txfCity.setText(p.getTown());
        txfAdress.setText(p.getAdress());
        txfContry.setText(p.getCountry());
        txfPhoneNr.setText(p.getPhoneNumber() + "");
    }

    private void companionFields(GridPane pane, boolean show) {
        Label lblCompanionHeadline = new Label("Program for ledsagere");
        lblCompanionHeadline.setFont(Font.font(24));
        HBox companion = new HBox();
        companion.setSpacing(10);
        txfCompanionName = new TextField();
        companion.getChildren().add(new Label("Ledsagernavn:"));
        companion.getChildren().add(txfCompanionName);
        Label lblTourName;
        Label lblTourDate;
        Label lblPriceAndDescription;
        Button btnAddTour;
        HBox companionHbox;

        if (show) {
            companionInfo.getChildren().add(lblCompanionHeadline);
            companionInfo.getChildren().add(companion);
            rowCount += 2;
            for (int i = 0; i < selectedConference.getTours().size(); i++) {
                companionHbox = new HBox();
                companionHbox.setSpacing(10);
                int finalI = i;
                lblTourName = new Label(selectedConference.getTours().get(i).getTourType().getName());
                lblTourName.setMinWidth(200);
                lblTourDate = new Label(selectedConference.getTours().get(i).getDate().toString());
                lblTourDate.setPrefWidth(100);
                lblPriceAndDescription = new Label(selectedConference.getTours().get(i).getTourType().getDescription());
                lblPriceAndDescription.setMinWidth(200);
                btnAddTour = new Button("Tilmeld");
                btnAddTour.setOnAction(event -> {
                    saveTourBooking(selectedConference.getTours().get(finalI));
                    updatePriceFields();
                    sizeToScene();
                });
                companionHbox.getChildren().addAll(lblTourName, lblTourDate, lblPriceAndDescription, btnAddTour);
                companionInfo.getChildren().add(companionHbox);
            }
            HBox companionPrice = new HBox();
            companionPrice.setAlignment(Pos.CENTER_RIGHT);
            companionPrice.getChildren().add(lblCompanionPrice);
            companionInfo.getChildren().add(companionPrice);
            updatePriceFields();
            sizeToScene();
        } else if (!show) {
            companionInfo.getChildren().clear();
            sizeToScene();
        }
    }

    private void hotelFields(GridPane pane, boolean show) {

        Label lblHotelHeadline = new Label("Overnatningsønsker");
        lblHotelHeadline.setFont(Font.font(24));
        cbbHotels = new ComboBox<>();
        cbbHotels.getItems().addAll(selectedConference.getLocation().getHotels());
        cbbHotels.setOnAction(event -> updateHotelDescription());

        if (show) {
            hotelInfo.getChildren().add(lblHotelHeadline);
            HBox headlinesHBox = new HBox();
            HBox hotelHBox = new HBox();
            cbxShower = new CheckBox();
            cbxBreakfast = new CheckBox();
            cbxWifi = new CheckBox();
            Label lblHotel = new Label("Hotel");
            Label lblPrice = new Label("Priser: (dobbelt/enkelt)");
            Label lblShower = new Label("Bad");
            Label lblBreakfast = new Label("Mad");
            Label lblWifi = new Label("Wifi");
            lblHotel.setMinWidth(200);
            cbbHotels.setMinWidth(200);
            lblPrice.setMinWidth(250);
            lblHotelDescription.setMinWidth(250);
            lblShower.setMinWidth(50);
            cbxShower.setMinWidth(50);
            lblBreakfast.setMinWidth(50);
            cbxBreakfast.setMinWidth(50);
            lblWifi.setMinWidth(50);
            cbxWifi.setMinWidth(50);

            headlinesHBox.getChildren().addAll(lblHotel, lblPrice, lblShower, lblBreakfast, lblWifi);
            headlinesHBox.setSpacing(10);
            hotelHBox.getChildren().addAll(cbbHotels, lblHotelDescription, cbxShower, cbxBreakfast, cbxWifi);
            hotelHBox.setSpacing(10);
            hotelInfo.getChildren().add(headlinesHBox);
            hotelInfo.getChildren().add(hotelHBox);
            HBox hotelPrice = new HBox();
            hotelPrice.setAlignment(Pos.CENTER_RIGHT);
            hotelPrice.getChildren().add(lblHotelPrice);

            hotelInfo.getChildren().add(hotelPrice);

            cbxWifi.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    updatePriceFields();
                }
            });

            cbxShower.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    updatePriceFields();
                }
            });

            cbxBreakfast.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    updatePriceFields();
                }
            });

            updatePriceFields();
            sizeToScene();
        } else if (!show) {
            hotelInfo.getChildren().clear();
            sizeToScene();
        }
    }

    private void updateHotelDescription() {
        lblHotelDescription.setText(cbbHotels.getSelectionModel().getSelectedItem().getPriceDouble() + "/"
                + cbbHotels.getSelectionModel().getSelectedItem().getPriceSingle());
        updatePriceFields();
        sizeToScene();
    }

    private void createBooking() {
        Participant p = Service.createParticipant(txfName.getText().trim(), txfAdress.getText().trim(),
                Integer.parseInt(txfPhoneNr.getText().trim()), txfCity.getText().trim(), txfContry.getText().trim());
        Hotel selectedHotel = cbbHotels.getSelectionModel().getSelectedItem();
        Booking b = Service.createBooking(cbxWifi.isSelected(), cbxBreakfast.isSelected(), cbxShower.isSelected(),
                cbxSpeaker.isSelected(), p, selectedConference, selectedHotel, cbxCompanion.isSelected(),
                txfCompanionName.getText().trim());
        addTourBookingsToCompanion(b);
        hide();
    }

    private void saveTourBooking(Tour tour) {
        bookedTours.add(tour);
    }

    private void addTourBookingsToCompanion(Booking booking) {

        for (Tour t : bookedTours) {
            Service.addTourToCompanion(t, booking);
        }
    }

    private void updatePriceFields() {
        double companionPrice = 0.0;
        double hotelPricePrDay = 0.0;
        if (!cbxSpeaker.isSelected()) {
            totalPrice = selectedConference.getPrice() * selectedConference.getDuration();
        } else {
            totalPrice = 0.0;
        }
        if (cbxCompanion.isSelected()) {
            for (Tour t : bookedTours) {
                companionPrice += t.getTourType().getPrice();
            }
            lblCompanionPrice.setText("Ledsager udgifter: " + companionPrice);
            totalPrice += companionPrice;
            if (cbxHotel.isSelected() && cbbHotels.getSelectionModel().getSelectedItem() != null) {
                Hotel hotel = cbbHotels.getSelectionModel().getSelectedItem();
                hotelPricePrDay = hotel.getPriceDouble();
                if (cbxWifi.isSelected()) {
                    hotelPricePrDay += hotel.getWifiPrice();
                }
                if (cbxShower.isSelected()) {
                    hotelPricePrDay += hotel.getShowerPrice();
                }
                if (cbxBreakfast.isSelected()) {
                    hotelPricePrDay += hotel.getBreakfastPrice();
                }
                lblHotelPrice.setText(
                        "Overnatningspris: " + (hotelPricePrDay * (selectedConference.getDuration() - 1)) + " kr.");
                totalPrice += hotelPricePrDay * (selectedConference.getDuration() - 1);
            }
        } else {
            if (cbxHotel.isSelected() && cbbHotels.getSelectionModel().getSelectedItem() != null) {
                Hotel hotel = cbbHotels.getSelectionModel().getSelectedItem();
                hotelPricePrDay = hotel.getPriceSingle();
                if (cbxWifi.isSelected()) {
                    hotelPricePrDay += hotel.getWifiPrice();
                }
                if (cbxShower.isSelected()) {
                    hotelPricePrDay += hotel.getShowerPrice();
                }
                if (cbxBreakfast.isSelected()) {
                    hotelPricePrDay += hotel.getBreakfastPrice();
                }
                lblHotelPrice.setText(
                        "Overnatningspris: " + (hotelPricePrDay * (selectedConference.getDuration() - 1)) + " kr.");
                totalPrice += hotelPricePrDay * (selectedConference.getDuration() - 1);
            }
        }
        lblTotalPrice.setText("Total pris : " + totalPrice + " kr.");
    }
}
