/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionIncidencias;
import MODELO.Incidencia.IncidenciaTienda;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaIncidenciasTienda {

    List<IncidenciaTienda> incidencias;
    GestionIncidencias gi;

    public ListaIncidenciasTienda() {

    }

    public ListaIncidenciasTienda(int idTienda) throws SQLException {
        gi = new GestionIncidencias();
        incidencias = gi.getIncidencias(idTienda);
    }

    public List<IncidenciaTienda> mostrarIncidencias(LocalDate dateEntrada, LocalDate dateSalida) {
        List<IncidenciaTienda> lista = new ArrayList<>();
        for (IncidenciaTienda incidencia : incidencias) {

            int añoFecha = incidencia.getFecha().toLocalDate().getYear();
            int mesFecha = incidencia.getFecha().toLocalDate().getMonthValue();
            int diaFecha = incidencia.getFecha().toLocalDate().getDayOfMonth();
            int añoEntrada = dateEntrada.getYear();
            int mesEntrada = dateEntrada.getMonthValue();
            int diaEntrada = dateEntrada.getDayOfMonth();
            int añoSalida = dateSalida.getYear();
            int mesSalida = dateSalida.getMonthValue();
            int diaSalida = dateSalida.getDayOfMonth();

            if (añoFecha >= añoEntrada && añoFecha <= añoSalida) {
                if (añoFecha != añoEntrada && añoFecha != añoSalida) {
                    lista.add(incidencia);
                } else if (mesFecha > mesEntrada && mesFecha < mesSalida) {
                    lista.add(incidencia);
                } else if (mesFecha < mesEntrada || mesFecha > mesSalida) {
                    /* No entras en la siguiente */
                } else if (mesFecha == mesEntrada) {
                    if (diaFecha >= diaEntrada) {
                        lista.add(incidencia);
                    };
                } else if (mesFecha == mesSalida) {
                    if (diaFecha <= diaSalida) {
                        lista.add(incidencia);
                    }
                }
            }

        }
        return lista;
    }

    public void borrarIncidencia(int idIncidencia) throws SQLException {
        boolean seguir = true;
        this.gi.borrarIncidencia(idIncidencia);
        Iterator<IncidenciaTienda> it = this.incidencias.iterator();
        while (it.hasNext() && seguir) {
            IncidenciaTienda incidencia = it.next();
            if (incidencia.getIdIncidencia() == idIncidencia) {
                this.incidencias.remove(incidencia);
                seguir = false;
            }
        }
    }

    public List<IncidenciaTienda> mostrarIncidencias() {
        return incidencias;
    }

    public List<IncidenciaTienda> nuevaIncidencia(IncidenciaTienda incidencia) throws SQLException {
        gi.nuevaIncidencia(incidencia);
        incidencias = gi.getIncidencias(incidencia.getIdTienda());
        return this.incidencias;
    }

    public List<IncidenciaTienda> editarIncidencia(IncidenciaTienda incidencia) throws SQLException {
        boolean seguir = true;
        this.gi.editarIncidencia(incidencia);
        Iterator<IncidenciaTienda> it = this.incidencias.iterator();
        while (it.hasNext() && seguir) {
            IncidenciaTienda incidenciaCogida = it.next();
            if (incidenciaCogida.getIdIncidencia() == incidencia.getIdIncidencia()) {
                this.incidencias.remove(incidenciaCogida);
                this.incidencias.add(0,incidencia);
                seguir = false;
            }
        }
        return this.incidencias;
    }

}
