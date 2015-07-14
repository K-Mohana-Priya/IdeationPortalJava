package utility;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
public class DatabaseUtility {
	
	public static final String URL = "jdbc:mysql://localhost:8888/ideation";
	public static final String USERNAME = "myuser";
	public static final String PASSWORD = "tina";
	
	public static Connection getConnection() throws SQLException {
		Connection connection = null;
		connection = (Connection) DriverManager.getConnection(URL, USERNAME, PASSWORD);
		return connection;
	}
	public static void closeConnection(Connection connection) throws SQLException {
		connection.close();
	}
	
	
}
