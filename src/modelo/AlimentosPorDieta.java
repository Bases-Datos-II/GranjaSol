package modelo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Statement;
import java.sql.Statement;

//import javafx.beans.property.SimpleStringProperty;
//import javafx.beans.property.StringProperty;

public class AlimentosPorDieta{
	private Dieta codigoDieta;
	private Alimentos codigoAlimento;


	public AlimentosPorDieta(Dieta codigoDieta, Alimentos codigoAlimento) {
		this.codigoDieta = codigoDieta;
		this.codigoAlimento = codigoAlimento;

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

	public int Guardaralimentopordieta(Connection connection )
	{
		try
		{	//PreparedStatement instruccion = connection.prepareStatement("EXEC SP_INSERDIETA(?,?,?,?)");
			CallableStatement instruccion = connection.prepareCall("{call SP_INSERT_ALIM_DIETA(?,?)}");
			instruccion.setInt(1, codigoDieta.getCodigo());
			instruccion.setInt(2, codigoAlimento.getCodigoAlimento());
			return instruccion.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}
	public static Dieta traerultimadieta(Connection conexion)
	{
		Dieta d = null;
		Statement statement;
		try {
			  statement = conexion.createStatement();
			  ResultSet resultado = statement.executeQuery("select CODIGO_DIETA, NOMBRE_DIETA, "
			  		                                     + "FECHA_CREACION, "
                                                         + "PORCIONES, RECOMENDACIONES, CANTIDAD_NUTRIENTES "
                                                         + "from TBL_DIETA "
                                                         + "where CODIGO_DIETA = (select CODIGO_DIETA "
                                                                               + "from (select rownum, "
                                                                               + "CODIGO_DIETA "
                                                                               + "from TBL_DIETA "
                                                                               + " order by rownum desc) "
                                                                               + " where rownum=1)");

			 while(resultado.next())
			    { d = new Dieta(resultado.getInt("CODIGO_DIETA"),
				resultado.getString("NOMBRE_DIETA"),
				resultado.getDate("FECHA_CREACION"),
				resultado.getInt("PORCIONES"),
				resultado.getString("RECOMENDACIONES"),
				resultado.getInt("CANTIDAD_NUTRIENTES"));
			    }

			 return d;
				}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}


}