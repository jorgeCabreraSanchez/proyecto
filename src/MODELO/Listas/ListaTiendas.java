/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionTiendas;
import MODELO.Tienda;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author daw
 */
public class ListaTiendas {

    private Set<String> direcciones = new HashSet<>();
    private Set<String> ciudades = new HashSet<>();
    private Set<Tienda> listaTiendas = new HashSet<>();
    private Set<Tienda> listaTiendasMostrar = new HashSet<>();
    private GestionTiendas gs;

    public ListaTiendas() throws SQLException {
        gs = new GestionTiendas();
        this.listaTiendas = gs.cargarTiendas();
    }

    public Set<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<String> direcciones) {
        this.direcciones = direcciones;
    }

    public void setDirecciones(String direccion) {
        this.direcciones.add(direccion);
    }

    public Set<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Set<String> ciudades) {
        this.ciudades = ciudades;
    }

    public void setCiudades(String ciudad) {
        this.ciudades.add(ciudad);
    }

    public Set<Tienda> getListaTiendas() {
        return listaTiendas;
    }

    public void setListaTiendas(Set<Tienda> listaTiendas) {
        this.listaTiendas = listaTiendas;
    }

    public void nuevaTienda(Tienda tienda) throws SQLException {
        gs.nuevaTienda(tienda);
        this.listaTiendas.add(tienda);
    }

    public void borrarTienda(Tienda tienda) throws SQLException {
        gs.borrarTienda(tienda);
        this.listaTiendas.remove(tienda);
    }

    public void editarTienda(Integer antiguaTiendaID, Tienda nuevaTienda) throws SQLException {
        gs.editarTienda(antiguaTiendaID, nuevaTienda);
        /* Preguntar si el GestionTiendas lo pongo como atributo de la clase
        o lo creo en cada metodo */
        boolean seguir = true;
        Iterator<Tienda> it = listaTiendas.iterator();
        while (it.hasNext() && seguir == true) {
            Tienda tienda = it.next();
            if (tienda.getIdTienda() == antiguaTiendaID) {
                tienda.cogerDatos(nuevaTienda);
                seguir = false;
            }
        }
    }

    public Set<Tienda> getListaTiendasMostrar() {
        return listaTiendasMostrar;
    }

    public Set<Tienda> getListaTiendasMostrar(String ciu, String dire) {
        this.listaTiendasMostrar.clear();
        this.ciudades.clear();
        this.direcciones.clear();

        for (Tienda tienda : listaTiendas) {
            String ciudad = tienda.getCiudad();
            String direccion = tienda.getDireccion();
            if (empiezaPor(ciudad, ciu) && empiezaPor(direccion, dire)) {
                this.listaTiendasMostrar.add(tienda);
                if (!ciudades.contains(ciudad)) {
                    this.ciudades.add(ciudad);
                }
                if (!direcciones.contains(direccion)) {
                    this.direcciones.add(direccion);
                }
            }
        }
        return this.listaTiendasMostrar;
    }

    public void setListaTiendasMostrar(Set<Tienda> listaTiendasMostrar) {
        this.listaTiendasMostrar = listaTiendasMostrar;
    }

    /*Métodos propios*/
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
