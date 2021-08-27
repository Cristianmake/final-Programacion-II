package Principal.Utilidades;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.Turno;
import Principal.Entidades.Viaje;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.ObservableList;

/**
 *
 * @author KbServiCell
 */
public class Csv {

    public void exportarCsv(ObservableList<Turno> listaTurno) throws SQLException {
        ControladorBaseDatosA cp2 = new ControladorBaseDatosA();


        Statement ps = cp2.getConexion().createStatement();
        ResultSet rs = ps.executeQuery("SELECT * FROM turno");
        if (rs.next()) {

            listaTurno.add(new Turno(rs.getInt("idTurno"), rs.getDate("fecha"), rs.getString("nombre"),
                    rs.getString("apellido"), rs.getInt("dni"), rs.getInt("edad"),
                    rs.getInt("telefono"), rs.getString("direccion"), rs.getString("especialidad"),
                    rs.getString("horaTurno"), rs.getString("horaSalidaTurno"), rs.getFloat("importe")));

        }
        String outputFile = "C:/Users/Oscar/Desktop/ArchivoTurnos.csv";
        boolean siYaExiste = new File(outputFile).exists();

        if (siYaExiste) {
            File ArchivoTurnos = new File(outputFile);
            ArchivoTurnos.delete();
        }

        try {

            CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
            
            csvOutput.write("idTurno");
            csvOutput.write("Fecha");
            csvOutput.write("Nombre");
            csvOutput.write("Apellido");
            csvOutput.write("Dni");
            csvOutput.write("Edad");
            csvOutput.write("Telefono");
            csvOutput.write("Direccion");
            csvOutput.write("Especialidad");
            csvOutput.write("HoraTurno");
            csvOutput.write("HoraSalidaTurno");
            csvOutput.write("Importe");
            csvOutput.endRecord();

            for (Turno t : listaTurno) {

                csvOutput.write(Integer.toString(t.getIdTurno()));
                csvOutput.write(t.getFecha().toString());
                csvOutput.write(t.getNombre());
                csvOutput.write(t.getApellido());
                csvOutput.write(Integer.toString(t.getDni()));
                csvOutput.write(Integer.toString(t.getEdad()));
                csvOutput.write(Integer.toString(t.getTelefono()));
                csvOutput.write(t.getDireccion());
                csvOutput.write(t.getEspecialidad());
                csvOutput.write(t.getHoraTurno());
                csvOutput.write(t.getHoraSalidaTurno());
                csvOutput.write(Float.toString(t.getImporte()));

                csvOutput.endRecord();
            }

            csvOutput.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
