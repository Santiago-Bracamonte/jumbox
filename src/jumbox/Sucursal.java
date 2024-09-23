package jumbox;

import java.util.LinkedList;

import javax.swing.JOptionPane;

public class Sucursal {

	private String nombre;
	private String ubicacion;
	private LinkedList<Producto> productos;
	

	public Sucursal(String nombre, String ubicacion, LinkedList<Producto> productos) {
		super();
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.productos = new LinkedList<>();
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	@Override
	public String toString() {
		return "Sucursal [nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
	}
	
	public void agregarProducto(Producto producto) {
        productos.add(producto);
    }
	
	public void generarReporte() {
		String reporte = "Reporte de la Sucursal: " + this.nombre + "\n" +
                "Ubicación: " + this.ubicacion + "\n" +
                "Listado de productos:\n";
		
		if (productos.isEmpty()) {
			  reporte += "No hay productos en esta sucursal.\n";
		}else {
			for (Producto producto : productos) {
                reporte += producto.toString() + "\n";
            }
		}
		JOptionPane.showMessageDialog(null, reporte);
	}
}
