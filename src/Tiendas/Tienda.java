/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tiendas;

/**
 *
 * @author daw
 */
public class Tienda {
    private int idTienda;
    private String direccion;
    private String ciudad;

    public Tienda(){
        
    }
    
    public Tienda(int idTienda, String direccion, String ciudad) {
        this.idTienda = idTienda;
        this.direccion = direccion;
        this.ciudad = ciudad;
    }

    public int getIdTienda() {
        return idTienda;
    }

    public void setIdTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    
    
    
    
    
}
