package proyecto;

import Trabajadores.Trabajadores;
import Trabajadores.Listas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class FXMLDocumentController implements Initializable {

    Listas listas = new Listas();

    @FXML
    private AnchorPane fondo;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        FXMLLoader loader = new FXMLLoader();
//        loader.setLocation(getClass().getResource("/Jefe/InicioJefe.fxml"));
//        loader.setBuilderFactory(new JavaFXBuilderFactory());
//        AnchorPane page = this.fondoLogin;
//        try {
//            page = (AnchorPane) loader.load();
//        } catch (IOException ex) {
//        }
//
//        FadeTransition ft = new FadeTransition(Duration.millis(3000), page);
//        ft.setFromValue(0.0);
//        ft.setToValue(1.0);
//        ft.play();
//        Scene scene = new Scene(page);

        this.fieldUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            this.buttonLogin.setDisable(newValue.trim().isEmpty());
            if (!this.buttonLogin.isDisabled()) {
                this.buttonLogin.setStyle("-fx-background-color: #00ff00");
            } else {
                this.buttonLogin.setStyle("-fx-background-color: white");
            }
        });

        Platform.runLater(() -> this.fieldUsuario.requestFocus());
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
            String error = listas.comprobar(id1, contraseña);
            if (error.equalsIgnoreCase("Usuario")) {
                this.labelErrores.setText("- La ID escrita no existe.");
            }
            if (error.equalsIgnoreCase("Contraseña")) {
                this.labelErrores.setText("- La contraseña escrita es incorrecta.");
                this.fieldPassword.setText("");
            }

            if (error.equalsIgnoreCase("OK")) {
                Stage stage = (Stage) this.buttonCancelar.getScene().getWindow();
                stage.close();
                /* No funciona */
            }
        } catch (NumberFormatException e) {
            this.labelErrores.setText("- El ID solo puede contener números");
        }
    }

}
