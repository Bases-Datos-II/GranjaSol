package aplication;

import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import modelo.Animal;
import modelo.EspecieAnimal;
import modelo.TipoAnimal;
import utilidades.conexion;

public class FormInicialController implements Initializable{
	//HacEr conexión con la BD
	private conexion Conexion;
	private String tipoAnimal;
	int familia;

	//COLUMNAS TABLEVIEW
	@FXML private TableColumn<Animal,String> clmncodigoAnimal;
	@FXML private TableColumn<Animal,EspecieAnimal> clmnnombreEspecieAnimal;
	@FXML private TableColumn<Animal,Date> clmnfechaNacimiento;
	@FXML private TableColumn<Animal,String> clmnsexo;
	@FXML private TableColumn<Animal,Number> clmnnecesidadNutri;
	@FXML private TableColumn<Animal,Number> clmncoste;

	//Componentes GUI
	@FXML private ComboBox<String> cmbFamiliaAnimal;
	@FXML private ComboBox<TipoAnimal> cmbTipoAnimal;
	@FXML private ComboBox<EspecieAnimal> cmbEspecieAnimal;
	@FXML private TableView<Animal> tblViewAnimales;

	//Colecciones a Utilizar
	private ObservableList<TipoAnimal> listaTipoAnimal;
	private ObservableList<EspecieAnimal> listaEspecieAnimal;
	private ObservableList<Animal> listaAnimal;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Conexion = new conexion();
		Conexion.establecerConexion();

		//Inicializacion de Listas
		listaTipoAnimal = FXCollections.observableArrayList();
		listaEspecieAnimal = FXCollections.observableArrayList();
		listaAnimal = FXCollections.observableArrayList();

		//Enlance entre ComboBox y listas
		cmbTipoAnimal.setItems(listaTipoAnimal);
		cmbEspecieAnimal.setItems(listaEspecieAnimal);
		tblViewAnimales.setItems(listaAnimal);

		//Enlazar Columnas con Atributos
		clmncodigoAnimal.setCellValueFactory(new PropertyValueFactory<Animal,String>("codigoAnimal"));
		clmnnombreEspecieAnimal.setCellValueFactory(new PropertyValueFactory<Animal,EspecieAnimal>("codigoEspecieAnimal"));
		clmnfechaNacimiento.setCellValueFactory(new PropertyValueFactory<Animal,Date>("fechaNacimiento"));
		clmnsexo.setCellValueFactory(new PropertyValueFactory<Animal,String>("sexo"));
		clmnnecesidadNutri.setCellValueFactory(new PropertyValueFactory<Animal,Number>("necesidadNutri"));
		clmncoste.setCellValueFactory(new PropertyValueFactory<Animal,Number>("coste"));


		//Llenado de ComboBox
		cmbFamiliaAnimal.getItems().addAll("Bovinos","Aves","Rumiante","Porcino","Ovino","Caprino");
		Animal.llenarAnimal(Conexion.getConexion(), listaAnimal);
		selecFamilia();
		/*TipoAnimal.llenarTipoAnimal(Conexion.getConexion(), listaTipoAnimal, selecFamilia());*/
		/*gestionarEventos();*/

		Conexion.cerrarConexion();


	}

	@FXML private  Button btnboton;

	@FXML Scene inicioAnimal;

	public  void cambiarScene(Stage primaryStage){

	    btnboton.setOnAction(e -> primaryStage.setScene(inicioAnimal));
	}

	@FXML public void selecFamilia(){

		familia = cmbFamiliaAnimal.getSelectionModel().getSelectedIndex();
		listaTipoAnimal.clear();
		/*
		listaEspecieAnimal.clear();
		*/
		Conexion.establecerConexion();
		/*
		listaEspecieAnimal.clear();
		*/
		TipoAnimal.llenarTipoAnimal(Conexion.getConexion(), listaTipoAnimal, familia);


		System.out.print(familia);
	}

	@FXML public void selecTipoAnimal(){
		listaEspecieAnimal.clear();

		try{
			Conexion.establecerConexion();
		tipoAnimal = cmbTipoAnimal.getValue().getNombreTipo();

		EspecieAnimal.llenarEspecieAnimal(Conexion.getConexion(), listaEspecieAnimal, familia, tipoAnimal);
		//LISTO dime
		Conexion.cerrarConexion();
		}catch(Exception e){
			System.out.println("Seleccione un tipo de animal " + e);
		}
		System.out.println(tipoAnimal);
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
