package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alimentos;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormAlimentosController implements Initializable{
	private int codA;
	
	//Listando Columnas del TableView Alimentos
	@FXML private TableColumn<Alimentos, Integer> clmnCodigo;
	@FXML private TableColumn<Alimentos, String> clmnNombre;
	@FXML private TableColumn<Alimentos, Integer> clmnCalorias;
	//Listado Columnas tblNutrientes
	@FXML private TableColumn<Nutrientes, String> clmnNombreN;
	@FXML private TableColumn<Nutrientes, String> clmnTipoN;
	//Listando Componentes GUI
	@FXML private TextField txtCodAlimento;
	@FXML private TextField txtNombre;
	@FXML private TextField txtCalorias;
	
	@FXML private Button btnNuevo;
	@FXML private Button btnAgregar;
	@FXML private Button btnActualizar;
	@FXML private Button btnEliminar;
	@FXML private Button btnVolver;
	
	@FXML private ComboBox<Nutrientes> cmbNutrientes;
	@FXML private TableView<Alimentos> tblViewAlimentos;
	@FXML private TableView<Nutrientes> tblViewNutrientes;
	
	//Colecciones
	private ObservableList<Nutrientes> listaNutrientes;
	private ObservableList<Alimentos> listaAlimentos;
	
	private conexion Acceso;
	
	@Override
	public void initialize(URL location, ResourceBundle resourses) {
		Acceso= new conexion();
		Acceso.establecerConexion();
		//Inicializar Listas
		listaNutrientes= FXCollections.observableArrayList();
		listaAlimentos= FXCollections.observableArrayList();
		//Llenar Listas
		Alimentos.CargarAlimentos(Acceso.getConexion(), listaAlimentos);
		Nutrientes.llenarInformacion(Acceso.getConexion(), listaNutrientes);
		//Fusion de Componentes con Listas
		cmbNutrientes.setItems(listaNutrientes);
		tblViewAlimentos.setItems(listaAlimentos);
		tblViewNutrientes.setItems(listaNutrientes);
		//Enlazar Columnas
		clmnCodigo.setCellValueFactory(new PropertyValueFactory<Alimentos, Integer>("codigoAlimento"));
		clmnNombre.setCellValueFactory(new PropertyValueFactory<Alimentos, String>("nombreAlimento"));
		clmnCalorias.setCellValueFactory(new PropertyValueFactory<Alimentos, Integer>("calorias"));
		//Enlazar Columnas de Nutrientes
		clmnNombreN.setCellValueFactory(new PropertyValueFactory<Nutrientes, String>("nombreNutriente"));
		clmnTipoN.setCellValueFactory(new PropertyValueFactory<Nutrientes, String>("tipoNutriente"));
		
		gestionarEventos();
		//Nunca olvidar cerrar conexiones
		Acceso.cerrarConexion();
	}
	public void gestionarEventos() {
		tblViewAlimentos.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Alimentos>() {

					@Override
					public void changed(ObservableValue<? extends Alimentos> observable, Alimentos valorAnterior,
							Alimentos valorSeleccionado) {
						if(valorSeleccionado!=null) {
						txtCodAlimento.setText(String.valueOf(valorSeleccionado.getCodigoAlimento()));
						txtNombre.setText(valorSeleccionado.getNombreAlimento());
						txtCalorias.setText(String.valueOf(valorSeleccionado.getCalorias()));
						listaNutrientes.clear();
						codA=(valorSeleccionado.getCodigoAlimento());
						Acceso.establecerConexion();
						Nutrientes.verNutrientes(Acceso.getConexion(), listaNutrientes, codA);
						Acceso.cerrarConexion();
						btnAgregar.setDisable(true);
						btnActualizar.setDisable(false);
						btnEliminar.setDisable(false);
						}
					}
		});
	}
	public void verNutrientes() {
		
	}
	
	@FXML
	public void guardarRegistro() {
		//Crear una nueva instancia del tipo Alimento
		Alimentos a= new Alimentos(
				0, 
				txtNombre.getText(), 
				Integer.valueOf(txtCalorias.getText()) 
				);
		//Llamar al metodo guardar registro de la clase Alimento
		Acceso.establecerConexion();
		int resultado = a.guardarRegisto(Acceso.getConexion());
		Acceso.cerrarConexion();
		if(resultado == 1) {
			listaAlimentos.add(a);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Agregado");
			mensaje.setContentText("El registro ha sido agregado exitosamente");
			mensaje.setHeaderText("Resultado");
			mensaje.show();
		}
		
	}
	@FXML
	public void actualizarRegistro() {
		//Crear una nueva instancia del tipo Alimento
		Alimentos a= new Alimentos(
				Integer.valueOf(txtCodAlimento.getText()), 
				txtNombre.getText(), 
				Integer.valueOf(txtCalorias.getText()) 
				);
		//Llamar al metodo guardar registro de la clase Historial
		Acceso.establecerConexion();
		int resultado = a.actualizarRegistro(Acceso.getConexion());
		Acceso.cerrarConexion();
		if(resultado == 1) {
			listaAlimentos.set(tblViewAlimentos.getSelectionModel().getSelectedIndex(),a);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Actualizado");
			mensaje.setContentText("El registro ha sido Actualizado exitosamente");
			mensaje.setHeaderText("Resultado");
			mensaje.show();
		}
		
	}
	
	@FXML
	public void eliminarRegistro() {
		//Llamar al metodo guardar registro de la clase Historial
		Acceso.establecerConexion();
		int resultado = tblViewAlimentos.getSelectionModel().getSelectedItem().eliminarRegistro(Acceso.getConexion());
		Acceso.cerrarConexion();
		if(resultado == 1) {
			listaAlimentos.remove(tblViewAlimentos.getSelectionModel().getSelectedIndex());
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Eliminado");
			mensaje.setContentText("El registro ha sido Eliminado exitosamente");
			mensaje.setHeaderText("Resultado");
			mensaje.show();
		}
		
	}
	
	@FXML
	public void limpiarComponentes() {
		txtCodAlimento.setText(null);
		txtNombre.setText(null);
		txtCalorias.setText(null);
		cmbNutrientes.setValue(null);
		
		btnAgregar.setDisable(false);
		btnActualizar.setDisable(true);
		btnEliminar.setDisable(true);
	}
}
