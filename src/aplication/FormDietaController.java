package aplication;

import java.awt.Component;
import java.net.URL;
import java.sql.Date;
//import java.sql.ResultSet;
//import java.sql.Statement;
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
import modelo.AlimentosPorDieta;
import modelo.Animal;
import modelo.Dieta;
import modelo.Historial;
import utilidades.conexion;

public class FormDietaController implements Initializable {

	//MAIN
	private Main main;
	public Component frame;
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
		@FXML private DatePicker dpkrFechaFinal;
		@FXML private TextField txtCalorias;
		@FXML private Button btnGuardar;
		@FXML private Button btnAgregar;
		@FXML private Button btnAsignar;
		@FXML private Button btnNuevo;
		@FXML private Button btnAnimal;

		@FXML private ComboBox <Alimentos> cmbAlimentos;
		@FXML private ComboBox <Animal> cmbAnimal;
		@FXML private TableView <Dieta> tvDieta;


	//colecciones
	private ObservableList<Alimentos> listAlimentos;
	private ObservableList<Animal> listAnimales;
	private ObservableList<Dieta> listDieta;

	private conexion Conexion;
	private Dieta D;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1)
	{
		Conexion = new conexion();
		Conexion.establecerConexion();

		//Inicializar listas
		listAlimentos = FXCollections.observableArrayList();

		listDieta = FXCollections.observableArrayList();

		//llenar listas
		Alimentos.CargarAlimentos(Conexion.getConexion(), listAlimentos);
    	Dieta.CargarDietas(Conexion.getConexion(), listDieta);


		//enlazer combobox
		cmbAlimentos.setItems(listAlimentos);
		tvDieta.setItems(listDieta);

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
	public void habilitarAnimal()
	{
		Conexion = new conexion();
		Conexion.establecerConexion();
		listAnimales = FXCollections.observableArrayList();
		Animal.filtroanimal(Conexion.getConexion(), listAnimales);
		cmbAnimal.setItems(listAnimales);
		Conexion.cerrarConexion();
		cmbAlimentos.setDisable(true);
		btnAgregar.setDisable(true);
		cmbAnimal.setDisable(false);
		btnAsignar.setDisable(false);
		dpkrFechaFinal.setDisable(false);
	}
	@FXML
    public void guardaralimdieta()
    {
		Conexion.establecerConexion();
		Dieta D = AlimentosPorDieta.traerultimadieta(Conexion.getConexion());
		AlimentosPorDieta A = new AlimentosPorDieta(D,cmbAlimentos.getSelectionModel().getSelectedItem());
		Conexion.cerrarConexion();
		cmbAlimentos.getItems().remove(cmbAlimentos.getSelectionModel().getSelectedIndex());

		Conexion.establecerConexion();
		int result = A.Guardaralimentopordieta(Conexion.getConexion());
		Conexion.cerrarConexion();
		if(result == 1)
		{
			//listDieta.add(D);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("REGISTRO INGRESADO");
			mensaje.setContentText("REGISTRO INGRESADO EXITOSAMENTE");
			mensaje.setHeaderText("RESULTADO");
			mensaje.show();
		}
		this.btnAnimal.setDisable(false);


    }

	@FXML
	public void guardarregistro()
	{
		this.D = new Dieta(0,txtNombre.getText(),
				            Date.valueOf(dpkrFecha.getValue()),
				            Integer.valueOf(txtPorciones.getText()),
				            txtDescripcion.getText(),0);

		Conexion.establecerConexion();
		int resultado =  D.GuardarDieta(Conexion.getConexion());
		Conexion.cerrarConexion();
		if(resultado == 1)
		{
			//listDieta.add(D);
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
		tvDieta.setDisable(true);
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
	public void HabilitarAsignar()
	{
		Conexion = new conexion();
		Conexion.establecerConexion();

		Dieta d = AlimentosPorDieta.traerultimadieta(Conexion.getConexion());
		Historial H = new Historial(0,d,cmbAnimal.getSelectionModel().getSelectedItem(),
				                    Date.valueOf(dpkrFecha.getValue()),
				                    Date.valueOf(dpkrFechaFinal.getValue()));

		int resultado =  H.h_Dieta_nueva(Conexion.getConexion());
		Conexion.cerrarConexion();
		if(resultado == 1)
		{
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("REGISTRO INGRESADO");
			mensaje.setContentText("REGISTRO INGRESADO EXITOSAMENTE");
			mensaje.setHeaderText("RESULTADO");
			mensaje.show();
		}

		Conexion.establecerConexion();
		Dieta ds = AlimentosPorDieta.traerultimadieta(Conexion.getConexion());
		Conexion.cerrarConexion();
		listDieta.add(ds);
		clmNutrientes.setCellValueFactory(new PropertyValueFactory<Dieta, Number>("cantidadnutrientes"));
		btnAgregar.setDisable(true);
		cmbAnimal.setDisable(true);
		btnAsignar.setDisable(true);
		btnAnimal.setDisable(true);
		btnNuevo.setDisable(false);
		tvDieta.setDisable(false);
		dpkrFechaFinal.setDisable(true);
	}

	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}
}
