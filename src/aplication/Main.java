package aplication;

import javafx.application.Application;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Main extends Application {
	//Definiendo Stages
	private Stage formularioLogin;
	private Stage formularioDieta;
	private Stage formularioNutriente;
	private Stage formularioHistorial;
	private Stage formularioAnimal;
	private Stage formularioAlimento;

	//Enlazando Controladores
	private FormLoginController controladorLogin;
	private FormInicialController controladorVistaAnimal;
	private FormDietaController controladorDieta;
	private FormHistorialController controladorHistorial;
	private FormNutrientesController controladorNutriente;
	private FormAlimentosController controladorAlimentos;


	//Definiendo Escenas
	private Scene escenaLogin;
	private Scene escenaDieta;
	private Scene escenaAnimal;
	private Scene escenaHistorial;
	private Scene escenaNutriente;
	private Scene escenaAlimento;





	@Override
	public void start(Stage primaryStage) {

		formularioLogin = primaryStage;
		try {

			FXMLLoader loader = new FXMLLoader(getClass().getResource("FormLogin.fxml"));
			AnchorPane root = (AnchorPane)loader.load();
			escenaLogin = new Scene(root);
			escenaLogin.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			formularioLogin.setScene(escenaLogin);
			controladorLogin = loader.getController();
			controladorLogin.setMain(this);
			formularioLogin.show();

		} catch(Exception e) {
			e.printStackTrace();
		}

	}

	//Ir de INICIO A ANIMALES
	public void cambiarEscena(){
		if(formularioAnimal == null){
			formularioAnimal = new Stage();
			formularioAnimal.initOwner(formularioLogin);
			formularioAnimal.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FormInicial.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				formularioAnimal.setScene(scene);
				formularioAnimal.setTitle("ANIMALES");
				controladorVistaAnimal = loader.getController();
				controladorVistaAnimal.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formularioLogin.hide();
		formularioAnimal.show();
		}


	// IR DE INICIO A FORM DIETA
	public void iraDieta(){
		if(formularioDieta == null){
			formularioDieta = new Stage();
			formularioDieta.initOwner(formularioLogin);
			formularioDieta.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FormDieta2.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				formularioDieta.setScene(scene);
				formularioDieta.setTitle("DIETAS");
				controladorDieta = loader.getController();
				controladorDieta.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formularioLogin.hide();
		formularioDieta.show();
		}


	//DE INICIO A HISTORIAL
	public void iraHistorial(){
		if(formularioHistorial == null){
			formularioHistorial = new Stage();
			formularioHistorial.initOwner(formularioLogin);
			formularioHistorial.initModality(Modality.WINDOW_MODAL);

			try {
				FXMLLoader loader = new FXMLLoader(getClass().getResource("FormHistorial.fxml"));
				AnchorPane root = (AnchorPane)loader.load();
				Scene scene = new Scene(root);
				formularioHistorial.setScene(scene);
				formularioHistorial.setTitle("Historial");
				controladorHistorial = loader.getController();
				controladorHistorial.setMain(this);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		formularioLogin.hide();
		formularioHistorial.show();
		}

	public static void main(String[] args) {
		launch(args);
	}
}
