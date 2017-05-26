/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Alertas;
import MODELO.Tienda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.control.Alert;

/**
 *
 * @author Jorge Cabrera
 */
public class GestionTiendas {

    private Connection connect;

    public GestionTiendas() {
        connect = Login.getConnect();
    }

    public Set<Tienda> cargarTiendas() throws SQLException {
        Set<Tienda> tiendas = new HashSet<>();

        String sentencia = "Select * from tiendas";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Tienda t = new Tienda();
            t.setIdTienda(rs.getInt(1));
            t.setDireccion(rs.getString(2));
            t.setCiudad(rs.getString(3));
            tiendas.add(t);
        }

        return tiendas;
    }

    public void borrarTienda(Tienda tienda) throws SQLException {
        String sentencia = "Delete from tiendas where idTienda = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, tienda.getIdTienda());
        ps.executeUpdate();        
        /*Alertas.generarAlerta("BD", "No se ha podido borrar la tienda", "No se ha podido borrar la tienda debido a que esta contiene empleados, y los empleados siempre tienen que estar asignados a una tienda", Alert.AlertType.ERROR); */
    }

    public void editarTienda(Integer antiguaTiendaID, Tienda nuevaTienda) throws SQLException {
        String sentencia = "Update tiendas set IDTienda = ?, direccion = ?, Ciudad = ? where IDTienda = ?";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, nuevaTienda.getIdTienda());
        ps.setString(2, nuevaTienda.getDireccion());
        ps.setString(3, nuevaTienda.getCiudad());
        ps.setInt(4, antiguaTiendaID);
        ps.executeUpdate(); 
    }

    public void nuevaTienda(Tienda tienda) throws SQLException {
            String sentencia = "Insert into tiendas values(?,?,?)";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setInt(1, tienda.getIdTienda());
            ps.setString(2, tienda.getDireccion());
            ps.setString(3, tienda.getCiudad());
            ps.executeUpdate();               
    }

}
