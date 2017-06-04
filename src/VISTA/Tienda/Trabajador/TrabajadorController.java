package VISTA.Tienda.Trabajador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DATOS.GestionTrabajadores;
import MODELO.Alertas;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Tienda.Trabajadores.TrabajadoresController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author daw
 */
public class TrabajadorController implements Initializable {

    Trabajadores trabajadorVer;
    GestionTrabajadores gs;
    String tipo;

    @FXML
    private Label idTienda;
    @FXML
    private ImageView foto;
    @FXML
    private Label nombre;
    @FXML
    private AnchorPane fondo1;
    @FXML
    private AnchorPane fondito;
    @FXML
    private Label textnombre;
    @FXML
    private Label textPuesto;
    @FXML
    private Label textTienda;
    @FXML
    private Label textEstado;
    @FXML
    private Circle estado;
    @FXML
    private Label textHorario;
    @FXML
    private Label textContraseña;
    @FXML
    private Label textID;
    @FXML
    private Label puesto;
    @FXML
    private Label contraseña;
    @FXML
    private Label IDTrabajador;
    @FXML
    private Label horario;
    @FXML
    private Button botonModificar;
    @FXML
    private Button botonCancelar;
    @FXML
    private TextField textfieldNombre;
    @FXML
    private TextField textfieldContraseña;
    @FXML
    private TextField textFieldidTienda;
    @FXML
    private ComboBox<String> comboboxPuesto;
    @FXML
    private ComboBox<String> comboboxHorario;
    @FXML
    private Label apellido1;
    @FXML
    private Label apellido2;
    @FXML
    private TextField textfieldApellido1;
    @FXML
    private TextField textfieldApellido2;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        gs = new GestionTrabajadores();
    }

    private void modoJefe() {
        edicion("Jefe");
    }

    private void edicion(String puestoUsuario) {
        this.nombre.textProperty().bindBidirectional(this.textfieldNombre.textProperty());
        this.nombre.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                desclickarOtro();
                textfieldNombre.setVisible(true);
                nombre.setVisible(false);
            }
        });
        this.apellido1.textProperty().bindBidirectional(this.textfieldApellido1.textProperty());
        this.apellido1.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                desclickarOtro();
                textfieldApellido1.setVisible(true);
                apellido1.setVisible(false);
            }
        });
        this.apellido2.textProperty().bindBidirectional(this.textfieldApellido2.textProperty());
        this.apellido2.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                desclickarOtro();
                textfieldApellido2.setVisible(true);
                apellido2.setVisible(false);
            }
        });

        if (puestoUsuario.equalsIgnoreCase("Jefe")) {
            this.comboboxPuesto.setItems(FXCollections.observableArrayList("Encargado", "Empleado"));
            this.puesto.textProperty().bindBidirectional(this.comboboxPuesto.valueProperty());
            this.puesto.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    desclickarOtro();
                    comboboxPuesto.setVisible(true);
                    puesto.setVisible(false);
                }
            });
        }

        this.contraseña.textProperty().bindBidirectional(this.textfieldContraseña.textProperty());
        this.contraseña.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                desclickarOtro();
                textfieldContraseña.setVisible(true);
                contraseña.setVisible(false);
            }
        });
        if (!puestoUsuario.equalsIgnoreCase("Empleado")) {
            this.comboboxHorario.setItems(FXCollections.observableArrayList("Mañana", "Tarde"));
            this.horario.textProperty().bindBidirectional(this.comboboxHorario.valueProperty());
            this.horario.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    desclickarOtro();
                    comboboxHorario.setVisible(true);
                    horario.setVisible(false);
                }
            });
        }
        if (puestoUsuario.equalsIgnoreCase("Jefe")) {
            this.idTienda.textProperty().bindBidirectional(this.textFieldidTienda.textProperty());
            this.idTienda.setOnMousePressed(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    desclickarOtro();
                    textFieldidTienda.setVisible(true);
                    idTienda.setVisible(false);
                }
            });
        }

    }

    private void desclickarOtro() {
        this.nombre.setVisible(true);
        this.textfieldNombre.setVisible(false);
        this.puesto.setVisible(true);
        this.comboboxPuesto.setVisible(false);
        this.contraseña.setVisible(true);
        this.textfieldContraseña.setVisible(false);
        this.horario.setVisible(true);
        this.comboboxHorario.setVisible(false);
        this.idTienda.setVisible(true);
        this.textFieldidTienda.setVisible(false);
    }

    private void modoEncargado(int idTrabajador) {
        if (this.trabajadorVer instanceof Encargado && this.trabajadorVer.getId() != idTrabajador) {
            this.contraseña.setText("******");
            this.botonCancelar.setVisible(false);
            this.botonModificar.setVisible(false);
        } else {
            edicion("Encargado");
        }
    }

    /* id del trabajador que ha abierto la aplicacion */
    private void modoEmpleado(int idTrabajador) {
        if (this.trabajadorVer.getId() != idTrabajador) {
            this.contraseña.setText("******");
            this.botonModificar.setVisible(false);
            this.botonCancelar.setVisible(false);
        } else {
            edicion("Empleado");
        }

    }

    /* id del trabajador que vamos a ver */
    public void setIDTrabajador(int idTrabajadorVer, int idTrabajadorSoy, String tipo) throws SQLException {
        this.trabajadorVer = gs.trabajadorCompleto(idTrabajadorVer);
        this.tipo = tipo;
        if (tipo.equalsIgnoreCase("Jefe")) {
            modoJefe();
        } else if (tipo.equalsIgnoreCase("Encargado")) {
            modoEncargado(idTrabajadorSoy);
        } else {
            modoEmpleado(idTrabajadorSoy);
        }

        String puesto, idTienda;
        if (this.trabajadorVer instanceof Empleado) {
            Empleado empleado = (Empleado) this.trabajadorVer;
            puesto = "Empleado";
            idTienda = String.valueOf(empleado.getIdTienda());
        } else {
            Encargado encargado = (Encargado) this.trabajadorVer;
            puesto = "Encargado";
            idTienda = String.valueOf(encargado.getIdTienda());
        }
        this.nombre.setText(trabajadorVer.getNombre());
        this.apellido1.setText(trabajadorVer.getApellido1());
        this.apellido2.setText(apellido2(trabajadorVer.getApellido2()));
        this.IDTrabajador.setText(String.valueOf(this.trabajadorVer.getId()));
        this.puesto.setText(puesto);
        this.contraseña.setText(this.trabajadorVer.getContraseña());
        if (this.trabajadorVer.getEstado().equalsIgnoreCase("Conectado")) {
            this.estado.setFill(Color.GREEN);
        }
        this.horario.setText(this.trabajadorVer.getHorario());
        this.idTienda.setText(idTienda);
    }

    private String apellido2(String apellido2) {
        String devolver = "";
        if (apellido2 != null) {
            devolver = apellido2;
        }
        return devolver;
    }

    @FXML
    private void modificar(ActionEvent event) {
        if(verificar()){
            if(this.puesto.getText().equalsIgnoreCase("Encargado")){
                Trabajador trabajador = 
            }
        }
    }

    @FXML
    private void cancelar(ActionEvent event) {
    }

    
    private boolean verificar(){
        boolean devolver = true;
        if(this.nombre.getText().isEmpty()){
            devolver = false;
            this.nombre.setVisible(false);
            this.textfieldNombre.setVisible(true);
            this.textfieldNombre.setStyle("-fx-border-color:red");
        } else {
            this.textfieldNombre.setStyle("-fx-border-color:none");
        }
        
        if(this.apellido1.getText().isEmpty()){
            devolver = false;
            this.apellido1.setVisible(false);
            this.textfieldApellido1.setVisible(true);
            this.textfieldApellido1.setStyle("-fx-border-color:red");
        } else {
            this.textfieldApellido1.setStyle("-fx-border-color:none");
        }
        
        if(this.puesto.getText().isEmpty()){
            devolver = false;
            this.puesto.setVisible(false);
            this.comboboxPuesto.setVisible(true);
            this.comboboxPuesto.setStyle("-fx-border-color:red");
        } else {
            this.comboboxPuesto.setStyle("-fx-border-color:none");
        }
        
        if(this.contraseña.getText().isEmpty()){
            devolver = false;
            this.contraseña.setVisible(false);
            this.textfieldContraseña.setVisible(true);
            this.textfieldContraseña.setStyle("-fx-border-color:red");
        } else {
            this.textfieldContraseña.setStyle("-fx-border-color:none");
        }
        
        if(this.horario.getText().isEmpty()){
            devolver = false;
            this.horario.setVisible(false);
            this.comboboxHorario.setVisible(true);
            this.comboboxHorario.setStyle("-fx-border-color:red");
        } else {
            this.comboboxHorario.setStyle("-fx-border-color:none");
        }
        
        if(this.idTienda.getText().isEmpty()){
            devolver = false;
            this.idTienda.setVisible(false);
            this.textFieldidTienda.setVisible(true);
            this.textFieldidTienda.setStyle("-fx-border-color:red");
        } else {
            this.textFieldidTienda.setStyle("-fx-border-color:none");
        }       
        
        return devolver;
    }
    
    
    
    
    
}
