package modelo;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Animal{
	private StringProperty codigoAnimal;
	private EspecieAnimal codigoEspecieAnimal;
	private Date fechaNacimiento;
	private StringProperty sexo;
	private IntegerProperty necesidadNutri;
	private IntegerProperty coste;

	public Animal(String codigoAnimal, EspecieAnimal codigoEspecieAnimal, Date fechaNacimiento,
String sexo, int necesidadNutri, int coste) {
		this.codigoAnimal = new SimpleStringProperty(codigoAnimal);
		this.codigoEspecieAnimal = codigoEspecieAnimal;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = new SimpleStringProperty(sexo);
		this.necesidadNutri = new SimpleIntegerProperty(necesidadNutri);
		this.coste = new SimpleIntegerProperty(coste);
	}

	//Metodos atributo: codigoAnimal
	public String getCodigoAnimal() {
		return codigoAnimal.get();
	}
	public void setCodigoAnimal(String codigoAnimal) {
		this.codigoAnimal = new SimpleStringProperty(codigoAnimal);
	}
	public StringProperty CodigoAnimalProperty() {
		return codigoAnimal;
	}
	//Metodos atributo: codigoEspecieAnimal
	public EspecieAnimal getCodigoEspecieAnimal() {
		return codigoEspecieAnimal;
	}
	public void setCodigoEspecieAnimal(EspecieAnimal codigoEspecieAnimal) {
		this.codigoEspecieAnimal = codigoEspecieAnimal;
	}
	//Metodos atributo: fechaNacimiento
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	//Metodos atributo: sexo
	public String getSexo() {
		return sexo.get();
	}
	public void setSexo(String sexo) {
		this.sexo = new SimpleStringProperty(sexo);
	}
	public StringProperty SexoProperty() {
		return sexo;
	}
	//Metodos atributo: necesidadNutri
	public int getNecesidadNutri() {
		return necesidadNutri.get();
	}
	public void setNecesidadNutri(int necesidadNutri) {
		this.necesidadNutri = new SimpleIntegerProperty(necesidadNutri);
	}
	public IntegerProperty NecesidadNutriProperty() {
		return necesidadNutri;
	}
	//Metodos atributo: coste
	public int getCoste() {
		return coste.get();
	}
	public void setCoste(int coste) {
		this.coste = new SimpleIntegerProperty(coste);
	}
	public IntegerProperty CosteProperty() {
		return coste;
	}

	public void guardarAnimal(){

	}

	public void actualizarAnimal(){

	}

	public void llenarFamiliaAnimal(){

	}
}