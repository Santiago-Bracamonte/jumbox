package jumbox;


public class Usuario {
	private String nombre;
	private String email;
	private String tipoUsuario;
	
	public Usuario(String nombre, String email, String tipoUsuario) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.tipoUsuario = tipoUsuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", email=" + email + ", tipoUsuario=" + tipoUsuario + "]";
	}

	

}
