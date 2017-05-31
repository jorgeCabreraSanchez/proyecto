/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

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

        if (contraseña.equals(contraseñaUser)) {
            if (!rs.getString(6).equalsIgnoreCase("jefe")) {
                
                sentencia = "insert into fichaje(idTrabajador,Dia,Entrada) values (?,?,?);";
                ps = connect.prepareStatement(sentencia);
                ps.setInt(1, id);
                ps.setDate(2, Date.valueOf(LocalDate.now()));
                ps.setTime(3, Time.valueOf(LocalTime.now()));

                ps.executeUpdate();
            }

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

        return devolver;
    }

}
