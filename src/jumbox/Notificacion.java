package jumbox;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class Notificacion {
	
	private LocalDate fechaNotificacion;
	private String tipoNotificacion;
	private String estado;
	public Notificacion(LocalDate fechaNotificacion, String tipoNotificacion, String estado) {
		super();
		this.fechaNotificacion = fechaNotificacion;
		this.tipoNotificacion = tipoNotificacion;
		this.estado = estado;
	}
	public LocalDate getFechaNotificacion() {
		return fechaNotificacion;
	}
	public void setFechaNotificacion(LocalDate fechaNotificacion) {
		this.fechaNotificacion = fechaNotificacion;
	}
	public String getTipoNotificacion() {
		return tipoNotificacion;
	}
	public void setTipoNotificacion(String tipoNotificacion) {
		this.tipoNotificacion = tipoNotificacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	@Override
	public String toString() {
		return "Notificacion [fechaNotificacion=" + fechaNotificacion + ", tipoNotificacion=" + tipoNotificacion
				+ ", estado=" + estado + "]";
	}
		public void generarNotificacionStockBajo(Producto producto) {
			if (producto.getStock()<100) {
				 String tipoNotificacion = "Stock bajo";
			        String estado = "Pendiente";			        
			       
			        JOptionPane.showMessageDialog(null, 
			            "Notificación generada:\n" +
			            "Tipo: " + tipoNotificacion + "\n" +
			            "Estado: " + estado + "\n" +
			            "Producto: " + producto.getNombre() + "\n" +
			            "Stock actual: " + producto.getStock() + " unidades.",
			            "Alerta de Stock Bajo", 
			            JOptionPane.WARNING_MESSAGE);
			    }
		}
	
	
}
