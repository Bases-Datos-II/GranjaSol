package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormAlimentosController implements Initializable{
	//Listando Componentes de la Interfaz
	@FXML private ComboBox<Nutrientes> cmbNutrientes;
	
	//Colecciones
	private ObservableList<Nutrientes> listaNutrientes;
	
	private conexion Acceso;
	
	@Override
	public void initialize(URL location, ResourceBundle resourses) {
		Acceso= new conexion();
		Acceso.establecerConexion();
		listaNutrientes= FXCollections.observableArrayList();
		Nutrientes.llenarInformacion(Acceso.getConexion(), listaNutrientes);
		cmbNutrientes.setItems(listaNutrientes);
		
		Acceso.cerrarConexion();
	}
}
