package Principal.Entidades;

import java.sql.Date;

import com.sun.org.apache.bcel.internal.generic.IFGT;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author KbServiCell
 */
public class Turno {
    private IntegerProperty idTurno; //Propiedad que envuelve un valor
    private Date fecha;
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty dni;
    private IntegerProperty edad;
    private IntegerProperty telefono;
    private StringProperty direccion;
    private StringProperty especialidad;
    private StringProperty horaTurno;
    private StringProperty horaSalidaTurno;
    private FloatProperty importe;

    public Turno() {
      
        this.idTurno = new SimpleIntegerProperty();
        this.fecha = fecha;
        this.nombre = new SimpleStringProperty("");
        this.apellido = new SimpleStringProperty("");
        this.dni = new SimpleIntegerProperty();
        this.edad = new SimpleIntegerProperty();
        this.telefono = new SimpleIntegerProperty();
        this.direccion = new SimpleStringProperty("");
        this.especialidad = new SimpleStringProperty("");
        this.horaTurno = new SimpleStringProperty("");
        this.horaSalidaTurno = new SimpleStringProperty("");
        this.importe = new SimpleFloatProperty();
        

    }
    
    public Turno(Integer idTurno,Date fecha,String nombre,String apellido, Integer dni, Integer edad, Integer telefono, String direccion, String especialidad, String horaTurno, String horaSalidaTurno, Float importe){
        this.idTurno = new SimpleIntegerProperty(idTurno);
        this.fecha = fecha;
        this.nombre = new SimpleStringProperty(nombre);
        this.apellido = new SimpleStringProperty(apellido);
        this.dni = new SimpleIntegerProperty(dni);
        this.edad = new SimpleIntegerProperty(edad);
        this.telefono = new SimpleIntegerProperty(telefono);
        this.direccion = new SimpleStringProperty(direccion);
        this.especialidad = new SimpleStringProperty(especialidad);
        this.horaTurno = new SimpleStringProperty(horaTurno);
        this.horaSalidaTurno = new SimpleStringProperty(horaSalidaTurno);
        this.importe = new SimpleFloatProperty(importe);
    }

    public int getIdTurno() {
        return idTurno.get();
    }

    public void setIdTurno(int idTurno) {
        this.idTurno.set(idTurno);
    }

    public IntegerProperty idViajeProperty() {
        return idTurno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty origenProperty() {
        return nombre;
    }

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }

    public StringProperty apellidoProperty() {
        return apellido;
    }

    public int getDni() {
        return dni.get();
    }

    public void setDni(int dni) {
        this.dni.set(dni);
    }

    public IntegerProperty dniProperty() {
        return dni;
    }

    public int getEdad() {
        return edad.get();
    }

    public void setEdad(int edad) {
        this.edad.set(edad);
    }

    public IntegerProperty edadProperty() {
        return edad;
    }

    public int getTelefono() {
        return telefono.get();
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }

    public IntegerProperty telefonoProperty() {
        return telefono;
    }

    public String getDireccion() {
        return direccion.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public String getEspecialidad() {
        return especialidad.get();
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad.set(especialidad);
    }

    public StringProperty especialidadProperty() {
        return especialidad;
    }

    public String getHoraTurno() {
        return horaTurno.get();
    }

    public void setHoraTurno(String horaTurno) {
        this.horaTurno.set(horaTurno);
    }

    public StringProperty horaTurnoProperty() {
        return horaTurno;
    }

    public String getHoraSalidaTurno() {
        return horaSalidaTurno.get();
    }

    public void setHoraSalidaTurno(String HoraSalidaTurno) {
        this.horaSalidaTurno.set(HoraSalidaTurno);
    }

    public StringProperty HoraSalidaTurnoProperty() {
        return horaSalidaTurno;
    }

     public float getImporte() {
        return importe.get();
    }

    public void setImporte(float Importe) {
        this.importe.set(Importe);
    }

    public FloatProperty ImporteProperty() {
        return importe;
    }

}
