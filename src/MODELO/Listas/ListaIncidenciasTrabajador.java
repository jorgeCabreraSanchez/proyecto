/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionIncidencias;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Incidencia.IncidenciaTrabajador;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaIncidenciasTrabajador {
        List<IncidenciaTrabajador> incidencias;
    GestionIncidencias gi;

    public ListaIncidenciasTrabajador() {

    }

    public ListaIncidenciasTrabajador(int idTrabajador) throws SQLException {
        gi = new GestionIncidencias();
        incidencias = gi.getIncidenciasTrabajador(idTrabajador);
    }

    public List<IncidenciaTrabajador> mostrarIncidencias(LocalDate dateEntrada, LocalDate dateSalida) {
        List<IncidenciaTrabajador> lista = new ArrayList<>();
        for (IncidenciaTrabajador incidencia : incidencias) {

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
        this.gi.borrarIncidenciaTrabajador(idIncidencia);
        Iterator<IncidenciaTrabajador> it = this.incidencias.iterator();
        while (it.hasNext() && seguir) {
            IncidenciaTrabajador incidencia = it.next();
            if (incidencia.getIdIncidencia() == idIncidencia) {
                this.incidencias.remove(incidencia);
                seguir = false;
            }
        }
    }

    public List<IncidenciaTrabajador> mostrarIncidencias() {
        return incidencias;
    }

    public List<IncidenciaTrabajador> nuevaIncidencia(IncidenciaTrabajador incidencia) throws SQLException {
        gi.nuevaIncidenciaTrabajador(incidencia);
        incidencias = gi.getIncidenciasTrabajador(incidencia.getIdTrabajador());
        return this.incidencias;
    }

    public List<IncidenciaTrabajador> editarIncidencia(IncidenciaTrabajador incidencia) throws SQLException {
        boolean seguir = true;
        this.gi.editarIncidenciaTrabajador(incidencia);
        Iterator<IncidenciaTrabajador> it = this.incidencias.iterator();
        while (it.hasNext() && seguir) {
            IncidenciaTrabajador incidenciaCogida = it.next();
            if (incidenciaCogida.getIdIncidencia() == incidencia.getIdIncidencia()) {
                this.incidencias.remove(incidenciaCogida);
                this.incidencias.add(0,incidencia);
                seguir = false;
            }
        }
        return this.incidencias;
    }
}
