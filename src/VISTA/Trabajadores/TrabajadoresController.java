/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Trabajadores;

import MODELO.Alertas;
import MODELO.Listas.ListaTrabajadores;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Empleado.Inicio.InicioEmpleadosController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import static javafx.scene.input.KeyCode.T;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class TrabajadoresController implements Initializable {

    private int idTienda;
    private ListaTrabajadores lt;
    private ObservableList listaTrabajadores = FXCollections.observableArrayList();

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
    private TableColumn<Trabajadores, Integer> columnaidTienda;
    @FXML
    private TextField textfieldNombre;
    @FXML
    private TextField textfieldApellido1;
    @FXML
    private ContextMenu menuNombre;
    @FXML
    private ContextMenu menuApellido1;
    @FXML
    private AnchorPane fondo;
    @FXML
    private AnchorPane fondito;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    public void mostrarTrabajadores() throws SQLException {
        tabla();
        actualizarTrabajador("largo");
        nombreModificado();
        apellido1Modificado();
    }

    private void tabla() throws SQLException {
        lt = new ListaTrabajadores(this.idTienda);
        this.tabla.setEditable(true);
        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.tabla.setItems(listaTrabajadores);
        tabla.setPlaceholder(new Label("No se ha encontrado ningún trabajador."));
        this.tabla.sort();

//        columnaID.setCellFactory(TextFieldTableCellT<arbajadores>forTableColumn());
//        columnaID.setOnEditCommit(
//                (CellEditEvent<Trabajadores, Integer> t) -> {
//                    ((Trabajadores) t.getTableView().getItems().get(
//                            t.getTablePosition().getRow())).setEmail(t.getNewValue());
//                });
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaNombre.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
        columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
            @Override
            public void handle(CellEditEvent<Trabajadores, String> t) {
                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()));
                if (!t.getNewValue().isEmpty()) {
                    try {
                        lt.editarTrabajador(trabajador.getId(), "Nombre", t.getNewValue());
                        trabajador.setNombre(t.getNewValue());
                        actualizarTrabajador("largo");
                    } catch (SQLException ex) {
                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    Alertas.generarAlerta("BD", "El nombre no puede estar vacío", Alert.AlertType.INFORMATION);
                    trabajador.setNombre(t.getOldValue());
                    actualizarTrabajador("largo");
                }

            }

        });

        columnaApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
        columnaApellido1.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
        columnaApellido1.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
            @Override
            public void handle(CellEditEvent<Trabajadores, String> t) {
                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()));
                if (!t.getNewValue().isEmpty()) {
                    try {
                        lt.editarTrabajador(trabajador.getId(), "Apellido1", t.getNewValue());
                        trabajador.setApellido1(t.getNewValue());
                        actualizarTrabajador("largo");
                    } catch (SQLException ex) {
                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    Alertas.generarAlerta("BD", "El Apellido1 no puede estar vacío", Alert.AlertType.INFORMATION);
                    trabajador.setApellido1(t.getOldValue());
                    actualizarTrabajador("largo");
                }

            }

        });

        columnaApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
        columnaApellido2.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
        columnaApellido2.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
            @Override
            public void handle(CellEditEvent<Trabajadores, String> t) {
                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()));
                if (!t.getNewValue().isEmpty()) {
                    try {
                        lt.editarTrabajador(trabajador.getId(), "Apellido2", t.getNewValue());
                        trabajador.setApellido2(t.getNewValue());
                        actualizarTrabajador("largo");
                    } catch (SQLException ex) {
                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    Alertas.generarAlerta("BD", "El Apellido2 no puede estar vacío", Alert.AlertType.INFORMATION);
                    trabajador.setApellido2(t.getOldValue());
                    actualizarTrabajador("largo");
                }

            }

        });

        columnaPuesto.setCellValueFactory(
                new Callback<CellDataFeatures<Trabajadores, String>, ObservableValue<String>>() {
            public ObservableValue<String> call(CellDataFeatures<Trabajadores, String> t) {
                Trabajadores trabajador = t.getValue();
                StringProperty puesto;
                if (trabajador instanceof Empleado) {
                    puesto = new SimpleStringProperty("Empleado");

                } else {
                    puesto = new SimpleStringProperty("Encargado");
                }
                return puesto;
            }
        });
        columnaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
        columnaHorario.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
        columnaHorario.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
            @Override
            public void handle(CellEditEvent<Trabajadores, String> t) {
                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()));
                if (!t.getNewValue().isEmpty()) {
                    try {
                        lt.editarTrabajador(trabajador.getId(), "Horario", t.getNewValue());
                        trabajador.setHorario(t.getNewValue());
                        actualizarTrabajador("largo");
                    } catch (SQLException ex) {
                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    Alertas.generarAlerta("BD", "El Horario no puede estar vacío", Alert.AlertType.INFORMATION);
                    trabajador.setHorario(t.getOldValue());
                    actualizarTrabajador("largo");
                }

            }

        });

        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columnaEstado.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
        columnaEstado.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
            @Override
            public void handle(CellEditEvent<Trabajadores, String> t) {
                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
                        t.getTablePosition().getRow()));
                if (!t.getNewValue().isEmpty()) {
                    try {
                        lt.editarTrabajador(trabajador.getId(), "Estado", t.getNewValue());
                        trabajador.setEstado(t.getNewValue());
                        actualizarTrabajador("largo");
                    } catch (SQLException ex) {
                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
                    }
                } else {
                    Alertas.generarAlerta("BD", "El Estado no puede estar vacío", Alert.AlertType.INFORMATION);
                    trabajador.setEstado(t.getOldValue());
                    actualizarTrabajador("largo");
                }

            }

        });

        columnaIncidencias.setCellValueFactory(new PropertyValueFactory<>("incidencias"));

        columnaidTienda.setCellValueFactory(new PropertyValueFactory<>("idTienda"));
