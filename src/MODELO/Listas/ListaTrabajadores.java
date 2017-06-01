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
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author daw
 */
public class ListaTrabajadores {

    private Set<Trabajadores> trabajadores = new HashSet<>();
    private Set<Trabajadores> trabajadoresMostrar = new HashSet<>();
    private GestionTrabajadores gt;

    public ListaTrabajadores(int idTienda) throws SQLException {
        gt = new GestionTrabajadores();
        traerTrabajadores(idTienda);
    }
    
    public void traerTrabajadores(int idTienda) throws SQLException{
        trabajadores = gt.trabajadoresEnLaTienda(idTienda);
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
        }

        for (Trabajadores trabajador : listaCoger) {
            if (empiezaPor(trabajador.getNombre(), nom) && empiezaPor(trabajador.getApellido1(), ape)) {
                lista.add(trabajador);
            }
        }
        this.trabajadoresMostrar.clear();
        this.trabajadoresMostrar.addAll(lista);

        return lista;
    }

    public void editarTrabajador(int idTrabajador, String campo,String nuevo) throws SQLException {
        gt.modificarTrabajador(idTrabajador,campo,nuevo);
    }
    

    public void eliminarTrabajador(int idTrabajador) throws SQLException {
        this.gt.borrarTrabajador(idTrabajador);
        boolean seguir = true;

        Iterator<Trabajadores> it = this.trabajadores.iterator();
        while (it.hasNext() && seguir) {
            Trabajadores trabajador = it.next();
            if (trabajador.getId() == idTrabajador) {
                this.trabajadores.remove(trabajador);
                this.trabajadoresMostrar.remove(trabajador);
                seguir = false;
            }
        }
    }

    
    public void nuevoTrabajador(Trabajadores trabajador) throws SQLException{
      gt.nuevoTrabajador(trabajador);
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
