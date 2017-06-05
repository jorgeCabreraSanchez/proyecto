/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionProductos;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Producto;
import MODELO.Trabajadores.Trabajadores;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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

    public Set<Producto> getProductos(String nom, String tamaño) {
        Set<Producto> lista = new HashSet<>();
        Set<Producto> listaCoger = new HashSet<>();
        if (tamaño.equalsIgnoreCase("largo")) {
            listaCoger = this.catalogoMostrar;
        } else {
            listaCoger = this.catalogo;
        }

        for (Producto producto : listaCoger) {
            if (empiezaPor(producto.getNombre(), nom)) {
                lista.add(producto);
            }
        }
        this.catalogoMostrar.clear();
        this.catalogoMostrar.addAll(lista);

        return lista;
    }

    public void eliminarProducto(int idProducto) throws SQLException {
        this.gp.eliminarProducto(idProducto);
        boolean seguir = true;

        Iterator<Producto> it = this.catalogo.iterator();
        while (it.hasNext() && seguir) {
            Producto producto = it.next();
            if (producto.getIdProducto() == idProducto) {
                this.catalogo.remove(producto);
                this.catalogoMostrar.remove(producto);
                seguir = false;
            }
        }
    }

    public Set<Producto> nuevoProducto(Producto producto) throws SQLException {
        this.gp.nuevoProducto(producto);
        this.catalogo = this.gp.catalogo();
        return this.catalogo;
    }

    public Set<Producto> editarProducto(Producto producto) throws SQLException {
        boolean seguir = true;
        this.gp.modificarProducto(producto);
        Iterator<Producto> it = this.catalogo.iterator();
        while (it.hasNext() && seguir) {
            Producto productoAntiguo = it.next();
            if (productoAntiguo.getIdProducto() == producto.getIdProducto()) {
                this.catalogo.remove(productoAntiguo);
                this.catalogo.add(producto);
                seguir = false;
            }
        }
        return this.catalogo;
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
