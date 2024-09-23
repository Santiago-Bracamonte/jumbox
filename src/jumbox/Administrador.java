package jumbox;

import javax.swing.JOptionPane;

public class Administrador extends Usuario {


	    public Administrador(String nombre, String email, String contrasena) {
	        super(nombre, email, contrasena, "Administrador");
	    }
	    //CRUD completo.Ingresa, modifica, elimina y visualiza. 

	    @Override
	    public void agregarProducto(Producto producto) {
	        productos.add(producto);
	        JOptionPane.showMessageDialog(null,"Producto agregado: " + producto.getNombre());
	    }

	    @Override
	    public void modificarProducto(Producto producto, int nuevoStock) {
	        producto.setStock(nuevoStock);
	        JOptionPane.showMessageDialog(null, "Stock del producto modificado: " + producto.getNombre() + " - Nuevo stock: " + nuevoStock);
	    }

	    @Override
	    public void eliminarProducto(String nombreProducto) {
	        Producto producto = buscarProductoPorNombre(nombreProducto);
	        if (producto != null) {
	            productos.remove(producto);
	            JOptionPane.showMessageDialog(null,"Producto eliminado: " + nombreProducto);
	        } else {
	        	JOptionPane.showMessageDialog(null,"Producto no encontrado: " + nombreProducto);
	        }
	    }

	    public Producto buscarProductoPorNombre(String nombreProducto) {
	        for (Producto p : productos) {
	            if (p.getNombre().equalsIgnoreCase(nombreProducto)) {
	                return p;
	            }
	        }
	        return null;
	    }

	    @Override
	    public void verProductos() {
	        super.verProductos();
	    }
	
	

	
	
}
