package Principal;

import Principal.Entidades.ControladorUser;
import Principal.Ventanas.LoginController;
import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;

/**
 * @author KbServiCell
 */
//La clase Application es la clase principal desde la que se extienden las aplicaciones JavaFX.
public class MiApp extends Application {

    private Stage ventana;
    public MiApp(){
    }
    //Método iniciando la ventana, start se llama en en el subproceso de la aplicación Java Fx
    @Override
    public void start(Stage ventanaPrincipal) {
        this.ventana = ventanaPrincipal;
        this.ventana.setTitle("Sistema Turnos Centro Salud");
        System.out.println("La aplicacion se inicio correctamente");
        System.out.println("Abriendo Login...");
        //ABRE LA APP
        mostrarLogin();
        System.out.println("Login abierto correctamente!");
    }

    //Muestra la pantalla para registrarse o loguearse
    public void mostrarLogin() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MiApp.class.getResource("Ventanas/login.fxml"));
            AnchorPane login = (AnchorPane) loader.load();
            Scene scene = new Scene(login);
            ventana.setScene(scene);
         
            //Lo hago bonito
            ventana.initStyle(StageStyle.UNDECORATED);
            ventana.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));

            LoginController controller = loader.getController();
            controller.setMiApp(this);
            ventana.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        launch(args);                        //Esto se utiliza para ejecutar la aplicación es como el new Contructor();
        ControladorUser controladorU1 = new ControladorUser();
        if (controladorU1.crearTabla()) {
            System.out.println("La tabla se creo CORRECTAMENTE");
        }
    }
}
