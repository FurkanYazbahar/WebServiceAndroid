package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
		
	static String ip       = "localhost";
	static String port     = "3306";
	static String userName = "root";
	static String pass     = "1234";
	static String database = "news_DB";
	static String timeZone = "?useUnicode=true&useLegacyDatetimeCode=false&serverTimezone=Turkey";
	static private String connectString = "jdbc:mysql://" + ip + ":" + port + "/" + database + timeZone;
	
	public Connection getConnect(){
		Connection c = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			c = DriverManager.getConnection(connectString,userName,pass);
			System.out.println("Connection Success"); 
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection Fails");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	
	public static void main(String[] args) {
		MySqlConnection con = new MySqlConnection();
		con.getConnect();
	}
}