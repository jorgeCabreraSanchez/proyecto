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
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaProductos {

    Set<Producto> productos = new HashSet<>();
    Set<Producto> productosMostrar = new HashSet<>();
    GestionProductos gp;

    public ListaProductos(int idTienda) throws SQLException {
        gp = new GestionProductos();
        productos = gp.productosEnLaTienda(idTienda);
        productosMostrar.addAll(productos);
    }

    public Set<Producto> getProductos() {
        return this.productos;
    }

    public Set<Producto> getProductos(String nombre, String tamaño) {
        Set<Producto> lista = new HashSet<>();
        Set<Producto> listaCoger = new HashSet<>();

        if (tamaño.equalsIgnoreCase("largo")) {
            listaCoger.addAll(productosMostrar);
        } else {
            listaCoger.addAll(productos);
        }

        Iterator it = listaCoger.iterator();
        while (it.hasNext()) {
            Producto producto = (Producto) it.next();
            if (empiezaPor(producto.getNombre(), nombre)) {
                lista.add(producto);
            }
        }

        this.productosMostrar = lista;
        return this.productosMostrar;
    }

    

    public void nuevoProductoEnTienda(int idTienda, Producto producto) throws SQLException {        
        gp.nuevoProductoEnTienda(idTienda, producto.getIdProducto());       
        this.productos.add(producto);        
    }

    public void eliminarProductoEnTienda(int idTienda,Producto producto) throws SQLException {
        gp.eliminarProductoEnTienda(idTienda, producto.getIdProducto());
        this.productos.remove(producto);
    }

    private boolean empiezaPor(String palabra1, String empieza) {
        if (empieza.equalsIgnoreCase("")) {
            return true;
        }
        int longitud = empieza.length();
        if (longitud > palabra1.length()) {
            return false;
        }
        String palabra = palabra1.substring(0, longitud);

        if (palabra.equalsIgnoreCase(empieza)) {
            return true;
        } else {
            return false;
        }
    }

}
