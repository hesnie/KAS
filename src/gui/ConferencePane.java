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
import javafx.scene.text.Font;
import model.model.Conference;
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

        HBox hbxButtons = new HBox(40);
        this.add(hbxButtons, 0, 2, 1, 2);
        hbxButtons.setPadding(new Insets(10, 0, 0, 0));
        hbxButtons.setAlignment(Pos.BASELINE_LEFT);

        HBox hbxInfo = new HBox();
        hbxInfo.setSpacing(50);
        this.add(hbxInfo, 0, 1);

        Label lblComp = new Label("Konferencer");
        lblComp.setFont(Font.font(30));
        this.add(lblComp, 0, 0);

        lvwConferences = new ListView<>();
        hbxInfo.getChildren().add(lvwConferences);
        lvwConferences.setPrefWidth(200);
        lvwConferences.setPrefHeight(200);
        lvwConferences.getItems().setAll(Service.getConferencesFromStorage());

        lvwConferences.getSelectionModel().clearSelection();

        ChangeListener<Conference> listener = (ov, oldConference, newConference) -> selectedConferenceChanged();
        lvwConferences.getSelectionModel().selectedItemProperty().addListener(listener);

        txaDescription = new TextArea("Marker konference for info");
        hbxInfo.getChildren().add(txaDescription);
        txaDescription.setEditable(false);

        btnCreate = new Button("Opret konference");
        hbxButtons.getChildren().add(btnCreate);
        btnCreate.setMinWidth(120);
        btnCreate.setOnAction(event -> createConference());

        btnAdmin = new Button("Administrer konference");
        btnAdmin.setMinWidth(150);
        hbxButtons.getChildren().add(btnAdmin);
        btnAdmin.setOnAction(event -> adminAction());

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red");
        this.add(lblError, 0, 4);
        btnToursAndCompanions = new Button("Udflugter og ledsagere");
        hbxButtons.getChildren().add(btnToursAndCompanions);
        btnToursAndCompanions.setOnAction(event -> toursAndCompanionsAction());

        btnHotelsAndParticipants = new Button("Hoteller og deltagere");
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

    private void updateControls() {
        Conference conference = lvwConferences.getSelectionModel().getSelectedItem();
        if (conference != null) {
            txaDescription.setText(Service
                    .ConferenceOutputTextForConferenceWindow(lvwConferences.getSelectionModel().getSelectedItem()));
        } else {
            txaDescription.clear();
        }
    }

    private void adminAction() {
        lblError.setText("Det har vi så godt nok ikke lige nået... ");
    }

    private void toursAndCompanionsAction() {
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

    private void hotelsAndParticipants() {
        txaDescription.setText("Hoteller og deltagere:" + "\n");
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

    private void bookingsOnConference() {
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