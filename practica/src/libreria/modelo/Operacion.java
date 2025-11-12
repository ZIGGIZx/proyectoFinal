package libreria.modelo;

import java.time.LocalDate;

public class Operacion {
	private int identificador;
	private String tipo;
	private Libro libro;
	private Usuario usuario;
	private LocalDate fecha;
	public Operacion(int identificador, String tipo, Libro libro, Usuario usuario, LocalDate fecha) {
		this.identificador = identificador;
		this.tipo = tipo;
		this.libro = libro;
		this.usuario = usuario;
		this.fecha = fecha;
	}
	public int getIdentificador() {
		return identificador;
	}
	public void setId(int identificador) {
		this.identificador = identificador;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Libro getLibro() {
		return libro;
	}
	public void setLibro(Libro libro) {
		this.libro = libro;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	@Override
	public String toString() {
		return "Operacion [id=" + identificador + ", tipo=" + tipo + ", libro=" + libro + ", usuario=" + usuario + ", fecha="
				+ fecha + "]";
	}
}
