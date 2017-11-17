package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import model.model.Location;
import model.service.Service;

public class LocationPane extends GridPane {

    private Button btnCreate, btnAdmin;
    private ListView<Location> lvwLocations;
    private TextArea txaDescription;
    private Label lblError;

    public LocationPane() {
        setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(true);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 2, 1, 2);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_LEFT);

        HBox hbxInfo = new HBox();
        this.add(hbxInfo, 0, 1);
        hbxInfo.setSpacing(50);

        Label lblComp = new Label("Beliggenheder");
        lblComp.setFont(Font.font(30));
        this.add(lblComp, 0, 0);

        lvwLocations = new ListView<>();
        hbxInfo.getChildren().add(lvwLocations);
        lvwLocations.setPrefWidth(200);
        lvwLocations.setPrefHeight(200);
        lvwLocations.getItems().setAll(Service.getLocationsFromStorage());

        lvwLocations.getSelectionModel().clearSelection();

        ChangeListener<Location> listener = (ov, oldLocation, newLocation) -> selectedLocationChanged();
        lvwLocations.getSelectionModel().selectedItemProperty().addListener(listener);

        txaDescription = new TextArea("Marker beliggenhed for mere info");
        hbxInfo.getChildren().add(txaDescription);
        txaDescription.setEditable(false);

        btnCreate = new Button("Opret beliggenhed");
        btnCreate.setMinWidth(120);
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> createAction());

        btnAdmin = new Button("Administrer beliggenhed");
        btnAdmin.setMinWidth(150);
        hbxButtons.getChildren().add(btnAdmin);
        btnAdmin.setOnAction(event -> adminAction());

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red");
        this.add(lblError, 0, 4);
        // -------------------------------------------------------------------------

    }

    private void selectedLocationChanged() {
        updateControls();
    }

    public void updateControls() {
        Location location = lvwLocations.getSelectionModel().getSelectedItem();
        if (location != null) {
            txaDescription.setText(Service.locationOutputTextForLocationWindow(location));
        } else {
            txaDescription.clear();
        }
    }

    public void createAction() {
        LocationWindow location = new LocationWindow("Ny lokation");
        location.showAndWait();

        lvwLocations.getItems().setAll(Service.getLocationsFromStorage());

    }

    public void adminAction() {
        lblError.setText("Det har vi så godt nok ikke lige nået... ");
    }

}
