package aplication;

import java.awt.Component;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Animal;
import modelo.EspecieAnimal;
import modelo.TipoAnimal;
import utilidades.conexion;

public class FormInicialController implements Initializable{
	//MAIN
		private Main main;
		public Component frame;

	//HacEr conexión con la BD
	private conexion Conexion;
	private String tipoAnimal;
	private String genero;
	int codigoEspecie;
	int familia;
	private String familia2;
	private String codigoAnimal;

	//COLUMNAS TABLEVIEW
	@FXML private TableColumn<Animal,String> clmncodigoAnimal;
	@FXML private TableColumn<Animal,EspecieAnimal> clmnnombreEspecieAnimal;
	@FXML private TableColumn<Animal,Date> clmnfechaNacimiento;
	@FXML private TableColumn<Animal,String> clmnsexo;
	@FXML private TableColumn<Animal,Number> clmnnecesidadNutri;
	@FXML private TableColumn<Animal,Number> clmncoste;
	@FXML private TableColumn<Animal,String> clmnTipoAnimal;
	@FXML private TableColumn<Animal,String> clmnFamiliaAnimal;

	//@FXML private TableColumn<EspecieAnimal,String> clmntipoAnima;

	//Componentes GUI
	@FXML private Label lblCodigoAnimal;
	@FXML private TextField txtCoste;
	@FXML private TextField txtRequerimentos;
	@FXML private RadioButton rbtFemenino;
	@FXML private RadioButton rbtMasculino;
	@FXML private DatePicker dtpFecha;
	@FXML private ComboBox<String> cmbFamiliaAnimal;
	@FXML private ComboBox<TipoAnimal> cmbTipoAnimal;
	@FXML private ComboBox<EspecieAnimal> cmbEspecieAnimal;
	@FXML private TableView<Animal> tblViewAnimales;
	//@FXML private TableView<EspecieAnimal> tblViewTipoAnimal;
	@FXML private Button btnActualizar;
	@FXML private Button btnEliminar;
	@FXML private Button btnNuevo;
	@FXML private Button btnGuardar;

	//Colecciones a Utilizar
	private ObservableList<TipoAnimal> listaTipoAnimal;
	private ObservableList<EspecieAnimal> listaEspecieAnimal;
	private ObservableList<Animal> listaAnimal;

	private ObservableList<EspecieAnimal> listaTipoAnimal2;



	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Conexion = new conexion();
		Conexion.establecerConexion();

		//Inicializacion de Listas
		listaTipoAnimal = FXCollections.observableArrayList();
		listaEspecieAnimal = FXCollections.observableArrayList();
		listaAnimal = FXCollections.observableArrayList();

		listaTipoAnimal2 = FXCollections.observableArrayList();

		//Enlance entre ComboBox y listas
		cmbTipoAnimal.setItems(listaTipoAnimal);
		cmbEspecieAnimal.setItems(listaEspecieAnimal);
		tblViewAnimales.setItems(listaAnimal);
		//tblViewTipoAnimal.setItems(listaTipoAnimal2);

		//Enlazar Columnas con Atributos
		clmncodigoAnimal.setCellValueFactory(new PropertyValueFactory<Animal,String>("codigoAnimal"));
		clmnnombreEspecieAnimal.setCellValueFactory(new PropertyValueFactory<Animal,EspecieAnimal>("codigoEspecieAnimal"));
		clmnfechaNacimiento.setCellValueFactory(new PropertyValueFactory<Animal,Date>("fechaNacimiento"));
		clmnsexo.setCellValueFactory(new PropertyValueFactory<Animal,String>("sexo"));
		clmnnecesidadNutri.setCellValueFactory(new PropertyValueFactory<Animal,Number>("necesidadNutri"));
		clmncoste.setCellValueFactory(new PropertyValueFactory<Animal,Number>("coste"));

		clmnTipoAnimal.setCellValueFactory(new PropertyValueFactory<Animal,String>("NombreTipoAnimal"));
		clmnFamiliaAnimal.setCellValueFactory(new PropertyValueFactory<Animal,String>("Caracteristica"));

		//clmntipoAnima.setCellValueFactory(new PropertyValueFactory<EspecieAnimal,String>("codigoTipoAnimal"));


		//Llenado de ComboBox
		cmbFamiliaAnimal.getItems().addAll("BOVINO","AVE","RUMIANTE","PORCINO","OVINO","CAPRINO");

		//Llenado TableView
		Animal.llenarAnimal(Conexion.getConexion(), listaAnimal);

