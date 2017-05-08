/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

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

    private List<String> ciudadesHayTienda = ArrayList < > ();
    private List<Tienda> tiendas = ArrayList < > ();
    private List<String> direcciones = ArrayList < > ();
    private List<String> tiendasMostrar = ArrayList < > ();

    public ListaTiendas() {
        cargarTiendas();
    }

    public List<String> getDirecciones() {
        return this.direcciones;
    }

    public List<String> getCiudadesHayTienda() {
        return this.ciudadesHayTienda;
    }

    public List<Tienda> getTiendas() {
        return tiendasMostrar;
    }

    public List<Tienda> getTiendas(String ciudad, String direccion) {
        this.tiendasMostrar.clear();
        this.ciudadesHayTienda.clear();
        this.direcciones.clear();

        for (Tienda tienda : tiendas) {
            if (tienda.getCiudad().startsWith(ciudad) && tienda.getDireccion().startsWith(direccion)) {
                this.tiendasMostrar.add(tienda);
                if (!ciudadesHayTienda.contains(ciudad)) {
                    this.ciudadesHayTienda.add(ciudad);
                }
                if (!direcciones.contains(direccion)) {
                    this.direcciones.add(direccion);
                }
            }
        }
        return this.tiendasMostrar;
    }

    public void cargarTiendas() {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            tiendas.clear();

            String sentencia = "Select * from tiendas";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Tienda t = new Tienda();
                t.setIdTienda(rs.getInt(1));
                t.setDireccion(rs.getString(2););
                t.setCiudad(rs.getString(3););
                tiendas.add(t);
            }
            tiendasMostrar.add(tiendas);
        } catch (SQLException ex) {
            MODELO.Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer la información de las tiendas", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void borrarTienda(int idTienda) {

    }

}
