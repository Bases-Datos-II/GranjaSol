package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.EspecieAnimal;
import modelo.TipoAnimal;
import utilidades.conexion;

public class FormInicialController implements Initializable{
	//HacEr conexión con la BD
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
		selecFamilia();
		/*TipoAnimal.llenarTipoAnimal(Conexion.getConexion(), listaTipoAnimal, selecFamilia());*/
		/*gestionarEventos();*/

		Conexion.cerrarConexion();


	}

	@FXML public void selecFamilia(){
		int familia;
		Conexion.establecerConexion();
		familia = cmbFamiliaAnimal.getSelectionModel().getSelectedIndex();
		listaTipoAnimal.clear();
		TipoAnimal.llenarTipoAnimal(Conexion.getConexion(), listaTipoAnimal, familia);
		Conexion.cerrarConexion();
		System.out.print(familia);
	}

	/*public void gestionarEventos(){

		cmbFamiliaAnimal.getSelectionModel().selectedIndexProperty().addListener(
				new ChangeListener() {

					@Override
					public void changed(ObservableValue arg0, Object arg1, Object arg2) {
						System.out.println("Se selecciono elemento");

					}

		});
		}*/

}
