/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jefe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;


public class JefeController implements Initializable {

    @FXML
    private AnchorPane fondo;
    @FXML
    private TextField ciudad;
    @FXML
    private ContextMenu menuCiudad;
    @FXML
    private TextField dirección;
    @FXML
    private MenuItem menuCiudadItems;


    
    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        this.menuCiudad.getItems().add(new MenuItem("Valencia"));
        this.menuCiudad.getItems().add(new MenuItem("Madrid"));
        this.ciudad.setContextMenu(menuCiudad);
       this.menuCiudad.show(ciudad.getClip(), 85, 40);
                
        this.ciudad.textProperty().addListener((observable,oldValue,newValue) -> {
            if(!oldValue.equalsIgnoreCase(newValue) && !newValue.isEmpty()){
                
            }
            
            
        });
        
        
 
    }    

    @FXML
    private void elegirCiudad(ActionEvent event) {
         System.out.println();
    }

    @FXML
    private void elegirDireccion(ActionEvent event) {
    }

    @FXML
    private void elegirCiudadItem(ActionEvent event) {
    }

    @FXML
    private void elegirCiudadMenu(ActionEvent event) {
        this.ciudad.setText(this.menuCiudad.getUserData().toString());
    }


    
}
