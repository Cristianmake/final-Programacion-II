package Principal.Ventanas;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author KbServiCell 
 */
public class AdminPrincipalController {

    @FXML
    private Button consultaMensual;
    @FXML
    private Button adminUsuarios;
    @FXML
    private Button mostrarEstadisticas;
    @FXML
    private Button cerrarSesion;

    //BOTONES
    //Apretando el boton para consultar la planilla mensual
    @FXML
    private void apretarConsulta(ActionEvent event) {
        System.out.println("Abriendo ventana de turnos mensuales");

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de administrador de turnos
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminTurnos.fxml"));
            Parent root3 = (Parent) FXMLLoader.load();
            Stage ventanaAdminTurnos = new Stage();
            ventanaAdminTurnos.setScene(new Scene(root3));

            //La hago bonita
            ventanaAdminTurnos.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
            ventanaAdminTurnos.setTitle("Turnos Mensuales");
            ventanaAdminTurnos.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de turnos mensuales...");

            //Inicio la ventana
            ventanaAdminTurnos.show();

            System.out.println("Ventana de turnos mensuales abierta correctamente!");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Apretando el botón para administrar Usuarios
    @FXML
    private void apretarAdminUsuarios(ActionEvent event) {
        System.out.println("Abriendo ventana de Administrar usuarios");

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de administrador de usuarios
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("adminUsuarios.fxml"));
            Parent root4 = (Parent) FXMLLoader.load();
            Stage ventanaUsuarios = new Stage();
            ventanaUsuarios.setScene(new Scene(root4));

            //La hago bonita
            ventanaUsuarios.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
            ventanaUsuarios.setTitle("Administrador de Usuarios");
            ventanaUsuarios.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de administrador de usuarios...");

            //Inicio la ventana
            ventanaUsuarios.show();

            System.out.println("Ventana de administrador de usuarios abierta correctamente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
  //Apretando el botón para consultar las Estadísticas
    @FXML
    private void apretarMostrarEstadisticas(ActionEvent event) {
        System.out.println("Abriendo ventana de Estadísticas");

        try {
            ((Node) (event.getSource())).getScene().getWindow().hide();
            //Cargo el archivo fxml de la ventana de mostrar estadísticas
            FXMLLoader FXMLLoader = new FXMLLoader(getClass().getResource("estadisticasTurnos.fxml"));
            Parent root5 = (Parent) FXMLLoader.load();
            Stage ventanaestadisticasTurnos = new Stage();
            ventanaestadisticasTurnos.setScene(new Scene(root5));

            //La hago bonita
            ventanaestadisticasTurnos.getIcons().add(new Image("file:/C:/Users/oscar/eclipse-workspace/MiAppCentroSalud/MiApp/src/imagenes/doc.png"));
            ventanaestadisticasTurnos.setTitle("Mostrar Estadísticas");
            ventanaestadisticasTurnos.initStyle(StageStyle.UNDECORATED);

            System.out.println("Iniciando la ventana de Estadísticas...");

            //Inicio la ventana
            ventanaestadisticasTurnos.show();

            System.out.println("Ventana de Estadísticas abierta correctamente!");
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Apretando el botón para cerrar sesión y volver al login
    @FXML
    private void apretarCerrarSesionA(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Cerrando ventana de administrador");
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

}
