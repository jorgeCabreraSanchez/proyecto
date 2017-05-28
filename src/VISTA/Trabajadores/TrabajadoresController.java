/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Trabajadores;

import MODELO.Listas.ListaTrabajadores;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Trabajadores;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class TrabajadoresController implements Initializable {

    private int idTienda;
    private ListaTrabajadores lt;
    private ObservableList listaTrabajadores;

    @FXML
    private TableView<Trabajadores> tabla;
    @FXML
    private TableColumn<Trabajadores, Integer> columnaID;
    @FXML
    private TableColumn<Trabajadores, String> columnaNombre;
    @FXML
    private TableColumn<Trabajadores, String> columnaApellido1;
    @FXML
    private TableColumn<Trabajadores, String> columnaApellido2;
    @FXML
    private TableColumn<Trabajadores, String> columnaPuesto;
    @FXML
    private TableColumn<Trabajadores, String> columnaHorario;
    @FXML
    private TableColumn<Trabajadores, String> columnaEstado;
    @FXML
    private TableColumn<Trabajadores, Integer> columnaIncidencias;
    @FXML
    private TextField textfieldNombre;
    @FXML
    private TextField textfieldApellido1;
    @FXML
    private ContextMenu menuNombre;
    @FXML
    private ContextMenu menuApellido1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void mostrarTrabajadores() throws SQLException {
        tabla();
        actualizarTrabajador("corto");
        nombreModificado();
        apellido1Modificado();
    }

    private void tabla() throws SQLException {
        lt = new ListaTrabajadores(this.idTienda);

        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        columnaApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        columnaPuesto.setCellValueFactory(
                new Callback<CellDataFeatures<Trabajadores, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Trabajadores, String> t) {
                Trabajadores tra = t.getValue();
                StringProperty puesto;
                if (tra instanceof Empleado) {
                    puesto = new SimpleStringProperty("Empleado");

                } else {
                    puesto = new SimpleStringProperty("Encargado");
                }
                return puesto;
            }
        });
        columnaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columnaIncidencias.setCellValueFactory(new PropertyValueFactory<>("incidencias"));

        tabla.setTableMenuButtonVisible(true);
        tabla.setPlaceholder(new Label("No se ha encontrado ningún trabajador."));
    }

    private void nombreModificado() {
        this.menuNombre.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                textfieldNombre.setText(mn.getUserData().toString());
            }
        });

        textfieldNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                actualizarTrabajador("corto");
                menuNombre.hide();
            } else if (!oldValue.equalsIgnoreCase(newValue) && oldValue.length() > newValue.length()) {
                actualizarTrabajador("corto");
                menuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
            } else {
                actualizarTrabajador("largo");
                menuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
            }
        });
    }

    private void apellido1Modificado() {
        this.menuApellido1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                MenuItem mn = (MenuItem) e.getTarget();
                textfieldApellido1.setText(mn.getUserData().toString());
            }
        });

        textfieldApellido1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty()) {
                actualizarTrabajador("corto");
                menuApellido1.hide();
            } else if (!oldValue.equalsIgnoreCase(newValue) && oldValue.length() > newValue.length()) {
                actualizarTrabajador("corto");
                menuApellido1.show(textfieldApellido1, Side.BOTTOM, 0, 0);
            } else {
                actualizarTrabajador("largo");
                menuApellido1.show(textfieldApellido1, Side.BOTTOM, 0, 0);
            }
        });
    }

    private void actualizarTrabajador(String tamaño) {
        String nom = this.textfieldNombre.getText();
        String ape = this.textfieldApellido1.getText();
        Set<Trabajadores> lista = lt.getTrabajadores(nom, ape, tamaño);
        this.listaTrabajadores = FXCollections.observableArrayList(lista);
        this.tabla.getItems().clear();
        this.tabla.getItems().addAll(listaTrabajadores);

        ObservableList<MenuItem> listaNombre = FXCollections.observableArrayList();
        ObservableList<MenuItem> listaApellido1 = FXCollections.observableArrayList();
        for (Trabajadores trabajador : lista) {
            String nombre = trabajador.getNombre();
            MenuItem e = new MenuItem(nombre);
            e.setUserData(nombre);
            listaNombre.add(e);

            String apellido1 = trabajador.getApellido1();
            MenuItem e2 = new MenuItem(apellido1);
            e2.setUserData(apellido1);
            listaApellido1.add(e);

        }
        this.menuNombre.getItems().clear();
        this.menuNombre.getItems().setAll(listaNombre);
        this.menuApellido1.getItems().clear();
        this.menuApellido1.getItems().setAll(listaApellido1);
    }

//    private void rellenarConTodo() {
//        Set<Trabajadores> trabajadores = lt.getTrabajadores();
//        this.listaTrabajadores = FXCollections.observableArrayList(trabajadores);
//        this.tabla.getItems().clear();
//        this.tabla.getItems().addAll(listaTrabajadores);
//
//        ObservableList<MenuItem> listaNombre = FXCollections.observableArrayList();
//        System.out.println("Antes");
//        System.out.println(menuNombre.getItems());
//        System.out.println("Antes");
//        System.out.println(menuApellido1.getItems());
//        ObservableList<MenuItem> listaApellido1 = FXCollections.observableArrayList();
//
//        for (Trabajadores trabajador : trabajadores) {
//            String nombre = trabajador.getNombre();
//            MenuItem e = new MenuItem(nombre);
//            e.setUserData(nombre);
//            listaNombre.add(e);
//
//            String apellido1 = trabajador.getApellido1();
//            System.out.println(apellido1);
//            MenuItem e2 = new MenuItem(apellido1);
//            e2.setUserData(apellido1);
//            listaApellido1.add(e);
//        }
//
//        this.menuApellido1.getItems().setAll(listaApellido1);
//        this.menuNombre.getItems().setAll(listaNombre);
//        System.out.println();
//        System.out.println("despues");
//        System.out.println(menuNombre.getItems());
//        System.out.println("despues");
//        System.out.println(menuApellido1.getItems());
////      Puede que aqui haga falta hacer un clear de las listas al volver de otra ventana 
//    }
    public void setIDTienda(int idTienda) {
        this.idTienda = idTienda;
    }

    public int getIDTienda() {
        return idTienda;
    }

    @FXML
    private void nombreClickado(MouseEvent event) {
        if (this.menuNombre.isShowing()) {
            this.menuNombre.hide();
        } else {
            this.menuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
        }
        if (this.menuApellido1.isShowing()) {
            this.menuApellido1.hide();
        }
    }

    @FXML
    private void apellido1Clickado(MouseEvent event) {
        if (this.menuApellido1.isShowing()) {
            this.menuApellido1.hide();
        } else {
            this.menuApellido1.show(textfieldApellido1, Side.BOTTOM, 0, 0);
        }
        if (this.menuNombre.isShowing()) {
            this.menuNombre.hide();
        }
    }

    @FXML
    private void desclickar(MouseEvent event) {
        if (this.menuNombre.isShowing()) {
            this.menuNombre.hide();
        } else if (this.menuApellido1.isShowing()) {
            this.menuApellido1.hide();
        }
    }

}
