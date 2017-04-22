/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Empleados;

/**
 *
 * @author jorge
 */
public abstract class Empleados {

    protected int id;
    protected String nombre;
    protected String contraseña;

    public Empleados() {

    }

    public Empleados(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }



}
