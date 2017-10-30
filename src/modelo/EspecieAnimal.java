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

	public static void llenarEspecieAnimal(Connection connection, ObservableList<EspecieAnimal> listaEspAn, String familia, String tipo)
	{
			try {
				String consulta = "SELECT "
						+ "A.CODIGO_ESPECIE, "
						+ "A.CODIGO_TIPO_ANIMAL, "
						+ "A.NOMBRE_ESPECIE, "
						+ "A.CARACTERISTICA, "
						+ "A.USO, "
						+ "B.NOMBRE_TIPO "
						+ "FROM TBL_ESPECIE_ANIMAL A "
						+ "INNER JOIN TBL_TIPO_ANIMAL B "
						+ "ON(A.CODIGO_TIPO_ANIMAL = B.CODIGO_TIPO_ANIMAL) "
						+ "WHERE A.CARACTERISTICA = ? AND B.NOMBRE_TIPO = ?";
				PreparedStatement sentencia = connection.prepareStatement(consulta);
		    	sentencia.setString(1, familia);
		    	sentencia.setString(2, tipo);
		    	ResultSet resultado = sentencia.executeQuery();

				while(resultado.next())
				{
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch( Exception a){
				System.out.println("llenar animal"+a);
			}


		}


	@Override
	public String toString(){
		return nombreEspecie.get();
	}



	public static void llenartblvtipAnimal(Connection connection, ObservableList<EspecieAnimal> listaAnimal,String codigoAnimal)
	{

		System.out.println(codigoAnimal);
		try {

			String consulta = "SELECT  A.CODIGO_ANIMAL, "
					+ "A.CODIGO_ESPECIE, "
					+ "B.CODIGO_TIPO_ANIMAL, "
					+ "C.NOMBRE_TIPO, "
					+ "B.NOMBRE_ESPECIE, "
					+ "B.CARACTERISTICA, "
					+ "B.USO, "
					+ "A.FECHA_NACIMIENTO, "
					+ "A.SEXO, "
					+ "A.NECESIDAD_NUTRI, "
					+ "A.COSTE_ANIMAL, "
					+ "C.NOMBRE_TIPO "
					+ "FROM TBL_ANIMAL A "
					+ "INNER JOIN TBL_ESPECIE_ANIMAL B "
					+ "ON(A.CODIGO_ESPECIE = B.CODIGO_ESPECIE) "
					+ "INNER JOIN TBL_TIPO_ANIMAL C "
					+ "ON(B.CODIGO_TIPO_ANIMAL = C.CODIGO_TIPO_ANIMAL) "
					+ "WHERE A.CODIGO_ANIMAL = ?";
			PreparedStatement sentencia = connection.prepareStatement(consulta);
			sentencia.setString(1, codigoAnimal);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()){
				listaAnimal.add(new EspecieAnimal
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch( Exception a){
			System.out.println("llenar animal"+a);
		}
	}

}