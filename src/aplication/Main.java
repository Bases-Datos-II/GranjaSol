package aplication;

import javafx.application.Application;
//import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
//import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Main extends Application {


	//@FXML private Button btnboton;
	@Override
	public void start(Stage primaryStage) {

		try {

			AnchorPane root = (AnchorPane)FXMLLoader.load(getClass().getResource("FormNutrientes.fxml"));

			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	//NUEVOOOOOOOOOoooooooooooo
	public static void main(String[] args) {
		launch(args);
	}
}
