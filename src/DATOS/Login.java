/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
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

    public Trabajadores comprobar(Integer id, String contraseña) throws IOException, SQLException {
        Trabajadores trabajador = null;

        String sentencia = "Select idTrabajador,nombre,apellido1,apellido2,contraseña,tipo,idTienda,horario,Imagen from trabajadores where IDTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        rs.next();

        String contraseñaUser = rs.getString(5);

        if (contraseña.equals(contraseñaUser)) {
            if (!rs.getString(6).equalsIgnoreCase("jefe")) {

                sentencia = "call ficharEntrada(?);";
                ps = connect.prepareStatement(sentencia);
                ps.setInt(1, id);
                ps.executeUpdate();
            }

            String tipo = rs.getString(6);
            if (tipo.equalsIgnoreCase("Jefe")) {
                trabajador = new Jefe(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),"Conectado",rs.getInt(1),rs.getString(8),rs.getBytes(9));
            }
            if (tipo.equalsIgnoreCase("Encargado")) {
                trabajador = new Encargado(rs.getInt(7), rs.getString(8),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), "Conectado", rs.getInt(1),rs.getBytes(9));
            }
            if (tipo.equalsIgnoreCase("Empleado")) {//                    
                trabajador = new Empleado(rs.getInt(7), rs.getString(8),rs.getString(2),rs.getString(3),rs.getString(4), rs.getString(5), "Conectado", rs.getInt(1),rs.getBytes(9));
            }
        }
        return trabajador;
    }

}
