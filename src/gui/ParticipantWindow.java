package gui;

import model.model.Conference;
import model.service.Service;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ParticipantWindow extends Stage {

    public ParticipantWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // -----------------------------------------------------------------
    private Button btnBooking;
    private ListView<Conference> lvwConferences;
    private TextArea txaDescription;

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(true);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        btnBooking = new Button("Add");
        pane.add(btnBooking, 0, 3);
        GridPane.setMargin(btnBooking, new Insets(10, 10, 0, 10));

        lvwConferences = new ListView<>();
        pane.add(lvwConferences, 0, 0, 1, 3);
        lvwConferences.setPrefWidth(200);
        lvwConferences.setPrefHeight(200);
        lvwConferences.getItems().setAll(Service.getConferencesFromStorage());

        txaDescription = new TextArea("her skal konferencen beskrives");
        pane.add(txaDescription, 2, 0, 2, 3);
        txaDescription.setEditable(false);

    }
}
