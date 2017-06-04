/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Trabajadores;

import MODELO.Trabajadores.Trabajadores;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author jorge
 */
public class Empleado extends Trabajadores {

    private IntegerProperty idTienda = new SimpleIntegerProperty();
    private IntegerProperty incidencias = new SimpleIntegerProperty();

    public Empleado(int idTienda, String horario, int incidencias, String nombre, int id, String apellido1, String contraseña, String estado) {
        super(nombre, id, apellido1, contraseña, estado, horario);
        this.idTienda.set(idTienda);
        this.incidencias.set(incidencias);
    }

    /* Este lo uso para mostrar en lista Trabajadores */
    public Empleado(String horario, int incidencias, int id, String nombre, String apellido1, String apellido2, String estado, Integer idTienda) {
        super(id, nombre, apellido1, apellido2, estado, horario);
        this.incidencias.set(incidencias);
        this.idTienda.set(idTienda);
    }

    public Empleado(int idTienda, String horario, int incidencias, int id, String nombre, String apellido1, String estado) {
        super(id, nombre, apellido1, estado, horario);
        this.idTienda.set(idTienda);
        this.incidencias.set(incidencias);
    }

    public Empleado(int idTienda, String horario, String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2, horario);
        this.idTienda.set(idTienda);
    }

    public Empleado(int idTienda, String horario, int incidencias, String nombre, String apellido1, String apellido2, String contraseña, String estado, int id, byte[] imagen) {
        super(nombre, apellido1, apellido2, contraseña, estado, id, horario, imagen);
        this.idTienda.set(idTienda);
        this.incidencias.set(incidencias);
    }

    public Empleado(int idTienda, String horario, String nombre, String apellido1, String apellido2, String contraseña, String estado, int id, byte[] imagen) {
        super(nombre, apellido1, apellido2, contraseña, estado, id, horario, imagen);
        this.idTienda.set(idTienda);
    }

    public int getIncidencias() {
        return incidencias.get();
    }

    public void setIncidencias(int value) {
        incidencias.set(value);
    }

    public IntegerProperty incidenciasProperty() {
        return incidencias;
    }

    public Image getImagen() throws IOException {
//        try ( ObjectInputStream entrada = new ObjectInputStream())
        ByteArrayInputStream in = new ByteArrayInputStream(this.imagen);
        BufferedImage read = ImageIO.read(in);
        return SwingFXUtils.toFXImage(read, null);
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
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

}
