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
public class ListaProductos {
    Set<Producto> productos = new HashSet<>();
    Set<Producto> productosMostrar = new HashSet<>();

    public ListaProductos(int idTienda) throws SQLException {
        GestionProductos gp = new GestionProductos();
        productos = gp.productosEnLaTienda(idTienda);
        productosMostrar.addAll(productos);
    }
    
    public ListaProductos() throws SQLException{
        GestionProductos gp = new GestionProductos();
        productos = gp.todosLosProductos();
        productosMostrar.addAll(productos);
    }
    
    public Set<Producto> getProductos(){
        return this.productosMostrar;
    }
    
    public Set<Producto> getProductos(String nombre,String tamaño){
        Set<Producto> lista = new HashSet<>();
        Set<Producto> listaCoger = new HashSet<>();
        if(tamaño.equalsIgnoreCase("largo")){
            listaCoger = productosMostrar;
        } else {
            
        }
        this.productos.iterator();
    }
    
    
}
