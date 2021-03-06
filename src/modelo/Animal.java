package modelo;


import java.sql.CallableStatement;
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

public class Animal{
	private StringProperty codigoAnimal;
	private EspecieAnimal codigoEspecieAnimal;
	private Date fechaNacimiento;
	private StringProperty sexo;
	private IntegerProperty necesidadNutri;
	private IntegerProperty coste;
	private TipoAnimal nombreTipoANI;
	private StringProperty NombreTipoAnimal;
	private StringProperty Caracteristica;

	public Animal(String codigoAnimal, EspecieAnimal codigoEspecieAnimal, Date fechaNacimiento,
String sexo, int necesidadNutri, int coste) {
		this.codigoAnimal = new SimpleStringProperty(codigoAnimal);
		this.codigoEspecieAnimal = codigoEspecieAnimal;
		this.fechaNacimiento = fechaNacimiento;
		this.sexo = new SimpleStringProperty(sexo);
		this.necesidadNutri = new SimpleIntegerProperty(necesidadNutri);
		this.coste = new SimpleIntegerProperty(coste);
		setNombreTipoAnimal(codigoEspecieAnimal.getCodigoTipoAnimal().getNombreTipo());
		setCaracteristica(codigoEspecieAnimal.getCaracteristica());
	}
	public String getNombreTipoAnimal() {
		return NombreTipoAnimal.get();
	}
	public void setNombreTipoAnimal(String NombreTipoAnimal) {
		this.NombreTipoAnimal = new SimpleStringProperty(NombreTipoAnimal);
	}
	public StringProperty NombreTipoAnimalProperty() {
		return NombreTipoAnimal;
	}


	public String getCaracteristica() {
		return Caracteristica.get();
	}
	public void setCaracteristica(String Caracteristica) {
		this.Caracteristica = new SimpleStringProperty(Caracteristica);
	}
	public StringProperty CaracteristicaProperty() {
		return Caracteristica;
	}


	public TipoAnimal getNombreTipoANI() {
		return nombreTipoANI;
	}
	public void setNombreTipoANI(TipoAnimal nombreTipoANI) {
		this.nombreTipoANI = nombreTipoANI;
	}

	//Metodos atributo: codigoAnimal
	public String getCodigoAnimal() {
		return codigoAnimal.get();
	}
	public void setCodigoAnimal(String codigoAnimal) {
		this.codigoAnimal = new SimpleStringProperty(codigoAnimal);
	}
	public StringProperty CodigoAnimalProperty() {
		return codigoAnimal;
	}
	//Metodos atributo: codigoEspecieAnimal
	public EspecieAnimal getCodigoEspecieAnimal() {
		return codigoEspecieAnimal;
	}
	public void setCodigoEspecieAnimal(EspecieAnimal codigoEspecieAnimal) {
		this.codigoEspecieAnimal = codigoEspecieAnimal;
	}
	//Metodos atributo: fechaNacimiento
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	//Metodos atributo: sexo
	public String getSexo() {
		return sexo.get();
	}
	public void setSexo(String sexo) {
		this.sexo = new SimpleStringProperty(sexo);
	}
	public StringProperty SexoProperty() {
		return sexo;
	}
	//Metodos atributo: necesidadNutri
	public int getNecesidadNutri() {
		return necesidadNutri.get();
	}
	public void setNecesidadNutri(int necesidadNutri) {
		this.necesidadNutri = new SimpleIntegerProperty(necesidadNutri);
	}
	public IntegerProperty NecesidadNutriProperty() {
		return necesidadNutri;
	}
	//Metodos atributo: coste
	public int getCoste() {
		return coste.get();
	}
	public void setCoste(int coste) {
		this.coste = new SimpleIntegerProperty(coste);
	}
	public IntegerProperty CosteProperty() {
		return coste;
	}






