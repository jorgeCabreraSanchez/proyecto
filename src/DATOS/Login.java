 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Alertas;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Trabajadores;
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

            String sentencia = "Select * from trabajadores where IDTrabajador = ?";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();

            String contraseñaUser;
            try {
                contraseñaUser = rs.getString(5);
            } catch (SQLException e) {
                return "Usuario";
            }
            try {
                if (contraseña.equals(contraseñaUser)) {
                    String tipo = rs.getString(6);
                    
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
                        Trabajadores trabajador = new Empleado(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(7),rs.getString(8),rs.getString(9));
                        ListaTrabajadores.setTrabajadores(trabajador);
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
