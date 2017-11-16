package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.service.Service;

public class StartWindow extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("KAS");
        GridPane pane = new GridPane();
        initContent(pane);
        Service.initContent();

        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private Button btnAdmin, btnParticipant;
    private Label lblWelcome;

    private void initContent(GridPane pane) {

        // ================================================================================================

        lblWelcome = new Label("Velkommen til KAS");
        lblWelcome.setFont(Font.font(30));
        pane.add(lblWelcome, 1, 1);
        GridPane.setMargin(lblWelcome, new Insets(10, 10, 0, 10));

        btnAdmin = new Button("Admin");
        btnAdmin.setMinHeight(80);
        btnAdmin.setMinWidth(250);
        pane.add(btnAdmin, 1, 2);
        GridPane.setMargin(btnAdmin, new Insets(10, 10, 0, 10));
        btnAdmin.setOnAction(event -> adminAction());

        btnParticipant = new Button("Deltager");
        btnParticipant.setMinHeight(80);
        btnParticipant.setMinWidth(250);
        pane.add(btnParticipant, 1, 3);
        GridPane.setMargin(btnParticipant, new Insets(10, 10, 0, 10));
        btnParticipant.setOnAction(event -> participantAction());

    }

    // =====================================================================================================
    // Actions
    private void adminAction() {
        LoginWindow login = new LoginWindow("Login");
        login.show();

    }

    private void participantAction() {
        ParticipantWindow participant = new ParticipantWindow("Participant");
        participant.show();
    }

}
