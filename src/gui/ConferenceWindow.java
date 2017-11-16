package gui;

import java.time.LocalDate;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.model.Conference;
import model.model.ConferenceType;
import model.model.Hotel;
import model.model.Location;
import model.model.Tour;
import model.model.TourType;
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
    private Label lblStartDate, lblLength, lblPrice, lblType, lblLocation, lblDescription, lblTourName;
    private TextField txfStartDate, txfLength, txfPrice;
    private TextArea txaDescription;
    private ComboBox<ConferenceType> cbbType;
    private ComboBox<Location> cbbLocation;
    private DatePicker datePicker, tour;
    private ArrayList<Tour> tours = new ArrayList<>();

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
        // date picker
        datePicker = new DatePicker();
        pane.add(datePicker, 3, 1);

        // --------------------------------------
        // Buttons

        btnNewConferenceType = new Button("Ny type");
        pane.add(btnNewConferenceType, 0, 4);

        btnNewConferenceType.setOnAction(event -> CreateTypeAction());

        btnSave = new Button("Gem");
        pane.add(btnSave, 1, 4);
        btnSave.setOnAction(event -> CreateConferenceAction());

        btnCancel = new Button("Cancel");
        pane.add(btnCancel, 2, 4);
        btnCancel.setOnAction(event -> cancelAction());

        // -------------------------------------
        // Comboboxes
        cbbLocation = new ComboBox<>();
        cbbLocation.getItems().addAll(Storage.getLocations());
        cbbLocation.setOnAction(event -> updateLocation(pane));
        pane.add(cbbLocation, 3, 0);

        cbbType = new ComboBox<>();
        cbbType.getItems().addAll(Storage.getConferenceTypes());
        cbbType.setOnAction(event -> UpdateDescription());
        pane.add(cbbType, 1, 0);

        // ------------------------------------
        // TextFields

        txfLength = new TextField("");
        pane.add(txfLength, 3, 2);

        txfPrice = new TextField("");
        pane.add(txfPrice, 3, 3);

        txaDescription = new TextArea("");
        pane.add(txaDescription, 1, 1);
        txaDescription.setMaxWidth(300);
        // --------------------------------------
        // Labels

        lblStartDate = new Label("Start dato: ");
        pane.add(lblStartDate, 2, 1);

        lblLength = new Label("Længde: ");
        pane.add(lblLength, 2, 2);

        lblPrice = new Label("Pris: ");
        pane.add(lblPrice, 2, 3);

        lblType = new Label("vælg Type: ");
        pane.add(lblType, 0, 0);

        lblLocation = new Label("Vælg beliggenhed: ");
        pane.add(lblLocation, 2, 0);

        lblDescription = new Label("Konference beskrivelse: ");
        pane.add(lblDescription, 0, 1);

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
            duration = Short.parseShort(txfLength.getText().trim());
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

        for (int i = 0; i < cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().size(); i++) {
            int j = i;
            lblTourName = new Label(cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().get(i).getName());
            pane.add(lblTourName, 4, i);
            // tour1 = new DatePicker();
            DatePicker d = new DatePicker();
            tourDates.add(d);
            tourDates.get(i).setOnAction(event -> tourDatePickerAction(new Tour(tourDates.get(j).getValue(),
                    cbbLocation.getSelectionModel().getSelectedItem().getToursTypes().get(j))));
            tourDates.get(i).setMaxWidth(110);
            pane.add(tourDates.get(i), 5, i);

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