package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.EspecieAnimal;
import modelo.TipoAnimal;
import utilidades.conexion;

public class FormInicialController implements Initializable{
	//Hacr conexión con la BD
	private conexion Conexion;

	//Componentes GUI
	@FXML private ComboBox cmbFamiliaAnimal;
	@FXML private ComboBox<TipoAnimal> cmbTipoAnimal;
	@FXML private ComboBox<EspecieAnimal> cmbEspecieAnimal;

	//Colecciones a Utilizar
	private ObservableList<TipoAnimal> listaTipoAnimal;
	private ObservableList<EspecieAnimal> listaEspecieAnimal;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		Conexion = new conexion();
		Conexion.establecerConexion();

		//Inicializacion de Listas
		listaTipoAnimal = FXCollections.observableArrayList();

		//Enlance entre ComboBox y listas
		cmbTipoAnimal.setItems(listaTipoAnimal);
		cmbEspecieAnimal.setItems(listaEspecieAnimal);


		//Llenado de ComboBox
		cmbFamiliaAnimal.getItems().addAll("Bovinos","Aves","Rumiante","Porcino","Ovino","Caprino");
		listaTipoAnimal.add(new TipoAnimal(1,"Vaca") );
	}



}
