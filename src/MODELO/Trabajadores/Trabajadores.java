/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Trabajadores;

/**
 *
 * @author jorge
 */
public abstract class Trabajadores {

    protected int id;
    protected String nombre;
    protected String apellido1;
    protected String apellido2;
    protected String contraseña;
    protected int idTienda;
    protected String horario;
    protected String estado;

    public Trabajadores() {

    }

    public Trabajadores(int id, String nombre, String apellido1, String contraseña, int iDTienda, String horario, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.contraseña = contraseña;
        this.idTienda = iDTienda;
        this.horario = horario;
        this.estado = estado;
    }

    public Trabajadores(int id, String nombre, String apellido1, String apellido2, String contraseña, int iDTienda, String horario, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contraseña = contraseña;
        this.idTienda = iDTienda;
        this.horario = horario;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    

    

   

    
    
   



}
