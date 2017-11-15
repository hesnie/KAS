package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.model.TourType;
import model.service.Service;
import storage.Storage;

public class CreateTourWindow extends Stage {

    private TourType tourType;

    public CreateTourWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    // ----------------------------------------------------------------------------------------
    private Label lblName, lblPrice, lblDescription, lblMaxParticipants;
    private TextField txfName, txfPrice, txfDescription, txfMaxParticipants;
    private Button btnSave, btnCancel;

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
        // Buttons

        btnSave = new Button("Gem");
        pane.add(btnSave, 1, 4);
        btnSave.setOnAction(event -> saveAction());

        btnCancel = new Button("Cancel");
        pane.add(btnCancel, 2, 4);

        // -------------------------------------
        // textFields
        txfName = new TextField("");
        pane.add(txfName, 1, 0);

        txfPrice = new TextField("");
        pane.add(txfPrice, 1, 1);

        txfDescription = new TextField("");
        pane.add(txfDescription, 1, 2);

        txfMaxParticipants = new TextField("");
        pane.add(txfMaxParticipants, 1, 3);

        // ---------------------------
        // labels

        lblName = new Label("Navn: ");
        pane.add(lblName, 0, 0);

        lblPrice = new Label("Adresse: ");
        pane.add(lblPrice, 0, 1);

        lblDescription = new Label("Beskrivelse");
        pane.add(lblDescription, 0, 2);

        lblMaxParticipants = new Label("MaxParticipants");
        pane.add(lblMaxParticipants, 0, 3);

    }

    // ====================================================================================

    public void saveAction() {
        String name = txfName.getText().trim();
        if (name.length() == 0) {
            return;
        }
        double price = 0;
        try {
            price = Double.parseDouble(txfPrice.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }

        String description = txfDescription.getText().trim();

        Short maxParticipants = 0;
        try {
            maxParticipants = Short.parseShort(txfMaxParticipants.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }

        TourType tourType = Service.createTourType(name, description, price, maxParticipants);

        hide();
    }

}
