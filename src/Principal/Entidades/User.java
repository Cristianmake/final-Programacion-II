package Principal.Entidades;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * @author KbServiCell
 */
public class User {
  
    private StringProperty nombre;
    private StringProperty apellido;
    private IntegerProperty dni;
    private IntegerProperty telefono;
    
    private SimpleStringProperty usuario;
    private SimpleStringProperty contrasena;
   
    
    public User (){
        this(null,null,0,0,null,null);
    }
    
     public User(String nombre, String apellido, int dni, int telefono, String usuario, String contrasena){
         this.nombre = new SimpleStringProperty(nombre);
         this.apellido = new SimpleStringProperty(apellido);
         this.dni = new SimpleIntegerProperty(dni);
         this.telefono = new SimpleIntegerProperty(telefono);
         this.usuario = new SimpleStringProperty(usuario);
         this.contrasena = new SimpleStringProperty(contrasena);
         
         
     }


    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }
    
    public StringProperty nombreProperty(){
        return nombre;
    }
    

    public String getApellido() {
        return apellido.get();
    }

    public void setApellido(String apellido) {
        this.apellido.set(apellido);
    }
    
    public StringProperty apellidoProperty(){
        return apellido;
    }

    
    
    public int getDni() {
        return dni.get();
    }

    public void setDni(int dni) {
        this.dni.set(dni);
    }
    
     public IntegerProperty dniProperty(){
        return dni;
    }
    
    
    public int getTelefono() {
        return telefono.get();
    }

    public void setTelefono(int telefono) {
        this.telefono.set(telefono);
    }
    
     public IntegerProperty telefonoProperty(){
        return telefono;
    }
     
     

    public String getUsuario() {
        return usuario.get();
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }
    public StringProperty usuarioProperty(){
        return usuario;
    }
    
    
    
    public String getContrasena() {
        return contrasena.get();
    }

    public void setContrasena(String contrasena) {
        this.contrasena.set(contrasena);
    }
    
    public StringProperty contrasenaProperty(){
        return contrasena;
    }
}
