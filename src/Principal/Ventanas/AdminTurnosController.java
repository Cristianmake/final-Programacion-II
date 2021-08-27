package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorTurno;
import Principal.Entidades.Turno;
import Principal.Utilidades.Csv;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class AdminTurnosController extends ControladorBaseDatosA implements Initializable {

    private Turno turno;
    @FXML
    private Button atras;
    @FXML
    private Button refresh;
    @FXML
    private Label total;
    @FXML
    private TableView<Turno> lista;
    @FXML
    private TableColumn<Turno, Integer> idColumna;
    @FXML
    private TableColumn<Turno, Date> fechaColumna;
    @FXML
    private TableColumn<Turno, String> nombreColumna;
    @FXML
    private TableColumn<Turno, String> apellidoColumna;
    @FXML
    private TableColumn<Turno, Integer> dniColumna;
    @FXML
    private TableColumn<Turno, Integer> edadColumna;
    @FXML
    private TableColumn<Turno, Integer> telefonoColumna;
    @FXML
    private TableColumn<Turno, String> direccionColumna;
    @FXML
    private TableColumn<Turno, String> especialidadColumna;
    @FXML
    private TableColumn<Turno, String> hTurnoColumna;
    @FXML
    private TableColumn<Turno, String> hSalidaTurnoColumna;
    @FXML
    private TableColumn<Turno, Float> importe;

    private ObservableList<Turno> listaTurno;

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        listaTurno = FXCollections.observableArrayList();
        ControladorBaseDatosA db = new ControladorBaseDatosA();
        db.llenarTurnos(db.getConexion(), listaTurno);

        lista.setItems(listaTurno);

        idColumna.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        //ACA AL CARGAR LA FECHA DESDE LA BASE DE DATOS ME RESTA UN DIA

        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));
