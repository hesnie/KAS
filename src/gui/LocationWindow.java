package gui;

import java.util.ArrayList;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.model.Hotel;
import model.model.Location;
import model.model.Tour;
import model.model.TourType;
import model.service.Service;

public class LocationWindow extends Stage {

    public LocationWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // -----------------------------------------------------------------------------------------------
    private Label lblName, lblAdress, lblDescription, lblTour, lblHotel, lblMaxParticipants;
    private Button btnCreateTour, btnCreateHotel, btnAdminTour, btnAdminHotel, btnSave, btnCancel, btnAddTourToLocation,
            btnAddHotelToLocation;
    private ListView<TourType> tours;
    private ListView<Hotel> hotels;
    private TextField txfName, txfAdress, txfDescription, txfMaxParticipants;

    private ArrayList<TourType> tourTypes = new ArrayList<>();
    private ArrayList<Hotel> selHotels = new ArrayList<>();

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // ------------------------------------
        // buttons

        btnCreateTour = new Button("Create Tour");
        pane.add(btnCreateTour, 2, 2);
        btnCreateTour.setOnAction(event -> CreateTourAction());

        btnAdminTour = new Button("Administrer tour");
        pane.add(btnAdminTour, 2, 3);
        btnAdminTour.setDisable(true);
        btnAdminTour.setOnAction(event -> AdminTourAction());

        btnCreateHotel = new Button("Create Hotel");
        pane.add(btnCreateHotel, 3, 2);
        btnCreateHotel.setOnAction(event -> CreateHotelAction());

        btnAdminHotel = new Button("Administrer Hotel");
        pane.add(btnAdminHotel, 3, 3);
        btnAdminHotel.setDisable(true);
        btnAdminHotel.setOnAction(event -> AdminHotelAction());

        btnSave = new Button("Gem");
        pane.add(btnSave, 2, 5);
        btnSave.setOnAction(event -> saveAction());

        btnCancel = new Button("Cancel");
        pane.add(btnCancel, 3, 5);

        btnAddTourToLocation = new Button("Tilføj tour til beliggenhed");
        pane.add(btnAddTourToLocation, 2, 4);
        btnAddTourToLocation.setOnAction(event -> AddTourToLocation());

        btnAddHotelToLocation = new Button("Tilføj hotel til beliggenhed");
        pane.add(btnAddHotelToLocation, 3, 4);
        btnAddHotelToLocation.setOnAction(event -> addHotelToLocation());
        // -----------------------------------
        // listview
        tours = new ListView<>();
        pane.add(tours, 2, 1);
        tours.setPrefWidth(200);
        tours.setPrefHeight(200);
        tours.getItems().setAll(Service.getTourTypesFromStorage());

        tours.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ChangeListener<TourType> listener1 = (ov, oldTourType, newTourType) -> selectedTourTypeChanged();
        tours.getSelectionModel().selectedItemProperty().addListener(listener1);

        hotels = new ListView<>();
        pane.add(hotels, 3, 1);
        hotels.setPrefWidth(200);
        hotels.setPrefHeight(200);
        hotels.getItems().setAll(Service.getHotelsFromStorage());

        hotels.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ChangeListener<Hotel> listener2 = (ov, oldHotel, newHotel) -> selectedHotelChanged();
        hotels.getSelectionModel().selectedItemProperty().addListener(listener2);

        // ----------------------------------
        // textfield

        txfName = new TextField("");
        pane.add(txfName, 1, 1);
        txfName.setEditable(true);

        txfAdress = new TextField("");
        pane.add(txfAdress, 1, 2);
        txfName.setEditable(true);

        txfDescription = new TextField("");
        pane.add(txfDescription, 1, 3);
        txfName.setEditable(true);

        txfMaxParticipants = new TextField();
        pane.add(txfMaxParticipants, 1, 4);

        // ----------------------------------------------
        // Labels

        lblName = new Label("Navn: ");
        pane.add(lblName, 0, 1);

        lblAdress = new Label("Adress: ");
        pane.add(lblAdress, 0, 2);

        lblDescription = new Label("Beskrivelse: ");
        pane.add(lblDescription, 0, 3);

        lblTour = new Label("Tours");
        pane.add(lblTour, 2, 0);

        lblHotel = new Label("Hoteller");
        pane.add(lblHotel, 3, 0);

        lblMaxParticipants = new Label("Max deltagere: ");
        pane.add(lblMaxParticipants, 0, 4);

    }

    // -------------------------------------------------
    private void AddTourToLocation() {
        tourTypes.addAll(tours.getSelectionModel().getSelectedItems());

    }

    private void addHotelToLocation() {
        selHotels.addAll(hotels.getSelectionModel().getSelectedItems());
    }

    private void saveAction() {
        String name = txfName.getText().trim();
        String adress = txfAdress.getText().trim();
        short maxParticipants = 0;
        String description = txfDescription.getText().trim();
        try {
            maxParticipants = Short.parseShort(txfMaxParticipants.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }
        Location location = Service.createLocation(name, adress, maxParticipants, description);
        for (int i = 0; i < tourTypes.size(); i++) {
            Service.addTourToLocation(tourTypes.get(i), location);
        }
        for (int i = 0; i < selHotels.size(); i++) {
            Service.addHotelToLocation(selHotels.get(i), location);
        }

        hide();

    }

    private void CreateTourAction() {
        CreateTourWindow Tour = new CreateTourWindow("Tour");
        Tour.showAndWait();

        updateAction();
    }

    private int selectedTourTypeChanged() {
        btnAdminTour.setDisable(false);

        int tourIndeks = tours.getSelectionModel().getSelectedIndex();
        return tourIndeks;

    }

    private void AdminTourAction() {

        CreateTourWindow Tour = new CreateTourWindow("Tour", selectedTourTypeChanged());
        Tour.showAndWait();

        updateAction();
    }

    private void CreateHotelAction() {
        CreateHotelWindow hotel = new CreateHotelWindow("Hotel");
        hotel.showAndWait();

        updateAction();
    }

    private int selectedHotelChanged() {
        btnAdminHotel.setDisable(false);

        int hotelIndeks = hotels.getSelectionModel().getSelectedIndex();
        return hotelIndeks;
    }

    private void AdminHotelAction() {

        CreateHotelWindow Hotel = new CreateHotelWindow("Tour", selectedHotelChanged());
        Hotel.showAndWait();

        updateAction();
    }

    private void updateAction() {
        tours.getItems().setAll(Service.getTourTypesFromStorage());
        hotels.getItems().setAll(Service.getHotelsFromStorage());
    }

}
