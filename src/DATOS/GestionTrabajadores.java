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
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class GestionTrabajadores {

    private Connection connect;

    public GestionTrabajadores() {
        connect = Login.getConnect();
    }

    public Set<Trabajadores> trabajadoresEnLaTienda(int idTienda) throws SQLException {
        Set<Trabajadores> trabajadores = new HashSet<>();

        String sentencia = "call infoTrabajadores(?)";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Trabajadores trabajador;
            if (rs.getString(5).equalsIgnoreCase("Empleado")) {
                trabajador = new Empleado(rs.getString(6), rs.getInt(8), rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7), idTienda);
            } else {
                trabajador = new Encargado(rs.getString(6), rs.getInt(8), rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(7), idTienda);
            }
            trabajadores.add(trabajador);
        }

        return trabajadores;
    }

    public void borrarTrabajador(int idTrabajador) throws SQLException {
        String sentencia = "delete from trabajadores where idTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTrabajador);
        ps.executeUpdate();
    }

    public void modificarTrabajador(int idTrabajador, String campo, String nuevo) throws SQLException {
        String sentencia = "Update trabajadores set " + campo + " = ? where idTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, nuevo);
        ps.setInt(2, idTrabajador);
        ps.executeUpdate();
    }

    public void modificarTrabajador(Trabajadores trabajador) throws SQLException {
        String sentencia = "Update trabajadores set nombre = ?,apellido1 = ?,apellido2 = ?,contraseña = ?,tipo = ?,idTienda = ?, horario = ?"
                + " where idTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, trabajador.getNombre());
        ps.setString(2, trabajador.getApellido1());
        ps.setString(3, trabajador.getApellido2());
        ps.setString(4, trabajador.getContraseña());
        if (trabajador instanceof Empleado) {
            Empleado empleado = (Empleado) trabajador;
            ps.setString(5, "Empleado");
            ps.setInt(6, empleado.getIdTienda());
            ps.setString(7, empleado.getHorario());

        } else {
            Encargado encargado = (Encargado) trabajador;
            ps.setString(5, "Encargado");
            ps.setInt(6, encargado.getIdTienda());
            ps.setString(7, encargado.getHorario());
        }
        ps.setInt(8, trabajador.getId());

        ps.executeUpdate();
    }

    public void modificarPerfil(Trabajadores trabajador) throws SQLException {
        String sentencia = "Update trabajadores set nombre = ?,apellido1 = ?,apellido2 = ?,contraseña = ? where idTrabajador = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, trabajador.getNombre());
        ps.setString(2, trabajador.getApellido1());
        ps.setString(3, trabajador.getApellido2());
        ps.setString(4, trabajador.getContraseña());
        ps.setInt(5, trabajador.getId());

        ps.executeUpdate();
    }

    public void nuevoTrabajador(Trabajadores trabajador) throws SQLException {
        String tipo;
        Integer idTienda;
        if (trabajador instanceof Empleado) {
            tipo = "Empleado";
            Empleado empleado = (Empleado) trabajador;
            idTienda = empleado.getIdTienda();
        } else {
            tipo = "Encargado";
            Encargado encargado = (Encargado) trabajador;
            idTienda = encargado.getIdTienda();
        }
        String sentencia = "Insert into trabajadores values (null,?,?,?,'',?,?,?,'Desconectado')";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, trabajador.getNombre());
        ps.setString(2, trabajador.getApellido1());
        ps.setString(3, trabajador.getApellido2());
        ps.setString(4, tipo);
        ps.setInt(5, idTienda);
        ps.setString(6, trabajador.getHorario());
        ps.executeUpdate();
    }

    public void desconectar(int idTrabajador, String tipo) throws SQLException {
        String sentencia = "Update trabajadores set estado = 'Desconectado' where idTrabajador = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTrabajador);
        ps.executeUpdate();

        if (!tipo.equalsIgnoreCase("Jefe")) {
            sentencia = "update fichaje set Salida = ? where idTrabajador = ? and Dia = ?;";
            ps = connect.prepareStatement(sentencia);
            ps.setTime(1, Time.valueOf(LocalTime.now()));
            ps.setInt(2, idTrabajador);
            ps.setDate(3, Date.valueOf(LocalDate.now()));

            ps.executeUpdate();
        }
    }
}
