package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class DietaPorDia{
	private IntegerProperty codigoDiario;
	private Historial codigoHistorial;
	private StringProperty comentarioDiario;
	private Date fechaDiario;
	private StringProperty cumplimientoDiario;
	private Dieta nombreDieta;
	private Animal codigoAnimal;

	public DietaPorDia(int codigoDiario, Historial codigoHistorial, String comentarioDiario, 
Date fechaDiario, String cumplimientoDiario, Dieta nombreDieta, 
Animal codigoAnimal) { 
		this.codigoDiario = new SimpleIntegerProperty(codigoDiario);
		this.codigoHistorial = codigoHistorial;
		this.comentarioDiario = new SimpleStringProperty(comentarioDiario);
		this.fechaDiario = fechaDiario;
		this.cumplimientoDiario = new SimpleStringProperty(cumplimientoDiario);
		this.nombreDieta = nombreDieta;
		this.codigoAnimal = codigoAnimal;
	}

	//Metodos atributo: codigoDiario
	public int getCodigoDiario() {
		return codigoDiario.get();
	}
	public void setCodigoDiario(int codigoDiario) {
		this.codigoDiario = new SimpleIntegerProperty(codigoDiario);
	}
	public IntegerProperty CodigoDiarioProperty() {
		return codigoDiario;
	}
	//Metodos atributo: codigoHistorial
	public Historial getCodigoHistorial() {
		return codigoHistorial;
	}
	public void setCodigoHistorial(Historial codigoHistorial) {
		this.codigoHistorial = codigoHistorial;
	}
	//Metodos atributo: comentarioDiario
	public String getComentarioDiario() {
		return comentarioDiario.get();
	}
	public void setComentarioDiario(String comentarioDiario) {
		this.comentarioDiario = new SimpleStringProperty(comentarioDiario);
	}
	public StringProperty ComentarioDiarioProperty() {
		return comentarioDiario;
	}
	//Metodos atributo: fechaDiario
	public Date getFechaDiario() {
		return fechaDiario;
	}
	public void setFechaDiario(Date fechaDiario) {
		this.fechaDiario = fechaDiario;
	}
	//Metodos atributo: cumplimientoDiario
	public String getCumplimientoDiario() {
		return cumplimientoDiario.get();
	}
	public void setCumplimientoDiario(String cumplimientoDiario) {
		this.cumplimientoDiario = new SimpleStringProperty(cumplimientoDiario);
	}
	public StringProperty CumplimientoDiarioProperty() {
		return cumplimientoDiario;
	}
	//Metodos atributo: nombreDieta
	public Dieta getNombreDieta() {
		return nombreDieta;
	}
	public void setNombreDieta(Dieta nombreDieta) {
		this.nombreDieta = nombreDieta;
	}
	//Metodos atributo: codigoAnimal
	public Animal getCodigoAnimal() {
		return codigoAnimal;
	}
	public void setCodigoAnimal(Animal codigoAnimal) {
		this.codigoAnimal = codigoAnimal;
	}

	public static void llenarInformacion(Connection connection, ObservableList<DietaPorDia> listaH) {
		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(
					"SELECT A.CODIGO_DIARIO, "
					+ "A.CODIGO_HISTORIAL, "
					+ "A.COMENTARIO, "
					+ "A.FECHA, "
					+ "A.CUMPLIMIENTO, "
					+ "B.CODIGO_DIETA, "
					+ "B.CODIGO_ANIMAL, "
					+ "B.FECHA_INICIO, "
					+ "B.FECHA_FIN, "
					+ "C.NOMBRE_DIETA, "
					+ "C.FECHA_CREACION, "
					+ "C.PORCIONES, "
					+ "C.RECOMENDACIONES, "
					+ "C.CANTIDAD_NUTRIENTES, "
					+ "D.CODIGO_ESPECIE, "
					+ "D.FECHA_NACIMIENTO, "
					+ "D.SEXO, "
					+ "D.NECESIDAD_NUTRI, "
					+ "D.COSTE_ANIMAL, "
					+ "E.CODIGO_TIPO_ANIMAL, "
					+ "E.NOMBRE_ESPECIE, "
					+ "E.CARACTERISTICA, "
					+ "E.USO, "
					+ "F.NOMBRE_TIPO "
					+ "FROM TBL_DIARIO A " + 
					"  INNER JOIN TBL_HISTORIAL B ON A.CODIGO_HISTORIAL = B.CODIGO_HISTORIAL " + 
					"  INNER JOIN TBL_DIETA C ON B.CODIGO_DIETA = C.CODIGO_DIETA " + 
					"  INNER JOIN TBL_ANIMAL D ON B.CODIGO_ANIMAL = D.CODIGO_ANIMAL " + 
					"  INNER JOIN TBL_ESPECIE_ANIMAL E ON D.CODIGO_ESPECIE = E.CODIGO_ESPECIE " + 
					"  INNER JOIN TBL_TIPO_ANIMAL F ON E.CODIGO_TIPO_ANIMAL = F.CODIGO_TIPO_ANIMAL"
					);
			while(resultado.next()) {
				listaH.add(new DietaPorDia(resultado.getInt("CODIGO_DIARIO"),
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
								resultado.getDate("FECHA_FIN")), 
						resultado.getString("COMENTARIO"), 
						resultado.getDate("FECHA"), 
						resultado.getString("CUMPLIMIENTO"),
						new Dieta(resultado.getInt("CODIGO_DIETA"), 
								resultado.getString("NOMBRE_DIETA"), 
								resultado.getDate("FECHA_CREACION"), 
								resultado.getInt("PORCIONES"), 
								resultado.getString("RECOMENDACIONES"), 
								resultado.getInt("CANTIDAD_NUTRIENTES")),
						new Animal(resultado.getString("CODIGO_ANIMAL"), 
								new EspecieAnimal(
										resultado.getInt("CODIGO_ESPECIE"),
										new TipoAnimal(
												resultado.getInt("CODIGO_TIPO_ANIMAL"),
												resultado.getString("NOMBRE_TIPO")
												),
										resultado.getString("NOMBRE_ESPECIE"),
										resultado.getString("CARACTERISTICA"),
										resultado.getString("USO")), 
								resultado.getDate("FECHA_NACIMIENTO"),
								resultado.getString("SEXO"),
								resultado.getInt("NECESIDAD_NUTRI"),
								resultado.getInt("COSTE_ANIMAL")))
						);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}