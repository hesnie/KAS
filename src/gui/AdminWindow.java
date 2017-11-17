package gui;

import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AdminWindow extends Stage {

    public AdminWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        BorderPane pane = new BorderPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // -------------------------------------------------------------------------

    private void initContent(BorderPane pane) {
        TabPane tabPane = new TabPane();
        initTabPane(tabPane);
        pane.setCenter(tabPane);
    }

    private void initTabPane(TabPane tabPane) {
        tabPane.setTabClosingPolicy(TabClosingPolicy.UNAVAILABLE);

        Tab tabLocation = new Tab("Beligenhed");
        tabPane.getTabs().add(tabLocation);

        LocationPane locationPane = new LocationPane();
        tabLocation.setContent(locationPane);

        Tab tabConference = new Tab("Konference");
        tabPane.getTabs().add(tabConference);

        ConferencePane conferencePane = new ConferencePane();
        tabConference.setContent(conferencePane);
    }

}
