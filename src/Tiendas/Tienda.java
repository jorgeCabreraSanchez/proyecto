/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiendas;

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

    private final IntegerProperty idTienda = new SimpleIntegerProperty();
    private final StringProperty ciudad = new SimpleStringProperty();
    private final StringProperty direccion = new SimpleStringProperty();

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

}
