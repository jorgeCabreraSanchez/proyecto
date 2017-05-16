/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import MODELO.Tienda;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    private Tienda t;

    public Set<String> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(Set<String> direcciones) {
        ListaTiendas.direcciones = direcciones;
    }

    public void setDirecciones(String direccion) {
        ListaTiendas.direcciones.add(direccion);
    }

    public Set<String> getCiudades() {
        return ciudades;
    }

    public void setCiudades(Set<String> ciudades) {
        ListaTiendas.ciudades = ciudades;
    }

    public void setCiudades(String ciudad) {
        ListaTiendas.ciudades.add(ciudad);
    }

    public Set<Tienda> getListaTiendas() {
        return listaTiendas;
    }

    public void setListaTiendas(Set<Tienda> listaTiendas) {
        ListaTiendas.listaTiendas = listaTiendas;
    }

    public void setListaTiendas(Tienda Tienda) {
        ListaTiendas.listaTiendas.add(Tienda);
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
        ListaTiendas.listaTiendasMostrar = listaTiendasMostrar;
    }

    public Tienda getT() {
        return t;
    }

    public void setT(Tienda t) {
        ListaTiendas.t = t;
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
