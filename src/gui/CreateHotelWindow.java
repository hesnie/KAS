package gui;

import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.model.Hotel;
import model.service.Service;
import storage.Storage;

public class CreateHotelWindow extends Stage {

    private Hotel hotel;
    int indeks = 0;

    public CreateHotelWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    public CreateHotelWindow(String title, int indeks) {
        this.hotel = Service.getHotelsFromStorage().get(indeks);
        this.indeks = indeks;

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
    private Label lblName, lblAdress, lblSingle, lblDouble;
    private TextField txfName, txfAdress, txfSingle, txfDouble, txfWifiPrice, txfBreakfastPrice, txfShowerPrice;
    private Button btnSave, btnCancel;
    private CheckBox cbxWifi, cbxBreakfast, cbxShower;

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        HBox hbxButtons = new HBox();
        hbxButtons.setSpacing(10);
        hbxButtons.setAlignment(Pos.CENTER_RIGHT);
        pane.add(hbxButtons, 1, 7);

        // --------------------------------------
        // checkbox
        cbxWifi = new CheckBox("Wifi");
        pane.add(cbxWifi, 0, 4);
        ChangeListener<Boolean> listener1 = (ov, oldValue, newValue) -> wifiAction(newValue);
        cbxWifi.selectedProperty().addListener(listener1);

        cbxBreakfast = new CheckBox("Morgenmad");
        pane.add(cbxBreakfast, 0, 5);
        ChangeListener<Boolean> listener2 = (ov, oldValue, newValue) -> breakfastAction(newValue);
        cbxBreakfast.selectedProperty().addListener(listener2);

        cbxShower = new CheckBox("Bad");
        pane.add(cbxShower, 0, 6);
        ChangeListener<Boolean> listener3 = (ov, oldValue, newValue) -> showerAction(newValue);
        cbxShower.selectedProperty().addListener(listener3);

        // --------------------------------------
        // buttons
        btnSave = new Button("Gem");
        hbxButtons.getChildren().add(btnSave);
        btnSave.setOnAction(event -> saveAction());

        btnCancel = new Button("Annuller");
        hbxButtons.getChildren().add(btnCancel);
        btnCancel.setOnAction(Event -> cancelAction());

        // --------------------------------------
        // TextField
        txfName = new TextField("");
        pane.add(txfName, 1, 0);

        txfAdress = new TextField("");
        pane.add(txfAdress, 1, 1);

        txfSingle = new TextField("");
        pane.add(txfSingle, 1, 2);

        txfDouble = new TextField("");
        pane.add(txfDouble, 1, 3);

        txfWifiPrice = new TextField("0");
        pane.add(txfWifiPrice, 1, 4);
        txfWifiPrice.setDisable(true);

        txfBreakfastPrice = new TextField("0");
        pane.add(txfBreakfastPrice, 1, 5);
        txfBreakfastPrice.setDisable(true);

        txfShowerPrice = new TextField("0");
        pane.add(txfShowerPrice, 1, 6);
        txfShowerPrice.setDisable(true);

        // -------------------------------------
        // Labels

        lblName = new Label("Navn: ");
        pane.add(lblName, 0, 0);

        lblAdress = new Label("Adresse: ");
        pane.add(lblAdress, 0, 1);

        lblSingle = new Label("Single pris: ");
        pane.add(lblSingle, 0, 2);

        lblDouble = new Label("Double pris: ");
        pane.add(lblDouble, 0, 3);

        if (hotel != null) {
            txfName.setText(hotel.getName());
            txfAdress.setText(hotel.getAdress());
            txfSingle.setText("" + hotel.getPriceDouble());
            txfDouble.setText("" + hotel.getPriceDouble());
            txfWifiPrice.setText("" + hotel.getWifiPrice());
            txfBreakfastPrice.setText("" + hotel.getBreakfastPrice());
            txfShowerPrice.setText("" + hotel.getShowerPrice());
            cbxWifi.setSelected(hotel.isHasWifi());
            cbxBreakfast.setSelected(hotel.isHasBreakfast());
            cbxShower.setSelected(hotel.isHasShower());
        }

    }

    // ====================================================================================

    private void wifiAction(Boolean checked) {
        txfWifiPrice.setDisable(!checked);
    }

    private void breakfastAction(Boolean checked) {
        txfBreakfastPrice.setDisable(!checked);
    }

    private void showerAction(Boolean checked) {
        txfShowerPrice.setDisable(!checked);
    }

    private void saveAction() {
        String name = txfName.getText().trim();
        String adress = txfAdress.getText().trim();
        double priceSingle = 0;
        double priceDouble = 0;
        Boolean wifi = cbxWifi.isSelected();
        Boolean breakfast = cbxBreakfast.isSelected();
        Boolean shower = cbxShower.isSelected();
        double wifiPrice = 0;
        double breakfastPrice = 0;
        double showerPrice = 0;

        try {
            priceSingle = Double.parseDouble(txfSingle.getText().trim());
            priceDouble = Double.parseDouble(txfDouble.getText().trim());
            wifiPrice = Double.parseDouble(txfWifiPrice.getText().trim());
            breakfastPrice = Double.parseDouble(txfBreakfastPrice.getText().trim());
            showerPrice = Double.parseDouble(txfShowerPrice.getText().trim());
        } catch (NumberFormatException ex) {
            // do nothing
        }
        if (hotel == null) {
            Hotel hotel = Service.createHotel(name, adress, priceSingle, priceDouble, wifi, breakfast, shower,
                    wifiPrice, breakfastPrice, showerPrice);
        } else {
            Service.hotelSet(hotel, name, adress, priceSingle, priceDouble, wifi, breakfast, shower, wifiPrice,
                    breakfastPrice, showerPrice);

        }
        hide();
    }

    private void cancelAction() {
        hide();
    }

}
