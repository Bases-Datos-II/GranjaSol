package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormNutrientesController implements Initializable{
	//Columnas
	@FXML private TableColumn<Nutrientes, Integer> clmCodigo;
	@FXML private TableColumn<Nutrientes, String> clmNombre;
	@FXML private TableColumn<Nutrientes, String> clmTipo;
	//Componente GUI
	@FXML private TextField txtCodNutriente;
	@FXML private TextField txtNombre;
	@FXML private TextField txtTipo;
	
	@FXML private Button btnNuevo;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;
	@FXML private Button btnEliminar;
	@FXML private Button btnVolver;
	
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
		
		gestionarEventos();
		//Siempre CIERRA LA CONEXION
		Acces.cerrarConexion();
	}
	public void gestionarEventos() {
		tblViewNutrientes.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Nutrientes>() {

					@Override
					public void changed(ObservableValue<? extends Nutrientes> observable, Nutrientes valorAnterior,
							Nutrientes valorSeleccionado) {
						txtCodNutriente.setText(String.valueOf(valorSeleccionado.getCodigoNutriente()));
						txtNombre.setText(valorSeleccionado.getNombreNutriente());
						txtTipo.setText(valorSeleccionado.getTipoNutriente());
						
						btnGuardar.setDisable(true);
						btnActualizar.setDisable(false);
						btnEliminar.setDisable(false);
						
					}
		});
	}
	@FXML
	public void limpiarComponentes() {
		txtCodNutriente.setText(null);
		txtNombre.setText(null);
		txtTipo.setText(null);
		
		btnGuardar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
	}

}
