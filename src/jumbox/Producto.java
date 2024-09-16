package jumbox;

import java.time.LocalDate;

public class Producto {

	private String nombre;
	private String categoria;
	private double precio;
	private LocalDate fechaVto;
	private int stock;
	private String ubicacion;
	
	
	public Producto(String nombre, String categoria, double precio, LocalDate fechaVto, int stock, String ubicacion) {
		super();
		this.nombre = nombre;
		this.categoria = categoria;
		this.precio = precio;
		this.fechaVto = fechaVto;
		this.stock = stock;
		this.ubicacion = ubicacion;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public LocalDate getFechaVto() {
		return fechaVto;
	}
	public void setFechaVto(LocalDate fechaVto) {
		this.fechaVto = fechaVto;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	@Override
	public String toString() {
		return "Producto [nombre=" + nombre + ", categoria=" + categoria + ", precio=" + precio + ", fechaVto="
				+ fechaVto + ", stock=" + stock + ", ubicacion=" + ubicacion + "]";
	}
	
	
	
}
