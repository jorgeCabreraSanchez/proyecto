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
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javax.imageio.ImageIO;

/**
 *
 * @author jorge
 */
public abstract class Trabajadores {
    
    protected IntegerProperty id = new SimpleIntegerProperty();
    protected StringProperty nombre = new SimpleStringProperty();
    protected StringProperty apellido1 = new SimpleStringProperty();
    protected StringProperty apellido2 = new SimpleStringProperty();
    protected StringProperty contraseña = new SimpleStringProperty();
    protected StringProperty estado = new SimpleStringProperty();
    protected StringProperty horario = new SimpleStringProperty();
    protected byte[] imagen;
    
    public Trabajadores() {
        
    }
    
    public Trabajadores(String nombre, int id, String apellido1, String contraseña, String estado,String horario) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.contraseña.set(contraseña);
        this.estado.set(estado);
        this.horario.set(horario);
    }
    
    public Trabajadores(String nombre, String apellido1, String apellido2,String horario) {
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
        this.horario.set(horario);
    }
    
    public Trabajadores(int id, String nombre, String apellido1, String apellido2, String estado,String horario) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
        this.estado.set(estado);
        this.horario.set(horario);
    }
    
    public Trabajadores(int id, String nombre, String apellido1, String estado,String horario) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.estado.set(estado);
        this.horario.set(horario);
    }
    
    public Trabajadores(String nombre, String apellido1, String apellido2, String contraseña, String estado, int id,String horario,byte[] imagen) {
        this.id.set(id);
        this.nombre.set(nombre);
        this.apellido1.set(apellido1);
        this.apellido2.set(apellido2);
        this.contraseña.set(contraseña);
        this.estado.set(estado);
        this.horario.set(horario);
        this.imagen = imagen;
    }

    public Image getImagen() throws IOException {
//        try ( ObjectInputStream entrada = new ObjectInputStream())
       
            ByteArrayInputStream in = new ByteArrayInputStream(this.imagen);
            BufferedImage read = ImageIO.read(in);      
            
            return SwingFXUtils.toFXImage(read, new WritableImage(200, 200));
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }        
    
    public String getEstado() {
        return estado.get();
    }
    
    public void setEstado(String value) {
        estado.set(value);
    }
    
    public StringProperty estadoProperty() {
        return estado;
    }
    
    public String getContraseña() {
        return contraseña.get();
    }
    
    public void setContraseña(String value) {
        contraseña.set(value);
    }
    
    public StringProperty contraseñaProperty() {
        return contraseña;
    }
    
    public String getApellido2() {
        return apellido2.get();
    }
    
    public void setApellido2(String value) {
        apellido2.set(value);
    }
    
    public StringProperty apellido2Property() {
        return apellido2;
    }
    
    public String getApellido1() {
        return apellido1.get();
    }
    
    public void setApellido1(String value) {
        apellido1.set(value);
    }
    
    public StringProperty apellido1Property() {
        return apellido1;
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
    
    public int getId() {
        return id.get();
    }
    
    public void setId(int value) {
        id.set(value);
    }
    
    public IntegerProperty idProperty() {
        return id;
    }
    
    public String getHorario() {
        return horario.get();
    }    

    public void setHorario(String value) {
        horario.set(value);
    }

    public StringProperty horarioProperty() {
        return horario;
    }
    
    public String toString() {
        String apellido2string = this.apellido2.get();
        if (apellido2string == null) {
            apellido2string = "";
        }
        return "IDTrabajador: " + this.id.get() + "   Nombre: " + this.nombre.get() + "   Apellidos: " + this.apellido1.get() + " " + apellido2string;
    }
    
}
