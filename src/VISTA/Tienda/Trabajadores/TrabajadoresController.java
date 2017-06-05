/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VISTA.Tienda.Trabajadores;

import MODELO.Alertas;
import MODELO.Listas.ListaTrabajadores;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Jefe;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Perfil.PerfilController;
import VISTA.Tienda.Which.TiendaWhichController;
import VISTA.Tienda.Trabajador.TrabajadorController;
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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Jorge Cabrera
 */
public class TrabajadoresController implements Initializable {

    private int idTienda;
    private ListaTrabajadores lt;
    private ObservableList listaTrabajadores = FXCollections.observableArrayList();
    private Trabajadores trabajador;

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
    private TableColumn<Trabajadores, Integer> columnaIncidencias;
    @FXML
    private TextField textfieldNombre;
    @FXML
    private AnchorPane fondo;
    @FXML
    private AnchorPane fondito;
    @FXML
    private Button buttonVolver;
    @FXML
    private ContextMenu contextmenuNombre;
    @FXML
    private ContextMenu contextmenuApellido1;
    @FXML
    private TextField textfieldApellido1;
    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellido1;
    @FXML
    private TextField Apellido2;
    @FXML
    private ComboBox<String> Puesto;
    @FXML
    private ComboBox<String> Horario;
    @FXML
    private MenuButton menu;
    @FXML
    private Label labelTienda;
    @FXML
    private Button botonNuevo;
    @FXML
    private Button botonEliminar;
    @FXML
    private AnchorPane fondoTotal;
    @FXML
    private Button botonVer;

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

        this.Puesto.setItems(FXCollections.observableArrayList("Encargado", "Empleado"));
        this.Horario.setItems(FXCollections.observableArrayList("Mañana", "Tarde"));

        this.tabla.setItems(listaTrabajadores);
        tabla.setPlaceholder(new Label("No se ha encontrado ningún trabajador."));

        columnaID.setCellValueFactory(new PropertyValueFactory<>("id"));

        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
//        columnaNombre.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
//        columnaNombre.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
//            @Override
//            public void handle(CellEditEvent<Trabajadores, String> t) {
//                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                if (!t.getNewValue().isEmpty()) {
//                    try {
//                        lt.editarTrabajador(trabajador.getId(), "Nombre", t.getNewValue());
//                        trabajador.setNombre(t.getNewValue());
//                    } catch (SQLException ex) {
//                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//                    }
//                } else {
//                    Alertas.generarAlerta("BD", "El nombre no puede estar vacío", Alert.AlertType.INFORMATION);
//                    trabajador.setNombre(t.getOldValue());
//
//                }
//                actualizarTrabajador("largo");
//            }
//
//        });

        columnaApellido1.setCellValueFactory(new PropertyValueFactory<>("apellido1"));
//        columnaApellido1.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
//        columnaApellido1.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
//            @Override
//            public void handle(CellEditEvent<Trabajadores, String> t) {
//                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                if (!t.getNewValue().isEmpty()) {
//                    try {
//                        lt.editarTrabajador(trabajador.getId(), "Apellido1", t.getNewValue());
//                        trabajador.setApellido1(t.getNewValue());
//                    } catch (SQLException ex) {
//                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//                    }
//                } else {
//                    Alertas.generarAlerta("BD", "El Apellido1 no puede estar vacío", Alert.AlertType.INFORMATION);
//                    trabajador.setApellido1(t.getOldValue());
//                }
//                actualizarTrabajador("largo");
//
//            }
//
//        });

