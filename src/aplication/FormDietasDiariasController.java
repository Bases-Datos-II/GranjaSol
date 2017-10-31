package aplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import modelo.DietaPorDia;
import modelo.Historial;

public class FormDietasDiariasController implements Initializable{
	private ObservableList<DietaPorDia> informacion;

	@FXML private TableView<DietaPorDia> tblInformacion;

	@FXML private TableColumn<DietaPorDia,Number> clmncodigoDiario;
	@FXML private TableColumn<DietaPorDia,Historial> clmncodigoHistorial;
	@FXML private TableColumn<DietaPorDia,String> clmncomentarioDiario;
	@FXML private TableColumn<DietaPorDia,Date> clmnfechaDiario;
	@FXML private TableColumn<DietaPorDia,String> clmncumplimientoDiario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
