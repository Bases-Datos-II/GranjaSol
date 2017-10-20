package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class Alimentos{
	private StringProperty nombreAlimento;
	private IntegerProperty codigoAlimento;
	private IntegerProperty calorias;

	public Alimentos(String nombreAlimento, int codigoAlimento, int calorias) {
		this.nombreAlimento = new SimpleStringProperty(nombreAlimento);
		this.codigoAlimento = new SimpleIntegerProperty(codigoAlimento);
		this.calorias = new SimpleIntegerProperty(calorias);
	}

	//Metodos atributo: nombreAlimento
	public String getNombreAlimento() {
		return nombreAlimento.get();
	}
	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = new SimpleStringProperty(nombreAlimento);
	}
	public StringProperty NombreAlimentoProperty() {
		return nombreAlimento;
	}
	//Metodos atributo: codigoAlimento
	public int getCodigoAlimento() {
		return codigoAlimento.get();
	}
	public void setCodigoAlimento(int codigoAlimento) {
		this.codigoAlimento = new SimpleIntegerProperty(codigoAlimento);
	}
	public IntegerProperty CodigoAlimentoProperty() {
		return codigoAlimento;
	}
	//Metodos atributo: calorias
	public int getCalorias() {
		return calorias.get();
	}
	public void setCalorias(int calorias) {
		this.calorias = new SimpleIntegerProperty(calorias);
	}
	public IntegerProperty CaloriasProperty() {
		return calorias;
	}
}