	public static int actualizarAnimal(Connection connection, String codigoAnimal, int necesidadNutri, int coste){

		try {
			CallableStatement instruccion = connection.prepareCall("{call ACTUALIZARANIMAL(?,?,?)}");
			instruccion.setString(1,codigoAnimal);
			instruccion.setInt(2,necesidadNutri);
			instruccion.setInt(3, coste);
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Actualizacion con �xito");
		return 0;
	}

	public int guardarAnimal(Connection connection){
		try {
			CallableStatement instruccion = connection.prepareCall("{call SP_INGRESARANIMAL(?,?,?,?,?,?)}");
			instruccion.setString(1,codigoAnimal.get());
			instruccion.setInt(2,codigoEspecieAnimal.getCodigoEspecie());
			instruccion.setDate(3, fechaNacimiento);
			instruccion.setString(4, sexo.get());
			instruccion.setInt(5, necesidadNutri.get());
			instruccion.setInt(6, coste.get());
			return instruccion.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Registro con Exito con �xito");
		return 0;


	}

	@Override
	public String toString()
	{
		return codigoAnimal.get() + " ( " + fechaNacimiento.toString()+ " )";
	}

	public static void llenarAnimal(Connection connection, ObservableList<Animal> listaAnimal){
		try {
			String consulta = "SELECT A.CODIGO_ANIMAL,A.CODIGO_ESPECIE, B.CODIGO_TIPO_ANIMAL, "
							+ "C.NOMBRE_TIPO,B.NOMBRE_ESPECIE,B.CARACTERISTICA,B.USO, "
							+ "A.FECHA_NACIMIENTO, A.SEXO, A.NECESIDAD_NUTRI, A.COSTE_ANIMAL "
							+ "FROM TBL_ANIMAL A "
							+ "INNER JOIN TBL_ESPECIE_ANIMAL B ON(A.CODIGO_ESPECIE = B.CODIGO_ESPECIE) "
							+ "INNER JOIN TBL_TIPO_ANIMAL C ON(B.CODIGO_TIPO_ANIMAL = C.CODIGO_TIPO_ANIMAL)";
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(consulta);

			while(resultado.next())
			{
				listaAnimal.add(new Animal(resultado.getString("CODIGO_ANIMAL"),
								new EspecieAnimal(resultado.getInt("CODIGO_ESPECIE"),
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
						resultado.getInt("COSTE_ANIMAL")
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
	public static void filtroanimal(Connection connection, ObservableList<Animal> listAnimal)
	{
		String consulta = "SELECT  A.CODIGO_ANIMAL,A.CODIGO_ESPECIE, B.CODIGO_TIPO_ANIMAL, "
							+ "C.NOMBRE_TIPO,B.NOMBRE_ESPECIE,B.CARACTERISTICA,B.USO, "
							+ "A.FECHA_NACIMIENTO, A.SEXO, A.NECESIDAD_NUTRI, A.COSTE_ANIMAL "
							+ "FROM TBL_ANIMAL A "
							+ "INNER JOIN TBL_ESPECIE_ANIMAL B ON(A.CODIGO_ESPECIE = B.CODIGO_ESPECIE) "
							+ "INNER JOIN TBL_TIPO_ANIMAL C ON(B.CODIGO_TIPO_ANIMAL = C.CODIGO_TIPO_ANIMAL) "
				        + "inner join TBL_HISTORIAL D on (A.CODIGO_ANIMAL = D.CODIGO_ANIMAL) "
				        + "where A.NECESIDAD_NUTRI <= (select CANTIDAD_NUTRIENTES "
				        + "from (select rownum, CANTIDAD_NUTRIENTES "
				        + "from TBL_DIETA order by rownum desc) where rownum=1) "
				        + "AND D.FECHA_FIN < SYSDATE";

		try {
			Statement instruccion = connection.createStatement();
			ResultSet resultado = instruccion.executeQuery(consulta);

			while(resultado.next())
			{
				listAnimal.add(new Animal(resultado.getString("CODIGO_ANIMAL"),
								new EspecieAnimal(resultado.getInt("CODIGO_ESPECIE"),
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
						resultado.getInt("COSTE_ANIMAL")
						)
						);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



	}


}