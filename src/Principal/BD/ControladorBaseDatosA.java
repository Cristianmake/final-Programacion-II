package Principal.BD;

import Principal.Entidades.Turno;
import Principal.Entidades.User;
import Principal.Ventanas.LoginController;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

import javax.swing.JOptionPane;

/**
 * @author KbServiCell
 */
public class ControladorBaseDatosA {


    private String nombreBaseDatos = "bdsalud";
    private String urlServidor = "jdbc:mysql://localhost:3306/" + nombreBaseDatos + "?useUnicode=true&useJDBCCompiantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private String usuario = "root";
    private String password = "";
    Connection conexion;

    public ControladorBaseDatosA() {
        try {
            //Driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Nombre del servidor. localhost:3306 es la ruta y el puerto de la conexión MySQL
            //El root es el nombre de usuario por default. No hay contraseña
            //Se inicia la conexión
            conexion = DriverManager.getConnection(urlServidor, usuario, password);
        
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexion a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexion a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error en la conexion a la base de datos: " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
          
        } finally {
          
            System.out.println("Conexion a la BD exitosa!!");
       
            
        }

    }

    public Connection getConexion() {
        return conexion;
    }

    //Metodo para obtener el usuario y contraseña de la base de datos
    public int login(String usuario, String contrasena) {
        int resultado = 0;
        try {
            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM user WHERE usuario='" + usuario + "' AND contrasena='" + contrasena + "'");
            if (rs.next()) {
                resultado = 1;
            } else {
                resultado = 0;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
        return resultado;

    }

    //Metodo para hacer el nro de turnos autoIncremental
    public int idTurnoAutoIncrementable() {
        int id = 1;
        PreparedStatement ps = null;
        ResultSet rs = null;

        ControladorBaseDatosA db = new ControladorBaseDatosA();
        try {
            ps = db.getConexion().prepareStatement("SELECT MAX(idTurno) FROM turno");
            rs = ps.executeQuery();
            while (rs.next()) {
                id = rs.getInt(1) + 1;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {

            }
        }
        return id;
    }

    //Metodo para cargar la lista de turnos
    public void llenarTurnos(Connection connection, ObservableList<Turno> listaTurno) {
        try {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM turno");
            while (rs.next()) {
                listaTurno.add(new Turno(rs.getInt("idTurno"), rs.getDate("fecha") /*rs.getObject("fecha", Date.class)*/, rs.getString("nombre"),
                        rs.getString("apellido"), rs.getInt("dni"), rs.getInt("edad"), rs.getInt("telefono"),
                        rs.getString("direccion"), rs.getString("especialidad"), rs.getString("horaTurno"), rs.getString("horaSalidaTurno"),
                        rs.getFloat("importe")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para cargar la lista de usuarios
    public void llenarUsers(Connection connection, ObservableList<User> listaUsers) {
        try {
            Statement ps = connection.createStatement();
            ResultSet rs = ps.executeQuery("SELECT * FROM user");
            while (rs.next()) {
                listaUsers.add(new User(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("dni"), rs.getInt("telefono"), rs.getString("usuario"), rs.getString("contrasena")));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Error: " + e.getMessage(), JOptionPane.ERROR_MESSAGE);
        }
    }

    //Metodo para obtener el ultimo id del registro guardado
    public int obtenerUltimoId() {
        int ultimoValor = 0;
        try {
            PreparedStatement stmtr = conexion.prepareStatement("SELECT MAX(`idTurno`)+1 FROM `turno`");
            ResultSet rsr = stmtr.executeQuery();
            if (rsr.next()) {
                ultimoValor = rsr.getInt(1);
            }
            stmtr.close();
            rsr.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ultimoValor;
    }
    
    //Metodo para obtener el importe total de los turnos
    public float obtenerTotalImporte(){
        float totalImporte = 0;
        try {
            PreparedStatement stmtr = conexion.prepareStatement("SELECT SUM(`importe`) FROM `turno` ");
            ResultSet rsr = stmtr.executeQuery();
            if (rsr.next()) {
                totalImporte = rsr.getFloat(1);
            }
            stmtr.close();
            rsr.close();
            conexion.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return totalImporte;
    } 
    
}