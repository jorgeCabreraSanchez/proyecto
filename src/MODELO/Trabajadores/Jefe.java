/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Trabajadores;

import MODELO.Trabajadores.Trabajadores;
import java.sql.Blob;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;

/**
 *
 * @author jorge
 */
public class Jefe extends Trabajadores {

    public Jefe(String nombre, int id, String apellido1, String contraseña, String estado, String horario) {
        super(nombre, id, apellido1, contraseña, estado, horario);
    }

    public Jefe(int id, String nombre, String apellido1, String apellido2, String estado, String horario) {
        super(id, nombre, apellido1, apellido2, estado, horario);
    }

    public Jefe(int id, String nombre, String apellido1, String estado, String horario) {
        super(id, nombre, apellido1, estado, horario);
    }

    public Jefe(String nombre, String apellido1, String apellido2, String contraseña, String estado, int id, String horario) {
        super(nombre, apellido1, apellido2, contraseña, estado, id, horario);
    }

}
