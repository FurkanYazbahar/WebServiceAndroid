package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBOpener {
	private static String dbUsername = "root";
	private static String dbPassword = "1234";
	private static String dbName     = "news_DB";
	private static String dbIp       = "localhost";
	private static String dbPort     = "3306";
	private static String driver     = "com.mysql.cj.jdbc.Driver";
	private static String timeZone   = "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	
	
	static private String connectionString = "jdbc:mysql://" + dbIp + ":" + dbPort + "/" + dbName + timeZone;
	
	public static Connection open() {
		return open(dbUsername, dbPassword, dbName, dbIp, dbPort);
	}
	
	public static Connection open(String _dbUsername, String _dbPassword) {
		return open(_dbUsername, _dbPassword, dbName, dbIp, dbPort);
	}
	
	public static Connection open(String _dbUsername, String _dbPassword, String _dbName) {
		return open(_dbUsername, _dbPassword, _dbName, dbIp, dbPort);
	}
		
	public static Connection open(String _dbUsername, String _dbPassword, String _dbName, String _dbIp, String _dbPort) {
		Connection con = null;
		try {
			Class.forName(driver).newInstance();
			
			connectionString = "jdbc:mysql://" + _dbIp + ":" + _dbPort + "/" + _dbName + timeZone;

			con = DriverManager.getConnection(connectionString , _dbUsername, _dbPassword);
			System.out.println("Connection Success");
		} catch(Exception e) {
			System.out.println("Database Connection Error!\n" + _dbIp + _dbName + "\n" + e);
		}        
		return con;
	}
}