package jumbox;

import javax.swing.JOptionPane;

public class Cajero extends Usuario {
	public Cajero(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, "Cajero");
    }
	 @Override
	    public void agregarProducto(Producto producto) {
	        productos.add(producto);
	        JOptionPane.showMessageDialog(null,"Producto ingresado: " + producto.getNombre());
	    }

	    @Override
	    public void verProductos() {
	        super.verProductos();
	    }
	    
		@Override
		public void modificarProducto(Producto producto, int nuevoStock) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public void eliminarProducto(String nombreProducto) {
			// TODO Auto-generated method stub
			
		}
		@Override
		public Producto buscarProductoPorNombre(String nombreProducto) {
			// TODO Auto-generated method stub
			return null;
		}
}
