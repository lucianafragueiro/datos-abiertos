package data.open.py.connection;

import java.sql.Connection;
import java.sql.DriverManager;
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
			con = DriverManager.getConnection(url,user,pass);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
