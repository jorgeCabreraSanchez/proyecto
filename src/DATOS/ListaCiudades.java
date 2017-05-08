/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DATOS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;

/**
 *
 * @author Jorge Cabrera
 */
public class ListaCiudades {

    private List<String> ciudades = new ArrayList<>();

    public ListaCiudades() {
        cargarCiudades();
    }

    public void cargarCiudades() {
        try (Connection connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root")) {
            String sentencia = "Select * from ciudades";
            PreparedStatement ps = connect.prepareStatement(sentencia);

            ResultSet rs = ps.executeQuery();
            ciudades.clear();
            while (rs.next()) {
                ciudades.add(rs.getString(1));
            }
        } catch (SQLException ex) {
            MODELO.Alertas.generarAlerta("Error BD", "Ha habido un error intentando traer las ciudades", ex.getMessage(), Alert.AlertType.ERROR);
        }
    }
    
    public List<String> getCiudades(){
        return ciudades;
    }

}
