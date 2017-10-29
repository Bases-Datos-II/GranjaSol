package modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;

public class NutrientesPorAlimento{
	private Nutrientes codigoNutrientes;
	private Alimentos codigoAlimento;

	public NutrientesPorAlimento(Nutrientes codigoNutrientes, Alimentos codigoAlimento) {
		this.codigoNutrientes = codigoNutrientes;
		this.codigoAlimento = codigoAlimento;
	}

	//Metodos atributo: codigoNutrientes
	public Nutrientes getCodigoNutrientes() {
		return codigoNutrientes;
	}
	public void setCodigoNutrientes(Nutrientes codigoNutrientes) {
		this.codigoNutrientes = codigoNutrientes;
	}
	//Metodos atributo: codigoAlimento
	public Alimentos getCodigoAlimento() {
		return codigoAlimento;
	//MENSAJE PRUEBA
	}
	public void setCodigoAlimento(Alimentos codigoAlimento) {
		this.codigoAlimento = codigoAlimento;
	}
	public void guardarRegisto() {
		
	}
	public void actualizarRegistro() {
		
	}
	public void eliminarRegistro() {
		
	}
	//Pendiente
	public static void cargarInformacion(Connection connection, ObservableList<NutrientesPorAlimento> lista) {
		try {
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery(
					"SELECT A.CODIGO_NUTRIENTE, "
					+ "A.CODIGO_ALIMENTO, "
					+ "B.NOMBRE_NUTRIENTE, "
					+ "B.TIPO_NUTRIENTE, "
					+ "C.NOMBRE_ALIMENTO, "
					+ "C.CALORIAS FROM TBL_NUTRIENTES_X_ALIMENTO A " 
					+ "INNER JOIN TBL_NUTRIENTES B "
					+ "ON A.CODIGO_NUTRIENTE = B.CODIGO_NUTRIENTE " 
					+ "INNER JOIN TBL_ALIMENTOS C "
					+ "ON A.CODIGO_ALIMENTO = C.CODIGO_ALIMENTO "
					);
			while(resultado.next()) {
				lista.add(
						new NutrientesPorAlimento(
								new Nutrientes(
										resultado.getInt("CODIGO_NUTRIENTE"), 
										resultado.getString("NOMBRE_NUTRIENTE"), 
										resultado.getString("TIPO_NUTRIENTE")
										), 
								new Alimentos(
										resultado.getInt("CODIGO_ALIMENTO"), 
										resultado.getString("NOMBRE_ALIMENTO"), 
										resultado.getInt("CALORIAS"))
								)
						);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}