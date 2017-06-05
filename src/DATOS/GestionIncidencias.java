/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Incidencia.IncidenciaTrabajador;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jorge Cabrera
 */
public class GestionIncidencias {

    Connection connect;

    public GestionIncidencias() {
        connect = Login.getConnect();
    }

    
    public List<IncidenciaTienda> getIncidencias(int idTienda) throws SQLException{
        List<IncidenciaTienda> incidencias = new ArrayList<>();
        
        String sentencia = "Select idIncidencia,Titulo,Descripcion,Fecha from incidenciastiendas where idTienda = ? order by fecha desc;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            incidencias.add(new IncidenciaTienda(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDate(4)));
        }

        return incidencias;
        
    }
    
    public void nuevaIncidencia(IncidenciaTienda incidencia) throws SQLException{
        String sentencia = "Insert into incidenciastiendas (idTienda,Titulo,Descripcion,Fecha) values (?,?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, incidencia.getIdTienda());
        ps.setString(2, incidencia.getTitulo());
        ps.setString(3, incidencia.getDescripcion());
        ps.setDate(4, incidencia.getFecha());
        ps.executeUpdate();
    }
    
    public void borrarIncidencia(int idIncidencia) throws SQLException{
        String sentencia = "Delete from incidenciastiendas where idIncidencia = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idIncidencia);
        ps.executeUpdate();
    }
    
    public void editarIncidencia(IncidenciaTienda incidencia) throws SQLException{
        String sentencia = "Update incidenciastiendas set titulo = ?, descripcion = ?,fecha = ? where idIncidencia = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, incidencia.getTitulo());
        ps.setString(2, incidencia.getDescripcion());
        ps.setDate(3, incidencia.getFecha());
        ps.setInt(4, incidencia.getIdIncidencia());
        ps.executeUpdate();
    }
    
        public List<IncidenciaTrabajador> getIncidenciasTrabajador(int idTrabajador) throws SQLException{
        List<IncidenciaTrabajador> incidencias = new ArrayList<>();
        
        String sentencia = "Select idIncidencia,Titulo,Descripcion,Fecha,Tipo from incidenciastrabajadores where idTrabajador = ? order by fecha desc;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTrabajador);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            incidencias.add(new IncidenciaTrabajador(rs.getString(5), rs.getInt(1), rs.getString(2),rs.getString(3),rs.getDate(4).toLocalDate()));
        }

        return incidencias;
        
    }
    
    public void nuevaIncidenciaTrabajador(IncidenciaTrabajador incidencia) throws SQLException{
        String sentencia = "Insert into incidenciastrabajadores (idTrabajador,Titulo,Descripcion,Fecha,Tipo) values (?,?,?,?,'comportamiento')";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, incidencia.getIdTrabajador());
        ps.setString(2, incidencia.getTitulo());
        ps.setString(3, incidencia.getDescripcion());
        ps.setDate(4, incidencia.getFecha());
        ps.executeUpdate();
    }
    
    public void borrarIncidenciaTrabajador(int idIncidencia) throws SQLException{
        String sentencia = "Delete from incidenciastrabajadores where idIncidencia = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idIncidencia);
        ps.executeUpdate();
    }
    
    public void editarIncidenciaTrabajador(IncidenciaTrabajador incidencia) throws SQLException{
        String sentencia = "Update incidenciastrabajadores set titulo = ?, descripcion = ?,fecha = ? where idIncidencia = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, incidencia.getTitulo());
        ps.setString(2, incidencia.getDescripcion());
        ps.setDate(3, incidencia.getFecha());
        ps.setInt(4, incidencia.getIdIncidencia());
        ps.executeUpdate();
    }
    
    
   
}
