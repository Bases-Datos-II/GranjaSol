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

public class Dieta{
	private IntegerProperty codigoDieta;
	private StringProperty nombreDieta;
	private Date fechaCreacion;
	private IntegerProperty porciones;
	private StringProperty recomendaciones;
	private IntegerProperty cantnutri;

	public Dieta(int codigoDieta, String nombreDieta, Date fechaCreacion,
                 int porciones, String recomendaciones, int cantnutri)
	{
		this.codigoDieta = new SimpleIntegerProperty(codigoDieta);
		this.nombreDieta = new SimpleStringProperty(nombreDieta);
		this.fechaCreacion = fechaCreacion;
		this.porciones = new SimpleIntegerProperty(porciones);
		this.recomendaciones = new SimpleStringProperty(recomendaciones);
		this.cantnutri = new SimpleIntegerProperty(cantnutri);
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
    //nutrientes
	public int getCantidadNutrientes() {
		return cantnutri.get();
	}
	public void setCantidadNutrientes(int cantidadnutrientes) {
		this.cantnutri = new SimpleIntegerProperty(cantidadnutrientes);
	}
	public IntegerProperty CantidadNutrientesProperty() {
		return cantnutri;
	}

	public static void CargarDietas(Connection connection, ObservableList<Dieta> lista)
	{
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery
					("select CODIGO_DIETA, "
							+ "NOMBRE_DIETA, "
							+ "FECHA_CREACION, "
							+ "PORCIONES, "
							+ "RECOMENDACIONES, "
							+ "CANTIDAD_NUTRIENTES "
							+ "from TBL_DIETA ");
			while(resultado.next())
			{
				lista.add(new Dieta(resultado.getInt("CODIGO_DIETA"),
						            resultado.getString("NOMBRE_DIETA"),
						            resultado.getDate("FECHA_CREACION"),
						            resultado.getInt("PORCIONES"),
						            resultado.getString("RECOMENDACIONES"),
						            resultado.getInt("CANTIDAD_NUTRIENTES")));
			}
		}
		catch (SQLException e) {

			e.printStackTrace();
		}
	}
	@Override
	public String toString()
	{
		return nombreDieta.get();
	}

}