package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.Animal;
import modelo.Dieta;
import modelo.Historial;
import utilidades.conexion;

public class FormHistorialController implements Initializable{
	//Listando componentes de la interfaz
	@FXML private ComboBox<Animal> cmbCodAnimal;
	@FXML private ComboBox<Dieta> cmbCodDieta;
	
	//Colecciones
	private ObservableList<Animal> listaAnimal;
	private ObservableList<Dieta> listaDieta;
	
	private conexion Acces;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Acces= new conexion();
		Acces.establecerConexion();
		listaAnimal= FXCollections.observableArrayList();
		listaDieta= FXCollections.observableArrayList();
		Historial.llenarInformacion(Acces.getConexion(), listaAnimal, listaDieta);
		cmbCodAnimal.setItems(listaAnimal);
		cmbCodDieta.setItems(listaDieta);
		
		Acces.cerrarConexion();
		
		
	}
	

}
