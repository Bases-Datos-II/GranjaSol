package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

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

	public void llenarTipoAnimal(){

	}

	@Override
	public String toString(){
		return nombreTipo.get();
	}
}