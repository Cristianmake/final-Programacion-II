package Principal.Entidades;

import Principal.BD.ControladorBaseDatosA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author KbServiCell
 */
public class ControladorUser extends ControladorBaseDatosA {

    private String borrarTablaSQL = "DROP TABLE IF EXISTS user";

    private String crearTablaSQL = "CREATE TABLE IF NOT EXISTS `user`(`nombre` varchar(50) DEFAULT NULL, `apellido` varchar(50) DEFAULT NULL, `dni` int DEFAULT NULL, `telefono` int DEFAULT NULL, `usuario` varchar(50) DEFAULT NULL,`contrasena` varchar(25) DEFAULT NULL, PRIMARY KEY(`dni`))";
    private String insertarSQL = "INSERT INTO `user`(`nombre`, `apellido`, `dni`, `telefono`, `usuario`,`contrasena`) VALUES('%s', '%s', %s, %s, '%s', '%s')";
    private String buscarPorIdSQL = "SELECT * FROM user WHERE nombre = '%s'";
    private String buscarTodosSQL = "SELECT * FROM user";
    private String actualizarSQL = "UPDATE `user` SET `nombre`='Cris' WHERE dni=34063403 ";
    private String eliminarSQL = "DELETE FROM `user` WHERE `dni`=%s";

    public ControladorUser() {
        super(); // invoca al constructor de la clase superior que comparte el mismo tipo de parametrización.
    }

    public boolean borrarDatos(int dni) {
        try {
            String SQL = String.format(eliminarSQL, dni);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public boolean borrarTabla() {

        try {
            String SQL = borrarTablaSQL;
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean crearTabla() {

        try {
            String SQL = crearTablaSQL;
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public boolean insertarSQL(User u) {
        try {
            String SQL = String.format(insertarSQL, u.getNombre(), u.getApellido(), u.getDni(), u.getTelefono(), u.getUsuario(), u.getContrasena());
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;

    }

    public boolean actualizarSQL() {
        try {
            String SQL = String.format(actualizarSQL);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public User buscarPorDniSQL(int dni) {

        try {
            String SQL = String.format(buscarPorIdSQL, dni);
            PreparedStatement sentencia = getConexion().prepareStatement(SQL);
            ResultSet rs = sentencia.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    User userNuevo = new User();
                    userNuevo.setNombre(rs.getString(1));
                    userNuevo.setApellido(rs.getString(2));
                    userNuevo.setDni(rs.getInt(3));
                    userNuevo.setTelefono(rs.getInt(4));
                    userNuevo.setUsuario(rs.getString(5));
                    userNuevo.setContrasena(rs.getString(6));

                    return userNuevo;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

    public List<User> buscarTodos() {
        try {
            String SQL = buscarTodosSQL;
            Statement sentencia = getConexion().createStatement();
            ResultSet rs = sentencia.executeQuery(SQL);

            List<User> user = new ArrayList<>();

            if (rs != null) {
                while (rs.next()) {
                    User userNuevo = new User();
                    userNuevo.setNombre(rs.getString(1));
                    userNuevo.setApellido(rs.getString(2));
                    userNuevo.setDni(rs.getInt(3));
                    userNuevo.setTelefono(rs.getInt(4));
                    userNuevo.setUsuario(rs.getString(5));
                    userNuevo.setContrasena(rs.getString(6));

                    user.add(userNuevo);
                }

            }
            return user;

        } catch (SQLException ex) {
            Logger.getLogger(ControladorUser.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
