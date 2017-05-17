/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

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

    private static Connection connect;

    public Login() throws SQLException {
        connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root");
    }

    public static Connection getConnect() {
        return Login.connect;
    }

    public String comprobar(Integer id, String contraseña) throws IOException, SQLException {
        String devolver = "";

        String sentencia = "Select * from trabajadores where IDTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        rs.next();

        String contraseñaUser = "";
        try {
            contraseñaUser = rs.getString(5);
        } catch (SQLException e) {
            devolver = "Usuario";
        }
        try {
            if (contraseña.equals(contraseñaUser)) {
                sentencia = "Update trabajadores set estado = 'Conectado' where IDTrabajador = ?";
                ps = connect.prepareStatement(sentencia);
                ps.setInt(1, id);
                ps.executeUpdate();

                String tipo = rs.getString(6);
                if (tipo.equalsIgnoreCase("Jefe")) {
                    devolver = "Jefe";
                }
                if (tipo.equalsIgnoreCase("Encargado")) {
                    devolver = "Encargado";
                }
                if (tipo.equalsIgnoreCase("Empleado")) {
//                    Trabajadores trabajador = new Empleado(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(7), rs.getString(8), rs.getString(9));
//                    ListaTrabajadores.setTrabajadores(trabajador);
                    devolver = "Empleado";
                }
            } else {
                devolver = "Contraseña";
            }
        } catch (SQLException e) {
            Alertas.generarAlerta("Error BD", "Ha habido un problema con la BD y no se han podido cargar los datos", Alert.AlertType.ERROR);
        }

        return devolver;
    }

}
