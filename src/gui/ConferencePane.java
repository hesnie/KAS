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
import model.model.Conference;
import model.model.Location;
import model.service.Service;

public class ConferencePane extends GridPane {

    private Button btnCreate, btnAdmin;
    private ListView<Conference> lvwConferences;
    private TextArea txaDescription;

    public ConferencePane() {
        setPadding(new Insets(20));
        setHgap(20);
        setVgap(10);
        setGridLinesVisible(false);

        Label lblComp = new Label("Konferencer");
        this.add(lblComp, 0, 0);

        lvwConferences = new ListView<>();
        this.add(lvwConferences, 0, 1, 1, 3);
        lvwConferences.setPrefWidth(200);
        lvwConferences.setPrefHeight(200);
        lvwConferences.getItems().setAll(Service.getConferencesFromStorage());

        lvwConferences.getSelectionModel().clearSelection();

        ChangeListener<Conference> listener = (ov, oldConference, newConference) -> selectedConferenceChanged();
        lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        txaDescription = new TextArea();
        this.add(txaDescription, 1, 2);
        txaDescription.setEditable(false);

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 4, 3, 1);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_CENTER);

        btnCreate = new Button("Opret konference");
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setOnAction(event -> createConference());

        btnAdmin = new Button("Administrer konference");
        hbxButtons.getChildren().add(btnAdmin);

        // -------------------------------------------------------------------------

    }

    private void createConference() {
        ConferenceWindow conference = new ConferenceWindow("Ny konference");
        conference.showAndWait();

        lvwConferences.getItems().setAll(Service.getConferencesFromStorage());
    }

    private void selectedConferenceChanged() {
        updateControls();
    }

    public void updateControls() {
        Conference conference = lvwConferences.getSelectionModel().getSelectedItem();
        if (conference != null) {
            txaDescription.setText(Service
                    .ConferenceOutputTextForConferenceWindow(lvwConferences.getSelectionModel().getSelectedItem()));
        } else {
            txaDescription.clear();
        }
    }

}
