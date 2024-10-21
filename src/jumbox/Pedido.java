package jumbox;

import java.time.LocalDate;

public class Pedido {
	private LocalDate fechaPedido;
	private String estado; 
	private int cantidad;
	
	public Pedido(LocalDate fechaPedido, String estado, int cantidad) {
		super();
		this.fechaPedido = fechaPedido;
		this.estado = estado;
		this.cantidad = cantidad;
	}
	
	public LocalDate getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(LocalDate fechaPedido) {
		this.fechaPedido = fechaPedido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	
	@Override
	public String toString() {
		return "Pedido [fechaPedido=" + fechaPedido + ", estado=" + estado + ", cantidad=" + cantidad + "]";
	}
	
	
	
}
