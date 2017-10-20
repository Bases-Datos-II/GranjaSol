package modelo;

import java.sql.Date;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Dieta{
	private IntegerProperty codigoDieta;
	private StringProperty nombreDieta;
	private Date fechaCreacion;
	private IntegerProperty porciones;
	private StringProperty recomendaciones;

	public Dieta(int codigoDieta, String nombreDieta, Date fechaCreacion,
int porciones, String recomendaciones) {
		this.codigoDieta = new SimpleIntegerProperty(codigoDieta);
		this.nombreDieta = new SimpleStringProperty(nombreDieta);
		this.fechaCreacion = fechaCreacion;
		this.porciones = new SimpleIntegerProperty(porciones);
		this.recomendaciones = new SimpleStringProperty(recomendaciones);
	}

	//Metodos atributo: codigoDieta
	public int getCodigoDieta() {
		return codigoDieta.get();
	}
	public void setCodigoDieta(int codigoDieta) {
		this.codigoDieta = new SimpleIntegerProperty(codigoDieta);
	}
	public IntegerProperty CodigoDietaProperty() {
		return codigoDieta;
	}
	//Metodos atributo: nombreDieta
	public String getNombreDieta() {
		return nombreDieta.get();
	}
	public void setNombreDieta(String nombreDieta) {
		this.nombreDieta = new SimpleStringProperty(nombreDieta);
	}
	public StringProperty NombreDietaProperty() {
		return nombreDieta;
	}
	//Metodos atributo: fechaCreacion
	public Date getFechaCreacion() {
		return fechaCreacion;
	}
	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}
	//Metodos atributo: porciones
	public int getPorciones() {
		return porciones.get();
	}
	public void setPorciones(int porciones) {
		this.porciones = new SimpleIntegerProperty(porciones);
	}
	public IntegerProperty PorcionesProperty() {
		return porciones;
	}
	//Metodos atributo: recomendaciones
	public String getRecomendaciones() {
		return recomendaciones.get();
	}
	public void setRecomendaciones(String recomendaciones) {
		this.recomendaciones = new SimpleStringProperty(recomendaciones);
	}
	public StringProperty RecomendacionesProperty() {
		return recomendaciones;
	}
}