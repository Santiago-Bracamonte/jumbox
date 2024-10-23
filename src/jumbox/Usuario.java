package jumbox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public abstract class Usuario {
	private String nombre;
	private String email;
	private String contrasena;
	private String tipoUsuario;
	LinkedList<Usuario> usuarios = new 	LinkedList<Usuario>();
	LinkedList<Producto> productos = new LinkedList<Producto>();
	


	public Usuario(String nombre, String email, String contrasena, String tipoUsuario) {
	super();
	this.nombre = nombre;
	this.email = email;
	this.contrasena = contrasena;
	this.tipoUsuario = tipoUsuario;
}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	 
	 public String getContrasena() {
		return contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}


	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", contrasena=" + contrasena + ", tipoUsuario="
				+ tipoUsuario + ", usuarios=" + usuarios + "]";
	}

	 public Usuario login(String email, String contrasena, LinkedList<Usuario> usuarios) {
	        for (Usuario usuario : usuarios) {
	            if (usuario.getEmail().equals(email) && usuario.getContrasena().equals(contrasena)) {
	                return usuario;
	            }
	        }
	        JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
	        return null; 
}
	 
	 public abstract void agregarProducto (Producto producto);
	 
	 public abstract void modificarProducto (Producto producto, int nuevoStock);
	
	 public abstract void eliminarProducto (String nombreProducto);
	 public void verProductos() {
	        if (productos.isEmpty()) {
	            JOptionPane.showMessageDialog(null, "No hay productos en el inventario.");
	        } else {
	            String productosList = "Productos en inventario:\n";
	            for (Producto producto : productos) {
	                productosList += producto.getNombre() + " - Stock: " + producto.getStock() + "\n";
	            }
	            JOptionPane.showMessageDialog(null, productosList);
	        }
	    }
	 public abstract Producto buscarProductoPorNombre(String nombreProducto);

	 public static Usuario iniciarSesion(String email, String contrasena) {
		 Connection conn = (Connection) conexion.getInstance().getConnection();
	        Usuario usuarioEncontrado = null;

	        try {
	            String query = "SELECT * FROM usuario WHERE email = ? AND contrasena = ?";
	            PreparedStatement statement = (PreparedStatement) conn.prepareStatement(query);
	            statement.setString(1, email);
	            statement.setString(2, contrasena);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                String nombre = resultSet.getString("nombre");
	                String tipoUsuario = resultSet.getString("tipo_usuario");

	                if (tipoUsuario.equalsIgnoreCase("Administrador")) {
	                    usuarioEncontrado = new Administrador(nombre, email, contrasena);
	                } else if (tipoUsuario.equalsIgnoreCase("Cajero")) {
	                    usuarioEncontrado = new Cajero(nombre, email, contrasena);
	                } else if (tipoUsuario.equalsIgnoreCase("EncargadoDeposito")) {
	                    usuarioEncontrado = new EncargadoDeposito(nombre, email, contrasena);
	                }

	                JOptionPane.showMessageDialog(null, "Inicio de sesión exitoso. ¡Bienvenido, " + nombre + "!");
	            } else {
	                JOptionPane.showMessageDialog(null, "Email o contraseña incorrectos", "Error de autenticación", JOptionPane.ERROR_MESSAGE);
	            }

	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
	        }

	        return usuarioEncontrado;
	    }
		 
	 public static void registrarUsuario() {
			Connection conn = (Connection) conexion.getInstance().getConnection();

			String nombre = JOptionPane.showInputDialog("Ingrese su nombre:");
			String email = JOptionPane.showInputDialog("Ingrese su email:");
			String contrasena = JOptionPane.showInputDialog("Ingrese su contraseña:");

			String []tipoUsuario = { "Adminsitrador", "Cajero", "Encargado de Deposito" };
			String opcion = (String) JOptionPane.showInputDialog(null, "Seleccione una opción", "Menú principal",
					JOptionPane.PLAIN_MESSAGE, null, tipoUsuario, tipoUsuario[0]);
			try {
				String query = "INSERT INTO usuario (nombre, email, contrasena, tipo_usuario) VALUES (?, ?, ?, ?)";
				PreparedStatement statement = (PreparedStatement) conn.prepareStatement(query);
				statement.setString(1, nombre);
				statement.setString(2, email);
				statement.setString(3, contrasena);
				statement.setString(4, opcion);

				int filas = statement.executeUpdate();
				if (filas > 0) {
					JOptionPane.showMessageDialog(null, "Registro exitoso");
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null, "Error al registrar usuario en la base de datos.", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
	 
}

