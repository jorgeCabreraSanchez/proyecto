/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Alertas.Alertas;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.FadeTransition;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Jorge Cabrera
 */
public class Login {

    public Login() {

    }

    public String comprobar(Integer id, String contraseña, Stage stage) throws IOException {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {

            String sentencia = "Select Contraseña,Tipo from trabajadores where IDTrabajador = ?";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            String contraseñaUser;
            try {
                contraseñaUser = rs.getString(1);
            } catch (SQLException e) {
                return "Usuario";
            }
            try {
                if (contraseña.equals(contraseñaUser)) {
                    String tipo = rs.getString(2);
                    stage.close();
                    Parent root = null;
                    if (tipo.equalsIgnoreCase("Jefe")) {
                        root = FXMLLoader.load(getClass().getResource("/Jefe/InicioJefe.fxml"));

                    }
                    if (tipo.equalsIgnoreCase("Encargado")) {

                    }
                    if (tipo.equalsIgnoreCase("Empleado")) {

                    }
                    Stage stage2 = new Stage();
                    stage2.initModality(Modality.APPLICATION_MODAL);
                    stage2.setScene(new Scene(root));
                    stage2.show();
                    /* Hacer la carga */
                    return "OK";
                }
                return "Contraseña";
            } catch (SQLException e) {
                Alertas.generarAlerta("Ha habido un problema con la BD y no se han podido cargar los datos", Alert.AlertType.ERROR);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Alertas.generarAlerta("Ha habido un problema con la BD y no se puede acceder.", "Hable con el administrador de la BD para solucionar este problema.", Alert.AlertType.ERROR);
        }

        return "";
    }

}
