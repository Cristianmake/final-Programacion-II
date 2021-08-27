package Principal.Ventanas;

/**
 * FXML Controller class
 * @author KbServiCell
 */
import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorUser;
import Principal.Entidades.User;
import Principal.MiApp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

public class RegistroController extends ControladorBaseDatosA{

    @FXML
    private TextField tNombre;
    @FXML
    private TextField tApellido;
    @FXML
    private TextField tDni;
    @FXML
    private TextField tTelefono;
    @FXML
    private TextField tUsuario;
    @FXML
    private PasswordField tContrasena;
    @FXML
    private Button botonAtras;
    @FXML
    private Button botonRegistrarse;

    private MiApp app;
    private User user;
    private boolean clickRegistro = false;

    public RegistroController() {

    }

    public void setApp(MiApp app) {
        this.app = app;
    }
 
    public void valoresIniciales(User user) {
        this.user = user;
        tNombre.setText(user.getNombre());
        tApellido.setText(user.getApellido());
        tDni.setText(Integer.toString(user.getDni()));
        tTelefono.setText(Integer.toString(user.getTelefono()));

        tUsuario.setText(user.getUsuario());
        tContrasena.setText(user.getContrasena());
    }

    public boolean siClickeoOk() {
        return clickRegistro;
    }

    //Botoness
    //Una vez llenado los datos apretando el botón de registrarse
    @FXML
    private void apretarRegistrarse(ActionEvent event) {
        ControladorUser controladorU = new ControladorUser();

        if (siEsInvalido()) {
            if (controladorU.buscarPorDniSQL(Integer.parseInt(tDni.getText())) == null || user.getDni() == Integer.parseInt(tDni.getText())) {

                User u1 = new User();
                
                u1.setNombre(tNombre.getText());                
                u1.setApellido(tApellido.getText());
                u1.setDni(Integer.parseInt(tDni.getText()));
                u1.setTelefono(Integer.parseInt(tTelefono.getText()));
                u1.setUsuario(tUsuario.getText());
                u1.setContrasena(tContrasena.getText());
                
                clickRegistro = true;
               ((Node) (event.getSource())).getScene().getWindow().hide();

                JOptionPane.showMessageDialog(null, "Bienvenido " + u1.getNombre());
                
                controladorU.insertarSQL(u1);
                
                System.out.println("Nuevo usuario registrado con exito!!");
            }
        }

    }

    @FXML
    private void apretarAtras(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Chao Chao!");
    }

    //Comprobando que los datos ingresados sean válidos
    public boolean siEsInvalido() {
        String errorMessage = "";

        if (tNombre.getText() == null || tNombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n";
        }
        if (tApellido.getText() == null || tApellido.getText().length() == 0) {
            errorMessage += "Apellido Invalido!\n";
        }
        if (tDni.getText() == null || tDni.getText().length() == 0) {
            errorMessage += "DNI no valida!\n";
        }
        if (tTelefono.getText() == null || tTelefono.getText().length() == 0) {
            errorMessage += "Telefono invalido!\n";
        }

        if (tUsuario.getText() == null || tUsuario.getText().length() == 0) {
            errorMessage += "usuario no valido!\n";
        }

        if (tContrasena.getText() == null || tContrasena.getText().length() == 0) {
            errorMessage += "contraseña no valido!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            // Muestra el mensaje de error
            Alert alerta1 = new Alert(Alert.AlertType.ERROR);
            alerta1.setTitle("Error");
            alerta1.setHeaderText("Tipo de dato incorrecto");
            alerta1.setContentText("Corrija los valores no sea pánfilo!");
            alerta1.showAndWait();
            return false;
        }
    }
}


