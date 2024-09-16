package jumbox;

import java.time.LocalDate;

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
	
	
	
}
