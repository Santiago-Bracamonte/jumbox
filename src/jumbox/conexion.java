package jumbox;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class conexion {
private static String URL ="jdbc:mysql://localhost:3306/Jumbox";
private static String USER = "root";
private static String PASSWORD="";

private static Connection conect;
private static conexion instance;

private conexion() {
	try {
		conect = (Connection) DriverManager.getConnection(URL,USER,PASSWORD);
		JOptionPane.showMessageDialog(null, "se conectó a la BD");
	} catch (SQLException e) {
		JOptionPane.showMessageDialog(null, "NO se conectó a la BD");

	}
}
public static conexion getInstance() {
	if (instance == null) {
		instance = new conexion();
	}
	return instance;
}
public Connection getConnection() {
	return conect;
}

}
