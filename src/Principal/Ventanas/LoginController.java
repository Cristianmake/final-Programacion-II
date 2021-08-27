package Principal.Ventanas;

import Principal.MiApp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import Principal.BD.ControladorBaseDatosA;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class LoginController extends ControladorBaseDatosA {

    private MiApp app;
    @FXML
    private Label mensaje;
    @FXML
    private TextField usuario;
    @FXML
    private PasswordField contrasena;
    @FXML
    private Label lblErrors;


//Constructor
    public LoginController() {

    }
    public void setMiApp(MiApp app) {
        this.app = app;
    }

    //Apretando el botón de iniciar sesión
    @FXML
    private void apretarIniciarSesion(ActionEvent event) throws IOException, Exception {
        ControladorBaseDatosA control = new ControladorBaseDatosA();
       //Compruebo la existencia de la contraseÃ±a y el usuario de la base de datos existente
        if(control.login(usuario.getText(), contrasena.getText())==1){
        	
            try {
                //Cierro la ventana de login
                ((Node) (event.getSource())).getScene().getWindow().hide();
                System.out.println("Abriendo Ventana Principal...");
                //Cargo el fxml de la ventana principal
                FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("principal.fxml"));
                Parent root1 = (Parent) FXMLLoader.load();
                Stage ventanaPrincipal = new Stage();
                ventanaPrincipal.setScene(new Scene(root1));
                
                //Lo hago bonito
                ventanaPrincipal.setTitle("Sistema Turnos Centro Salud");
                ventanaPrincipal.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
                ventanaPrincipal.initStyle(StageStyle.UNDECORATED);

                //Se inicia la Pantalla
                ventanaPrincipal.show();
              
 
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            //Si el usuario no ingresa nada
            if (usuario.getText().equals("") && contrasena.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese algun usuario o contrasena por favor");
                System.out.println("Ingresa algo por favor!");
            } else {
                //Si ingresa algo pero es incorrecto
                //mensaje.setText("Usuario y/o Contrasena incorrectos");
            	// JOptionPane.showMessageDialog(null, "Usuario y/o Contrasena incorrectos!");
                System.out.println("Usuario y/o Contrasena incorrectos!");
                //JOptionPane.showMessageDialog(null, "Ingrese algun usuario y/o contraseÃ±a validos");
            }  
        }
        
        //Entrando como administrador
        if (usuario.getText().equals("admins") && contrasena.getText().equals("admins")) {
          System.out.println("Iniciando Sesion como ADMINISTRADOR...");
           try {
                //Cierro la ventana de login
                ((Node) (event.getSource())).getScene().getWindow().hide();

                System.out.println("Abriendo Ventana ADMINPrincipal...");
                //Cargo el fxml de la ventana principal
                FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminPrincipal.fxml"));
                Parent root1 = (Parent) FXMLLoader.load();
                Stage ventanaPrincipal = new Stage();
                ventanaPrincipal.setScene(new Scene(root1));

                //Lo hago bonito
                ventanaPrincipal.setTitle("Sistema Turnos Centro Salud");
                ventanaPrincipal.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
                ventanaPrincipal.initStyle(StageStyle.UNDECORATED);

                //Se inicia la Pantalla
                ventanaPrincipal.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
          
        }
    }

    //Apretando el boton de Registro
    @FXML
    private void apretarRegistro(ActionEvent event) throws IOException {

        System.out.println("Abriendo ventana de Registro...");

        try {
            //Cargo el archivo fxml de la ventana de registro
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("registro.fxml"));
            Parent root2 = (Parent) FXMLLoader.load();
            Stage ventanaRegistro = new Stage();
            ventanaRegistro.setScene(new Scene(root2));

            //La hago bonita
            ventanaRegistro.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/registro.png"));
            ventanaRegistro.setTitle("Registro");
            ventanaRegistro.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de registro...");
            //Inicio la ventana
         
            ventanaRegistro.show();
            System.out.println("Ventana de registro abierta correctamente!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //salir con la X del login
    @FXML
    private void botonCerrar(MouseEvent event) {
        System.out.println("Que tenga un buen dia...!");
        System.exit(0);
       
    }

}
