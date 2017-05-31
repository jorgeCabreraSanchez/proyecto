/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import MODELO.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Jorge Cabrera
 */
public class GestionProductos {

    private Connection connect;

    public GestionProductos() {
        connect = Login.getConnect();
    }

    public Set<Producto> productosEnLaTienda(int idTienda) throws SQLException {
        Set<Producto> productos = new HashSet<>();

        String sentencia = "Select * from producto where idProducto in (Select Productos_idProductos from catalogo where Tiendas_idTienda = ?);";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            productos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }

        return productos;
    }    
    
    public void nuevoProductoEnTienda(int idTienda,int idProducto) throws SQLException{
        String sentencia = "insert into catalogo values(?,?);";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ps.setInt(2,idProducto);
        ps.executeUpdate();
    }
    
    public void eliminarProductoEnTienda(int idTienda,int idProducto) throws SQLException{
        String sentencia = "Delete from catalogo where tiendas_idTienda = ? and productos_idProductos = ?;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ps.setInt(1, idTienda);
        ps.setInt(2,idProducto);
        ps.executeUpdate();
    }

    public Set<Producto> todosLosProductos() throws SQLException {
        Set<Producto> productos = new HashSet<>();

        String sentencia = "Select * from producto;";
        PreparedStatement ps = connect.prepareStatement(sentencia);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            productos.add(new Producto(rs.getInt(1), rs.getString(2), rs.getString(3)));
        }

        return productos;
    }
    
    public void nuevoProducto(Producto producto){
        
    }
    
    public void modificarProducto(int idProducto,Producto producto){
        
    }
    
    public void eliminarProducto(int idProducto){
        
    }
            
            
}

