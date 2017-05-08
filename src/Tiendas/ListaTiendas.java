/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiendas;

import MODELO.Tienda;
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
    private ObservableList<MenuItem> direcciones = FXCollections.observableArrayList();

    public ListaTiendas() {
    }

    public ObservableList<MenuItem> getCiudades(String palabra) {
        cargarTiendas(palabra,"ciudad","");
        return ciudades;
    }

    public ObservableList<Tienda> getTiendas() {
        return tiendas;
    }

    public ObservableList<MenuItem> getDirecciones() {
        return direcciones;
    }
    
    public ObservableList<MenuItem> getDirecciones(String palabra,String ciudad) {
        cargarTiendas(palabra,"direccion",ciudad);
        return direcciones;
    }

    public void cargarTiendas(String palabra, String lugar, String ciudad2) {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            tiendas.clear();
            if (lugar.equalsIgnoreCase("ciudad")) {
                ciudades.clear();
            }
            direcciones.clear();

            String sentencia = "";
            if (lugar.equalsIgnoreCase("direccion")) {
                sentencia = "call tiendaDirComienzaPor(?,?)";
            } else {
                sentencia = "call tiendaCiuComienzaPor(?)";
            }

            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setString(1, palabra);
            if(lugar.equalsIgnoreCase("direccion")){
                ps.setString(2, ciudad2);
            }
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String ciudad = rs.getString(3);
                String direccion = rs.getString(2);

                Tienda t = new Tienda();
                t.setIdTienda(rs.getInt(1));
                t.setDireccion(direccion);
                t.setCiudad(ciudad);
                tiendas.add(t);

                if (lugar.equalsIgnoreCase("ciudad")) {
                    if (!ciudadExiste(ciudad)) {
                        MenuItem mn = new MenuItem(ciudad);
                        mn.setUserData(ciudad);
                        ciudades.add(mn);
                    }
                }

                MenuItem mn2 = new MenuItem(direccion);
                mn2.setUserData(direccion);
                direcciones.add(mn2);
            }
        } catch (SQLException ex) {
            MODELO.Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer la información de las tiendas", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private boolean ciudadExiste(String ciudad) {
        Iterator it = this.ciudades.iterator();
        while (it.hasNext()) {
            MenuItem m = (MenuItem) it.next();
            if (m.getText().equalsIgnoreCase(ciudad)) {
                return true;
            }
        }
        return false;
    }
    
    public void borrarTienda(int idTienda){
        
    }
    
}