        columnaApellido2.setCellValueFactory(new PropertyValueFactory<>("apellido2"));
//        columnaApellido2.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
//        columnaApellido2.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
//            @Override
//            public void handle(CellEditEvent<Trabajadores, String> t) {
//                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                if (!t.getNewValue().isEmpty()) {
//                    try {
//                        lt.editarTrabajador(trabajador.getId(), "Apellido2", t.getNewValue());
//                        trabajador.setApellido2(t.getNewValue());
//                    } catch (SQLException ex) {
//                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//                    }
//                } else {
//                    Alertas.generarAlerta("BD", "El Apellido2 no puede estar vacío", Alert.AlertType.INFORMATION);
//                    trabajador.setApellido2(t.getOldValue());
//                }
//                actualizarTrabajador("largo");
//            }
//        });

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
//        ObservableList<String> puestos = FXCollections.observableArrayList("Empleado", "Encargado");
//        columnaPuesto.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
//        columnaPuesto.setOnEditCommit((event) -> {
//            Trabajadores trabajador = ((Trabajadores) event.getTableView().getItems().get(
//                    event.getTablePosition().getRow()));
//            System.out.println("Hola");
//            try {
//                lt.editarTrabajador(trabajador.getId(), "Tipo", event.getNewValue());
//
//                actualizarTrabajador("largo");
//            } catch (SQLException ex) {
//                Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//            }
//        });

        columnaHorario.setCellValueFactory(new PropertyValueFactory<>("horario"));
//        columnaHorario.setCellFactory(TextFieldTableCell.<Trabajadores>forTableColumn());
//        columnaHorario.setOnEditCommit(new EventHandler<CellEditEvent<Trabajadores, String>>() {
//            @Override
//            public void handle(CellEditEvent<Trabajadores, String> t) {
//                Trabajadores trabajador = ((Trabajadores) t.getTableView().getItems().get(
//                        t.getTablePosition().getRow()));
//                if (!t.getNewValue().isEmpty()) {
//                    try {
//                        lt.editarTrabajador(trabajador.getId(), "Horario", t.getNewValue());
//                        trabajador.setHorario(t.getNewValue());
//                    } catch (SQLException ex) {
//                        Alertas.generarAlerta("BD", "Ha habido un error intentando editar el trabajador y no se ha podido", String.valueOf(ex.getErrorCode()) + "  " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
//                    }
//                } else {
//                    Alertas.generarAlerta("BD", "El Horario no puede estar vacío", Alert.AlertType.INFORMATION);
//                    trabajador.setHorario(t.getOldValue());
//                }
//                actualizarTrabajador("largo");
//            }
//
//        });

