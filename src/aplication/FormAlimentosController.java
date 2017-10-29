package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Alimentos;
import modelo.Nutrientes;
import utilidades.conexion;

public class FormAlimentosController implements Initializable{
	//Listando Columnas del TableView
	@FXML private TableColumn<Alimentos, Integer> clmnCodigo;
	@FXML private TableColumn<Alimentos, String> clmnNombre;
	@FXML private TableColumn<Alimentos, Integer> clmnCalorias;
	//Listando Componentes de la Interfaz
	@FXML private ComboBox<Nutrientes> cmbNutrientes;
	@FXML private TableView<Alimentos> tblViewAlimentos;
	
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
		//Enlazar Columnas
		clmnCodigo.setCellValueFactory(new PropertyValueFactory<Alimentos, Integer>("codigoAlimento"));
		clmnNombre.setCellValueFactory(new PropertyValueFactory<Alimentos, String>("nombreAlimento"));
		clmnCalorias.setCellValueFactory(new PropertyValueFactory<Alimentos, Integer>("calorias"));
		//Nunca olvidar cerrar conexiones
		Acceso.cerrarConexion();
	}
}
