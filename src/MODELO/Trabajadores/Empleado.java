/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Trabajadores;

import MODELO.Trabajadores.Trabajadores;

/**
 *
 * @author jorge
 */
public class Empleado extends Trabajadores {

    private int idTienda;
    private String horario;
    private int incidencias;

    public Empleado(int idTienda, String horario, int incidencias, String nombre, int id, String apellido1, String contraseña, String estado) {
        super(nombre, id, apellido1, contraseña, estado);
        this.idTienda = idTienda;
        this.horario = horario;
        this.incidencias = incidencias;
    }

    /* Este lo uso para mostrar en lista Trabajadores */
    public Empleado(String horario, int incidencias, int id, String nombre, String apellido1, String apellido2, String estado) {
        super(id, nombre, apellido1, apellido2, estado);
        this.horario = horario;
        this.incidencias = incidencias;
    }

    public Empleado(int idTienda, String horario, int incidencias, int id, String nombre, String apellido1, String estado) {
        super(id, nombre, apellido1, estado);
        this.idTienda = idTienda;
        this.horario = horario;
        this.incidencias = incidencias;
    }

    public Empleado(int idTienda, String horario, int incidencias, String nombre, String apellido1, String apellido2, String contraseña, String estado, int id) {
        super(nombre, apellido1, apellido2, contraseña, estado, id);
        this.idTienda = idTienda;
        this.horario = horario;
        this.incidencias = incidencias;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(int incidencias) {
        this.incidencias = incidencias;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    

}
