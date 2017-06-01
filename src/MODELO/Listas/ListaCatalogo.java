/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionProductos;
import MODELO.Producto;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaCatalogo {
     GestionProductos gp;
     Set<Producto> catalogo = new HashSet<>();
     Set<Producto> catalogoMostrar = new HashSet<>();

    public ListaCatalogo() throws SQLException {
        gp = new GestionProductos();        
        catalogo = gp.catalogo();
        catalogoMostrar.addAll(catalogo);
    }
    
    public Set<Producto> catalogo() throws SQLException {
        return gp.catalogo();        
    }
}
