package data.open.py.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

public class Conexion {
	private static Conexion conexion;
	private ResourceBundle properties;
	private Connection con;
	
	private Conexion(){
		createConnection();
	}
	
	public static Conexion getInstance(){
		if(conexion == null){
			conexion = new Conexion();
		}
		
		return conexion;
	}
	
	public static Connection getConnection(){
		return getInstance().con;
	}
		
	private void createConnection(){
		String host;
		String port;
		String db;
		String user;
		String pass;
		String url;
		properties = ResourceBundle.getBundle("data.open.py.util.configuracion");
		host = properties.getString("host");
		port = properties.getString("port");
		db = properties.getString("db");
		user = properties.getString("user");
		pass = properties.getString("passwd");
		url = "jdbc:postgresql://"+host+":"+port+"/"+db;
		
		try {
			Class.forName("org.postgresql.Driver");
			//String dbUrl = System.getenv("JDBC_DATABASE_URL");
			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResultByQuery(String query){
		Statement statm;
		ResultSet result = null;
		
		try {
			statm = getConnection().createStatement();
			result = statm.executeQuery(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
		
	}
	
}
