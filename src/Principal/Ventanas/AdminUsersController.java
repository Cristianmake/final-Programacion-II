package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorUser;
import Principal.Entidades.User;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class AdminUsersController extends ControladorBaseDatosA implements Initializable {

    private User user;
    @FXML
    private Button atras;
    @FXML
    private Button actualizar;
    @FXML
    private TableView<User> listado;
    @FXML
    private TableColumn<User, String> lNombre;
    @FXML
    private TableColumn<User, String> lApellido;
    @FXML
    private TableColumn<User, Integer> lDni;
    @FXML
    private TableColumn<User, Integer> lTelefono;

    private ObservableList<User> listaUsers;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaUsers = FXCollections.observableArrayList();
        ControladorBaseDatosA dbz = new ControladorBaseDatosA();
        dbz.llenarUsers(dbz.getConexion(), listaUsers);
        listado.setItems(listaUsers);
        lNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        lDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        lTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    //BOTONES
    //Apretar nuevo
    @FXML
    private void apretarNuevo(ActionEvent event) {
        System.out.println("Abriendo ventana de Registro...");
        try {
            //Cargo el archivo fxml de la ventana de registro
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root2 = (Parent) FXMLLoader.load();
            Stage ventanaRegistro = new Stage();
            ventanaRegistro.setScene(new Scene(root2));

            //La hago bonita
            ventanaRegistro.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
            ventanaRegistro.setTitle("Registro");
            ventanaRegistro.initStyle(StageStyle.UNDECORATED);
            //ventanaRegistro.initStyle(StageStyle.UTILITY);
            //ventanaRegistro.initModality(Modality.WINDOW_MODAL);
           

            System.out.println("Iniciando la ventana de registro...");
            //Inicio la ventana

            ventanaRegistro.show();
            System.out.println("Ventana de registro abierta correctamente!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Apretar refresh
    @FXML
    private void apretarRefrescar(ActionEvent event) {
        listaUsers = FXCollections.observableArrayList();
        ControladorBaseDatosA dbz = new ControladorBaseDatosA();
        dbz.llenarUsers(dbz.getConexion(), listaUsers);
        listado.setItems(listaUsers);
        lNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        lApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        lDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        lTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
    }

    //Apretar Eliminar Usuario
    @FXML
    private void apretarEliminar(ActionEvent event) {
        ControladorUser controlador = new ControladorUser();
        int selectedIndex = listado.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            controlador.borrarDatos(lDni.getCellData(selectedIndex));
            listado.getItems().remove(selectedIndex);
        } else {
            // Nada seleccionado
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Error");
            alerta1.setContentText("Selecciona algún elemento de la lista");
            alerta1.showAndWait();

        }
    }

    //Apretar Editar Usuario
    @FXML
    private void apretarModificar(ActionEvent event) {
        
        System.out.println("Abriendo ventana para modificar...");
        try { 
            User selectedUser = listado.getSelectionModel().getSelectedItem();
            if (selectedUser != null) {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de registro
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("modificarUsuario.fxml"));
            Parent root5 = (Parent) FXMLLoader.load();
            Stage ventanaRegistro = new Stage();
            ventanaRegistro.setScene(new Scene(root5));

            //La hago bonita
            ventanaRegistro.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/editar.png"));
            ventanaRegistro.setTitle("Modificar");
            ventanaRegistro.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de modificar...");
            //Inicio la ventana
            ModificarUserController control = FXMLLoader.getController();
            
            control.initialize(selectedUser);
            
            ventanaRegistro.show();
            System.out.println("Ventana de modificar abierta correctamente!");
            }else {
            // Si no se selecciona nada
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Error");
            alerta1.setContentText("Selecciona algún elemento de la lista");
            alerta1.showAndWait();

        }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Apretar botón atrás
    @FXML
    private void apretarBotonAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

}
