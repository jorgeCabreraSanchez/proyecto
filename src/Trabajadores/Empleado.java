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
public class Empleado extends Trabajadores{

    public Empleado(int id, String nombre, String contraseña, int iDTienda) {
        super(id, nombre, contraseña, iDTienda);
    }

    public Empleado(int id, String nombre, String apellidos, String contraseña, int iDTienda) {
        super(id, nombre, apellidos, contraseña, iDTienda);
    }
    
    
    
}
