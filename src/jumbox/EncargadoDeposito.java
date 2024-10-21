package jumbox;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class EncargadoDeposito extends Usuario {
	private Connection con= (Connection) conexion.getInstance().getConnection();
	public EncargadoDeposito(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, "Encargado de Depósito");
    }
	//CRUD agrega, actualiza stock (modifica) visualiza. 
	
	@Override
	public void agregarProducto(Producto producto) {
	    String query = "INSERT INTO producto (nombre, categoria, precio, stock, ubicacion, fechaVto) VALUES (?, ?, ?, ?, ?, ?)";

	    try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        statement.setString(1, producto.getNombre());
	        statement.setString(2, producto.getCategoria());
	        statement.setDouble(3, producto.getPrecio());
	        statement.setInt(4, producto.getStock());
	        statement.setString(5, producto.getUbicacion());
	        statement.setDate(6, java.sql.Date.valueOf(producto.getFechaVto()));

	        int filas = statement.executeUpdate();
	        if (filas > 0) {
	            JOptionPane.showMessageDialog(null, "Producto agregado con éxito: " + producto.getNombre());
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
                JOptionPane.showMessageDialog(null, "Stock del producto " + producto.getNombre() + " actualizado a: " + nuevoStock);
            } else {
                JOptionPane.showMessageDialog(null, "El producto no se encontró.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al modificar producto: " + e.getMessage());
        }
    }
    @Override
    public void verProductos() {
        String query = "SELECT * FROM producto";

        try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            String productosList = ""; 
            while (resultSet.next()) {
                String nombre = resultSet.getString("nombre");
                String categoria = resultSet.getString("categoria");
                double precio = resultSet.getDouble("precio");
                int stock = resultSet.getInt("stock");
                String ubicacion = resultSet.getString("ubicacion");
                java.sql.Date sqlDate = resultSet.getDate("fechaVto");
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
	@Override
	public void eliminarProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto buscarProductoPorNombre(String nombreProducto) {
	    String query = "SELECT * FROM producto WHERE nombre = ?";
	    Producto producto = null;

	    try (PreparedStatement statement = (PreparedStatement) con.prepareStatement(query)) {
	        statement.setString(1, nombreProducto);
	        ResultSet resultSet = statement.executeQuery();

	        if (resultSet.next()) {
	            String nombre = resultSet.getString("nombre");
	            String categoria = resultSet.getString("categoria");
	            double precio = resultSet.getDouble("precio");
	            int stock = resultSet.getInt("stock");
	            String ubicacion = resultSet.getString("ubicacion");
	            java.sql.Date sqlDate = resultSet.getDate("fechaVto");
	            LocalDate fechaVto = (sqlDate != null) ? sqlDate.toLocalDate() : null;

	            producto = new Producto(nombre, categoria, precio, fechaVto, stock, ubicacion);
	        } else {
	            JOptionPane.showMessageDialog(null, "Producto no encontrado.");
	        }
	    } catch (SQLException e) {
	        JOptionPane.showMessageDialog(null, "Error al buscar producto: " + e.getMessage());
	    }

	    return producto;
	}
}