		selecFamilia();



	}



	@FXML public void selecFamilia(){

		familia2 = cmbFamiliaAnimal.getValue();
		System.out.println(familia2);
		//familia = cmbFamiliaAnimal.getSelectionModel().getSelectedIndex();

		/*
		listaEspecieAnimal.clear();
		*/
		Conexion.establecerConexion();
		listaTipoAnimal.clear();
		gestionarEventos();
		/*gestionarEventos2();
		 */
		/*
		listaEspecieAnimal.clear();
		*/
		TipoAnimal.otroTipoAnimal(Conexion.getConexion(), listaTipoAnimal, familia2);


		//System.out.print(familia);
	}

	@FXML public void selecTipoAnimal() throws SQLException{
		listaEspecieAnimal.clear();

		try{
			Conexion.establecerConexion();
		tipoAnimal = cmbTipoAnimal.getValue().getNombreTipo();
		EspecieAnimal.llenarEspecieAnimal(Conexion.getConexion(), listaEspecieAnimal, familia2, tipoAnimal);
		//Conexion.cerrarConexion();
		}catch( Exception a){
			System.out.println("llenar animal"+a);
		}
	}

	@FXML public void especieAnimal(){
		codigoEspecie = cmbEspecieAnimal.getValue().getCodigoEspecie();
		System.out.println(codigoEspecie);
		tblViewAnimales.isFocused();

	}

		public void gestionarEventos(){

			tblViewAnimales.getSelectionModel().selectedItemProperty().addListener(
					new ChangeListener<Animal>(){

						@Override
						public void changed(ObservableValue<? extends Animal>
						arg0,
						Animal ValorAnterior,
						Animal ValorSeleccionado) {
							txtCoste.setText(String.valueOf(ValorSeleccionado.getCoste()));
							txtRequerimentos.setText(String.valueOf(ValorSeleccionado.getNecesidadNutri()));
							if (ValorSeleccionado.getSexo().equals("F")){
								rbtFemenino.setSelected(true);
								rbtMasculino.setSelected(false);
							} else if (ValorSeleccionado.getSexo().equals("M")){
								rbtFemenino.setSelected(false);
								rbtMasculino.setSelected(true);

							}
							dtpFecha.setValue(ValorSeleccionado.getFechaNacimiento().toLocalDate());
							/*cmbTipoAnimal.setValue(ValorSeleccionado.get);*/
							cmbFamiliaAnimal.setValue(ValorSeleccionado.getCaracteristica());
							cmbEspecieAnimal.setValue(ValorSeleccionado.getCodigoEspecieAnimal());
							cmbTipoAnimal.setValue(ValorSeleccionado.getNombreTipoANI());

							lblCodigoAnimal.setText(ValorSeleccionado.getCodigoAnimal());
							codigoAnimal = (ValorSeleccionado.getCodigoAnimal());
							System.out.println(codigoAnimal);
							listaTipoAnimal2.clear();

							btnGuardar.setDisable(true);
							btnActualizar.setDisable(false);
							btnEliminar.setDisable(false);


						}
					}

					);

		}

		@FXML public void limpiarComponentes(){

			cmbFamiliaAnimal.setValue(null);
			cmbTipoAnimal.setValue(null);
			cmbEspecieAnimal.setValue(null);
			dtpFecha.setValue(null);
			rbtFemenino.setDisable(true);
			rbtMasculino.setDisable(true);
			lblCodigoAnimal.setText(null);
			txtCoste.setText(null);
			txtRequerimentos.setText(null);
			btnGuardar.setDisable(false);
			btnActualizar.setDisable(true);
			btnEliminar.setDisable(true);
			listaTipoAnimal2.clear();
		}

		public void limpiarTodo()
		{
			cmbFamiliaAnimal.setValue(null);
			cmbTipoAnimal.setValue(null);
			cmbEspecieAnimal.setValue(null);
			dtpFecha.setValue(null);
			rbtFemenino.setDisable(true);
			rbtMasculino.setDisable(true);
			lblCodigoAnimal.setText(null);
			txtCoste.setText(null);
			txtRequerimentos.setText(null);
			btnGuardar.setDisable(false);
			btnActualizar.setDisable(true);
			btnEliminar.setDisable(true);
			listaTipoAnimal2.clear();
		}

		@FXML public void actualizarAnimal(){
			//Animal a = new Animal(codigoAnimal, codigoEspecie, null, codigoAnimal, codigoEspecie, codigoEspecie);
			Animal.actualizarAnimal(Conexion.getConexion(),
					codigoAnimal,
					Integer.parseInt(txtRequerimentos.getText()),
					Integer.parseInt(txtCoste.getText()));
			JOptionPane.showMessageDialog(null, "EXITOSO", "Registro Actualizado Exitosamente", codigoEspecie, null);

		}

		@FXML public void guardarElemento(){
			Animal a = new Animal(cmbFamiliaAnimal.getValue(),
					cmbEspecieAnimal.getSelectionModel().getSelectedItem(),
					Date.valueOf(dtpFecha.getValue()),
					genero,
					Integer.parseInt(txtRequerimentos.getText()),
					Integer.parseInt(txtCoste.getText())
					);
			int resultado = a.guardarAnimal(Conexion.getConexion());
			if (resultado == 1){
				listaAnimal.add(a);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Agregado");
			mensaje.setContentText("Registro ha sido agregado exitosamente");
			mensaje.setHeaderText("Resultado");
			mensaje.show();
			limpiarTodo();
			btnGuardar.setDisable(true);
			}
		}

		@FXML public void selectGenero()
		{
			if(rbtFemenino.isSelected())
				genero = ""+'F';
			else
				genero =""+'M';
			System.out.println(genero);
		}



		public Main getMain() {
			return main;
		}

		public void setMain(Main main) {
			this.main = main;
		}

}
