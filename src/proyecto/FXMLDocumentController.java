package proyecto;

import Trabajadores.Trabajadores;
import Trabajadores.Listas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    Login login = new Login();

    @FXML
    private TextField fieldUsuario;
    @FXML
    private Button buttonLogin;
    @FXML
    private PasswordField fieldPassword;
    @FXML
    private Button buttonCancelar;
    @FXML
    private Label labelErrores;
    @FXML
    private AnchorPane fondoHuerto;
    @FXML
    private AnchorPane fondoLogin;
    @FXML
    private AnchorPane fondoAtras;
    @FXML
    private AnchorPane fondoLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TranslateTransition transition = new TranslateTransition();
        transition.setDuration(Duration.seconds(2));
        transition.setNode(this.fondoLogin);
        transition.setToY(288);
        transition.play();

        this.fieldUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            this.buttonLogin.setDisable(newValue.trim().isEmpty());
            if (!this.buttonLogin.isDisabled()) {
                this.buttonLogin.setStyle("-fx-background-color: #00ff00");
            } else {
                this.buttonLogin.setStyle("-fx-background-color: white");
            }
        });

    }

    @FXML
    public void login(ActionEvent event) throws IOException {
        verificar(this.fieldUsuario.getText(), this.fieldPassword.getText());
    }

    @FXML
    private void limpiar(ActionEvent event) {
        this.fieldPassword.setText("");
        this.fieldUsuario.setText("");
    }

    private void verificar(String id, String contraseña) throws IOException {
        Integer id1 = null;
        this.labelErrores.setText("");

        try {
            id1 = Integer.parseInt(id);
            Stage stage = (Stage) this.buttonCancelar.getScene().getWindow();
            String error = login.comprobar(id1, contraseña, stage);
            if (error.equalsIgnoreCase("Usuario")) {
                this.labelErrores.setText("- La ID escrita no existe.");
            }
            if (error.equalsIgnoreCase("Contraseña")) {
                this.labelErrores.setText("- La contraseña escrita es incorrecta.");
                this.fieldPassword.setText("");
            }
            if (error.equalsIgnoreCase("OK")) {
                /* Hacer pregunta, lo cargo aqui o desde el login? */
            }
            
        } catch (NumberFormatException e) {
            this.labelErrores.setText("- El ID solo puede contener números");
        }
    }

}
