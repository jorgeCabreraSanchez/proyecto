package VISTA.Empleado.Inicio;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import MODELO.Trabajadores.Empleado;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author daw
 */
public class InicioEmpleadosController implements Initializable {

    @FXML
    private Pane foto;
    @FXML
    private Label nombre;
    @FXML
    private Label tipoEmpleado;
    @FXML
    private Button botonIncidencia;
    @FXML
    private Button botonCalendario;
    @FXML
    private Label tienda;

    /**
     * Initializes the controller class.
     */
    Connection  conn;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proyecto", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(InicioEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        String sentencia = "select nombre, apellido1, apellido2 from trabajadores where IDTrabajador = ?";
        try {
            PreparedStatement ps = conn.prepareStatement(sentencia);
            /*Empleado emple = (Empleado) ListaTrabajadores.getTrabajadores().get(0);
            ps.setInt(1,emple.getId()); 
            ResultSet rs = ps.executeQuery(); 
            
            rs.next();
            nombre.setText(sentencia);rs.getString(1);*/
            
        } catch (SQLException ex) {
            Logger.getLogger(InicioEmpleadosController.class.getName()).log(Level.SEVERE, null, ex);
            
            
        }
    }    
    
}
