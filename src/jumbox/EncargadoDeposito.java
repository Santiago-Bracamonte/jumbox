package jumbox;

import javax.swing.JOptionPane;

public class EncargadoDeposito extends Usuario {
	
	public EncargadoDeposito(String nombre, String email, String contrasena) {
        super(nombre, email, contrasena, "Encargado de Depósito");
    }
	//CRUD agrega, actualiza stock (modifica) visualiza. 
	
	@Override
    public void agregarProducto(Producto producto) {
        productos.add(producto);
        JOptionPane.showMessageDialog(null, "Producto agregado: " + producto.getNombre());
    }

    @Override
    public void modificarProducto(Producto producto, int nuevoStock) {
        producto.setStock(nuevoStock);
        JOptionPane.showMessageDialog(null,"Stock del producto modificado: " + producto.getNombre() + " - Nuevo stock: " + nuevoStock);
    }

    @Override
    public void verProductos() {
        super.verProductos();
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