//        columnaidTienda.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());        
//        columnaidTienda.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, Integer>>() {
//            @Override
//            public void handle(CellEditEvent<Trabajadores, String> t) {
//                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                if (!t.getNewValue().isEmpty()) {
//                    try {
//                        lt.editarTrabajador(trabajador.getId(), "idTienda", t.getNewValue());
//                        if (trabajador instanceof Empleado) {
//                            Empleado empleado = (Empleado) trabajador;
//                            empleado.setIdTienda(Integer.parseInt(t.getNewValue()));
//                        } else {
//                            Encargado encargado = (Encargado) trabajador;
//                            encargado.setIdTienda(Integer.parseInt(t.getNewValue()));
//                        }
//
//                        actualizarTrabajador("largo");
//                    } catch (SQLException ex) {
//                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//                    }
//                } else {
//                    Alertas.generarAlerta("BD", "El idTienda no puede estar vacío", Alert.AlertType.INFORMATION);
//                    if (trabajador instanceof Empleado) {
//                            Empleado empleado = (Empleado) trabajador;
//                            empleado.setIdTienda(Integer.parseInt(t.getOldValue()));
//                        } else {
//                            Encargado encargado = (Encargado) trabajador;
//                            encargado.setIdTienda(Integer.parseInt(t.getOldValue()));
//                        }
//                    actualizarTrabajador("largo");
//                }
//
//            }
//
//        }); 
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
        this.listaTrabajadores.clear();
        this.listaTrabajadores.addAll(lista);

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
        desclickarContextMenu();
    }

    private void desclickarContextMenu() {
        if (this.menuNombre.isShowing()) {
            this.menuNombre.hide();
        } else if (this.menuApellido1.isShowing()) {
            this.menuApellido1.hide();
        }
    }

    @FXML
    private void accionVer(ActionEvent event) {
        desclickarContextMenu();
        try {
            Trabajadores trabajador = this.tabla.getSelectionModel().getSelectedItem();
            if (trabajador != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Empleado/Inicio/InicioEmpleados.fxml"));
                Parent root = loader.load();
                InicioEmpleadosController controller = loader.getController();
                controller.setTrabajador(trabajador, this.idTienda);
                controller.modoJefe();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Stage stageActual = (Stage) this.textfieldNombre.getScene().getWindow();
                stageActual.close();
            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajador", "No se ha podido visualizar el trabajador", Alert.AlertType.ERROR);
        }
    }

    private void accionEditar(ActionEvent event) {
        desclickarContextMenu();
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        desclickarContextMenu();
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        desclickarContextMenu();
        Trabajadores trabajador = this.tabla.getSelectionModel().getSelectedItem();

        if (trabajador != null) {
            Optional<ButtonType> boton = Alertas.generarAlerta("Borrado de trabajador", "Desea borrar el trabajador con la siguiente información: ", trabajador.toString(), Alert.AlertType.CONFIRMATION);
            if (boton.get().getText().equalsIgnoreCase("Aceptar")) {
                try {
                    lt.eliminarTrabajador(trabajador.getId());
                    actualizarTrabajador("largo");
                } catch (SQLException ex) {
                    Alertas.generarAlerta("BD", "No se ha podido borrar el trabajador", Alert.AlertType.ERROR);
                }
            }
        }

    }

}
