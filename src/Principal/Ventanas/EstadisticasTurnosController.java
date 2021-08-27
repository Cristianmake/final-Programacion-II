package Principal.Ventanas;

import Principal.BD.ControladorBaseDatosA;
import Principal.Entidades.Turno;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

/**
 * FXML Controller class
 * @author KbServiCell
 */
public class EstadisticasTurnosController implements Initializable {

    @FXML
    private LineChart<String, Integer> grafico;
    @FXML
    private CategoryAxis xAxis;

    private ObservableList<String> mesNombre = FXCollections.observableArrayList();
    private Turno turno;
    
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ControladorBaseDatosA a1 = new ControladorBaseDatosA();
         // Obtengo el array con los nombres de los meses
        String[] meses = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
        // Los convierte en una lista
        mesNombre.addAll(Arrays.asList(meses));
        // Asigna los nombres al axis x
        xAxis.setCategories(mesNombre);
        
        XYChart.Series series = new XYChart.Series();
 
      
    }    
    
    public void setUserData(List<Turno> turno) {
        // Calcula las fechas según el mes
        int[] contador = new int[12];
        for (Turno t : turno) {
            int mes = t.getFecha().getMonth() ;
            contador[mes]++;
        }
        XYChart.Series<String, Integer> series = new XYChart.Series<>();
        for (int i = 0; i < contador.length; i++) {
            series.getData().add(new XYChart.Data<>(mesNombre.get(i), contador[i]));
        }
        
        grafico.getData().add(series);
    }
   
    //Apretar botón cerrar
    @FXML
    private void apretarCerrar(ActionEvent event) throws IOException{
        ((Node) (event.getSource())).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminPrincipal.fxml"));
        Scene scene = new Scene(root);
        Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        appStage.setScene(scene);
        appStage.toFront();
        appStage.show();
    }
    
}

