/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

public class Total {

    public Total() {

    }

    public String comprobar(Integer id, String contraseña) throws IOException {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            try {
                String sentencia = "Select contraseña,tipo from empleados where ID = ?";
                PreparedStatement ps = connect.prepareStatement(sentencia);
                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();
                rs.next();
                if (contraseña.equals(rs.getString(1))) {
                    String tipo = rs.getString(2);
                    if (tipo.equalsIgnoreCase("Jefe")) {
                        Stage stage = new Stage();
                        Parent root = FXMLLoader.load(getClass().getResource("InicioJefe.fxml"));
                        Scene scene = new Scene(root);
                        stage.setScene(scene);
                        stage.show();
                    }
                    if (tipo.equalsIgnoreCase("Encargado")) {

                    }
                    if (tipo.equalsIgnoreCase("Empleado")) {

                    }

                    return "OK";
                }
                return "Contraseña";
            } catch (SQLException e) {
                return "Usuario";
            } catch (Exception e) {
                System.out.println("Hay otro error");
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Alert alerta = new Alert(Alert.AlertType.ERROR);
            alerta.setHeaderText("Ha habido un problema relacionado con la BD y no se puede acceder.");
            alerta.setContentText("Hable con el administrador de la red para solucionar este problema.");
            alerta.showAndWait();
        }

        return "poyas";

    }

}
