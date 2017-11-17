package gui;

import java.util.ArrayList;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.SelectionModel;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
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
import javafx.scene.layout.VBox;
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
        pane.setGridLinesVisible(true);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // ---------------------------------------
        VBox vbxTextFields = new VBox();
        pane.add(vbxTextFields, 1, 1);
        vbxTextFields.setSpacing(27);
        vbxTextFields.setPadding(new Insets(27, 0, 0, 0));
        vbxTextFields.setAlignment(Pos.TOP_LEFT);

        HBox hbxLists = new HBox();
        hbxLists.setSpacing(10);
        hbxLists.setAlignment(Pos.TOP_CENTER);
        pane.add(hbxLists, 2, 1);

        VBox vbxTours = new VBox();
        vbxTours.setSpacing(10);
        hbxLists.getChildren().add(vbxTours);

        VBox vbxHotels = new VBox();
        vbxHotels.setSpacing(10);
        hbxLists.getChildren().add(vbxHotels);

        HBox hbxName = new HBox();
        hbxName.setAlignment(Pos.CENTER_LEFT);
        vbxTextFields.getChildren().add(hbxName);

        HBox hbxAdress = new HBox();
        hbxAdress.setAlignment(Pos.CENTER_LEFT);
        vbxTextFields.getChildren().add(hbxAdress);

        HBox hbxDescription = new HBox();
        hbxDescription.setAlignment(Pos.CENTER_LEFT);
        vbxTextFields.getChildren().add(hbxDescription);

        HBox hbxMaxParticipants = new HBox();
        hbxMaxParticipants.setAlignment(Pos.CENTER_LEFT);
        vbxTextFields.getChildren().add(hbxMaxParticipants);

        HBox hbxButtons = new HBox();
        hbxButtons.setAlignment(Pos.CENTER);
        hbxButtons.setSpacing(50);
        vbxTextFields.getChildren().add(hbxButtons);

        // ----------------------------------------------
        // Labels

        lblName = new Label("Navn: ");
        lblName.setMinWidth(80);
        hbxName.getChildren().add(lblName);

        lblAdress = new Label("Adresse: ");
        lblAdress.setMinWidth(80);
        hbxAdress.getChildren().add(lblAdress);

        lblDescription = new Label("Beskrivelse: ");
        lblDescription.setMinWidth(80);
        hbxDescription.getChildren().add(lblDescription);

        lblTour = new Label("Udflugter");
        vbxTours.getChildren().add(lblTour);

        lblHotel = new Label("Hoteller");
        vbxHotels.getChildren().add(lblHotel);

        lblMaxParticipants = new Label("Max deltagere: ");
        lblMaxParticipants.setMinWidth(80);
        hbxMaxParticipants.getChildren().add(lblMaxParticipants);

        // -----------------------------------
        // listview
        tours = new ListView<>();
        tours.setPrefWidth(200);
        tours.setMaxHeight(200);
        tours.getItems().setAll(Service.getTourTypesFromStorage());
        vbxTours.getChildren().add(tours);

        tours.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ChangeListener<TourType> listener1 = (ov, oldTourType, newTourType) -> selectedTourTypeChanged();
        tours.getSelectionModel().selectedItemProperty().addListener(listener1);

        hotels = new ListView<>();
        hotels.setPrefWidth(200);
        hotels.setMaxHeight(200);
        hotels.getItems().setAll(Service.getHotelsFromStorage());
        vbxHotels.getChildren().add(hotels);

        hotels.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        ChangeListener<Hotel> listener2 = (ov, oldHotel, newHotel) -> selectedHotelChanged();
        hotels.getSelectionModel().selectedItemProperty().addListener(listener2);

        // ------------------------------------
        // buttons

        btnCreateTour = new Button("Opret Tour");
        vbxTours.getChildren().add(btnCreateTour);
        btnCreateTour.setOnAction(event -> CreateTourAction());

        btnAdminTour = new Button("Administrer tour");
        vbxTours.getChildren().add(btnAdminTour);
        btnAdminTour.setDisable(true);
        btnAdminTour.setOnAction(event -> AdminTourAction());

        btnCreateHotel = new Button("Opret Hotel");
        vbxHotels.getChildren().add(btnCreateHotel);
        btnCreateHotel.setOnAction(event -> CreateHotelAction());

        btnAdminHotel = new Button("Administrer Hotel");
        vbxHotels.getChildren().add(btnAdminHotel);
        btnAdminHotel.setDisable(true);
        btnAdminHotel.setOnAction(event -> AdminHotelAction());

        btnSave = new Button("Gem");
        hbxButtons.getChildren().add(btnSave);
        btnSave.setOnAction(event -> saveAction());

        btnCancel = new Button("Anuller");
        hbxButtons.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> cancelAction());

        btnAddTourToLocation = new Button("Tilføj udflugt til beliggenhed");
        vbxTours.getChildren().add(btnAddTourToLocation);
        btnAddTourToLocation.setOnAction(event -> AddTourToLocation());

        btnAddHotelToLocation = new Button("Tilføj hotel til beliggenhed");
        vbxHotels.getChildren().add(btnAddHotelToLocation);
        btnAddHotelToLocation.setOnAction(event -> addHotelToLocation());

        // ----------------------------------
        // textfield

        txfName = new TextField("");
        hbxName.getChildren().add(txfName);
        txfName.setEditable(true);

        txfAdress = new TextField("");
        hbxAdress.getChildren().add(txfAdress);
        txfName.setEditable(true);

        txfDescription = new TextField("");
        hbxDescription.getChildren().add(txfDescription);
        txfName.setEditable(true);

        txfMaxParticipants = new TextField();
        hbxMaxParticipants.getChildren().add(txfMaxParticipants);
        txfMaxParticipants.setEditable(true);

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

    private void cancelAction() {
        hide();
    }

}
