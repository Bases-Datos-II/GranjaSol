package aplication;

import java.awt.Component;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class FormLoginController implements Initializable {
	private Main main;
	public Component frame;


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {



	}
	public Main getMain() {
		return main;
	}

	public void setMain(Main main) {
		this.main = main;
	}

	@FXML public void cambiarAAnimal(){
		main.cambiarEscena();
		}

	@FXML public void cambiarADieta(){
		main.iraDieta();
	}

	@FXML public void cambiaraHistoial(){
		main.iraHistorial();
	}

}
