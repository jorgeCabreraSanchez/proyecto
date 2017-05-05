/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiendas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaTiendas {

    private ObservableList<MenuItem> ciudades = FXCollections.observableArrayList();
    private ObservableList<Tienda> tiendas = FXCollections.observableArrayList();

    public ListaTiendas() {
    }    
    
    public ObservableList<MenuItem> getCiudades(String palabra) {
        cargarTiendas(palabra);
        return ciudades;
    }

    public ObservableList<Tienda> getTiendas() {
        return tiendas;
    }
        
    
    public void cargarTiendas(String palabra){
        try {
            tiendas.clear();
            ciudades.clear();
            Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root");
            String sentencia = "call tiendaCiuComienzaPor(?)";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setString(1, palabra);
            ResultSet rs = ps.executeQuery();
            
            Tienda t = new Tienda();
            while(rs.next()){
                t.setIdTienda(rs.getInt(1));
                t.setDireccion(rs.getString(2));
                t.setCiudad(rs.getString(3));
                tiendas.add(t);
                ciudades.add(new MenuItem(rs.getString(3)));
            }
        } catch (SQLException ex) {
            Alertas.Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer la información de las tiendas",ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
