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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
		@FXML private TableColumn<Dieta, Number> clmNutrientes;


	//complementos gui
		@FXML private TextField txtNombre;
		@FXML private TextField txtPorciones;
		@FXML private TextField txtDescripcion;
		@FXML private DatePicker dpkrFecha;
		@FXML private TextField txtCalorias;
		@FXML private Button btnGuardar;
		@FXML private Button btnAgregar;
		@FXML private Button btnAsignar;
		@FXML private Button btnNuevo;

		@FXML private ComboBox <Alimentos> cmbAlimentos;
		@FXML private ComboBox <Animal> cmbAnimal;
		@FXML private TableView <Dieta> tvDieta;

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
    	Dieta.CargarDietas(Conexion.getConexion(), listDieta);
    	Animal.llenarAnimal(Conexion.getConexion(), listAnimales);

		//enlazer combobox
		cmbAlimentos.setItems(listAlimentos);
		tvDieta.setItems(listDieta);
		cmbAnimal.setItems(listAnimales);

		//enlazar columnas
		clmNombre.setCellValueFactory(new PropertyValueFactory<Dieta, String>("nombre"));
		clmfechaCreacion.setCellValueFactory(new PropertyValueFactory<Dieta, Date>("fechaCreacion"));
		clmPorciones.setCellValueFactory(new PropertyValueFactory<Dieta, Integer>("porciones"));
		clmDescripcion.setCellValueFactory(new PropertyValueFactory<Dieta, String>("recomendaciones"));
		clmNutrientes.setCellValueFactory(new PropertyValueFactory<Dieta, Number>("cantidadnutrientes"));

		GestionarEventos();


		Conexion.cerrarConexion();
	}


	@FXML
	public void guardarregistro()
	{
		Dieta D = new Dieta(0,txtNombre.getText(),
				            Date.valueOf(dpkrFecha.getValue()),
				            Integer.valueOf(txtPorciones.getText()),
				            txtDescripcion.getText(),0);

		Conexion.establecerConexion();
		int resultado =  D.GuardarDieta(Conexion.getConexion());
		Conexion.cerrarConexion();
		if(resultado == 1)
		{
			listDieta.add(D);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("REGISTRO INGRESADO");
			mensaje.setContentText("REGISTRO INGRESADO EXITOSAMENTE");
			mensaje.setHeaderText("RESULTADO");
			mensaje.show();
		}
		txtNombre.setDisable(true);
		txtPorciones.setDisable(true);
		txtDescripcion.setDisable(true);
		dpkrFecha.setDisable(true);

		btnGuardar.setDisable(true);

		btnAgregar.setDisable(false);
		cmbAlimentos.setDisable(false);


	}

	public void GestionarEventos()
	{
		tvDieta.getSelectionModel().selectedItemProperty().addListener(
				           new ChangeListener<Dieta>()
				           {
							 @Override
							 public void changed(ObservableValue<? extends Dieta> arg0,
									 Dieta ValorAnterior, Dieta ValorSelect)
							 {
								 txtNombre.setText(ValorSelect.getNombre());
								 dpkrFecha.setValue(ValorSelect.getFechaCreacion().toLocalDate());
								 txtPorciones.setText(String.valueOf(ValorSelect.getPorciones()));
								 txtDescripcion.setText(ValorSelect.getRecomedaciones());
								 txtCalorias.setText(String.valueOf(ValorSelect.getCantidadNutrientes()));
							 }
				           } );
	}
	@FXML
	public void HabilitarNuevo()
	{
		txtNombre.setDisable(false);
		txtPorciones.setDisable(false);
		txtDescripcion.setDisable(false);
		dpkrFecha.setDisable(false);

		btnGuardar.setDisable(false);

		txtNombre.setText(null);
		txtPorciones.setText(null);
		txtDescripcion.setText(null);
		dpkrFecha.setValue(null);
		txtCalorias.setText(null);
		btnNuevo.setDisable(true);

	}

	@FXML
	public void HabilitarGuardar()
	{
		txtNombre.setDisable(true);
		txtPorciones.setDisable(true);
		txtDescripcion.setDisable(true);
		dpkrFecha.setDisable(true);

		btnGuardar.setDisable(true);

		btnAgregar.setDisable(false);
		cmbAlimentos.setDisable(false);
	}

	@FXML
	public void HabilitarAgregar()
	{
		cmbAnimal.setDisable(false);
		btnAsignar.setDisable(false);
	}

	@FXML
	public void HabilitarAsignar()
	{
		txtNombre.setDisable(true);
		txtPorciones.setDisable(true);
		txtDescripcion.setDisable(true);
		dpkrFecha.setDisable(true);
		txtCalorias.setDisable(true);
		btnGuardar.setDisable(true);

		btnAgregar.setDisable(true);
		cmbAlimentos.setDisable(true);
		cmbAnimal.setDisable(true);
		btnAsignar.setDisable(true);
		btnNuevo.setDisable(false);

	}

}
