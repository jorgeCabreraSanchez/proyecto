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
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaIncidenciasTienda {

    Set<IncidenciaTienda> incidencias;

    public ListaIncidenciasTienda() {

    }

    public ListaIncidenciasTienda(int idTienda) throws SQLException {
        GestionIncidencias gs = new GestionIncidencias();
        incidencias = gs.getIncidencias(idTienda);
    }

    public Set<IncidenciaTienda> mostrarIncidencias(LocalDate dateEntrada, LocalDate dateSalida) {
        Set<IncidenciaTienda> lista = new HashSet<>();
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
                if (añoFecha == añoEntrada) {
                    if (mesFecha >= mesEntrada) {
                        if (diaFecha >= diaEntrada) {
                            lista.add(incidencia);
                        }
                    }
                } else if (añoFecha == añoSalida) {
                    if (mesFecha <= mesSalida) {
                        if (diaFecha <= diaSalida) {
                            lista.add(incidencia);
                        }
                    }
                }

            } else {
                lista.add(incidencia);
            }

        }
        return lista;
    }

    public Set<IncidenciaTienda> mostrarIncidencias() {
        return incidencias;
    }

}
