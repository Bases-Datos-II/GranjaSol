package modelo;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Historial{
	private IntegerProperty codigoHistorial;
	private Dieta codigoDieta;
	private Animal codigoAnimal;
	private Date fechaInicio;
	private Date fechaFin;

	public Historial(int codigoHistorial, Dieta codigoDieta, Animal codigoAnimal,
Date fechaInicio, Date fechaFin) {
		this.codigoHistorial = new SimpleIntegerProperty(codigoHistorial);
		this.codigoDieta = codigoDieta;
		this.codigoAnimal = codigoAnimal;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	//Metodos atributo: codigoHistorial
	public int getCodigoHistorial() {
		return codigoHistorial.get();
	}
	public void setCodigoHistorial(int codigoHistorial) {
		this.codigoHistorial = new SimpleIntegerProperty(codigoHistorial);
	}
	public IntegerProperty CodigoHistorialProperty() {
		return codigoHistorial;
	}
	//Metodos atributo: codigoDieta
	public Dieta getCodigoDieta() {
		return codigoDieta;
	}
	public void setCodigoDieta(Dieta codigoDieta) {
		this.codigoDieta = codigoDieta;
	}
	//Metodos atributo: codigoAnimal
	public Animal getCodigoAnimal() {
		return codigoAnimal;
	}
	public void setCodigoAnimal(Animal codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}
	//Metodos atributo: fechaInicio
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	//Metodos atributo: fechaFin
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
}