/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MODELO.Listas;

import DATOS.GestionProductos;
import MODELO.Alertas;
import MODELO.Producto;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;
import javafx.scene.control.Alert;

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

    public void generarTXT(int idTienda) throws IOException {
        generarTXT(Paths.get(new File("").getAbsolutePath() + "\\productos"), idTienda);
    }

    public void generarTXT(Path directorio, int idTienda) throws IOException {
        if (directorio != null) {

            DateTimeFormatter formato = DateTimeFormatter.ofPattern("'Productos Tienda Nº'" + idTienda + "  'fecha' dd-mm-yyyy 'hora' HH-mm-ss");
            LocalDateTime nombre = LocalDateTime.now();

            Path archivo = Paths.get(directorio + "\\" + formato.format(nombre) + ".txt");
            BufferedWriter bw = Files.newBufferedWriter(archivo, StandardOpenOption.CREATE);

            for (Producto producto : this.productos) {
                bw.append(String.format("%-4d %-16s %-2s", producto.getIdProducto(), producto.getNombre(), producto.getDescripcion()));
                bw.newLine();
            }

            Alert alerta = new Alert(Alert.AlertType.INFORMATION);
            alerta.setTitle("Productos");
            alerta.setHeaderText("La lista de productos se ha impreso correctamente");
            alerta.setContentText("Ubicación del archivo: " + archivo.toAbsolutePath().toString());
            alerta.showAndWait();

            bw.close();

        }
    }

    public void nuevoProductoEnTienda(int idTienda, Producto producto) throws SQLException {
        gp.nuevoProductoEnTienda(idTienda, producto.getIdProducto());
        this.productos.add(producto);
    }

    public void eliminarProductoEnTienda(int idTienda, Producto producto) throws SQLException {
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
