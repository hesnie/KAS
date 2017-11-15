package gui;

import java.util.ArrayList;
import java.util.Optional;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.model.Location;
import model.service.Service;

public class LocationPane extends GridPane {

    private Button btnCreate, btnAdmin;
    private ListView<Location> lvwLocations;
    private TextArea txaDescription;

    public LocationPane() {
        setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);

        Label lblComp = new Label("Locations");
        this.add(lblComp, 0, 0);

        lvwLocations = new ListView<>();
        this.add(lvwLocations, 0, 1, 1, 3);
        lvwLocations.setPrefWidth(200);
        lvwLocations.setPrefHeight(200);
        lvwLocations.getItems().setAll(Service.getLocationsFromStorage());

        // lvwLocations.getSelectionModel().clearSelection();

        txaDescription = new TextArea("her skal beligenheden beskrives");
        this.add(txaDescription, 1, 2);
        txaDescription.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 4, 3, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        btnCreate = new Button("Opret beliggenhed");
        hbxButtons.getChildren().add(btnCreate);

        btnAdmin = new Button("Administrer beliggenhed");
        hbxButtons.getChildren().add(btnAdmin);

        // -------------------------------------------------------------------------

    }

}