//      fechaColumna.setCellValueFactory(convertirLocalDateAdate(new PropertyValueFactory<>("fecha")));

        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumna.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        dniColumna.setCellValueFactory(new PropertyValueFactory<>("dni"));
        edadColumna.setCellValueFactory(new PropertyValueFactory<>("edad"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        especialidadColumna.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        hTurnoColumna.setCellValueFactory(new PropertyValueFactory<>("horaTurno"));
        hSalidaTurnoColumna.setCellValueFactory(new PropertyValueFactory<>("horaSalidaTurno"));
        importe.setCellValueFactory(new PropertyValueFactory<>("importe"));

        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        float importeTotal = ct.obtenerTotalImporte();
        total.setText(Float.toString(importeTotal));

    }

    //BOTONES
    //Apretar botón para nuevo registro
    @FXML
    private void apretarNuevoRegistro(ActionEvent event) throws IOException {
        System.out.println("Abriendo ventana Nuevo registro...");
        FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminNuevoRegistro.fxml"));
        Parent root5 = (Parent) FXMLLoader.load();
        Stage ventanaNuevoRegistro = new Stage();
        ventanaNuevoRegistro.setScene(new Scene(root5));

        //Lo hago bonito
        ventanaNuevoRegistro.setTitle("Nuevo Registro");
        ventanaNuevoRegistro.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/disco.png"));
        ventanaNuevoRegistro.initStyle(StageStyle.UNDECORATED);

        //Se inicia la Pantalla
        ventanaNuevoRegistro.show();
    }

    //Apretar Actualizar
    @FXML
    private void actualizarListado(ActionEvent event) {
        listaTurno = FXCollections.observableArrayList();
        ControladorBaseDatosA db = new ControladorBaseDatosA();
        db.llenarTurnos(db.getConexion(), listaTurno);

        lista.setItems(listaTurno);

        idColumna.setCellValueFactory(new PropertyValueFactory<>("idTurno"));
        //ACA AL CARGAR LA FECHA DESDE LA BASE DE DATOS ME RESTA UN DIA

        fechaColumna.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        nombreColumna.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumna.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        dniColumna.setCellValueFactory(new PropertyValueFactory<>("dni"));
        edadColumna.setCellValueFactory(new PropertyValueFactory<>("edad"));
        telefonoColumna.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        direccionColumna.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        especialidadColumna.setCellValueFactory(new PropertyValueFactory<>("especialidad"));
        hTurnoColumna.setCellValueFactory(new PropertyValueFactory<>("horaTurno"));
        hSalidaTurnoColumna.setCellValueFactory(new PropertyValueFactory<>("horaSalidaTurno"));
        importe.setCellValueFactory(new PropertyValueFactory<>("importe"));

        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        float importeTotal = ct.obtenerTotalImporte();
        total.setText(Float.toString(importeTotal));
    }

    //Apretar botón para eliminar un registro
    @FXML
    private void apretarEliminarRegistro(ActionEvent event) {
        ControladorTurno controladorT = new ControladorTurno();
        int selectedIndex = lista.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            controladorT.borrarDatos(idColumna.getCellData(selectedIndex));
            lista.getItems().remove(selectedIndex);
        } else {
            // Nada seleccionado
            Alert alerta2 = new Alert(Alert.AlertType.ERROR);
            alerta2.setTitle("Error");
            alerta2.setHeaderText("Error");
            alerta2.setContentText("Selecciona algun elemento de la lista");
            alerta2.showAndWait();

        }
    }

    //Apretar botón de modificar algún registro
    @FXML
    private void apretarModificarRegistro(ActionEvent event) {
        
        System.out.println("Abriendo ventana para modificar...");
        try {
            Turno selectedTurno = lista.getSelectionModel().getSelectedItem();
            if (selectedTurno != null) {
                ((Node) (event.getSource())).getScene().getWindow().hide();
                
                //Cargo el archivo fxml de la ventana de registro
                FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("modificarRegistro.fxml"));
                Parent root12 = (Parent) FXMLLoader.load();
                Stage ventanaEditarRegistro = new Stage();
                ventanaEditarRegistro.setScene(new Scene(root12));

                //La hago bonita
                ventanaEditarRegistro.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
                ventanaEditarRegistro.setTitle("Modificar");
                ventanaEditarRegistro.initStyle(StageStyle.UNDECORATED);

                System.out.println("Iniciando la ventana de modificar...");
                //Inicio la ventana
                ModificarRegistroController contro2 = FXMLLoader.getController();

                contro2.initialize(selectedTurno);

                ventanaEditarRegistro.show();
                System.out.println("Ventana de modificar abierta correctamente!");
            } else {
                // Si no se selecciona nada
                Alert alerta1 = new Alert(Alert.AlertType.ERROR);
                alerta1.setTitle("Error");
                alerta1.setHeaderText("Error");
                alerta1.setContentText("Selecciona algun elemento de la lista");
                alerta1.showAndWait();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Apretar botón para mostrar estadísticas
    @FXML
    private void apretarMostrarEstadisticas(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Abriendo ventana de Estadisticas...");
        FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("estadisticasTurnos.fxml"));
        Parent root6 = (Parent) FXMLLoader.load();
        Stage ventanaEstadisticas = new Stage();
        ventanaEstadisticas.setScene(new Scene(root6));

        //Lo hago bonito
        ventanaEstadisticas.setTitle("Estadisticas");
        ventanaEstadisticas.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/estatus.png"));
        ventanaEstadisticas.initStyle(StageStyle.UNDECORATED);
        
        EstadisticasTurnosController controlador = FXMLLoader.getController();
        controlador.setUserData(listaTurno);
        //Se inicia la Pantalla
        ventanaEstadisticas.show();

    }
  //Apretar boton exportar
    @FXML
    private void apretarExportar(ActionEvent event) throws SQLException {
        Csv xp = new Csv();
        xp.exportarCsv(listaTurno);
        System.out.println("Archivo exportado correctamente!!");
        JOptionPane.showMessageDialog(null, "El archivo se exporto Correctamente");
    }


    //Apretar botón atrás
    @FXML
    private void apretarAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
}
