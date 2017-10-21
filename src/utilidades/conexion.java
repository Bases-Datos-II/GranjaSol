package utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
	private Connection connection;
	private String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	private final String USUARIO = "GSOL";
	private final String CONTRASENA = "oracle";

	public Connection getConexion() {
		return connection;
	}

	public void setConexion(Connection connection) {
		this.connection = connection;
	}

	public void establecerConexion(){
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			connection = DriverManager.getConnection(URL, USUARIO, CONTRASENA);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cerrarConexion(){
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
