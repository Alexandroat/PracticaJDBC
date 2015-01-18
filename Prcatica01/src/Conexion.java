import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

	private final static String DRIVER = "com.mysql.jdbc.Driver";

	private Connection conn = null;

	public Conexion() {
		System.out.println("Cenectando...");
		try {

			Class.forName(DRIVER);

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:8889/Quizit", "Quizit", "Quizit");
			if (conn != null) {
				System.out.println("---Conexion Establecida---");
			} else {
				System.err
						.println("No se ha podido establecer la conexion con el servidor");
			}
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.err.println(e.getMessage());
		}
	}

	public Connection getConexion() {
		return this.conn;
	}

}
