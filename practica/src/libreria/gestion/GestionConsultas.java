package libreria.gestion;

import libreria.estructuras.SimpleLinkedList;
import libreria.modelo.Libro;
import libreria.modelo.Usuario;

public class GestionConsultas {

    // Calcula el monto total de los libros que estan actualmente en prestamo
    public static void calcularMontoTotalDePrestamos(Libro[] arregloLibros) {
        if (arregloLibros == null || arregloLibros.length == 0) {
            System.out.println("No hay libros registrados en el sistema.");
            return;
        }

        double total = 0;
        int cantidadPrestados = 0;

        for (Libro libro : arregloLibros) {
            if (!libro.getDisponible()) {
                total += libro.getPrecio();
                cantidadPrestados++;
            }
        }

        System.out.println("\nMONTO TOTAL DE LIBROS EN PRESTAMO");
        System.out.println("Cantidad de libros prestados: " + cantidadPrestados);
        System.out.println("Monto total: $" + total);
    }

    // Busca libros cuyo autor contenga la subcadena indicada
    public static SimpleLinkedList<Libro> buscarPorAutor(String subcadena, Libro[] arregloLibros) {
        SimpleLinkedList<Libro> listaResultado = new SimpleLinkedList<>();

        if (arregloLibros == null || arregloLibros.length == 0) {
            System.out.println("No hay libros registrados en el sistema.");
            return listaResultado;
        }

        // Buscar libros que contengan la subcadena en el autor
        for (Libro libro : arregloLibros) {
            if (libro.getAutor().toLowerCase().contains(subcadena.toLowerCase())) {
                listaResultado.addLast(libro);
            }
        }

        // Mostrar resultado
        if (listaResultado.size() == 0) {
            System.out.println("No se encontraron libros con esa subcadena en el autor.");
        } else {
            System.out.println("\nLIBROS CON AUTOR QUE CONTIENE: '" + subcadena + "'");
            System.out.println("Total encontrados: " + listaResultado.size());
            System.out.println("-");
            listaResultado.mostrar();
        }

        return listaResultado;
    }

    // Busca usuarios con cantidad de prestamos igual o mayor a un valor dado
    public static SimpleLinkedList<Usuario> usuariosConMasPrestamosQue(Usuario[] arregloUsuarios, int cantidad) {
        SimpleLinkedList<Usuario> listaResultado = new SimpleLinkedList<>();

        if (arregloUsuarios == null || arregloUsuarios.length == 0) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return listaResultado;
        }

        // Buscar usuarios con cantidad de prestamos >= cantidad
        for (Usuario usuario : arregloUsuarios) {
            if (usuario.getCantidadPrestados() >= cantidad) {
                listaResultado.addLast(usuario);
            }
        }

        // Mostrar resultado
        if (listaResultado.size() == 0) {
            System.out.println("No hay usuarios con " + cantidad + " o mas libros prestados.");
        } else {
            System.out.println("\nUSUARIOS CON " + cantidad + " O MAS LIBROS PRESTADOS");
            System.out.println("Total encontrados: " + listaResultado.size());
            System.out.println("-");
            listaResultado.mostrar();
        }

        return listaResultado;
    }
}
