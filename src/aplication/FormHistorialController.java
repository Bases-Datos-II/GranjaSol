package aplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Animal;
import modelo.Dieta;
import modelo.Historial;
import utilidades.conexion;

public class FormHistorialController implements Initializable{
	//Columnas
	@FXML private TableColumn<Historial, Integer> clmCodigo;
	@FXML private TableColumn<Historial, Dieta> clmDieta;
	@FXML private TableColumn<Historial, Animal> clmCodAnimal;
	@FXML private TableColumn<Historial, Date> clmFechaIni;
	@FXML private TableColumn<Historial, Date> clmFechaFin;
	//Listando componentes de la GUI
	@FXML private DatePicker dtpkrFechaIni;
	@FXML private DatePicker dtpkrFechaFin;
	@FXML private Button btnNuevo;
	@FXML private Button btnGuardar;
	@FXML private Button btnActualizar;
	@FXML private Button btnEliminar;
	
	@FXML private ComboBox<Animal> cmbCodAnimal;
	@FXML private ComboBox<Dieta> cmbCodDieta;
	@FXML private TableView<Historial> tblViewHistorial;
	
	//Colecciones
	private ObservableList<Animal> listaAnimal;
	private ObservableList<Dieta> listaDieta;
	private ObservableList<Historial> listaH;
	
	private conexion Acces;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Acces= new conexion();
		Acces.establecerConexion();
		//Inicializar Listas
		
		listaAnimal= FXCollections.observableArrayList();
		listaDieta= FXCollections.observableArrayList();
		listaH= FXCollections.observableArrayList();
		//Llenar Listas
		
		Animal.llenarAnimal(Acces.getConexion(), listaAnimal);
		Dieta.CargarDietas(Acces.getConexion(), listaDieta);
		Historial.llenarInformacion(Acces.getConexion(), listaH);
		
		//Enlazar listas con Componentes
		tblViewHistorial.setItems(listaH);
		cmbCodAnimal.setItems(listaAnimal);
		cmbCodDieta.setItems(listaDieta);
		//Elnzar Columnas
		clmCodigo.setCellValueFactory(new PropertyValueFactory<Historial, Integer>("codigoHistorial"));
		clmDieta.setCellValueFactory(new PropertyValueFactory<Historial, Dieta>("codigoDieta"));
		clmCodAnimal.setCellValueFactory(new PropertyValueFactory<Historial, Animal>("codigoAnimal"));
		clmFechaIni.setCellValueFactory(new PropertyValueFactory<Historial, Date>("fechaInicio"));
		clmFechaFin.setCellValueFactory(new PropertyValueFactory<Historial, Date>("fechaFin"));
		gestionarEventos();
		Acces.cerrarConexion();
		
		
	}
	
	public void gestionarEventos() {
		tblViewHistorial.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Historial>() {

					@Override
					public void changed(ObservableValue<? extends Historial> observable, Historial valorAnterior,
							Historial valorSeleccionado) {
						cmbCodAnimal.setValue(valorSeleccionado.getCodigoAnimal());;
						cmbCodDieta.setValue(valorSeleccionado.getCodigoDieta());
						dtpkrFechaIni.setValue(valorSeleccionado.getFechaInicio().toLocalDate());
						dtpkrFechaFin.setValue(valorSeleccionado.getFechaFin().toLocalDate());
						
					}
					
		}
				);
	}
	

}
