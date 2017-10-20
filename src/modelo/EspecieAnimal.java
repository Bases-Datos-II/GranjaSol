package modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class EspecieAnimal{
	private IntegerProperty codigoEspecie;
	private TipoAnimal codigoTipoAnimal;
	private StringProperty nombreEspecie;
	private StringProperty caracteristica;
	private StringProperty uso;

	public EspecieAnimal(int codigoEspecie, TipoAnimal codigoTipoAnimal, String nombreEspecie,
String caracteristica, String uso) {
		this.codigoEspecie = new SimpleIntegerProperty(codigoEspecie);
		this.codigoTipoAnimal = codigoTipoAnimal;
		this.nombreEspecie = new SimpleStringProperty(nombreEspecie);
		this.caracteristica = new SimpleStringProperty(caracteristica);
		this.uso = new SimpleStringProperty(uso);
	}

	//Metodos atributo: codigoEspecie
	public int getCodigoEspecie() {
		return codigoEspecie.get();
	}
	public void setCodigoEspecie(int codigoEspecie) {
		this.codigoEspecie = new SimpleIntegerProperty(codigoEspecie);
	}
	public IntegerProperty CodigoEspecieProperty() {
		return codigoEspecie;
	}
	//Metodos atributo: codigoTipoAnimal
	public TipoAnimal getCodigoTipoAnimal() {
		return codigoTipoAnimal;
	}
	public void setCodigoTipoAnimal(TipoAnimal codigoTipoAnimal) {
		this.codigoTipoAnimal = codigoTipoAnimal;
	}
	//Metodos atributo: nombreEspecie
	public String getNombreEspecie() {
		return nombreEspecie.get();
	}
	public void setNombreEspecie(String nombreEspecie) {
		this.nombreEspecie = new SimpleStringProperty(nombreEspecie);
	}
	public StringProperty NombreEspecieProperty() {
		return nombreEspecie;
	}
	//Metodos atributo: caracteristica
	public String getCaracteristica() {
		return caracteristica.get();
	}
	public void setCaracteristica(String caracteristica) {
		this.caracteristica = new SimpleStringProperty(caracteristica);
	}
	public StringProperty CaracteristicaProperty() {
		return caracteristica;
	}
	//Metodos atributo: uso
	public String getUso() {
		return uso.get();
	}
	public void setUso(String uso) {
		this.uso = new SimpleStringProperty(uso);
	}
	public StringProperty UsoProperty() {
		return uso;
	}
}