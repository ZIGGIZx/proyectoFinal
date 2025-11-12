package libreria.modelo;

public class Usuario {
	private int numeroDeUsuario;
	private int dni;
	private String nombre;
	private String direccion;
	private int telefono;
	private int cantidadPrestados;
	
	
	
	public Usuario(int numeroDeUsuario, int dni, String nombre, String direccion, int telefono,int cantidadPrestados) {
		this.numeroDeUsuario = numeroDeUsuario;
		this.dni = dni;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.cantidadPrestados = cantidadPrestados;
	}
	
	public int getNumeroDeUsuario() {
		return numeroDeUsuario;
	}


	public void setNombreDeUsuario(int numeroDeUsuario) {
		this.numeroDeUsuario = numeroDeUsuario;
	}


	public int getDni() {
		return dni;
	}


	public void setDni(int dni) {
		this.dni = dni;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getDireccion() {
		return direccion;
	}


	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}


	public int getTelefono() {
		return telefono;
	}


	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}


	public int getCantidadPrestados() {
		return cantidadPrestados;
	}


	public void setCantidadPrestados(int cantidadPrestados) {
		this.cantidadPrestados = cantidadPrestados;
	}

	@Override
	public String toString() {
		return "Usuario [numeroDeUsuario=" + numeroDeUsuario + ", dni=" + dni + ", nombre=" + nombre + ", direccion="
				+ direccion + ", telefono=" + telefono + ", cantidadPrestados=" + cantidadPrestados + "]";
	}
}

