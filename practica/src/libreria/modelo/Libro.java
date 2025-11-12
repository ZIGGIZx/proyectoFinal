package libreria.modelo;

import java.util.Objects;

public class Libro implements Comparable<Libro> {
	private int codigo;
	private String titulo;
	private String autor;
	private double precio;
	private boolean disponible;
	
	public Libro(int codigo) {
		this.codigo= codigo;
	}
	public Libro(String autor) {
		this.autor=autor;
	}
	
	public Libro(int codigo, String titulo, String autor, double precio, boolean disponible) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.disponible = disponible;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public boolean getDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	@Override
	public String toString() {
		return "Libro [codigo=" + codigo + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio
				+ ", disponible=" + disponible + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(autor, codigo, disponible, precio, titulo);
	}

	@Override
    public int compareTo(Libro otroLibro) {
        return Integer.compare(this.codigo, otroLibro.codigo);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Libro)) {
            return false;
        }
        Libro otroLibro = (Libro) obj;
        return compareTo(otroLibro) == 0;
    }
	
}
