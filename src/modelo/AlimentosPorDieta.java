package modelo;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class AlimentosPorDieta{
	private Dieta codigoDieta;
	private Alimentos codigoAlimento;
	private StringProperty hora;

	public AlimentosPorDieta(Dieta codigoDieta, Alimentos codigoAlimento, String hora) {
		this.codigoDieta = codigoDieta;
		this.codigoAlimento = codigoAlimento;
		this.hora = new SimpleStringProperty(hora);
	}

	//Metodos atributo: codigoDieta
	public Dieta getCodigoDieta() {
		return codigoDieta;
	}
	public void setCodigoDieta(Dieta codigoDieta) {
		this.codigoDieta = codigoDieta;
	}
	//Metodos atributo: codigoAlimento
	public Alimentos getCodigoAlimento() {
		return codigoAlimento;
	}
	public void setCodigoAlimento(Alimentos codigoAlimento) {
		this.codigoAlimento = codigoAlimento;
	}
	//Metodos atributo: hora
	public String getHora() {
		return hora.get();
	}
	public void setHora(String hora) {
		this.hora = new SimpleStringProperty(hora);
	}
	public StringProperty HoraProperty() {
		return hora;
	}
}