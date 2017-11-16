package gui;

import model.service.Service;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TabPane.TabClosingPolicy;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
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
<<<<<<< HEAD
        BorderPane pane = new BorderPane();
        // initContent(pane);
=======
        GridPane pane = new GridPane();
        initContent(pane);
>>>>>>> c5ebe7c89f441ac9fa3b5a1c1294b015bba560f5

        Scene scene = new Scene(pane);
        setScene(scene);
    }

<<<<<<< HEAD
=======
    private Label lblName, lblPassword, lblError;
    private TextField txfName, txfPassword;
    private Button btnLogin;

>>>>>>> c5ebe7c89f441ac9fa3b5a1c1294b015bba560f5
    private void initContent(GridPane pane) {
        // show or hide grid lines
        pane.setGridLinesVisible(false);

        // set padding of the pane
        pane.setPadding(new Insets(20));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(10);
<<<<<<< HEAD
    }
}
=======

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
        pane.add(lblError, 1, 3);

    }

    private void loginAction() {
        if (txfPassword.getText().trim().equals("admin") && txfName.getText().trim().equals("admin")) {
            AdminWindow admin = new AdminWindow("Admin");
            admin.show();
        } else {
            lblError.setText("Forkert navn eller password");
        }
    }
}
>>>>>>> c5ebe7c89f441ac9fa3b5a1c1294b015bba560f5
