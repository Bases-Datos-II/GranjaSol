package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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
}