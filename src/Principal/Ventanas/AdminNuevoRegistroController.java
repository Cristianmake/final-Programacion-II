/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.ControladorTurno;
import Principal.Entidades.Turno;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class AdminNuevoRegistroController implements Initializable {

    private boolean clickRegistrarTurno = false;
    
    @FXML
    private Label numeroTurno;
    @FXML
    private DatePicker tFecha;
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
    private TextField sImporte;
    @FXML
    private Button registrarTurno;
    @FXML
    private Button botonCancelar;

    /**
     * Inicializa la clase de controlador.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControladorBaseDatosA ct = new ControladorBaseDatosA();
        int valor = ct.obtenerUltimoId();
        numeroTurno.setText(Integer.toString(valor));
    }    
    public boolean siClickeoOk() {
        return clickRegistrarTurno;
    }
    
    public boolean siEsInvalidoNuevoRegistro() {
        String errorMessage = "";

        if (tNombre.getText() == null || tNombre.getText().length() == 0) {
            errorMessage += "Nombre no valido!\n";
        }
        if (tApellido.getText() == null || tApellido.getText().length() == 0) {
            errorMessage += "Apellido Invalido!\n";
        }
        if (tDni.getText() == null || tDni.getText().length() == 0) {
            errorMessage += "Dni no valido!\n";
        }

        if (tEdad.getText() == null || tEdad.getText().length() == 0) {
            errorMessage += "Edad no valida!\n";
        }
        if (tTelefono.getText() == null || tTelefono.getText().length() == 0) {
            errorMessage += "Telefono invalido!\n";
        } else {
            try {
                Integer.parseInt(tTelefono.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Telefono invalido!!\n";
            }
        }
        if (tDireccion.getText() == null || tDireccion.getText().length() == 0) {
            errorMessage += "Direccion no valida!\n";
        }

        if (tEspecialidad.getText() == null || tEspecialidad.getText().length() == 0) {
            errorMessage += "Especialidad invalida!\n";
        } else {
            try {
                Integer.parseInt(tEspecialidad.getText());
            } catch (NumberFormatException e) {
                errorMessage += "Especialidad invalida!!\n";
            }
        }

        if (tFecha.getPromptText() == null || tFecha.getPromptText().length() == 0) {
            errorMessage += "Fecha no valida!\n";
        }
        if (sImporte.getText() == null || sImporte.getText().length() == 0) {
            errorMessage += "Importe invalido!\n";
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
    
    //Método para vaciar los campos ingresados anteriormente
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
        numeroTurno.setText(Integer.toString(valor));
    }
    
    //BOTONES
    //Apretar boton de registrar
    //Anotación que etiqueta una clase o miembro como accesible para marcado
    @FXML
    private void apretarRegistrar(ActionEvent event) {
        ControladorTurno controladorT = new ControladorTurno();
        ControladorBaseDatosA id = new ControladorBaseDatosA();
        
        if (siEsInvalidoNuevoRegistro()) {
            Turno t1 = new Turno();

            //Obtengo el método de id autoincrementable
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
          
            //Acá guardo los valores de hora 
            t1.setHoraTurno((sHoraTurno.getText()));
            t1.setHoraSalidaTurno((sHoraSalidaTurno.getText()));
            t1.setImporte(Float.parseFloat(sImporte.getText()));

            clickRegistrarTurno = true;
            JOptionPane.showMessageDialog(null, "Turno registrado con éxito");
            System.out.println("Turno Registrado correctamente!!");
            controladorT.insertarSQL(t1);
            limpiar();

        } else {
            if (tNombre.getText().equals("") || tApellido.equals("") || tDni.equals("") || tEdad.equals("") || tTelefono.equals("") || tDireccion.equals("") || tEspecialidad.equals("") || sImporte.equals("")) {
                JOptionPane.showMessageDialog(null, "Ingrese algun dato");
                System.out.println("Ingresa algo pánfilo!");
            }
        }
    }

    //Apretar botón cancelar
    @FXML
    private void apretarCancelar(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        System.out.println("Chau Chau!"); 
    }
    
}
