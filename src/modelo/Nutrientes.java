package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Nutrientes{
	private IntegerProperty codigoNutriente;
	private StringProperty nombreNutriente;
	private StringProperty tipoNutriente;

	public Nutrientes(int codigoNutriente, String nombreNutriente, String tipoNutriente) {
		this.codigoNutriente = new SimpleIntegerProperty(codigoNutriente);
		this.nombreNutriente = new SimpleStringProperty(nombreNutriente);
		this.tipoNutriente = new SimpleStringProperty(tipoNutriente);
	}

	//Metodos atributo: codigoNutriente
	public int getCodigoNutriente() {
		return codigoNutriente.get();
	}
	public void setCodigoNutriente(int codigoNutriente) {
		this.codigoNutriente = new SimpleIntegerProperty(codigoNutriente);
	}
	public IntegerProperty CodigoNutrienteProperty() {
		return codigoNutriente;
	}
	//Metodos atributo: nombreNutriente
	public String getNombreNutriente() {
		return nombreNutriente.get();
	}
	public void setNombreNutriente(String nombreNutriente) {
		this.nombreNutriente = new SimpleStringProperty(nombreNutriente);
	}
	public StringProperty NombreNutrienteProperty() {
		return nombreNutriente;
	}
	//Metodos atributo: tipoNutriente
	public String getTipoNutriente() {
		return tipoNutriente.get();
	}
	public void setTipoNutriente(String tipoNutriente) {
		this.tipoNutriente = new SimpleStringProperty(tipoNutriente);
	}
	public StringProperty TipoNutrienteProperty() {
		return tipoNutriente;
	}
	
	public static void llenarInformacion(Connection connection, ObservableList<Nutrientes> lista) {
		try {
			Statement statement= connection.createStatement();
			ResultSet resultado= statement.executeQuery(
					"SELECT CODIGO_NUTRIENTE, "
					+ "NOMBRE_NUTRIENTE, "
					+ "TIPO_NUTRIENTE "
					+ "FROM TBL_NUTRIENTES"
			);
			while(resultado.next()) {
				lista.add(
						new Nutrientes(
						resultado.getInt("CODIGO_NUTRIENTE"),
						resultado.getString("NOMBRE_NUTRIENTE"),
						resultado.getString("TIPO_NUTRIENTE"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void guardarRegisto() {
		
	}
	public void actualizarRegistro() {
		
	}
	public void eliminarRegistro() {
		
	}
	@Override
	public String toString() {
		return nombreNutriente.get();
	}
}