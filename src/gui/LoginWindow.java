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

public class LoginWindow extends Stage {

    public LoginWindow(String title) {
        initStyle(StageStyle.UTILITY);
        initModality(Modality.APPLICATION_MODAL);
        setResizable(false);

        setTitle(title);
        GridPane pane = new GridPane();
        initContent(pane);

        Scene scene = new Scene(pane);
        setScene(scene);
    }

    private Label lblName, lblPassword, lblError;
    private TextField txfName, txfPassword;
    private Button btnLogin;

    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);

        lblName = new Label("Navn: ");
        pane.add(lblName, 0, 0);

        lblPassword = new Label("Password: ");
        pane.add(lblPassword, 0, 1);

        txfName = new TextField();
        pane.add(txfName, 1, 0);

        txfPassword = new TextField();
        pane.add(txfPassword, 1, 1);

        btnLogin = new Button("Login");
        pane.add(btnLogin, 1, 2);
        btnLogin.setOnAction(event -> loginAction());

        lblError = new Label();
        lblError.setStyle("-fx-text-fill: red");
        pane.add(lblError, 0, 3, 2, 3);

    }

    private void loginAction() {
        if (txfPassword.getText().trim().equals("admin") && txfName.getText().trim().equals("admin")) {
            AdminWindow admin = new AdminWindow("Admin");
            admin.show();
        } else {
            lblError.setText("navn = admin - password = admin");
        }

    }
}
