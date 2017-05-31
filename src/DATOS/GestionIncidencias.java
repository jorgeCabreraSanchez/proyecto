/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        
        String sentencia = "Select * from incidenciastiendas where idTienda = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            incidencias.add(new IncidenciaTienda(rs.getInt(1), rs.getInt(2), rs.getString(3),rs.getString(4),rs.getString(5)));
        }

        return incidencias;
        
    }
}
