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

public class TipoAnimal{
	private IntegerProperty codigoTipoAnimal;
	private StringProperty nombreTipo;

	public TipoAnimal(int codigoTipoAnimal, String nombreTipo) {
		this.codigoTipoAnimal = new SimpleIntegerProperty(codigoTipoAnimal);
		this.nombreTipo = new SimpleStringProperty(nombreTipo);
	}



	//Metodos atributo: codigoTipoAnimal
	public int getCodigoTipoAnimal() {
		return codigoTipoAnimal.get();
	}
	public void setCodigoTipoAnimal(int codigoTipoAnimal) {
		this.codigoTipoAnimal = new SimpleIntegerProperty(codigoTipoAnimal);
	}
	public IntegerProperty CodigoTipoAnimalProperty() {
		return codigoTipoAnimal;
	}
	//Metodos atributo: nombreTipo
	public String getNombreTipo() {
		return nombreTipo.get();
	}
	public void setNombreTipo(String nombreTipo) {
		this.nombreTipo = new SimpleStringProperty(nombreTipo);
	}
	public StringProperty NombreTipoProperty() {
		return nombreTipo;
	}


	public static void otroTipoAnimal(Connection connection, ObservableList<TipoAnimal> listaTipAn, String familia){

		try {
			String consulta = "select "
					+ "DISTINCT B.NOMBRE_TIPO, "
					+ "B.CODIGO_TIPO_ANIMAL, "
					+ "A.CARACTERISTICA "
					+ "from TBL_ESPECIE_ANIMAL A "
					+ "INNER JOIN TBL_TIPO_ANIMAL B "
					+ "ON(A.CODIGO_TIPO_ANIMAL = "
					+ "B.CODIGO_TIPO_ANIMAL) "
					+ "WHERE A.CARACTERISTICA = ? ";

			PreparedStatement sentencia = connection.prepareStatement(consulta);
			sentencia.setString(1, familia);
			ResultSet resultado = sentencia.executeQuery();

			while(resultado.next()){
				listaTipAn.add(new
					TipoAnimal(
							resultado.getInt("CODIGO_TIPO_ANIMAL"),
							resultado.getString("NOMBRE_TIPO")
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
		return nombreTipo.get();
	}


}