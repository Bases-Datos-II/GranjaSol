package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import modelo.EspecieAnimal;
import modelo.TipoAnimal;

public class FormInicialController implements Initializable{
	@FXML private ComboBox cmbFamiliaAnimal;
	@FXML private ComboBox<TipoAnimal> cmbTipoAnimal;
	@FXML private ComboBox<EspecieAnimal> cmbEspecieAnimal;

	private ObservableList<TipoAnimal> listaTipoAnimal;
	private ObservableList<EspecieAnimal> listaEspecieAnimal;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		cmbFamiliaAnimal.getItems().addAll("Bovinos","Aves","Rumiante","Porcino","Ovino","Caprino","Otro");
		cmbTipoAnimal.setItems(listaTipoAnimal);
		cmbEspecieAnimal.setItems(listaEspecieAnimal);
	}


}
