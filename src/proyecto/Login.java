/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto;

import MODELO.Alertas;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;

/**
 *
 * @author Jorge Cabrera
 */
public class Login {

    public Login() {

    }

    public String comprobar(Integer id, String contraseña) throws IOException {
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
                    
                    sentencia = "Update trabajadores set estado = 'Conectado' where IDTrabajador = ?";
                    ps = connect.prepareStatement(sentencia);
                    ps.setInt(1, id);
                    ps.executeUpdate();
                    
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
                Alertas.generarAlerta("Error BD","Ha habido un problema con la BD y no se han podido cargar los datos", Alert.AlertType.ERROR);
            }

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            Alertas.generarAlerta("Error BD","Ha habido un problema con la BD y no se puede acceder.", "Hable con el administrador de la BD para solucionar este problema.", Alert.AlertType.ERROR);
        }

        return "";
    }

}
