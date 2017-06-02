/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Incidencia.IncidenciaTienda;
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
        String sentencia = "Update incidenciastiendas set titulo = ?, descripcion = ? where idIncidencia = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setString(1, incidencia.getTitulo());
        ps.setString(2, incidencia.getDescripcion());
        ps.setInt(3, incidencia.getIdIncidencia());
        ps.executeUpdate();
    }
    
    
   
}
