/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Trabajadores.Trabajadores;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw
 */
public class ListaTrabajadores {
     static private List<Trabajadores> trabajadores = new ArrayList<>();

    public static List<Trabajadores> getTrabajadores() {
        return trabajadores;
    }

    public static void setTrabajadores(List<Trabajadores> trabajadores) {
        ListaTrabajadores.trabajadores = trabajadores;
    }
    
    public static void setTrabajadores(Trabajadores trabajador) {
        ListaTrabajadores.trabajadores.add(trabajador);
    }
    
    
     
}
