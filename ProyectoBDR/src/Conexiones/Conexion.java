package Conexiones;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	Connection conn = null;
    // Librería de MySQL
    public String driver = "com.mysql.cj.jdbc.Driver";

    // Nombre de la base de datos
    public String database = "escuela";

    // Host
    public String hostname = "localhost";

    // Puerto
    public String port = "3306";

    // Ruta de nuestra base de datos (desactivamos el uso de SSL con "?useSSL=false")
    public String url = "jdbc:mysql://" + hostname + ":" + port + "/" + database + "?useSSL=false";

    // Nombre de usuario
    public String username = "root";

    // Clave de usuario
    public String password = "";

    public Connection conectarMySQL() {
        

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }
    
	public Connection getConnection() {
		// TODO Auto-generated method stub
		return conn;
	}


}