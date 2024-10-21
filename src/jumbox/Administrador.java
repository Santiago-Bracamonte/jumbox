package jumbox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Administrador extends Usuario {

		private Connection con= (Connection) conexion.getInstance().getConnection()
;	    public Administrador(String nombre, String email, String contrasena) {
	        super(nombre, email, contrasena, "Administrador");
	    }
	    //CRUD completo.Ingresa, modifica, elimina y visualiza. 

	    @Override
	    public void agregarProducto(Producto producto) {
	        String query = "INSERT INTO producto (nombre, categoria, precio, fechaVto, stock, ubicacion) VALUES (?, ?, ?, ?, ?, ?)";

	        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        	statement.setString(1, producto.getNombre());
	        	statement.setString(2, producto.getCategoria());
	        	statement.setDouble(3, producto.getPrecio());
	        	java.sql.Date fechaSQL = java.sql.Date.valueOf(producto.getFechaVto());	           
	        	statement.setDate(4, fechaSQL);	        	statement.setInt(5, producto.getStock());
	        	statement.setString(6, producto.getUbicacion());
	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                JOptionPane.showMessageDialog(null, "Producto agregado a la base de datos: " + producto.getNombre());
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al agregar producto: " + e.getMessage());
	        }
	    }

	    @Override
	    public void modificarProducto(Producto producto, int nuevoStock) {
	        String query = "UPDATE producto SET stock = ? WHERE nombre = ?";

	        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        	statement.setInt(1, nuevoStock);
	            statement.setString(2, producto.getNombre());

	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                JOptionPane.showMessageDialog(null, "Stock actualizado para: " + producto.getNombre() + " - Nuevo stock: " + nuevoStock);
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + e.getMessage());
	        }
	    }

	    @Override
	    public void eliminarProducto(String nombreProducto) {
	        String query = "DELETE FROM producto WHERE nombre = ?";

	        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        	statement.setString(1, nombreProducto);

	            int filas = statement.executeUpdate();
	            if (filas > 0) {
	                JOptionPane.showMessageDialog(null, "Producto eliminado de la base de datos: " + nombreProducto);
	            } else {
	                JOptionPane.showMessageDialog(null, "Producto no encontrado: " + nombreProducto);
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al eliminar producto: " + e.getMessage());
	        }
	    }

	    public Producto buscarProductoPorNombre(String nombreProducto) {
	        String query = "SELECT * FROM producto WHERE nombre = ?";
	        Producto producto = null;

	        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        	statement.setString(1, nombreProducto);

	            try (ResultSet ResulSet = statement.executeQuery()) {
	                if (ResulSet.next()) {
	                    String categoria = ResulSet.getString("categoria");
	                    double precio = ResulSet.getDouble("precio");
	                    int stock = ResulSet.getInt("stock");
	                    String ubicacion = ResulSet.getString("ubicacion");

	                    producto = new Producto(nombreProducto, categoria, precio, stock, ubicacion);
	                }
	            }
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
	        }

	        return producto;
	    }

	    @Override
	    public void verProductos() {
	        String query = "SELECT * FROM producto";

	        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
	             ResultSet ResulSet = statement.executeQuery()) {

	            String productosList = ""; 
	            while (ResulSet.next()) {
	                String nombre = ResulSet.getString("nombre");
	                String categoria = ResulSet.getString("categoria");
	                double precio = ResulSet.getDouble("precio");
	                int stock = ResulSet.getInt("stock");
	                String ubicacion = ResulSet.getString("ubicacion");
	                java.sql.Date sqlDate = ResulSet.getDate("fechaVto");  
	                LocalDate fechaVto = (sqlDate != null) ? sqlDate.toLocalDate() : null; 

	                productosList += "Nombre: " + nombre
	                        + ", Categoría: " + categoria
	                        + ", Precio: " + precio
	                        + ", Stock: " + stock
	                        + ", Ubicación: " + ubicacion
	                        + ", Fecha de Vencimiento: " + fechaVto
	                        + "\n";
	            }
	            JOptionPane.showMessageDialog(null, "Lista de productos:\n" + productosList);
	        } catch (SQLException e) {
	            JOptionPane.showMessageDialog(null, "Error al ver productos: " + e.getMessage());
	        }
	    }
	
	

	
	
}
