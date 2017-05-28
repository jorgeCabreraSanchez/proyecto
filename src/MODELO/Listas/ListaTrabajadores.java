/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionTrabajadores;
import MODELO.Trabajadores.Trabajadores;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author daw
 */
public class ListaTrabajadores {

    private Set<Trabajadores> trabajadores = new HashSet<>();
    private Set<Trabajadores> trabajadoresMostrar = new HashSet<>();

    public ListaTrabajadores(int idTienda) throws SQLException {
        GestionTrabajadores gs = new GestionTrabajadores();
        trabajadores = gs.trabajadoresEnLaTienda(idTienda);
        trabajadoresMostrar.addAll(trabajadores);
    }

    public Set<Trabajadores> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(Set<Trabajadores> trabajadores) {
        this.trabajadores = trabajadores;
    }

    public void setTrabajador(Trabajadores trabajador) {
        this.trabajadores.add(trabajador);
    }

    public Set<Trabajadores> getTrabajadores(String nom, String ape, String tamaño) {
        Set<Trabajadores> lista = new HashSet<>();
        Set<Trabajadores> listaCoger = new HashSet<>();
        if (tamaño.equalsIgnoreCase("largo")) {
            listaCoger = this.trabajadoresMostrar;
        } else {
            listaCoger = this.trabajadores;
            this.trabajadoresMostrar.clear();
            this.trabajadoresMostrar.addAll(this.trabajadores);
        }
        
        for (Trabajadores trabajador : listaCoger) {
            if (empiezaPor(trabajador.getNombre(), nom) && empiezaPor(trabajador.getApellido1(), ape)) {
                lista.add(trabajador);                
            }
        }
        
        if (tamaño.equalsIgnoreCase("largo")) {
            this.trabajadoresMostrar.clear();
            this.trabajadoresMostrar.addAll(lista);
        }
        return lista;
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
