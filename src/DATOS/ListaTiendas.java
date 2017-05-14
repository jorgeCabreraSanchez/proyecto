/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Alertas;
import MODELO.Tienda;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaTiendas {

    private static List<String> ciudadesHayTienda = new ArrayList<>();
    private static List<Tienda> tiendas = new ArrayList<>();
    private static List<String> direcciones = new ArrayList<>();
    private static List<Tienda> tiendasMostrar = new ArrayList<>();
    private static Tienda tiendaEditar = new Tienda();

    public ListaTiendas() {

    }

    public static Tienda getTiendaEditar() {
        return tiendaEditar;
    }

    public void setTiendaEditar(Tienda tiendaEditar) {
        ListaTiendas.tiendaEditar = tiendaEditar;
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

    public void borrarTienda(Tienda tienda) {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            String sentencia = "Delete from tiendas where idTienda = ?";
            PreparedStatement ps = connect.prepareStatement(sentencia);
            ps.setInt(1, tienda.getIdTienda());
            ps.executeUpdate();
            this.tiendas.remove(tienda);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido borrar la tienda", "No se ha podido borrar la tienda debido a que esta contiene empleados, y los empleados siempre tienen que estar asignados a una tienda", Alert.AlertType.ERROR);
        }

    }

    public void editarTienda(Tienda antiguaTienda, Tienda nuevaTienda) {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            String sentencia = "Update tiendas set ";
            
            /* En la lista de tiendas modifico la tienda */
            boolean seguir = true;
            Iterator<Tienda> it = ListaTiendas.tiendas.iterator();
            while (it.hasNext() && seguir == true) {
                Tienda tienda = it.next();
                if (tienda == antiguaTienda) {
                    tienda = nuevaTienda;
                    seguir = false;
                }
            }

        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido modificar la Tienda", Alert.AlertType.ERROR);
        }

    }

    private boolean empiezaPor(String palabra1, String empieza) {
        if (empieza.equalsIgnoreCase("")) {
            return true;
        }
        int longitud = empieza.length();
        if (longitud > palabra1.length()) {
            return false;
        }
        String palabra = palabra1.substring(0, longitud);

        if (palabra.equalsIgnoreCase(empieza)) {
            return true;
        } else {
            return false;
        }
    }

}
