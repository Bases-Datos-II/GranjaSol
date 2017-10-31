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
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import modelo.Animal;
import modelo.Dieta;
import modelo.DietaPorDia;
import utilidades.conexion;

public class FormDietasDiariasController implements Initializable{
	private ObservableList<DietaPorDia> ListaDiDiaria;
	private conexion Acces;
	private DietaPorDia Valor;

	//Componentes GUI
	@FXML private TableView<DietaPorDia> tblInformacion;
	@FXML private DatePicker dtpkrDiario;
	@FXML private TextField txtComentario;
	@FXML private Button btnActualizar;
	@FXML private CheckBox cbxCumplio;
	
	//Columnas
	@FXML private TableColumn<DietaPorDia,Dieta> clmnDieta;
	@FXML private TableColumn<DietaPorDia,Animal> clmnCodAnimal;
	@FXML private TableColumn<DietaPorDia,String> clmnCumplimiento;
	@FXML private TableColumn<DietaPorDia,Date> clmnfechaDiario;
	@FXML private TableColumn<DietaPorDia,String> clmnComentario;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Acces = new conexion();
		Acces.establecerConexion();
		//Inicializar Listas
		ListaDiDiaria = FXCollections.observableArrayList();
		//Llenar Lista
		DietaPorDia.llenarInformacion(Acces.getConexion(), ListaDiDiaria);
		//Enlazar Lista con Componentes
		tblInformacion.setItems(ListaDiDiaria);
		//Enlazar Columnas
		clmnDieta.setCellValueFactory(new PropertyValueFactory<DietaPorDia,Dieta>("nombreDieta"));
		clmnCodAnimal.setCellValueFactory(new PropertyValueFactory<DietaPorDia,Animal>("codigoAnimal"));
		clmnCumplimiento.setCellValueFactory(new PropertyValueFactory<DietaPorDia,String>("comentarioDiario"));
		clmnfechaDiario.setCellValueFactory(new PropertyValueFactory<DietaPorDia,Date>("fechaDiario"));
		clmnComentario.setCellValueFactory(new PropertyValueFactory<DietaPorDia,String>("cumplimientoDiario"));
		gestionarEventos();
		Acces.cerrarConexion();
	}
	public void gestionarEventos() {
		tblInformacion.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<DietaPorDia>() {

					@Override
					public void changed(ObservableValue<? extends DietaPorDia> observable, DietaPorDia valorAnterior,
							DietaPorDia valorSeleccionado) {
						if(valorSeleccionado!=null) {
							Valor= new DietaPorDia(valorSeleccionado.getCodigoDiario(), valorSeleccionado.getCodigoHistorial(), valorSeleccionado.getComentarioDiario(), valorSeleccionado.getFechaDiario(), valorSeleccionado.getCumplimientoDiario(), valorSeleccionado.getNombreDieta(), valorSeleccionado.getCodigoAnimal());
							dtpkrDiario.setValue(valorSeleccionado.getFechaDiario().toLocalDate());
							txtComentario.setText(valorSeleccionado.getComentarioDiario());
							if (valorSeleccionado.getCumplimientoDiario().equals("CUMPLIO")){
								cbxCumplio.setSelected(true);
							} 
						}
						
					}
		}
				);;
	}
	@FXML
	public void actualizarRegistro() {
		DietaPorDia a= new DietaPorDia(Valor.getCodigoDiario(), 
				Valor.getCodigoHistorial(), 
				txtComentario.getText(), 
				Valor.getFechaDiario(), 
				cbxCumplio.isSelected()?"CUMPLIO":"NO CUMPLIO", 
				Valor.getNombreDieta(), 
				Valor.getCodigoAnimal());
		Acces.establecerConexion();
		int resultado= a.actualizarRegistro(Acces.getConexion());
		Acces.cerrarConexion();
		if(resultado == 1) {
			ListaDiDiaria.set(tblInformacion.getSelectionModel().getSelectedIndex(),a);
			Alert mensaje = new Alert(AlertType.INFORMATION);
			mensaje.setTitle("Registro Actualizado");
			mensaje.setContentText("El registro se ha actualizado exitosamente");
			mensaje.setHeaderText("Resultado");
			mensaje.show();
		}
	}

}
