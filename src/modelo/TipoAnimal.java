package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import com.sun.webkit.ContextMenu.ShowContext;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class TipoAnimal{
	private IntegerProperty codigoTipoAnimal;
	private StringProperty nombreTipo;

	public TipoAnimal(int codigoTipoAnimal, String nombreTipo) {
		this.codigoTipoAnimal = new SimpleIntegerProperty(codigoTipoAnimal);
		this.nombreTipo = new SimpleStringProperty(nombreTipo);
	}

	//Metodos atributo: codigoTipoAnimal
	public int getCodigoTipoAnimal() {
		return codigoTipoAnimal.get();
	}
	public void setCodigoTipoAnimal(int codigoTipoAnimal) {
		this.codigoTipoAnimal = new SimpleIntegerProperty(codigoTipoAnimal);
	}
	public IntegerProperty CodigoTipoAnimalProperty() {
		return codigoTipoAnimal;
	}
	//Metodos atributo: nombreTipo
	public String getNombreTipo() {
		return nombreTipo.get();
	}
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = new SimpleStringProperty(nombreTipo);
	}
	public StringProperty NombreTipoProperty() {
		return nombreTipo;
	}

	public static void llenarTipoAnimal(Connection connection, ObservableList<TipoAnimal> listaTipAn, int familia){
		try {


			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("SELECT "
					+ "CODIGO_TIPO_ANIMAL, NOMBRE_TIPO FROM TBL_TIPO_ANIMAL "
					+ "WHERE CODIGO_TIPO_ANIMAL = 1 "
					+ "OR CODIGO_TIPO_ANIMAL = 2");

			while(resultado.next()){
				listaTipAn.add(
						new TipoAnimal(
								resultado.getInt("CODIGO_TIPO_ANIMAL"),
						resultado.getString("NOMBRE_TIPO")
						)
					);
				JOptionPane.showInputDialog(familia);

		}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}


	@Override
	public String toString(){
		return nombreTipo.get();
	}
}