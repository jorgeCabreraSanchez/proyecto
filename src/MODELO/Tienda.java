/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ReadOnlyStringProperty;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author daw
 */
public class Tienda {

    private IntegerProperty idTienda = new SimpleIntegerProperty();
    private StringProperty ciudad = new SimpleStringProperty();
    private StringProperty direccion = new SimpleStringProperty();

    public Tienda() {

    }

    public Tienda(int id, String ciudad, String direccion) {
        this.idTienda.set(id);
        this.ciudad.set(ciudad);
        this.direccion.set(direccion);
    }

    public int getIdTienda() {
        return idTienda.get();
    }

    public void setIdTienda(int value) {
        idTienda.set(value);
    }

    public String getCiudad() {
        return ciudad.get();
    }

    public void setCiudad(String value) {
        ciudad.set(value);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String value) {
        direccion.set(value);
    }

    public void cogerDatos(Tienda tienda){
        this.setIdTienda(tienda.getIdTienda());
        this.setCiudad(tienda.getCiudad());
        this.setDireccion(tienda.getDireccion());
    }
    
    public boolean igual(Tienda nueva) {
        boolean respuesta = true;
        if (this.ciudad.get().equalsIgnoreCase(nueva.getCiudad())) {
            respuesta = false;
        }
        if (this.idTienda.get() == nueva.getIdTienda()) {
            respuesta = false;
        }
        if (this.direccion.get().equalsIgnoreCase(nueva.getDireccion())) {
            respuesta = false;
        }
        
        return respuesta;
    }

    @Override
    public String toString() {
        return "Tienda{" + "idTienda=" + idTienda.getValue() + ", ciudad=" + ciudad.getValue() + ", direccion=" + direccion.getValue() + '}';
    }

}
