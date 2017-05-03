/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiendas;

import java.util.Collection;
import java.util.Iterator;
import javafx.collections.FXCollections;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaCiudadesConTienda {

    static private Collection<MenuItem> ciudades = FXCollections.observableArrayList();

    static public Collection<MenuItem> getCiudades() {
        return ciudades;
    }

    static public Collection<MenuItem> getCiudadesComienzanPor(String palabra) {
        Collection<MenuItem> ciudadesEspecificas = FXCollections.observableArrayList();
        Iterator i = ciudades.iterator();
        while (i.hasNext()) {
            MenuItem m = (MenuItem) i.next();
           if (m.toString().startsWith(palabra)) {
                ciudadesEspecificas.add(m);
            }
        }

        return ciudadesEspecificas;
    }

    static public void setCiudad(MenuItem ciudad) {
        ListaCiudadesConTienda.ciudades.add(ciudad);
    }

}
