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
public class Encargado extends Trabajadores{

    public Encargado(int id, String nombre, String contraseña, int iDTienda) {
        super(id, nombre, contraseña, iDTienda);
    }

    public Encargado(int id, String nombre, String apellidos, String contraseña, int iDTienda) {
        super(id, nombre, apellidos, contraseña, iDTienda);
    }
    
    
    
}
