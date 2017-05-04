/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import Alertas.Alertas;
import Tiendas.ListaCiudadesConTienda;
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
import javafx.scene.control.MenuItem;
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
                    
                    if (tipo.equalsIgnoreCase("Jefe")) { 
                        return "Jefe";
                    }
                    if (tipo.equalsIgnoreCase("Encargado")) {
                        return "Encargado";
                    }
                    if (tipo.equalsIgnoreCase("Empleado")) {
                        return "Empleado";
                    }
                   
                    /* Hacer la carga */

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
