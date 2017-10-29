package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
	//Funcionalidad
	public int guardarRegisto(Connection connection) {
		try {
			//Evitar inyeccion
			PreparedStatement instruccion= connection.prepareStatement(
					"INSERT INTO TBL_HISTORIAL " + 
					"(CODIGO_HISTORIAL, "
					+ "CODIGO_DIETA, "
					+ "CODIGO_ANIMAL, "
					+ "FECHA_INICIO, "
					+ "FECHA_FIN)VALUES " + 
					"(S_HISTORIAL.NEXTVAL, ?, ?, ?, ?)");
			instruccion.setInt(1, codigoDieta.getCodigo());
			instruccion.setString(2, codigoAnimal.getCodigoAnimal());
			instruccion.setDate(3, fechaInicio);
			instruccion.setDate(4, fechaFin);
			
			return instruccion.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} 
		
	}
	public int actualizarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement(
					"UPDATE TBL_HISTORIAL " + 
					"SET " + 
					" CODIGO_DIETA = ? " + 
					" ,CODIGO_ANIMAL = ? " + 
					" ,FECHA_INICIO = ? " + 
					" ,FECHA_FIN = ? " + 
					"WHERE " + 
					"CODIGO_HISTORIAL=?"
					);
			instruccion.setInt(1, codigoDieta.getCodigo());
			instruccion.setString(2, codigoAnimal.getCodigoAnimal());
			instruccion.setDate(3, fechaInicio);
			instruccion.setDate(4, fechaFin);
			instruccion.setInt(5, getCodigoHistorial());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	public int eliminarRegistro(Connection connection) {
		try {
			PreparedStatement instruccion = connection.prepareStatement(
					"DELETE FROM TBL_HISTORIAL " + 
					"WHERE " + 
					"CODIGO_HISTORIAL= ?"
					);
			instruccion.setInt(1, getCodigoHistorial());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
	}
	//Llenado de Informacion
	public static void llenarInformacion(Connection connection, ObservableList<Historial> listaH) {
		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT A.CODIGO_HISTORIAL, "
					+ "A.CODIGO_DIETA, "
					+ "A.CODIGO_ANIMAL, "
					+ "A.FECHA_INICIO, "
					+ "A.FECHA_FIN, " 
					+ "B.NOMBRE_DIETA, "
					+ "B.FECHA_CREACION, "
					+ "B.PORCIONES, "
					+ "B.RECOMENDACIONES, "
					+ "B.CANTIDAD_NUTRIENTES, " 
					+ "C.CODIGO_ESPECIE, "
					+ "C.FECHA_NACIMIENTO, "
					+ "C.SEXO, "
					+ "C.NECESIDAD_NUTRI, "
					+ "C.COSTE_ANIMAL, " 
					+ "D.CODIGO_TIPO_ANIMAL, "
					+ "D.NOMBRE_ESPECIE, "
					+ "D.CARACTERISTICA, "
					+ "D.USO, " 
					+ "E.NOMBRE_TIPO " 
					+ "FROM TBL_HISTORIAL A " 
					+ "INNER JOIN TBL_DIETA B "
					+ "ON A.CODIGO_DIETA = B.CODIGO_DIETA " 
					+ "INNER JOIN TBL_ANIMAL C "
					+ "ON A.CODIGO_ANIMAL = C.CODIGO_ANIMAL " 
					+ "INNER JOIN TBL_ESPECIE_ANIMAL D "
					+ "ON C.CODIGO_ESPECIE = D.CODIGO_ESPECIE " 
					+ "INNER JOIN TBL_TIPO_ANIMAL E "
					+ "ON D.CODIGO_TIPO_ANIMAL = E.CODIGO_TIPO_ANIMAL"
					);
			while(resultado.next()) {
				listaH.add(
						new Historial(
								resultado.getInt("CODIGO_HISTORIAL"), 
								new Dieta(
										resultado.getInt("CODIGO_DIETA"), 
										resultado.getString("NOMBRE_DIETA"), 
										resultado.getDate("FECHA_CREACION"), 
										resultado.getInt("PORCIONES"), 
										resultado.getString("RECOMENDACIONES"), 
										resultado.getInt("CANTIDAD_NUTRIENTES")
										), 
								new Animal(
										resultado.getString("CODIGO_ANIMAL"), 
										new EspecieAnimal(
												resultado.getInt("CODIGO_ESPECIE"), 
												new TipoAnimal(
														resultado.getInt("CODIGO_TIPO_ANIMAL"), 
														resultado.getString("NOMBRE_TIPO")
														), 
												resultado.getString("NOMBRE_ESPECIE"), 
												resultado.getString("CARACTERISTICA"), 
												resultado.getString("USO")
												), 
										resultado.getDate("FECHA_NACIMIENTO"), 
										resultado.getString("SEXO"), 
										resultado.getInt("NECESIDAD_NUTRI"), 
										resultado.getInt("COSTE_ANIMAL")
										), 
								resultado.getDate("FECHA_INICIO"), 
								resultado.getDate("FECHA_FIN"))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
}