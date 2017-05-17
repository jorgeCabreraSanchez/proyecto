/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import MODELO.Trabajadores.Trabajadores;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author daw
 */
public class ListaTrabajadores {
     private List<Trabajadores> trabajadores = new ArrayList<>();

    public List<Trabajadores> getTrabajadores() {
        return trabajadores;
    }

    public void setTrabajadores(List<Trabajadores> trabajadores) {
        this.trabajadores = trabajadores;
    }
    
    public void setTrabajadores(Trabajadores trabajador) {
        this.trabajadores.add(trabajador);
    }
    
    
     
}
