package Principal.Ventanas;

import Principal.Entidades.ControladorTurno;
import Principal.Entidades.Turno;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
public class ModificarRegistroController {

    @FXML
    private Label eNumero;
    @FXML
    private DatePicker eFecha;
    @FXML
    private Button confirmarEdicion;
    @FXML
    private TextField eNombre;
    @FXML
    private TextField eApellido;
    @FXML
    private TextField eDni;
    @FXML
    private TextField eEdad;
    @FXML
    private TextField eTelefono;
    @FXML
    private TextField eDireccion;
    @FXML
    private TextField eEspecialidad;
    @FXML
    private TextField eHoraTurno;
    @FXML
    private TextField eHoraSalidaTurno;
    @FXML
    private TextField eImporte;
    @FXML
    private Button botonCancelar;

    private Turno turno;

    @FXML
    public void initialize(Turno turno) {
        this.turno = turno;
        eNumero.setText(Integer.toString(turno.getIdTurno()));
        //FECHA
        eFecha.setValue(LocalDate.now());
        eNombre.setText(turno.getNombre());
        eApellido.setText(turno.getApellido());
        eDni.setText(Integer.toString(turno.getDni()));
        eEdad.setText(Integer.toString(turno.getEdad()));
        eTelefono.setText(Integer.toString(turno.getTelefono()));
        eDireccion.setText(turno.getDireccion());
        eEspecialidad.setText(turno.getEspecialidad());
        eHoraTurno.setText(turno.getHoraTurno());
        eHoraSalidaTurno.setText(turno.getHoraSalidaTurno());
        eImporte.setText(Float.toString(turno.getImporte()));

    }

    @FXML
    private void apretarConfirmarEdicion(ActionEvent event) throws IOException {
        System.out.println("Editando registro...");

        ControladorTurno controlBd = new ControladorTurno();
        if (controlBd.buscarPorIdSQL(Integer.parseInt(eNumero.getText())) == null || Integer.parseInt(eNumero.getText()) == turno.getIdTurno()) {
            controlBd.borrarDatos(turno.getIdTurno());
            turno.setFecha(java.sql.Date.valueOf(eFecha.getValue()));
            turno.setNombre(eNombre.getText());
            turno.setApellido(eApellido.getText());
            turno.setDni(Integer.parseInt(eDni.getText()));
            turno.setEdad(Integer.parseInt(eEdad.getText()));
            turno.setTelefono(Integer.parseInt(eTelefono.getText()));
            turno.setDireccion(eDireccion.getText());
            turno.setEspecialidad(eEspecialidad.getText());
            turno.setHoraTurno(eHoraTurno.getText());
            turno.setHoraSalidaTurno(eHoraSalidaTurno.getText());
            turno.setImporte(Float.parseFloat(eImporte.getText()));

            controlBd.insertarSQL(turno);
        }else {
                JOptionPane.showMessageDialog(null, "El ID ya siendo utilizado");
        }
        System.out.println("Registro editado correctamente!!");
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminTurnos.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }

    @FXML
    private void apretarCancelar(ActionEvent event) throws IOException {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminTurnos.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();  
    }

}
