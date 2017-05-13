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
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Alert;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaTiendas {

    private List<String> ciudadesHayTienda = new ArrayList<>();
    private List<Tienda> tiendas = new ArrayList<>();
    private List<String> direcciones = new ArrayList<>();
    private List<Tienda> tiendasMostrar = new ArrayList<>();

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

    public List<Tienda> getTiendas(String ciu, String dire) {
        this.tiendasMostrar.clear();
        this.ciudadesHayTienda.clear();
        this.direcciones.clear();
        
        for (Tienda tienda : tiendas) {
            String ciudad = tienda.getCiudad();
            String direccion = tienda.getDireccion();
            if (empiezaPor(ciudad, ciu) && empiezaPor(direccion, dire)) {                
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
                t.setDireccion(rs.getString(2));
                t.setCiudad(rs.getString(3));
                tiendas.add(t);
            }
            tiendasMostrar.addAll(tiendas);
        } catch (SQLException ex) {
            MODELO.Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer la información de las tiendas", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    public void borrarTienda(int idTienda) {

    }

    private boolean empiezaPor(String palabra1, String empieza) {
        if (empieza.equalsIgnoreCase("")) {
            return true;
        }
        int longitud = empieza.length();
        String palabra = palabra1.substring(0, longitud);

        if (palabra.equalsIgnoreCase(empieza)) {
            return true;
        } else {
            return false;
        }
    }

}
