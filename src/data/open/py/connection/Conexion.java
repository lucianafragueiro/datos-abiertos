package data.open.py.connection;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 
 * @author mbenitez
 * Clases encargada de proveer conexion
 * compratida pera la aplicación
 */
public class Conexion {
	private static Conexion conexion;
	private ResourceBundle properties;
	private Connection con;
	
	private Conexion(){
		createConnection();
	}
	
	/**
	 * Método que entrega una instancia 
	 * de la clase Conexion y si hay una
	 * instancia activa no lo crea devuelve la activa
	 * @return Conexion
	 */
	public static Conexion getInstance(){
		if(conexion == null){
			conexion = new Conexion();
		}
		
		return conexion;
	}
	
	/**
	 * Métdo que entrega la conexion de la clase Conexion
	 * @return Connection
	 */
	public static Connection getConnection(){
		return getInstance().con;
	}
	
	/**
	 * Método utilizado para cargar los datos de la conexion
	 * y crea la conexion a la base de datos
	 */
	private void createConnection(){
		String host;
		String port;
		String db;
		String user;
		String pass;
		String url;
		
		
		try {
			
			properties = ResourceBundle.getBundle("data.open.py.util.configuracion");
			host = properties.getString("host");
			port = properties.getString("port");
			db = properties.getString("db");
			user = properties.getString("user");
			pass = properties.getString("passwd");
			url = "jdbc:postgresql://"+host+":"+port+"/"+db;
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,user,pass);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Metodo para ejecutar una consulta en forma dinamica
	 * @param query
	 * @return ResultSet
	 */
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
