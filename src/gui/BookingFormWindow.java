package gui;

import model.model.Company;
import model.model.Conference;
import model.service.Service;
import storage.Storage;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class BookingFormWindow extends Stage {

    public BookingFormWindow(String title, Conference selectedConference) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);
        initHeadline(selectedConference);

        Scene scene = new Scene(pane);
        setScene(scene);

    }

    private void initHeadline(Conference selectedConference) {
        lblHeadline.setText("Tilmelding til: " + selectedConference.getConferenceType().getName());//
    }

    // -----------------------------------------------------------------
    private Label lblHeadline = new Label();
    private TextField txfName, txfAdress, txfCity, txfCountry, txfPhoneNr, txfCompanionName;
    private CheckBox cbxSpeaker, cbxCompanion, cbxHotel, cbxCompany;
    private ComboBox<Company> cbbCompanies;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(true);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        txfName = new TextField();
        txfAdress = new TextField();
        txfCity = new TextField();
        txfCountry = new TextField();
        txfPhoneNr = new TextField();

        Label lblName = new Label("Navn");
        Label lblAdress = new Label("Adresse");
        Label lblCity = new Label("By");
        Label lblCountry = new Label("Land");
        Label lblPhoneNr = new Label("Telefonnr.");

        cbxSpeaker = new CheckBox("Foredragsholder?");
        cbxCompanion = new CheckBox("Medtager ledsager");
        cbxHotel = new CheckBox("Ønsker overnatning");
        cbxCompany = new CheckBox("Firmabetaling");

        cbbCompanies = new ComboBox<>();
        cbbCompanies.getItems().addAll(Storage.getCompanies());

        VBox rowOne = new VBox();
        VBox rowTwo = new VBox();

        HBox lineOneOne = new HBox();
        lineOneOne.getChildren().add(lblName);
        lineOneOne.getChildren().add(txfName);

        HBox lineTwoOne = new HBox();
        lineTwoOne.getChildren().add(lblAdress);
        lineTwoOne.getChildren().add(txfAdress);

        HBox lineThreeOne = new HBox();
        lineThreeOne.getChildren().add(lblCity);
        lineThreeOne.getChildren().add(txfCity);

        HBox lineFourOne = new HBox();
        lineFourOne.getChildren().add(cbxCompany);
        lineFourOne.getChildren().add(cbbCompanies);

        HBox lineFiveOne = new HBox();
        lineFiveOne.getChildren().add(cbxCompanion);
        lineFiveOne.getChildren().add(cbxHotel);

        HBox lineOneTwo = new HBox();
        lineOneTwo.getChildren().add(cbxSpeaker);

        HBox lineTwoTwo = new HBox();
        lineTwoTwo.getChildren().add(lblPhoneNr);
        lineTwoTwo.getChildren().add(txfPhoneNr);

        HBox lineThreeTwo = new HBox();
        lineThreeTwo.getChildren().add(lblCountry);
        lineThreeTwo.getChildren().add(txfCountry);

        rowOne.setMinWidth(300);
        rowTwo.setMinWidth(300);

        rowOne.getChildren().add(new Label("Deltagerinformation"));
        rowOne.getChildren().add(lineOneOne);
        rowOne.getChildren().add(lineTwoOne);
        rowOne.getChildren().add(lineThreeOne);
        rowOne.getChildren().add(lineFourOne);
        rowOne.getChildren().add(lineFiveOne);

        rowTwo.getChildren().add(new Label(""));
        rowTwo.getChildren().add(lineOneTwo);
        rowTwo.getChildren().add(lineTwoTwo);
        rowTwo.getChildren().add(lineThreeTwo);

        cbxCompanion.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                companionFields(pane, newValue);
            }
        });
        cbxHotel.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                hotelFields(pane, newValue);
            }
        });

        pane.add(lblHeadline, 0, 0);
        pane.add(rowOne, 0, 1);
        pane.add(rowTwo, 1, 1);
    }

    private void companionFields(GridPane pane, boolean show) {
        int xPos = 2;
        Label lblCompanionHeadline = new Label("Program for ledsagere");
        HBox companion = new HBox();
        txfCompanionName = new TextField();
        companion.getChildren().add(new Label("Ledsagernavn:"));
        companion.getChildren().add(txfCompanionName);
        if (show) {
            pane.add(lblCompanionHeadline, 0, xPos);
            pane.add(companion, 0, xPos + 1);
            sizeToScene();
        } else if (!show) {
            pane.getChildren().remove(lblCompanionHeadline);
            sizeToScene();
        }
    }

    private void hotelFields(GridPane pane, boolean show) {
        int xPos = 4;
        Label lblHotelHeadline = new Label("Overnatningsønsker");
        HBox hotel = new HBox();
        if (show) {
            pane.add(lblHotelHeadline, 0, xPos);
            pane.add(hotel, 0, xPos + 1);
            sizeToScene();
        } else if (!show) {
            pane.getChildren().remove(lblHotelHeadline);
            sizeToScene();
        }
    }
}
