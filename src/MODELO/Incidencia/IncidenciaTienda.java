/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jorge Cabrera
 */
public class IncidenciaTienda extends Incidencia {

    private final IntegerProperty idTienda = new SimpleIntegerProperty();

    public IncidenciaTienda(int idIncidencia,int idTienda, String titulo, String descripcion, String fecha) {
        super(idIncidencia, titulo, descripcion, fecha);
        this.idIncidencia.set(idTienda);
    }

    public int getIdTienda() {
        return idTienda.get();
    }

    public void setIdTienda(int value) {
        idTienda.set(value);
    }

    public IntegerProperty idTiendaProperty() {
        return idTienda;
    }

    public String getFecha() {
        return fecha.get();
    }

    public void setFecha(String value) {
        fecha.set(value);
    }

    public StringProperty fechaProperty() {
        return fecha;
    }

    public String getDescripcion() {
        return descripcion.get();
    }

    public void setDescripcion(String value) {
        descripcion.set(value);
    }

    public StringProperty descripcionProperty() {
        return descripcion;
    }

    public String getTitulo() {
        return titulo.get();
    }

    public void setTitulo(String value) {
        titulo.set(value);
    }

    public StringProperty tituloProperty() {
        return titulo;
    }

    public int getIdIncidencia() {
        return idIncidencia.get();
    }

    public void setIdIncidencia(int value) {
        idIncidencia.set(value);
    }

    public IntegerProperty idIncidenciaProperty() {
        return idIncidencia;
    }

}
