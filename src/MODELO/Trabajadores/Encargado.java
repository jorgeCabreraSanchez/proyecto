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
public class Encargado extends Trabajadores{

    public Encargado(int id, String nombre, String apellido1, String contraseña, int iDTienda, String horario, String estado) {
        super(id, nombre, apellido1, contraseña, iDTienda, horario, estado);
    }

    public Encargado(int id, String nombre, String apellido1, String apellido2, String contraseña, int iDTienda, String horario, String estado) {
        super(id, nombre, apellido1, apellido2, contraseña, iDTienda, horario, estado);
    }

    

    
    
    
    
}
