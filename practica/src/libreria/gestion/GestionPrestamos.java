package libreria.gestion;

import java.time.LocalDate;
import java.util.Scanner;

import Helper.Helper;
import libreria.estructuras.BinarySearchTree;
import libreria.estructuras.Queue;
import libreria.estructuras.Stack;
import libreria.modelo.Libro;
import libreria.modelo.Operacion;
import libreria.modelo.Usuario;

public class GestionPrestamos {

    public static void realizarPrestamo(BinarySearchTree<Libro> arbolLibros, Usuario[] arregloUsuarios,
            Stack<Operacion> acciones, Queue<Usuario> pendientes) {
        Scanner input = new Scanner(System.in);

        if (arregloUsuarios == null || arregloUsuarios.length == 0) {
            System.out.println("no hay usuarios registrados en el sistema.");
            return;
        }

        Usuario usuario = null;
        while (usuario == null) {
            int numeroUsuario = Helper.validarEntero("ingrese el numero del usuario: ", input);
            for (int i = 0; i < arregloUsuarios.length; i++) {
                if (arregloUsuarios[i].getNumeroDeUsuario() == numeroUsuario) {
                    usuario = arregloUsuarios[i];
                    break;
                }
            }
            if (usuario == null) {
                System.out.println("usuario no encontrado, intente de nuevo.");
            }
        }

        Libro libro = null;
        while (libro == null) {
            int codigoLibro = Helper.validarEntero("ingrese el codigo del libro: ", input);
            libro = arbolLibros.buscar(new Libro(codigoLibro, "", "", 0.0, true));
            if (libro == null) {
                System.out.println("libro no encontrado, intente de nuevo.");
            }
        }

        if (!libro.getDisponible()) {
            System.out.println("el libro no esta disponible, se agrega el usuario a la cola de espera.");
            pendientes.add(usuario);
            return;
        }

        libro.setDisponible(false);
        usuario.setCantidadPrestados(usuario.getCantidadPrestados() + 1);
        Operacion operacion = new Operacion(acciones.size() + 1, "prestamo", libro, usuario, LocalDate.now());
        acciones.push(operacion);

        System.out.println("prestamo realizado con exito.");
        System.out.println("libro: " + libro.getTitulo());
        System.out.println("usuario: " + usuario.getNombre());
    }

    public static void realizarDevolucion(Libro[] arregloLibros, Usuario[] arregloUsuarios, Stack<Operacion> acciones,
            BinarySearchTree<Libro> arbolLibros) {
        Scanner input = new Scanner(System.in);

        if (arregloUsuarios == null || arregloUsuarios.length == 0) {
            System.out.println("no hay usuarios registrados en el sistema.");
            return;
        }

        int numeroUsuario = Helper.validarEntero("ingrese el numero de usuario: ", input);
        Usuario usuario = null;
        for (int i = 0; i < arregloUsuarios.length; i++) {
            if (arregloUsuarios[i].getNumeroDeUsuario() == numeroUsuario) {
                usuario = arregloUsuarios[i];
                break;
            }
        }

        if (usuario == null) {
            System.out.println("usuario no encontrado.");
            return;
        }

        Libro libro = null;
        while (libro == null) {
            int codigoLibro = Helper.validarEntero("ingrese el codigo del libro: ", input);
            libro = arbolLibros.buscar(new Libro(codigoLibro, "", "", 0.0, true));
            if (libro == null) {
                System.out.println("libro no encontrado, intente de nuevo.");
            }
        }

        if (libro.getDisponible()) {
            System.out.println("el libro ya esta disponible, no hay prestamo registrado.");
            return;
        }

        libro.setDisponible(true);
        usuario.setCantidadPrestados(usuario.getCantidadPrestados() - 1);
        Operacion operacion = new Operacion(acciones.size() + 1, "devolucion", libro, usuario, LocalDate.now());
        acciones.push(operacion);

        System.out.println("devolucion realizada con exito.");
        System.out.println("libro: " + libro.getTitulo());
        System.out.println("usuario: " + usuario.getNombre());
    }

    public static void reversionDeOperaciones(Stack<Operacion> acciones) {
        if (acciones.empty()) {
            System.out.println("no hay operaciones para revertir.");
            return;
        }

        Operacion operacion = acciones.pop();

        if (operacion.getTipo().equals("prestamo")) {
            operacion.getLibro().setDisponible(true);
            operacion.getUsuario().setCantidadPrestados(operacion.getUsuario().getCantidadPrestados() - 1);

            System.out.println("se ha revertido el prestamo:");
            System.out.println("libro: " + operacion.getLibro().getTitulo());
            System.out.println("usuario: " + operacion.getUsuario().getNombre());

        } else if (operacion.getTipo().equals("devolucion")) {
            operacion.getLibro().setDisponible(false);
            operacion.getUsuario().setCantidadPrestados(operacion.getUsuario().getCantidadPrestados() + 1);

            System.out.println("se ha revertido la devolucion:");
            System.out.println("libro: " + operacion.getLibro().getTitulo());
            System.out.println("usuario: " + operacion.getUsuario().getNombre());
        }
    }

    public static void atenderPendientes(BinarySearchTree<Libro> arbolLibros, Queue<Usuario> pendientes,
            Stack<Operacion> acciones) {
        if (pendientes.isEmpty()) {
            System.out.println("no hay usuarios en espera de prestamo.");
            return;
        }

        Usuario usuario = pendientes.remove();
        Scanner input = new Scanner(System.in);

        int codigoLibro = Helper.validarEntero("ingrese el codigo del libro: ", input);
        Libro libro = arbolLibros.buscar(new Libro(codigoLibro, "", "", 0.0, true));

        if (libro == null) {
            System.out.println("libro no encontrado.");
            pendientes.add(usuario);
            return;
        }

        if (!libro.getDisponible()) {
            System.out.println("el libro no esta disponible, usuario sigue en espera.");
            pendientes.add(usuario);
            return;
        }

        libro.setDisponible(false);
        usuario.setCantidadPrestados(usuario.getCantidadPrestados() + 1);
        Operacion operacion = new Operacion(acciones.size() + 1, "prestamo", libro, usuario, LocalDate.now());
        acciones.push(operacion);

        System.out.println("prestamo realizado con exito:");
        System.out.println("libro: " + libro.getTitulo());
        System.out.println("usuario: " + usuario.getNombre());
    }
}
