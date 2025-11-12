package libreria.registro;

import libreria.modelo.Libro;

public class ResultadoRegistro {
    public Libro[] arregloLibros;
    public int librosCargados;

    public ResultadoRegistro(Libro[] arregloLibros, int librosCargados) {
        this.arregloLibros = arregloLibros;
        this.librosCargados = librosCargados;
    }
}
