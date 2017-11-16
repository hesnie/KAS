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

    private Button btnCreate, btnAdmin, btnToursAndCompanions, btnHotelsAndParticipants, btnBookingsOnConference;
    private ListView<Conference> lvwConferences;
    private TextArea txaDescription;
    private Label lblError;

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
        btnAdmin.setOnAction(event -> adminAction());

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red");
        hbxButtons.getChildren().add(lblError);

        btnToursAndCompanions = new Button("Udflugter og ledsager");
        hbxButtons.getChildren().add(btnToursAndCompanions);
        btnToursAndCompanions.setOnAction(event -> toursAndCompanionsAction());

        btnHotelsAndParticipants = new Button("Hoteller og deltager");
        hbxButtons.getChildren().add(btnHotelsAndParticipants);
        btnHotelsAndParticipants.setOnAction(event -> hotelsAndParticipants());

        btnBookingsOnConference = new Button("Deltagere til konference");
        hbxButtons.getChildren().add(btnBookingsOnConference);
        btnBookingsOnConference.setOnAction(event -> bookingsOnConference());

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

    public void adminAction() {
        lblError.setText("Det har vi så godt nok ikke lige nået... ");
    }

    public void toursAndCompanionsAction() {
        txaDescription.setText("Udflugter og ledsagere:" + "\n");
        if (lvwConferences.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < Service.listToursAndCompanions(lvwConferences.getSelectionModel().getSelectedItem())
                    .size(); i++) {
                txaDescription.appendText(
                        Service.listToursAndCompanions(lvwConferences.getSelectionModel().getSelectedItem()).get(i)
                                + "\n");
            }
        } else {
            return;
        }
    }

    public void hotelsAndParticipants() {
        txaDescription.setText("Hoteller og deltager:" + "\n");
        if (lvwConferences.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < Service.listHotelsAndParticipants(lvwConferences.getSelectionModel().getSelectedItem())
                    .size(); i++) {
                txaDescription.appendText(
                        Service.listHotelsAndParticipants(lvwConferences.getSelectionModel().getSelectedItem()).get(i)
                                + "\n");
            }
        } else {
            return;
        }
    }

    public void bookingsOnConference() {
        txaDescription.setText("Deltagere til konferencen:" + "\n");
        if (lvwConferences.getSelectionModel().getSelectedItem() != null) {
            for (int i = 0; i < Service.listBookingsOnConference(lvwConferences.getSelectionModel().getSelectedItem())
                    .size(); i++) {
                txaDescription.appendText(
                        Service.listBookingsOnConference(lvwConferences.getSelectionModel().getSelectedItem()).get(i)
                                + "\n");
            }

        } else {
            return;
        }

    }
}