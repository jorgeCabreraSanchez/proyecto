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
    protected String apellidos;
    protected String contraseña;
    protected int iDTienda;

    public Trabajadores() {

    }

    public Trabajadores(int id, String nombre, String contraseña,int iDTienda) {
        this.id = id;
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.iDTienda = iDTienda;
    }
    
    public Trabajadores(int id, String nombre,String apellidos, String contraseña,int iDTienda) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.contraseña = contraseña;
        this.iDTienda = iDTienda;
    }



}
