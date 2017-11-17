package gui;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.service.Service;

public class ConferenceTypeWindow extends Stage {

    public ConferenceTypeWindow(String title) {
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

    private Label lblName, lblDescription;
    private TextField txfName, txfDescription;
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

        // -----------------------------------
        // Buttons

        btnSave = new Button("Gem");
        pane.add(btnSave, 0, 3);
        btnSave.setOnAction(event -> SaveAction());

        btnCancel = new Button("Annuller");
        pane.add(btnCancel, 1, 3);
        btnCancel.setOnAction(EventHandler -> cancelAction());

        // ----------------------------------
        // TextField

        txfName = new TextField("");
        pane.add(txfName, 1, 0);

        txfDescription = new TextField("");
        pane.add(txfDescription, 1, 1);

        // --------------------------------
        // Label

        lblName = new Label("Navn: ");
        pane.add(lblName, 0, 0);

        lblDescription = new Label("Beskrivelse: ");
        pane.add(lblDescription, 0, 1);

    }

    // --------------------------------------------------------------
    private void SaveAction() {
        String name = txfName.getText().trim();
        String description = txfDescription.getText().trim();

        Service.createConferenceType(name, description);
        hide();
    }

    private void cancelAction() {
        hide();
    }
}