package gui;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.model.Conference;
import model.model.ConferenceType;
import model.model.Location;
import model.model.Tour;
import model.service.Service;
import storage.Storage;

public class ConferenceWindow extends Stage {

    public ConferenceWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // -------------------------------------------------------------------------------------------------
    private Button btnNewConferenceType, btnSave, btnCancel;
    private Label lblStartDate, lblDuration, lblPrice, lblType, lblLocation, lblDescription, lblTourName;
    private TextField txfStartDate, txfDuration, txfPrice;
    private TextArea txaDescription;
    private ComboBox<ConferenceType> cbbType;
    private ComboBox<Location> cbbLocation;
    private DatePicker datePicker, tour;
    private ArrayList<Tour> tours = new ArrayList<>();
    private HBox hbxAllFields;

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        // -------------------------------------
        // HBoxes

        hbxAllFields = new HBox();
        hbxAllFields.setSpacing(20);
        pane.add(hbxAllFields, 0, 1);

        HBox hbxType = new HBox();
        hbxType.setSpacing(3);
        pane.add(hbxType, 0, 0);

        HBox hbxDescription = new HBox();
        hbxAllFields.getChildren().add(hbxDescription);

        VBox vbxNewType = new VBox();
        vbxNewType.setSpacing(80);
        vbxNewType.setAlignment(Pos.TOP_CENTER);
        hbxDescription.getChildren().add(vbxNewType);

        VBox vbxFields = new VBox();
        vbxFields.setSpacing(10);
        hbxAllFields.getChildren().add(vbxFields);

        HBox hbxLocation = new HBox();
        vbxFields.getChildren().add(hbxLocation);

        HBox hbxStartDate = new HBox();
        vbxFields.getChildren().add(hbxStartDate);

        HBox hbxDuration = new HBox();
        vbxFields.getChildren().add(hbxDuration);

        HBox hbxPrice = new HBox();
        vbxFields.getChildren().add(hbxPrice);

        HBox hbxButtons = new HBox();
        hbxButtons.setSpacing(20);
        hbxButtons.setAlignment(Pos.CENTER_RIGHT);
        vbxFields.getChildren().add(hbxButtons);

        // --------------------------------------
        // Labels

        lblStartDate = new Label("Start dato: ");
        lblStartDate.setMinWidth(120);
        hbxStartDate.getChildren().add(lblStartDate);

        lblDuration = new Label("Varighed: ");
        lblDuration.setMinWidth(120);
        hbxDuration.getChildren().add(lblDuration);

        lblPrice = new Label("Pris pr. dag: ");
        lblPrice.setMinWidth(120);
        hbxPrice.getChildren().add(lblPrice);

        lblType = new Label("Konference type: ");
        lblType.setMinWidth(120);
        hbxType.getChildren().add(lblType);

        lblLocation = new Label("Beliggenhed: ");
        lblLocation.setMinWidth(120);
        hbxLocation.getChildren().add(lblLocation);

        lblDescription = new Label("Konference beskrivelse: ");
        vbxNewType.getChildren().add(lblDescription);

        // ------------------------------------
        // date picker
        datePicker = new DatePicker();
        hbxStartDate.getChildren().add(datePicker);

        // --------------------------------------
        // Buttons

        btnNewConferenceType = new Button("Ny type");
        btnNewConferenceType.setAlignment(Pos.CENTER);
        vbxNewType.getChildren().add(btnNewConferenceType);

        btnNewConferenceType.setOnAction(event -> CreateTypeAction());

        btnSave = new Button("Gem");
        hbxButtons.getChildren().add(btnSave);
        btnSave.setOnAction(event -> CreateConferenceAction());

        btnCancel = new Button("Annuller");
        hbxButtons.getChildren().add(btnCancel);
        btnCancel.setOnAction(event -> cancelAction());

        // -------------------------------------
        // Comboboxes
        cbbLocation = new ComboBox<>();
        cbbLocation.getItems().addAll(Storage.getLocations());
        cbbLocation.setOnAction(event -> updateLocation(pane));
        hbxLocation.getChildren().add(cbbLocation);

        cbbType = new ComboBox<>();
        cbbType.getItems().addAll(Storage.getConferenceTypes());
        cbbType.setOnAction(event -> UpdateDescription());
        cbbType.setMinWidth(300);
        cbbType.setMaxWidth(300);
        hbxType.getChildren().add(cbbType);

        // ------------------------------------
        // TextFields

        txfDuration = new TextField("");
        hbxDuration.getChildren().add(txfDuration);

        txfPrice = new TextField("");
        hbxPrice.getChildren().add(txfPrice);

        txaDescription = new TextArea("");
        hbxDescription.getChildren().add(txaDescription);
        txaDescription.setMinWidth(300);
        txaDescription.setMaxWidth(300);

        // ---------------------------------

    }

    // ----------------------------------------------------------------
    private void CreateTypeAction() {
        ConferenceTypeWindow type = new ConferenceTypeWindow("Ny konference type");
        type.showAndWait();

        cbbType.getItems().clear();
        cbbType.getItems().addAll(Storage.getConferenceTypes());
    }

    private void CreateConferenceAction() {
        double price = 0;
        short duration = 0;
        LocalDate startDate = datePicker.getValue();
        ConferenceType type = cbbType.getValue();
        Location location = cbbLocation.getValue();

        try {
            price = Double.parseDouble(txfPrice.getText().trim());
            duration = Short.parseShort(txfDuration.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothin
        }
        Conference conference = Service.createConference(startDate, duration, price, type, location);
        for (int i = 0; i < tours.size(); i++) {
            Service.addTourToConference(tours.get(i), conference);

        }
        hide();
    }

    private void UpdateDescription() {
        txaDescription.setText(cbbType.getSelectionModel().getSelectedItem().getDescription());

    }

    // DatePicker tour1;
    private ArrayList<DatePicker> tourDates = new ArrayList<>();

    private void updateLocation(GridPane pane) {
        VBox vbxTours = new VBox();
        vbxTours.setSpacing(10);
        HBox hbxTour;
        hbxAllFields.getChildren().add(vbxTours);

        for (int i = 0; i < cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().size(); i++) {
            int j = i;
            hbxTour = new HBox();
            lblTourName = new Label(cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().get(i).getName());
            lblTourName.setMinWidth(150);
            lblTourName.setMaxWidth(150);
            hbxTour.getChildren().add(lblTourName);
            // tour1 = new DatePicker();
            DatePicker d = new DatePicker();
            tourDates.add(d);
            tourDates.get(i).setOnAction(event -> tourDatePickerAction(new Tour(tourDates.get(j).getValue(),
                    cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().get(j))));
            tourDates.get(i).setMaxWidth(110);
            hbxTour.getChildren().add(d);
            vbxTours.getChildren().add(hbxTour);
            // pane.add(tourDates.get(i), 5, i);

        }
        sizeToScene();

    }

    private void tourDatePickerAction(Tour tour) {
        tours.add(tour);
    }

    private void cancelAction() {
        hide();
    }
}