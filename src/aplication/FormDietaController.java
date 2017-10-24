package aplication;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import modelo.Alimentos;
import modelo.Animal;
import modelo.Dieta;
import utilidades.conexion;

public class FormDietaController implements Initializable {
	//complementos gui
	@FXML private ComboBox <Alimentos> cmbAlimentos;
	@FXML private ComboBox <Animal> cmbAnimal;
	@FXML private TableView <Dieta> cmbDieta;
	
	//colecciones
	private ObservableList<Alimentos> listAlimentos;
	private ObservableList<Animal> listAnimales;
	private ObservableList<Dieta> listDieta;
	
	private conexion Conexion;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		Conexion = new conexion();
		Conexion.establecerConexion();
		
		//Inicializar listas
		listAlimentos = FXCollections.observableArrayList();
		listAnimales = FXCollections.observableArrayList();
		listDieta = FXCollections.observableArrayList();
		
		//llenar listas
		Alimentos.CargarAlimentos(Conexion.getConexion(), listAlimentos);
		//Animal(Conexion.getConexion()), listAnimal);		
		//Alumno.LlenarinformacionAlumno(conexion.getConnetion(), listaAlumnos);	
	}
}
