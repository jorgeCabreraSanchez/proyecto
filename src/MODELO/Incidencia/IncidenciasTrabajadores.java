/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Incidencia;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jorge Cabrera
 */
public class IncidenciasTrabajadores extends Incidencias {

    private final IntegerProperty idTrabajador = new SimpleIntegerProperty();
    private final StringProperty tipo = new SimpleStringProperty();

    public IncidenciasTrabajadores(int idIncidencias,int idTrabajador, String titulo, String descripcion,String tipo, String fecha) {
        super(idIncidencias, titulo, descripcion, fecha);
        this.idIncidencia.set(idTrabajador);
        this.tipo.set(tipo);
    }

    public String getTipo() {
        return tipo.get();
    }

    public void setTipo(String value) {
        tipo.set(value);
    }

    public StringProperty TipoProperty() {
        return tipo;
    }

    public int getIdTrabajador() {
        return idTrabajador.get();
    }

    public void setIdTrabajador(int value) {
        idTrabajador.set(value);
    }

    public IntegerProperty idTrabajadorProperty() {
        return idTrabajador;
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
