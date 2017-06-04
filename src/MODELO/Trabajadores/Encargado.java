/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Trabajadores;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author jorge
 */
public class Encargado extends Trabajadores {

    private IntegerProperty idTienda = new SimpleIntegerProperty();
    private IntegerProperty incidencias = new SimpleIntegerProperty();

    public Encargado(int idTienda, String horario, int incidencias, String nombre, int id, String apellido1, String contraseña, String estado) {
        super(nombre, id, apellido1, contraseña, estado, horario);
        this.idTienda.set(id);
        this.incidencias.set(incidencias);
    }

    /* Este es el que uso para mostrar en lista Trabajadores */
    public Encargado(String horario, int incidencias, int id, String nombre, String apellido1, String apellido2, String estado, Integer idTienda) {
        super(id, nombre, apellido1, apellido2, estado, horario);
        this.incidencias.set(incidencias);
        this.idTienda.set(idTienda);
    }

    public Encargado(int idTienda, String horario, String nombre, String apellido1, String apellido2) {
        super(nombre, apellido1, apellido2, horario);
        this.idTienda.set(idTienda);
    }

    public Encargado(int idTienda, String horario, int incidencias, int id, String nombre, String apellido1, String estado) {
        super(id, nombre, apellido1, estado, horario);
        this.idTienda.set(id);
        this.incidencias.set(incidencias);
    }

    public Encargado(int idTienda, String horario, int incidencias, String nombre, String apellido1, String apellido2, String contraseña, String estado, int id) {
        super(nombre, apellido1, apellido2, contraseña, estado, id, horario);
        this.idTienda.set(id);
        this.incidencias.set(incidencias);
    }

    public Encargado(int idTienda, String horario, String nombre, String apellido1, String apellido2, String contraseña, String estado, int id) {
        super(nombre, apellido1, apellido2, contraseña, estado, id, horario);
        this.idTienda.set(id);
    }

    public int getIncidencias() {
        return incidencias.get();
    }

    public void setIncidencias(int value) {
        incidencias.set(value);
    }

//    public Image getImagen() throws IOException {
////        try ( ObjectInputStream entrada = new ObjectInputStream())
//        ByteArrayInputStream in = new ByteArrayInputStream(this.imagen);
//        BufferedImage read = ImageIO.read(in);
//        return SwingFXUtils.toFXImage(read, null);
//    }
//
//    public void setImagen(byte[] imagen) {
//        this.imagen = imagen;
//    }

    public IntegerProperty incidenciasProperty() {
        return incidencias;
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
