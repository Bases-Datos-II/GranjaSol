package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormNutrientesController implements Initializable{
	//Columnas
	@FXML private TableColumn<Nutrientes, Integer> clmCodigo;
	@FXML private TableColumn<Nutrientes, String> clmNombre;
	@FXML private TableColumn<Nutrientes, String> clmTipo;
	
	@FXML private TableView<Nutrientes> tblViewNutrientes;
	
	private ObservableList<Nutrientes> listaN;
	
	private conexion Acces;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Acces= new conexion();
		Acces.establecerConexion();
		//Inicializar Coleccion
		listaN= FXCollections.observableArrayList();
		//Llenar Lista
		Nutrientes.llenarInformacion(Acces.getConexion(), listaN);
		//Enlazar a Componente
		tblViewNutrientes.setItems(listaN);
		//Enlazar Columnas
		clmCodigo.setCellValueFactory(new PropertyValueFactory<Nutrientes, Integer>("codigoNutriente"));
		clmNombre.setCellValueFactory(new PropertyValueFactory<Nutrientes, String>("nombreNutriente"));
		clmTipo.setCellValueFactory(new PropertyValueFactory<Nutrientes, String>("tipoNutriente"));
		//Siempre CIERRA LA CONEXION
		Acces.cerrarConexion();
		
		
	}

}
