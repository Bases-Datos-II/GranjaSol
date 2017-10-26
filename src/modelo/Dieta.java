package modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

public class Dieta
{
	private IntegerProperty codigo;
	private StringProperty nombre;
	private Date fechacreacion;
	private IntegerProperty porciones;
	private StringProperty recomendaciones;
	private IntegerProperty cantidadnutrientes;

	public Dieta(int codigo,String nombre,Date fecha,int porciones,String recomendaciones,int cantidad)
	{
		this.codigo = new SimpleIntegerProperty(codigo);
		this.nombre = new SimpleStringProperty(nombre);
		this.fechacreacion = fecha;
		this.porciones = new SimpleIntegerProperty(porciones);
		this.recomendaciones = new SimpleStringProperty(recomendaciones);
		this.cantidadnutrientes = new SimpleIntegerProperty(cantidad);
	}

	public int getCodigo()
	{
		return codigo.get();
	}
	public void setCodigo(int codigo)
	{
		this.codigo = new SimpleIntegerProperty(codigo);
	}
	public String getNombre()
	{
		return nombre.get();
	}
	public void setNombre(String nombre)
	{
		this.nombre = new SimpleStringProperty(nombre);
	}
	public Date getFechaCreacion()
	{
		return fechacreacion;
	}
	public void setFechaCreacion(Date fecha)
	{
		this.fechacreacion = fecha;
	}
	public int getPorciones()
	{
		return porciones.get();
	}
	public void setPorciones(int porciones)
	{
		this.porciones = new SimpleIntegerProperty(porciones);
	}
	public String getRecomedaciones()
	{
		return recomendaciones.get();
	}
	public void setRecomendaciones(String recomenda)
	{
		this.recomendaciones = new SimpleStringProperty(recomenda);
	}
	public int getCantidadNutrientes()
	{
		return cantidadnutrientes.get();
	}
	public void setCantidadNutrientes(int cantidad)
	{
		this.cantidadnutrientes = new SimpleIntegerProperty(cantidad);
	}
	public IntegerProperty codigoProperty()
	{
		return codigo;
	}
	public StringProperty nombreProperty()
	{
		return nombre;
	}
	public IntegerProperty porcionesProperty()
	{
		return porciones;
	}
	public StringProperty recomendacionesProperty()
	{
		return recomendaciones;
	}
	public IntegerProperty cantidadnutrientesProperty()
	{
		return cantidadnutrientes;
	}

	public int GuardarDieta(Connection connection )
	{
		try
		{
			PreparedStatement instruccion = connection.prepareStatement
					                                   ("INSERT INTO tbl_dieta (codigo_dieta, "
					                                                         + "nombre_dieta, "
					                                                         + "fecha_creacion, "
					                                                         + "porciones, "
					                                                         + "recomendaciones, "
					                                                         + "cantidad_nutrientes) "
					                                                         + "VALUES "
					                                                         + "(S_DIETA.NEXTVAL,?,?,?,?,'')");
			instruccion.setString(1, nombre.get());
			instruccion.setDate(2, fechacreacion);
			instruccion.setInt(3, porciones.get());
			instruccion.setString(4, recomendaciones.get());
			return instruccion.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return 0;
		}
	}

	public static void CargarDietas(Connection connection, ObservableList<Dieta> lista)
	{
		try
		{
			Statement statement = connection.createStatement();
			ResultSet resultado = statement.executeQuery
					("SELECT CODIGO_DIETA, "
							+ "NOMBRE_DIETA, "
							+ "FECHA_CREACION, "
							+ "PORCIONES, "
							+ "RECOMENDACIONES, "
							+ "CANTIDAD_NUTRIENTES "
							+ "FROM TBL_DIETA ");
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
		catch (SQLException e)
		{

			e.printStackTrace();
		}
	}
	@Override
	public String toString()
	{
		return nombre.get();
	}
}