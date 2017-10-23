package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

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

	public static void llenarEspecieAnimal(Connection connection, ObservableList<EspecieAnimal> listaEspAn, int familia,String tipoAnimal){
		System.out.println("Buscando Raza");
		/*if (familia == 1)
		{
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery("SELECT A.CODIGO_ESPECIE,"
					+ "A.CODIGO_TIPO_ANIMAL, "
					+ "A.NOMBRE_ESPECIE, "
					+ "A.CARACTERISTICA, "
					+ "A.USO, "
					+ "B.NOMBRE_TIPO "
					+ "FROM TBL_ESPECIE_ANIMAL A "
					+ "INNER JOIN TBL_TIPO_ANIMAL B "
					+ "ON(A.CODIGO_TIPO_ANIMAL = B.CODIGO_TIPO_ANIMAL)"
					+ "WHERE A.CARACTERISTICA = 'BOVINO'  AND B.NOMBRE_TIPO = '"+tipoAnimal+"'");

			while(resultado.next()){
				listaEspAn.add(new EspecieAnimal
						(resultado.getInt("CODIGO_ESPECIE"),
						new TipoAnimal(
								resultado.getInt("CODIGO_TIPO_ANIMAL"),
								resultado.getString("NOMBRE_TIPO")
								),
						resultado.getString("NOMBRE_ESPECIE"),
						resultado.getString("CARACTERISTICA"),
						resultado.getString("USO")
						)
					);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}*/

		 if (familia == 0)
		    {
		    try {
		    	String consulta = "SELECT A.CODIGO_ESPECIE, "
		    			+ "A.CODIGO_TIPO_ANIMAL, "
		    			+ "A.NOMBRE_ESPECIE, "
		    			+ "A.CARACTERISTICA, "
		    			+ "A.USO, B.NOMBRE_TIPO "
		    			+ "FROM TBL_ESPECIE_ANIMAL A "
		    			+ "INNER JOIN TBL_TIPO_ANIMAL B "
		    			+ "ON(A.CODIGO_TIPO_ANIMAL = B.CODIGO_TIPO_ANIMAL) "
		    			+ "WHERE A.CARACTERISTICA = 'BOVINO' AND "
		    			+ "B.NOMBRE_TIPO = ? ";
		    	PreparedStatement sentencia = connection.prepareStatement(consulta);
		    	sentencia.setString(1, tipoAnimal);
		    	/*sentencia.executeQuery(consulta)*/;
		    	/*
		      sentencia = connection.prepareStatement(consulta);
		      */
		       ResultSet resultado = sentencia.executeQuery();

		      while(resultado.next()){
		        listaEspAn.add(new EspecieAnimal
		            (resultado.getInt("CODIGO_ESPECIE"),
		            new TipoAnimal(
		                resultado.getInt("CODIGO_TIPO_ANIMAL"),
		                resultado.getString("NOMBRE_TIPO")
		                ),
		            resultado.getString("NOMBRE_ESPECIE"),
		            resultado.getString("CARACTERISTICA"),
		            resultado.getString("USO")
		            )
		          );
		      }
		    } catch (SQLException e) {

		     System.out.println("Erro e llenar espacie animal "+ e);
		    }
		  }

		 if (familia == 1)
		    {
		    try {
		    	String consulta = "SELECT A.CODIGO_ESPECIE, "
		    			+ "A.CODIGO_TIPO_ANIMAL, "
		    			+ "A.NOMBRE_ESPECIE, "
		    			+ "A.CARACTERISTICA, "
		    			+ "A.USO, B.NOMBRE_TIPO "
		    			+ "FROM TBL_ESPECIE_ANIMAL A "
		    			+ "INNER JOIN TBL_TIPO_ANIMAL B "
		    			+ "ON(A.CODIGO_TIPO_ANIMAL = B.CODIGO_TIPO_ANIMAL) "
		    			+ "WHERE A.CARACTERISTICA = 'AVE' AND "
		    			+ "B.NOMBRE_TIPO = ? ";
		    	PreparedStatement sentencia = connection.prepareStatement(consulta);
		    	sentencia.setString(1, tipoAnimal);
		    	/*sentencia.executeQuery(consulta)*/;
		    	/*
		      sentencia = connection.prepareStatement(consulta);
		      */
		       ResultSet resultado = sentencia.executeQuery();

		      while(resultado.next()){
		        listaEspAn.add(new EspecieAnimal
		            (resultado.getInt("CODIGO_ESPECIE"),
		            new TipoAnimal(
		                resultado.getInt("CODIGO_TIPO_ANIMAL"),
		                resultado.getString("NOMBRE_TIPO")
		                ),
		            resultado.getString("NOMBRE_ESPECIE"),
		            resultado.getString("CARACTERISTICA"),
		            resultado.getString("USO")
		            )
		          );
		      }
		    } catch (SQLException e) {

		     System.out.println("Erro e llenar espacie animal "+ e);
		    }
		  }

		 if (familia == 2)
		    {
		    try {
		    	String consulta = "SELECT A.CODIGO_ESPECIE, "
		    			+ "A.CODIGO_TIPO_ANIMAL, "
		    			+ "A.NOMBRE_ESPECIE, "
		    			+ "A.CARACTERISTICA, "
		    			+ "A.USO, B.NOMBRE_TIPO "
		    			+ "FROM TBL_ESPECIE_ANIMAL A "
		    			+ "INNER JOIN TBL_TIPO_ANIMAL B "
		    			+ "ON(A.CODIGO_TIPO_ANIMAL = B.CODIGO_TIPO_ANIMAL) "
		    			+ "WHERE A.CARACTERISTICA = 'RUMIANTE' AND "
		    			+ "B.NOMBRE_TIPO = ? ";
		    	PreparedStatement sentencia = connection.prepareStatement(consulta);
		    	sentencia.setString(1, tipoAnimal);
		    	/*sentencia.executeQuery(consulta)*/;
		    	/*
		      sentencia = connection.prepareStatement(consulta);
		      */
		       ResultSet resultado = sentencia.executeQuery();

		      while(resultado.next()){
		        listaEspAn.add(new EspecieAnimal
		            (resultado.getInt("CODIGO_ESPECIE"),
		            new TipoAnimal(
		                resultado.getInt("CODIGO_TIPO_ANIMAL"),
		                resultado.getString("NOMBRE_TIPO")
		                ),
		            resultado.getString("NOMBRE_ESPECIE"),
		            resultado.getString("CARACTERISTICA"),
		            resultado.getString("USO")
		            )
		          );
		      }
		    } catch (SQLException e) {

		     System.out.println("Erro e llenar espacie animal "+ e);
		    }
		  }


	}

	@Override
	public String toString(){
		return nombreEspecie.get();
	}
}