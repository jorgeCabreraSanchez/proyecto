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
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class GestionIncidencias {

    Connection connect;

    public GestionIncidencias() {
        connect = Login.getConnect();
    }

    
    public Set<IncidenciaTienda> getIncidencias(int idTienda) throws SQLException{
        Set<IncidenciaTienda> incidencias = new HashSet<>();
        
        String sentencia = "Select idIncidencia,Titulo,Descripcion,Fecha from incidenciastiendas where idTienda = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            incidencias.add(new IncidenciaTienda(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDate(4)));
        }

        return incidencias;
        
    }
    
        public Set<IncidenciaTienda> getIncidencias(int idTienda,LocalDate fechaEmpieza,LocalDate FechaAcaba) throws SQLException{
        Set<IncidenciaTienda> incidencias = new HashSet<>();
        
        String sentencia = "Select idIncidencia,Titulo,Descripcion,Fecha from incidenciastiendas where idTienda = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            incidencias.add(new IncidenciaTienda(rs.getInt(1), rs.getString(2), rs.getString(3),rs.getDate(4)));
        }

        return incidencias;
        
    }
}