        columnaIncidencias.setCellValueFactory(new PropertyValueFactory<>("incidencias"));

    }

    private void nombreModificado() {
        this.contextmenuNombre.setOnAction((ActionEvent e) -> {
            MenuItem mn = (MenuItem) e.getTarget();
            textfieldNombre.setText(mn.getUserData().toString());
        });

        textfieldNombre.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || oldValue.length() > newValue.length()) {
                actualizarTrabajador("corto");
            } else {
                actualizarTrabajador("largo");
            }
            contextmenuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);

        });
    }

    private void apellido1Modificado() {
        this.contextmenuApellido1.setOnAction((ActionEvent e) -> {
            MenuItem mn = (MenuItem) e.getTarget();
            textfieldApellido1.setText(mn.getUserData().toString());
        });

        this.textfieldApellido1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.isEmpty() || oldValue.length() > newValue.length()) {
                actualizarTrabajador("corto");
            } else {
                actualizarTrabajador("largo");
            }
            contextmenuApellido1.show(textfieldApellido1, Side.BOTTOM, 0, 0);
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
            listaApellido1.add(e2);

        }
        this.contextmenuNombre.getItems().clear();
        this.contextmenuNombre.getItems().setAll(listaNombre);
        this.contextmenuApellido1.getItems().clear();
        this.contextmenuApellido1.getItems().setAll(listaApellido1);
    }

    public void setTrabajador(Trabajadores trabajador, int idTienda) {
        this.trabajador = trabajador;
        this.idTienda = idTienda;
        this.labelTienda.setText(this.labelTienda.getText() + this.idTienda);
    }

    public void setTrabajador(Trabajadores trabajador) {
        this.trabajador = trabajador;
        if (trabajador instanceof Encargado) {
            Encargado encargado = (Encargado) trabajador;
            this.idTienda = encargado.getIdTienda();
        } else {
            Empleado empleado = (Empleado) trabajador;
            this.idTienda = empleado.getIdTienda();
        }
        this.Nombre.setVisible(false);
        this.Nombre.setManaged(false);
        this.Apellido1.setVisible(false);
        this.Apellido1.setManaged(false);
        this.Apellido2.setVisible(false);
        this.Apellido2.setManaged(false);
        this.Puesto.setVisible(false);
        this.Puesto.setManaged(false);
        this.Horario.setVisible(false);
        this.Horario.setManaged(false);
        this.botonNuevo.setVisible(false);
        this.botonNuevo.setManaged(false);
        this.botonEliminar.setVisible(false);
        this.botonEliminar.setManaged(false);
        this.buttonVolver.setLayoutY(391);
        this.fondoTotal.setMaxHeight(430);
        this.fondo.setMaxHeight(430);
        this.labelTienda.setText(this.labelTienda.getText() + this.idTienda);
    }

    public int getIDTienda() {
        return idTienda;
    }

    @FXML
    private void nombreClickado(MouseEvent event) {
        if (this.contextmenuNombre.isShowing()) {
            this.contextmenuNombre.hide();
        } else {
            this.contextmenuNombre.show(textfieldNombre, Side.BOTTOM, 0, 0);
        }
        if (this.contextmenuApellido1.isShowing()) {
            this.contextmenuApellido1.hide();
        }
    }

    @FXML
    private void apellido1Clickado(MouseEvent event) {
        if (this.contextmenuApellido1.isShowing()) {
            this.contextmenuApellido1.hide();
        } else {
            this.contextmenuApellido1.show(this.textfieldApellido1, Side.BOTTOM, 0, 0);
        }
        if (this.contextmenuNombre.isShowing()) {
            this.contextmenuNombre.hide();
        }
    }

    @FXML
    private void desclickar(MouseEvent event) {
        desclickarContextMenu();
    }

    private void desclickarContextMenu() {
        if (this.contextmenuNombre.isShowing()) {
            this.contextmenuNombre.hide();
        } else if (this.contextmenuApellido1.isShowing()) {
            this.contextmenuApellido1.hide();
        }
    }

    @FXML
    private void accionVer(ActionEvent event) {
        desclickarContextMenu();
        try {
            Trabajadores trabajador = this.tabla.getSelectionModel().getSelectedItem();
            if (trabajador != null) {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Tienda/Trabajador/Trabajador.fxml"));
                Parent root = loader.load();
                TrabajadorController controller = loader.getController();
                String tipo;
                int idTrabajador = 0;                
                if (this.trabajador instanceof Jefe) {
                    tipo = "Jefe";
                } else if (this.trabajador instanceof Encargado) {
                    tipo = "Encargado";
                    idTrabajador = this.trabajador.getId();                    
                } else {
                    tipo = "Empleado";
                    idTrabajador = this.trabajador.getId();
                }
                int incidencias;
                if(trabajador instanceof Empleado){
                    Empleado empleado = (Empleado) trabajador;
                    incidencias = empleado.getIncidencias();
                }  else {
                    Encargado encargado = (Encargado) trabajador;
                    incidencias = encargado.getIncidencias();
                }
                controller.setIDTrabajador(trabajador.getId(), idTrabajador, tipo,incidencias);

                Stage stage = new Stage();
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.setScene(new Scene(root));
                stage.showAndWait();
                try {
                    if (controller.modificado()) {
                        this.listaTrabajadores.clear();
                        this.listaTrabajadores.addAll(this.lt.editarTrabajador(controller.getTrabajadorModificado(),this.idTienda));
                        actualizarTrabajador("corto");
                        Trabajadores trabajadorasd = controller.getTrabajadorModificado();             
                    } else {
                        if(trabajador instanceof Empleado){
                            Empleado empleado = (Empleado) trabajador;
                            empleado.setIncidencias(controller.numeroIncidencias());
                        } else {
                            Encargado encargado = (Encargado) trabajador;
                            encargado.setIncidencias(controller.numeroIncidencias());
                        }
                    }
                } catch (SQLException ex) {
                    Alertas.generarAlerta("BD", "No se ha podido modificar el usuario", Alert.AlertType.ERROR);
                }
            }
        } catch (IOException ex) {
            Alertas.generarAlerta("Trabajador", "No se ha podido visualizar el trabajador", Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se puede visualizar el Trabajador", "Error : " + ex.getErrorCode() + " " + ex.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        desclickarContextMenu();

        if (verificarDatos()) {
            Trabajadores trabajador;
            if (this.Puesto.getValue().equalsIgnoreCase("Empleado")) {
                trabajador = new Empleado(this.idTienda, this.Horario.getValue(),
                        this.Nombre.getText(), this.Apellido1.getText(), this.Apellido2.getText());
            } else {
                trabajador = new Encargado(this.idTienda, this.Horario.getValue(),
                        this.Nombre.getText(), this.Apellido1.getText(), this.Apellido2.getText());
            }
            try {
                lt.nuevoTrabajador(trabajador);
                lt.traerTrabajadores(idTienda);
                actualizarTrabajador("corto");

                this.Nombre.clear();
                this.Apellido1.clear();
                this.Apellido2.clear();
                this.Puesto.setValue(this.Puesto.getPromptText());
                this.Horario.setValue(this.Horario.getPromptText());
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se ha podido inserter el trabajador", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    private boolean verificarDatos() {
        boolean devolver = true;
        if (this.Nombre.getText().isEmpty()) {
            devolver = false;
            this.Nombre.setStyle("-fx-border-color: red;");
        } else {
            this.Nombre.setStyle("-fx-border-color: none;");
        }

        if (this.Apellido1.getText().isEmpty()) {
            devolver = false;
            this.Apellido1.setStyle("-fx-border-color: red;");
        } else {
            this.Apellido1.setStyle("-fx-border-color: none;");
        }

        if (this.Apellido2.getText().isEmpty()) {
            devolver = false;
            this.Apellido2.setStyle("-fx-border-color: red;");
        } else {
            this.Apellido2.setStyle("-fx-border-color: none;");
        }

        if (this.Puesto.getSelectionModel().getSelectedItem() == null) {
            devolver = false;
            this.Puesto.setStyle("-fx-border-color: red;");
        } else {
            this.Puesto.setStyle("-fx-border-color: none;");
        }

        if (this.Horario.getSelectionModel().getSelectedItem() == null) {
            devolver = false;
            this.Horario.setStyle("-fx-border-color: red;");
        } else {
            this.Horario.setStyle("-fx-border-color: none;");
        }

        return devolver;
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

    @FXML
    private void desclickarPerfil() {
        desclickarContextMenu();
        this.menu.show();
    }

    @FXML
    private void accionVolver(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Which/TiendaWhich.fxml"));
            Parent root = loader.load();
            TiendaWhichController controller = loader.getController();
            if (this.trabajador instanceof Jefe) {
                controller.setTrabajador(this.idTienda, this.trabajador);
            } else {
                controller.setTrabajador(this.trabajador);
            }

            Stage stage = (Stage) this.buttonVolver.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Ver Tienda", "No se ha podido mostrar la ventana de ver Tienda", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void perfil(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Perfil/Perfil.fxml"));
            Parent root = loader.load();
            PerfilController controller = loader.getController();
            controller.setDatos(this.trabajador);

            Stage stage = (Stage) this.tabla.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException ex) {
            Alertas.generarAlerta("Perfil", "Ahora mismo no se puede mostrar el perfil, intentelo más tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void cerrarSesion(ActionEvent event) {
        PerfilController.cerrarSesion(this.trabajador, (Stage) this.tabla.getScene().getWindow());
    }

}
