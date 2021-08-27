package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorTurno;
import Principal.Entidades.Turno;
import Principal.Entidades.User;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class PrincipalController extends ControladorBaseDatosA implements Initializable {

    private Turno turno;
    private User user;
    private Stage ventana;
    private boolean clickRegistrarTurno = false;

    //Constructor
    public PrincipalController() {

    }

    //Datos fx 
    @FXML
    private Label numero;
    @FXML
    private TextField tNombre;
    @FXML
    private TextField tApellido;
    @FXML
    private TextField tDni;
    @FXML
    private TextField tEdad;
    @FXML
    private TextField tTelefono;
    @FXML
    private TextField tDireccion;
    @FXML
    private TextField tEspecialidad;
    @FXML
    private TextField sHoraTurno;
    @FXML
    private TextField sHoraSalidaTurno;
    @FXML
    private DatePicker tFecha;
    @FXML
    private TextField sImporte;

    //Botones
    @FXML
    private Button confirmarTurno;
    @FXML
    private Button cerrarSesion;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numero.setText(Integer.toString(valor));
    }

    public void valoresInicialesTurno(Turno turno) {
        this.turno = turno;

        tNombre.setText(turno.getNombre());
        tApellido.setText(turno.getApellido());
        tDni.setText(Integer.toString(turno.getDni()));
        tEdad.setText(Integer.toString(turno.getEdad()));
        tTelefono.setText(Integer.toString(turno.getTelefono()));
        tDireccion.setText(turno.getDireccion());
        tEspecialidad.setText(turno.getEspecialidad());
        sImporte.setText(Float.toString(turno.getImporte()));

        
    }

    public boolean siClickeoOk() {
        return clickRegistrarTurno;
    }

    //Metodo para vaciar los campos ingresados anteriormente
    private void limpiar() {
        tNombre.setText("");
        tApellido.setText("");
        tDni.setText("");
        tEdad.setText("");
        tTelefono.setText("");
        tDireccion.setText("");
        tEspecialidad.setText("");
        sHoraTurno.setText("");
        sHoraSalidaTurno.setText("");
        sImporte.setText("");
        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numero.setText(Integer.toString(valor));
    }

    //Metodo de comprobacion de datos
    public boolean siEsInvalidoPrincipal() {
        String errorMessage = "";

        if (tNombre.getText() == null || tNombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n";
        }
        if (tApellido.getText() == null || tApellido.getText().length() == 0) {
            errorMessage += "Apellido Invalido!\n";
        }
        if (tDni.getText() == null || tDni.getText().length() == 0) {
            errorMessage += "Dni no valido!\n";
        } else {
            try {
                Integer.parseInt(tDni.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Dni invalido!!\n";
            }}

        if (tEdad.getText() == null || tEdad.getText().length() == 0) {
            errorMessage += "Edad no valida!\n";
        } else {
            try {
                Integer.parseInt(tEdad.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Edad invalida!!\n";
            }}
        if (tTelefono.getText() == null || tTelefono.getText().length() == 0) {
            errorMessage += "Telèfono invalido!\n";
        } else {
            try {
                Integer.parseInt(tTelefono.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Telefono invalido!!\n";
            }
        }
        if (tDireccion.getText() == null || tDireccion.getText().length() == 0) {
            errorMessage += "Direcciòn no valida!\n";
        }

        if (tEspecialidad.getText() == null || tEspecialidad.getText().length() == 0) {
            errorMessage += "Especialidad invalida!\n";
        } 

        if (tFecha.getPromptText() == null || tFecha.getPromptText().length() == 0) {
            errorMessage += "Fecha no valida!\n";
        }
        if (sImporte.getText() == null || sImporte.getText().length() == 0) {
            errorMessage += "Dni invalido!\n";
        } else {
            try {
                Integer.parseInt(sImporte.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Importe invalido!!\n";
            }
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    //Botoness
    //Una vez llenado los datos apretando el boton de registrar turno
    @FXML
    public void apretarRegistrarTurno(ActionEvent event) {
        ControladorTurno controladorT = new ControladorTurno();
        ControladorBaseDatosA id = new ControladorBaseDatosA();

        if (siEsInvalidoPrincipal()) {
            Turno t1 = new Turno();

            //Obtengo el metodo de id autoincrementable
            t1.setIdTurno(id.idTurnoAutoIncrementable());
            t1.setNombre(tNombre.getText());
            t1.setApellido(tApellido.getText());
            t1.setDni(Integer.parseInt(tDni.getText()));
            t1.setEdad(Integer.parseInt(tEdad.getText()));
            t1.setTelefono(Integer.parseInt(tTelefono.getText()));
            t1.setDireccion(tDireccion.getText());
            t1.setEspecialidad(tEspecialidad.getText());
            //Obtengo la fecha del date picker
            t1.setFecha(java.sql.Date.valueOf(tFecha.getValue()));
            //Aca guardo los valores de hora 
            t1.setHoraTurno((sHoraTurno.getText()));
            t1.setHoraSalidaTurno((sHoraSalidaTurno.getText()));
            t1.setImporte(Float.parseFloat(sImporte.getText()));

            clickRegistrarTurno = true;
            JOptionPane.showMessageDialog(null, "Turno registrado con èxito");
            System.out.println("Turno Registrado correctamente!!");
            controladorT.insertarSQL(t1);
            limpiar();

        } else {
            if (tNombre.getText().equals("") || tApellido.getText().equals("") || tDni.getText().equals("") || tEdad.getText().equals("") || tTelefono.getText().equals("") || tDireccion.getText().equals("") || tEspecialidad.getText().equals("") || sImporte.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese algun dato");
                System.out.println("Ingresa algo pànfilo!");
            }
        }
    }

    //Apretando el botón de cerrar sesión
    @FXML
    public void apretarCerrarSesion(ActionEvent event) throws IOException {
        
        System.out.println("Sesión cerrada con èxito!!");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();

    }
}
