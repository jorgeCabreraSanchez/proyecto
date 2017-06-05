package VISTA.Tienda.Trabajador;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import DATOS.GestionTrabajadores;
import MODELO.Alertas;
import MODELO.Incidencia.IncidenciaTienda;
import MODELO.Incidencia.IncidenciaTrabajador;
import MODELO.Listas.ListaIncidenciasTrabajador;
import MODELO.Trabajadores.Empleado;
import MODELO.Trabajadores.Encargado;
import MODELO.Trabajadores.Trabajadores;
import VISTA.Tienda.Incidencias.NuevaEditar.NuevaEditarIncidenciaController;
import VISTA.Tienda.Trabajador.ModificarIncidencia.ModificarIncidenciaController;
import VISTA.Tienda.Trabajadores.TrabajadoresController;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Optional;
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
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import static javafx.scene.input.MouseEvent.MOUSE_PRESSED;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Modality;
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
    boolean modificado;
    ListaIncidenciasTrabajador lit;
    int numIncidencias;

    @FXML
    private Label idTienda;
    @FXML
    private Pane foto;
    @FXML
    private Label nombre;
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
    @FXML
    private DatePicker datePickerHasta;
    @FXML
    private DatePicker datePickerDesde;
    @FXML
    private Button buttonEditar;
    @FXML
    private Button buttonEliminar;
    @FXML
    private Button buttonNuevo;
    @FXML
    private Text label2;
    @FXML
    private Text label1;
    @FXML
    private AnchorPane fondo;
    @FXML
    private AnchorPane fondo1;
    @FXML
    private ListView<IncidenciaTrabajador> listaVistaIncidencias;

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

        try {
            this.lit = new ListaIncidenciasTrabajador(this.trabajadorVer.getId());
            this.listaVistaIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias()));

        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido mostrar las incidencias", Alert.AlertType.ERROR);
        }

    }

    private void desclickarOtro() {
        this.nombre.setVisible(true);
        this.textfieldNombre.setVisible(false);
        this.apellido1.setVisible(true);
        this.textfieldApellido1.setVisible(false);
        this.apellido2.setVisible(true);
        this.textfieldApellido2.setVisible(false);
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
            ocultar();
        } else {
            edicion("Encargado");
        }
    }

    private void ocultar() {
        this.botonCancelar.setVisible(false);
        this.botonModificar.setVisible(false);
        this.label1.setVisible(false);
        this.label1.setManaged(false);
        this.label2.setVisible(false);
        this.label2.setManaged(false);
        this.datePickerDesde.setVisible(false);
        this.datePickerDesde.setManaged(false);
        this.datePickerHasta.setVisible(false);
        this.datePickerHasta.setManaged(false);
        this.buttonNuevo.setVisible(false);
        this.buttonNuevo.setManaged(false);
        this.buttonEditar.setVisible(false);
        this.buttonEditar.setManaged(false);
        this.buttonEliminar.setVisible(false);
        this.buttonEliminar.setManaged(false);
        this.listaVistaIncidencias.setVisible(false);
        this.listaVistaIncidencias.setManaged(false);
        this.fondo.maxHeight(216);
        this.fondo1.maxHeight(216);
    }

    /* id del trabajador que ha abierto la aplicacion */
    private void modoEmpleado(int idTrabajador) {
        if (this.trabajadorVer.getId() != idTrabajador) {
            this.contraseña.setText("******");
            ocultar();
        } else {
            edicion("Empleado");
        }

    }

    /* id del trabajador que vamos a ver */
    public void setIDTrabajador(int idTrabajadorVer, int idTrabajadorSoy, String tipo, int numIncidencias) throws SQLException {
        this.trabajadorVer = gs.trabajadorCompleto(idTrabajadorVer);
        int incidencias;
        if (trabajadorVer instanceof Empleado) {
            Empleado empleado = (Empleado) trabajadorVer;
            empleado.setIncidencias(numIncidencias);
        } else {
            Encargado encargado = (Encargado) trabajadorVer;
            encargado.setIncidencias(numIncidencias);
        }
        this.numIncidencias = numIncidencias;
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
        if (verificar()) {
            this.modificado = true;
            Trabajadores trabajador;
            if (this.puesto.getText().equalsIgnoreCase("Encargado")) {
                trabajador = new Encargado();
            } else {
                trabajador = new Empleado();
            }
            trabajador.setId(this.trabajadorVer.getId());
            trabajador.setHorario(this.horario.getText());
            trabajador.setNombre(this.nombre.getText());
            trabajador.setApellido1(this.apellido1.getText());
            trabajador.setApellido2(apellido2(this.apellido2.getText()));
            trabajador.setContraseña(this.contraseña.getText());
            if (trabajador instanceof Empleado) {
                Empleado empleado = (Empleado) trabajador;
                empleado.setIdTienda(Integer.parseInt(this.idTienda.getText()));
            } else {
                Encargado encargado = (Encargado) trabajador;
                encargado.setIdTienda(Integer.parseInt(this.idTienda.getText()));
            }

            if (this.trabajadorVer instanceof Empleado && trabajador instanceof Empleado) {
                Empleado empleadoVer = (Empleado) this.trabajadorVer;
                Empleado empleadoModificado = (Empleado) trabajador;
                empleadoModificado.setIncidencias(empleadoVer.getIncidencias());
                numIncidencias = empleadoVer.getIncidencias();
                if (empleadoVer.igual(empleadoModificado)) {
                    this.modificado = false;
                }
            } else if (this.trabajadorVer instanceof Encargado && trabajador instanceof Encargado) {
                Encargado encargadoVer = (Encargado) this.trabajadorVer;
                Encargado encargadoModificado = (Encargado) trabajador;
                encargadoModificado.setIncidencias(encargadoVer.getIncidencias());
                numIncidencias = encargadoVer.getIncidencias();
                if (encargadoVer.igual(encargadoModificado)) {
                    this.modificado = false;
                }
            } else if (this.trabajadorVer instanceof Encargado && trabajador instanceof Empleado) {
                Encargado encargadoVer = (Encargado) this.trabajadorVer;
                Empleado empleadoModificado = (Empleado) trabajador;
                empleadoModificado.setIncidencias(encargadoVer.getIncidencias());
                numIncidencias = encargadoVer.getIncidencias();
            } else {
                Encargado encargadoModificado = (Encargado) trabajador;
                Empleado empleadoVer = (Empleado) this.trabajadorVer;
                encargadoModificado.setIncidencias(empleadoVer.getIncidencias());
                numIncidencias = empleadoVer.getIncidencias();
            }

            if (this.modificado) {
                Alertas.generarAlerta("Datos", "Los datos han sido modificados sucesfully.", Alert.AlertType.INFORMATION);
                this.trabajadorVer = trabajador;
                Stage stage = (Stage) this.nombre.getScene().getWindow();
                stage.close();
            } else {
                Alertas.generarAlerta("Datos", "No se ha modificado ningún dato.", Alert.AlertType.INFORMATION);
            }

        }
    }

    public int numeroIncidencias() {
        return this.numIncidencias;
    }

    public boolean modificado() {
        return this.modificado;
    }

    /* Si se ha editado, lo he modificado y he puesto el modificado, ya no es el de ver. */
    public Trabajadores getTrabajadorModificado() {
        return this.trabajadorVer;
    }

    @FXML
    private void cancelar(ActionEvent event) {
        Stage stage = (Stage) this.nombre.getScene().getWindow();
        stage.close();
    }

    private boolean verificar() {
        desclickarOtro();
        boolean devolver = true;
        if (this.nombre.getText().isEmpty()) {
            devolver = false;
            this.nombre.setVisible(false);
            this.textfieldNombre.setVisible(true);
            this.textfieldNombre.setStyle("-fx-border-color:red");
        } else {
            this.textfieldNombre.setStyle("-fx-border-color:none");
        }

        if (this.apellido1.getText().isEmpty()) {
            devolver = false;
            this.apellido1.setVisible(false);
            this.textfieldApellido1.setVisible(true);
            this.textfieldApellido1.setStyle("-fx-border-color:red");
        } else {
            this.textfieldApellido1.setStyle("-fx-border-color:none");
        }
        if (this.tipo.equalsIgnoreCase("Jefe")) {
            if (this.puesto.getText().isEmpty()) {
                devolver = false;
                this.puesto.setVisible(false);
                this.comboboxPuesto.setVisible(true);
                this.comboboxPuesto.setStyle("-fx-border-color:red");
            } else {
                this.comboboxPuesto.setStyle("-fx-border-color:none");
            }
        }

        if (this.contraseña.getText().isEmpty()) {
            devolver = false;
            this.contraseña.setVisible(false);
            this.textfieldContraseña.setVisible(true);
            this.textfieldContraseña.setStyle("-fx-border-color:red");
        } else {
            this.textfieldContraseña.setStyle("-fx-border-color:none");
        }

        if (!this.tipo.equalsIgnoreCase("Empleado")) {
            if (this.horario.getText().isEmpty()) {
                devolver = false;
                this.horario.setVisible(false);
                this.comboboxHorario.setVisible(true);
                this.comboboxHorario.setStyle("-fx-border-color:red");
            } else {
                this.comboboxHorario.setStyle("-fx-border-color:none");
            }
        }

        if (this.tipo.equalsIgnoreCase("Jefe")) {
            if (this.idTienda.getText().isEmpty()) {
                devolver = false;
                this.idTienda.setVisible(false);
                this.textFieldidTienda.setVisible(true);
                this.textFieldidTienda.setStyle("-fx-border-color:red");
            } else {
                this.textFieldidTienda.setStyle("-fx-border-color:none");
            }
        }

        return devolver;
    }

    @FXML
    private void introducirFecha(ActionEvent event) {
                actualizarIncidencias();
    }

    @FXML
    private void accionEditar(ActionEvent event) {
        IncidenciaTrabajador incidenciaDar = this.listaVistaIncidencias.getSelectionModel().getSelectedItem();
        if (incidenciaDar != null) {
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/VISTA/Tienda/Trabajador/ModificarIncidencia/ModificarIncidencia.fxml"));
                Parent root = loader.load();
                ModificarIncidenciaController controller = loader.getController();
                controller.botonEditar(incidenciaDar);

                Stage stageNuevo = new Stage();
                stageNuevo.setScene(new Scene(root));
                stageNuevo.initModality(Modality.APPLICATION_MODAL);
                stageNuevo.showAndWait();

                IncidenciaTrabajador incidencia = controller.cogerIncidencia();
                if (incidencia != null) {
                    this.listaVistaIncidencias.setItems(FXCollections.observableArrayList(this.lit.editarIncidencia(incidencia)));

                    actualizarIncidencias();
                }

            } catch (IOException ex) {
                Alertas.generarAlerta("Ventana Editar Incidencia", "No se ha podido abrir la ventana editar incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
            } catch (SQLException ex) {
                Alertas.generarAlerta("BD", "No se ha podido modificar la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
            }
        }
    }

    private void actualizarIncidencias() {
        LocalDate fechaEntrada;
        LocalDate fechaSalida;
        if (this.datePickerDesde.getValue() == null) {
            fechaEntrada = LocalDate.MIN;
        } else {
            fechaEntrada = this.datePickerDesde.getValue();
        }
        if (this.datePickerHasta.getValue() == null) {
            fechaSalida = LocalDate.now();
        } else {
            fechaSalida = this.datePickerHasta.getValue();

        }
        this.listaVistaIncidencias.setItems(FXCollections.observableArrayList(lit.mostrarIncidencias(fechaEntrada, fechaSalida)));
    }

    @FXML
    private void accionEliminar(ActionEvent event) {
        IncidenciaTrabajador incidencia = this.listaVistaIncidencias.getSelectionModel().getSelectedItem();
        if (incidencia != null) {
            Optional<ButtonType> boton = Alertas.generarAlerta("Tiendas", "Esta seguro que desea borra la Incidencia?", "Información de la Incidencia:  ID: " + incidencia.getIdIncidencia() + "   Titulo: " + incidencia.getTitulo() + " \n  Descripción: " + incidencia.getDescripcion(), Alert.AlertType.CONFIRMATION);
            if (boton.get().getText().equalsIgnoreCase("aceptar")) {
                try {
                    lit.borrarIncidencia(incidencia.getIdIncidencia());
                    actualizarIncidencias();
                    if (this.trabajadorVer instanceof Empleado) {
                        Empleado empleado = (Empleado) this.trabajadorVer;
                        empleado.setIncidencias(empleado.getIncidencias() - 1);
                    } else {
                        Encargado encargado = (Encargado) this.trabajadorVer;
                        encargado.setIncidencias(encargado.getIncidencias() - 1);
                    }
                    this.numIncidencias = this.numIncidencias -1;
                } catch (SQLException e) {
                    Alertas.generarAlerta("Error BD", "Ha habido un error intentando borrar la incidencia y no se ha podido", "Error: " + e.getErrorCode() + " " + e.getLocalizedMessage(), Alert.AlertType.ERROR);
                }
            }
        }
    }

    @FXML
    private void accionNuevo(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/VISTA/Tienda/Trabajador/ModificarIncidencia/ModificarIncidencia.fxml"));
            Parent root = loader.load();
            ModificarIncidenciaController controller = loader.getController();
            controller.botonNuevo();

            Stage stageNuevo = new Stage();
            stageNuevo.setScene(new Scene(root));
            stageNuevo.initModality(Modality.APPLICATION_MODAL);
            stageNuevo.showAndWait();

            IncidenciaTrabajador incidencia = controller.cogerIncidencia();
            if (incidencia != null) {
                incidencia.setIdTrabajador(this.trabajadorVer.getId());
                this.listaVistaIncidencias.setItems(FXCollections.observableArrayList(lit.nuevaIncidencia(incidencia)));
                actualizarIncidencias();
                if (this.trabajadorVer instanceof Empleado) {
                    Empleado empleado = (Empleado) this.trabajadorVer;
                    empleado.setIncidencias(empleado.getIncidencias() + 1);
                } else {
                    Encargado encargado = (Encargado) this.trabajadorVer;
                    encargado.setIncidencias(encargado.getIncidencias() + 1);
                }
                this.numIncidencias = this.numIncidencias +1;
            }

        } catch (IOException ex) {
            Alertas.generarAlerta("Ventana Nueva Incidencia", "No se ha podido abrir la ventana nueva incidencia", "Error: " + ex.getMessage(), Alert.AlertType.ERROR);
        } catch (SQLException ex) {
            Alertas.generarAlerta("BD", "No se ha podido crear la incidencia", "Error: " + ex.getErrorCode() + " " + ex.getLocalizedMessage(), Alert.AlertType.ERROR);
        }
    }

}
