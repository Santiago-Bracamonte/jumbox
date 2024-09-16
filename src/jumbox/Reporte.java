package jumbox;

import java.time.LocalDate;

public class Reporte {

	private String tipoReporte; //ventas o inventario
	private LocalDate fechaReporte;
	private String datos;
	
	public Reporte(String tipoReporte, LocalDate fechaReporte, String datos) {
		super();
		this.tipoReporte = tipoReporte;
		this.fechaReporte = fechaReporte;
		this.datos = datos;
	}

	public String getTipoReporte() {
		return tipoReporte;
	}

	public void setTipoReporte(String tipoReporte) {
		this.tipoReporte = tipoReporte;
	}

	public LocalDate getFechaReporte() {
		return fechaReporte;
	}

	public void setFechaReporte(LocalDate fechaReporte) {
		this.fechaReporte = fechaReporte;
	}

	public String getDatos() {
		return datos;
	}

	public void setDatos(String datos) {
		this.datos = datos;
	}

	@Override
	public String toString() {
		return "Reporte [tipoReporte=" + tipoReporte + ", fechaReporte=" + fechaReporte + ", datos=" + datos + "]";
	}
	
	
	
}
