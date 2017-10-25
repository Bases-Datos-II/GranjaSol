package aplication;

import java.net.URL;
import java.sql.Date;
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
import modelo.Animal;
import modelo.Dieta;
import utilidades.conexion;

public class FormDietaController implements Initializable {
	//complementos columna
		@FXML private TableColumn<Dieta, String> clmNombre;
		@FXML private TableColumn<Dieta, Date> clmfechaCreacion;
		@FXML private TableColumn<Dieta, Integer> clmPorciones;
		@FXML private TableColumn<Dieta, String> clmDescripcion;
		@FXML private TableColumn<Dieta, Integer> clmNutrientes;
        //@FXML private TableColumn<Dieta, Integer> clmnutri;

	//complementos gui
	@FXML private ComboBox <Alimentos> cmbAlimentos;
	@FXML private ComboBox <Animal> cmbAnimal;
	@FXML private TableView <Dieta> tvDieta;

	//colecciones
	private ObservableList<Alimentos> listAlimentos;
	//private ObservableList<Animal> listAnimales;
	private ObservableList<Dieta> listDieta;

	private conexion Conexion;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		Conexion = new conexion();
		Conexion.establecerConexion();

		//Inicializar listas
		listAlimentos = FXCollections.observableArrayList();
		//listA = FXCollections.observableArrayList();
		listDieta = FXCollections.observableArrayList();

		//llenar listas
		Alimentos.CargarAlimentos(Conexion.getConexion(), listAlimentos);
    	Dieta.CargarDietas(Conexion.getConexion(), listDieta);

		//enlazer combobox
		cmbAlimentos.setItems(listAlimentos);
		tvDieta.setItems(listDieta);

		//enlazar columnas
		clmNombre.setCellValueFactory(new PropertyValueFactory<Dieta, String>("nombreDieta"));
		clmfechaCreacion.setCellValueFactory(new PropertyValueFactory<Dieta, Date>("fechaCreacion"));
		clmPorciones.setCellValueFactory(new PropertyValueFactory<Dieta, Integer>("porciones"));
		clmDescripcion.setCellValueFactory(new PropertyValueFactory<Dieta, String>("recomendaciones"));
		clmNutrientes.setCellValueFactory(new PropertyValueFactory<Dieta, Integer>("cantnutri"));
        //clmnutri.setCellValueFactory(new PropertyValueFactory<Dieta, Integer>("Cantidadnut"));

		Conexion.cerrarConexion();


	}
}
