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
public abstract class Incidencias {

    protected final IntegerProperty idIncidencia = new SimpleIntegerProperty();
    protected final StringProperty titulo = new SimpleStringProperty();
    protected final StringProperty descripcion = new SimpleStringProperty();
    protected final StringProperty fecha = new SimpleStringProperty();

    public Incidencias(int idIncidencias,String titulo,String descripcion,String fecha) {
        this.idIncidencia.set(idIncidencias);
        this.titulo.set(titulo);
        this.descripcion.set(descripcion);
        this.fecha.set(fecha);
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
