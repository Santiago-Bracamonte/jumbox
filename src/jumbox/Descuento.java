package jumbox;

import java.time.LocalDate;

import javax.swing.JOptionPane;

abstract class Descuento {

	private double porcentaje;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public Descuento(double porcentaje, LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.porcentaje = porcentaje;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public double getPorcentaje() {
		return porcentaje;
	}
	public void setPorcentaje(double porcentaje) {
		this.porcentaje = porcentaje;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	@Override
	public String toString() {
		return "Descuento [porcentaje=" + porcentaje + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
	}
	
    public boolean aplicarDescuento(Producto producto) {
        LocalDate hoy = LocalDate.now();
        LocalDate dosMesesDesdeAhora = hoy.plusMonths(2);

        if (producto.getFechaVto().isBefore(dosMesesDesdeAhora) || producto.getFechaVto().isEqual(dosMesesDesdeAhora)) {
            double nuevoPrecio = producto.getPrecio() / 2;
            producto.setPrecio(nuevoPrecio);
            JOptionPane.showMessageDialog(null, "Se aplicó un descuento del 50% ya que el producto: " + producto.getNombre() + " vencerá en los próximos dos meses.");
            return true;
        }
        return false; 
    }
}
