package VISTA.Login;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import DATOS.Login;
import MODELO.Alertas;
import java.sql.SQLException;
import javafx.scene.control.Alert;

public class ControllerLogin implements Initializable {

    Login login;
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

    public ControllerLogin() {
        try {
            this.login = new Login();
        } catch (SQLException ex) {
            Alertas.generarAlerta("Conexión BD", "Ha habido un error en la BD y no se puede acceder", "Hable con el administrador de la red para solucionar el problema", Alert.AlertType.ERROR);
        }
    }

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
        Integer id1 = 0;
        this.labelErrores.setText("");

        try {
            id1 = Integer.parseInt(id);
            try {
                String ocurre = login.comprobar(id1, contraseña);

                if (ocurre.equalsIgnoreCase("Usuario")) {
                    this.labelErrores.setText("- La ID escrita no existe.");
                } else if (ocurre.equalsIgnoreCase("Contraseña")) {
                    this.labelErrores.setText("- La contraseña escrita es incorrecta.");
                    this.fieldPassword.setText("");
                } else {
                    Stage stage = (Stage) this.buttonCancelar.getScene().getWindow();
                    stage.close();

                    Parent root = new Parent() {
                    };

                    if (ocurre.equalsIgnoreCase("Jefe")) {
                        root = FXMLLoader.load(getClass().getResource("/VISTA/Jefe/Inicio/InicioJefe.fxml"));
                    } else if (ocurre.equalsIgnoreCase("Encargado")) {
                        root = FXMLLoader.load(getClass().getResource("/VISTA/Encargado/Inicio/InicioEncargado.fxml"));
                    } else if (ocurre.equalsIgnoreCase("Empleado")) {
                        root = FXMLLoader.load(getClass().getResource("/VISTA/Empleado/Inicio/InicioEmpleados.fxml"));
                    }
                    Stage stage2 = new Stage();
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.setScene(new Scene(root));
                    stage2.show();
                }
            } catch (SQLException ex) {
                Alertas.generarAlerta("Error BD", "Ha habido un problema con la BD y no se puede acceder.", "Hable con el administrador de la BD para solucionar este problema.", Alert.AlertType.ERROR);
            }
        } catch (NumberFormatException e) {
            this.labelErrores.setText("- El ID solo puede contener números");
        }
    }

}
