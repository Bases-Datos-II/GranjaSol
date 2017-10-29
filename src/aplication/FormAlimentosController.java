package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alimentos;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormAlimentosController implements Initializable{
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
						txtCodAlimento.setText(String.valueOf(valorSeleccionado.getCodigoAlimento()));
						txtNombre.setText(valorSeleccionado.getNombreAlimento());
						txtCalorias.setText(String.valueOf(valorSeleccionado.getCalorias()));
					}
		});
	}
}
