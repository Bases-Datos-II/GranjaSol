package aplication;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class FrmLoginController implements Initializable{
	
	@FXML
	private void handleButtonAction(ActionEvent event) throws IOException{
		System.out.println("you clicked me");
		Parent home_page_parent= FXMLLoader.load(getClass().getResource("FrmHistorial.fxml"));
		Scene home_page_scene = new Scene(home_page_parent);
		Stage app_stage
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		// TODO Auto-generated method stub
		
	}

}
