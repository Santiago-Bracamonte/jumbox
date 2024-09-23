package jumbox;

import java.util.LinkedList;

import javax.swing.JOptionPane;

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

}

