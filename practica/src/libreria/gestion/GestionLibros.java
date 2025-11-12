package libreria.gestion;

import java.util.Random;
import java.util.Scanner;

import Helper.Helper;
import libreria.estructuras.BinarySearchTree;
import libreria.modelo.Libro;
import libreria.registro.ResultadoRegistro;

public class GestionLibros {

    public static ResultadoRegistro registrarLibros(Libro[] arregloLibros, BinarySearchTree<Libro> arbolLibros) {
        Scanner input = new Scanner(System.in);
        Random random = new Random();

        int cantidad = Helper.validarEntero("Cuantos libros desea registrar?: ", input);

        int librosAnteriores = 0;
        if (arregloLibros != null) {
            librosAnteriores = arregloLibros.length;
        }

        Libro[] nuevoArreglo = new Libro[librosAnteriores + cantidad];

        if (arregloLibros != null) {
            for (int i = 0; i < arregloLibros.length; i++) {
                nuevoArreglo[i] = arregloLibros[i];
            }
        }

        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nLibro " + (i + 1) + " de " + cantidad + " -");

            boolean codigoValido = false;
            int codigo = 0;
            while (!codigoValido) {
                codigo = random.nextInt(100) + 1;
                if (arbolLibros.buscar(new Libro(codigo)) == null) {
                    codigoValido = true;
                    System.out.println("Codigo generado: " + codigo);
                }
            }

            String titulo = Helper.validarString("Ingrese el titulo: ", input);
            String autor = Helper.validarString("Ingrese el autor: ", input);
            double precio = Helper.validarDouble("Ingrese el precio: ", input);

            Libro libro = new Libro(codigo, titulo, autor, precio, true);

            int posicion = librosAnteriores + i;
            nuevoArreglo[posicion] = libro;
            arbolLibros.add(libro);

            System.out.println("Libro registrado exitosamente.");
        }

        return new ResultadoRegistro(nuevoArreglo, cantidad);
    }

    public static void consultarLibros(Libro[] arregloLibros) {
        if (arregloLibros == null || arregloLibros.length == 0) {
            System.out.println("No hay libros registrados en el sistema.");
            return;
        }

        System.out.println("\nCATALOGO DE LIBROS");
        System.out.println("Total de libros: " + arregloLibros.length);

        for (Libro libro : arregloLibros) {
            System.out.println(libro);
        }
    }
}
