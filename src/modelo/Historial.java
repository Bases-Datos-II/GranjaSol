package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

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
	public static void llenarInformacion(Connection connection, ObservableList<Animal> listaA, ObservableList<Dieta> listaD) {
		try {
			Statement statementA= connection.createStatement();
			Statement statementD= connection.createStatement();
			ResultSet resultadoA= statementA.executeQuery(
					"SELECT A.CODIGO_ANIMAL, "
					+ "A.CODIGO_ESPECIE, "
					+ "A.FECHA_NACIMIENTO, "
					+ "A.SEXO, "
					+ "A.NECESIDAD_NUTRI, "
					+ "A.COSTE_ANIMAL, " 
					+ "B.CODIGO_TIPO_ANIMAL, "
					+ "B.NOMBRE_ESPECIE, "
					+ "B.CARACTERISTICA, "
					+ "B.USO, " 
					+ "C.NOMBRE_TIPO " 
					+ "  FROM TBL_ANIMAL A " 
					+ "INNER JOIN TBL_ESPECIE_ANIMAL B ON A.CODIGO_ESPECIE = B.CODIGO_ESPECIE " 
					+ "INNER JOIN TBL_TIPO_ANIMAL C ON B.CODIGO_TIPO_ANIMAL = C.CODIGO_TIPO_ANIMAL"
			);
			ResultSet resultadoD= statementD.executeQuery(
					"SELECT CODIGO_DIETA, "
					+ "NOMBRE_DIETA, "
					+ "FECHA_CREACION, "
					+ "PORCIONES, "
					+ "RECOMENDACIONES, "
					+ "CANTIDAD_NUTRIENTES "
					+ "FROM TBL_DIETA "
			);
			while(resultadoA.next()) {
				listaA.add(
						new Animal(
								resultadoA.getString("CODIGO_ANIMAL"), 
								new EspecieAnimal(
										resultadoA.getInt("CODIGO_ESPECIE"), 
										new TipoAnimal(
												resultadoA.getInt("CODIGO_TIPO_ANIMAL"), 
												resultadoA.getString("NOMBRE_TIPO")
												), 
										resultadoA.getString("NOMBRE_ESPECIE"), 
										resultadoA.getString("CARACTERISTICA"), 
										resultadoA.getString("USO")
								), 
								resultadoA.getDate("FECHA_NACIMIENTO"), 
								resultadoA.getString("SEXO"), 
								resultadoA.getInt("NECESIDAD_NUTRI"), 
								resultadoA.getInt("COSTE_ANIMAL"))
						);
			}
			while(resultadoD.next()) {
				listaD.add(
						new Dieta(
						resultadoD.getInt("CODIGO_DIETA"),
						resultadoD.getString("NOMBRE_DIETA"),
						resultadoD.getDate("FECHA_CREACION"),
						resultadoD.getInt("PORCIONES"),
						resultadoD.getString("RECOMENDACIONES"),
						resultadoD.getInt("CANTIDAD_NUTRIENTES")
						)
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}