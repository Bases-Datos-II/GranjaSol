package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Alimentos{
	private StringProperty nombreAlimento;
	private IntegerProperty codigoAlimento;
	private IntegerProperty calorias;

	public Alimentos( int codigoAlimento,String nombreAlimento, int calorias) {
		this.nombreAlimento = new SimpleStringProperty(nombreAlimento);
		this.codigoAlimento = new SimpleIntegerProperty(codigoAlimento);
		this.calorias = new SimpleIntegerProperty(calorias);
	}

	//Metodos atributo: nombreAlimento
	public String getNombreAlimento() {
		return nombreAlimento.get();
	}
	public void setNombreAlimento(String nombreAlimento) {
		this.nombreAlimento = new SimpleStringProperty(nombreAlimento);
	}
	public StringProperty NombreAlimentoProperty() {
		return nombreAlimento;
	}
	//Metodos atributo: codigoAlimento
	public int getCodigoAlimento() {
		return codigoAlimento.get();
	}
	public void setCodigoAlimento(int codigoAlimento) {
		this.codigoAlimento = new SimpleIntegerProperty(codigoAlimento);
	}
	public IntegerProperty CodigoAlimentoProperty() {
		return codigoAlimento;
	}
	//Metodos atributo: calorias
	public int getCalorias() {
		return calorias.get();
	}
	public void setCalorias(int calorias) {
		this.calorias = new SimpleIntegerProperty(calorias);
	}
	public IntegerProperty CaloriasProperty() {
		return calorias;
	}
	public static void CargarAlimentos(Connection connection, ObservableList<Alimentos> lista )
	{
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery
					("select CODIGO_ALIMENTO, "
							+ "NOMBRE_ALIMENTO, "
							+ "CALORIAS "
							+ "from TBL_ALIMENTOS");
			while(resultado.next())
			{
				lista.add(new Alimentos(
						              resultado.getInt("CODIGO_ALIMENTO"),
					                  resultado.getString("NOMBRE_ALIMENTO"),
					                  resultado.getInt("CALORIAS")
					                  )
				          );
			}
		} 
		catch (SQLException e) {
		
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString()
	{
		return nombreAlimento.get();
	}
	
}