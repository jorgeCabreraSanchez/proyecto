/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trabajadores;

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
    protected int iDTienda;
    protected String horario;
    protected String estado;

    public Trabajadores() {

    }

    public Trabajadores(int id, String nombre, String apellido1, String contraseña, int iDTienda, String horario, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.contraseña = contraseña;
        this.iDTienda = iDTienda;
        this.horario = horario;
        this.estado = estado;
    }

    public Trabajadores(int id, String nombre, String apellido1, String apellido2, String contraseña, int iDTienda, String horario, String estado) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.contraseña = contraseña;
        this.iDTienda = iDTienda;
        this.horario = horario;
        this.estado = estado;
    }

    

   

    
    
   



}
