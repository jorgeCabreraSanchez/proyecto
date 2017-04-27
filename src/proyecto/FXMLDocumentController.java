package proyecto;

import Trabajadores.Trabajadores;
import Trabajadores.Listas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class FXMLDocumentController implements Initializable {

    Listas total = new Listas();

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

        this.fieldUsuario.textProperty().addListener((observable, oldValue, newValue) -> {
            this.buttonLogin.setVisible(!newValue.trim().isEmpty());
            if (this.buttonLogin.isVisible()){
                this.buttonLogin.setStyle("-fx-background-color: #00ff00");
            } else {
                this.buttonLogin.setStyle("-fx-background-color: none");
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
        Integer id1 = 0;
        this.labelErrores.setText("");

        try {
            id1 = Integer.parseInt(id);
            String error = total.comprobar(id1, contraseña);
            if (error.equalsIgnoreCase("Usuario")) {
                this.labelErrores.setText("- La ID escrita no existe.");
            } else if (error.equalsIgnoreCase("Contraseña")) {
                this.labelErrores.setText("- La contraseña escrita es incorrecta.");
            } else {
                System.out.println(error);
            }

        } catch (NumberFormatException e) {
            this.labelErrores.setText("- El ID solo puede contener números");
            this.fieldPassword.setText("");
        }
    }

}
