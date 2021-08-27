package Principal.Entidades;

import Principal.BD.ControladorBaseDatosA;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author KbServiCell
 */
public class ControladorTurno extends ControladorBaseDatosA {

    private String borrarTablaSQL = "DROP TABLE IF EXISTS turno";

    private String crearTablaSQL = "CREATE TABLE IF NOT EXISTS `turno`(`idTurno` int DEFAULT NULL, `fecha` date(50) DEFAULT NULL, `nombre` varchar(50) DEFAULT NULL, `apellido` varchar(50) DEFAULT NULL, `dni` int DEFAULT NULL,`edad` int DEFAULT NULL,`telefono` int DEFAULT NULL, `direccion` varchar(50) DEFAULT NULL, `especialidad` varchar(50) DEFAULT NULL,  `horaTurno` varchar(50) DEFAULT NULL, `horaSalidaTurno` varchar(50) DEFAULT NULL, `importe` float DEFAULT NULL, PRIMARY KEY(`idTurno`))";
    private String insertarSQL = "INSERT INTO `turno`(`idTurno`, `fecha`, `nombre`, `apellido`, `dni`, `edad`, `telefono`, `direccion`, `especialidad`, `horaTurno`, `horaSalidaTurno`, `importe`) VALUES(%s, '%s', '%s', '%s', %s, %s, %s, '%s', '%s', '%s', '%s', %s)";
    private String buscarPorIdSQL = "SELECT * FROM turno WHERE idTurno = %s";
    private String buscarTodosSQL = "SELECT * FROM turno";
    private String actualizarSQL = "UPDATE `turno` SET `nombre`='Cris' WHERE dni=34063403 ";
    private String eliminarSQL = "DELETE FROM `turno` WHERE `idTurno`=%s";

    public ControladorTurno() {
        super(); //llama por defecto al constructor de la clase superior
    }            //invoca al constructor de la clase superior que comparta el mismo tipo de parametrización

    public boolean borrarDatos(int dni) {
        try {
            String SQL = String.format(eliminarSQL, dni);
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);

            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }

    public boolean insertarSQL(Turno t) {
        try {
            String SQL = String.format(insertarSQL, t.getIdTurno(), t.getFecha(), t.getNombre(), t.getApellido(), t.getDni(), t.getEdad(), t.getTelefono(), t.getDireccion(), t.getEspecialidad(), t.getHoraTurno(), t.getHoraSalidaTurno(), t.getImporte());
            Statement sentencia = getConexion().createStatement();
            sentencia.execute(SQL);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;

    }

    public Turno buscarPorIdSQL(int dni) {

        try {
            String SQL = String.format(buscarPorIdSQL, dni);
            PreparedStatement sentencia = getConexion().prepareStatement(SQL);

            ResultSet rs = sentencia.executeQuery(SQL);

            if (rs != null) {
                while (rs.next()) {
                    Turno turnoNuevo = new Turno();
                    turnoNuevo.setIdTurno(rs.getInt(1));
                    turnoNuevo.setFecha(rs.getDate(2));
                    turnoNuevo.setNombre(rs.getString(3));
                    turnoNuevo.setApellido(rs.getString(4));
                    turnoNuevo.setDni(rs.getInt(5));
                    turnoNuevo.setEdad(rs.getInt(6));
                    turnoNuevo.setTelefono(rs.getInt(7));
                    turnoNuevo.setDireccion(rs.getString(8));
                    turnoNuevo.setEspecialidad(rs.getString(9));
                    turnoNuevo.setHoraTurno(rs.getString(10));
                    turnoNuevo.setHoraSalidaTurno(rs.getString(11));
                    turnoNuevo.setImporte(rs.getFloat(12));
              
                    return turnoNuevo;
                }
            }

        } catch (SQLException ex) {
            Logger.getLogger(ControladorTurno.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;

    }

}
