/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Jorge Cabrera
 */
public class Producto {

    private final IntegerProperty idProducto = new SimpleIntegerProperty();
    private final StringProperty nombre = new SimpleStringProperty();
    private final StringProperty descripcion = new SimpleStringProperty();

    public Producto(int idProducto,String nombre,String descripcion) {
        this.idProducto.set(idProducto);
        this.nombre.set(nombre);
        this.descripcion.set(descripcion);
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
    
    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String value) {
        nombre.set(value);
    }

    public StringProperty nombreProperty() {
        return nombre;
    }
        
    public int getIdProducto() {
        return idProducto.get();
    }

    public void setIdProducto(int value) {
        idProducto.set(value);
    }

    public IntegerProperty idProductoProperty() {
        return idProducto;
    }
    
    public String toString(){
        return this.nombre.get();
    }
    
}
