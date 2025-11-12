package libreria.gestion;

import java.util.Scanner;
import Helper.Helper;
import libreria.estructuras.BinarySearchTree;
import libreria.estructuras.Queue;
import libreria.estructuras.Stack;
import libreria.modelo.Libro;
import libreria.modelo.Operacion;
import libreria.modelo.Usuario;
import libreria.registro.ResultadoRegistro;
import libreria.registro.ResultadoRegistroUsuario;

public class Principal {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Queue<Usuario> pendientes = new Queue<>();
        Stack<Operacion> acciones = new Stack<>();
        Libro[] arregloLibros = null;
        BinarySearchTree<Libro> arbolLibros = new BinarySearchTree<Libro>();
        Usuario[] arregloUsuarios = null;
        int opcion;
        
        do {
            menu();
            opcion = Helper.validarEnteroPositivo("Elija una opcion: ", input);
            
            switch(opcion) {
                case 1:
                    ResultadoRegistro resultadoLibros = GestionLibros.registrarLibros(arregloLibros, arbolLibros);
                    arregloLibros = resultadoLibros.arregloLibros;
                    break;
                    
                case 2:
                    ResultadoRegistroUsuario resultadoUsuarios = GestionUsuarios.registrarUsuarios(arregloUsuarios, input);
                    arregloUsuarios = resultadoUsuarios.arregloUsuarios;
                    break;
                    
                case 3:
                    GestionPrestamos.realizarPrestamo(arbolLibros, arregloUsuarios, acciones, pendientes);
                    break;
                    
                case 4:
                    GestionPrestamos.realizarDevolucion(arregloLibros, arregloUsuarios, acciones, arbolLibros);
                    break;
                    
                case 5:
                    GestionPrestamos.reversionDeOperaciones(acciones);
                    break;
                    
                case 6:
                    GestionPrestamos.atenderPendientes(arbolLibros, pendientes, acciones);
                    break;
                    
                case 7:
                    GestionLibros.consultarLibros(arregloLibros);
                    break;
                    
                case 8:
                    GestionUsuarios.consultarUsuarios(arregloUsuarios);
                    break;
                    
                case 9:
                    GestionConsultas.calcularMontoTotalDePrestamos(arregloLibros);
                    break;
                    
                case 10:
                    String subcadena = Helper.validarString("Ingrese la subcadena del autor: ", input);
                    GestionConsultas.buscarPorAutor(subcadena, arregloLibros);
                    break;
                    
                case 11:
                    int cantidad = Helper.validarEntero("Ingrese la cantidad minima de libros prestados: ", input);
                    GestionConsultas.usuariosConMasPrestamosQue(arregloUsuarios, cantidad);
                    break;
                    
                case 12:
                    System.out.println("Fin del programa");
                    break;
                    
                default:
                    System.out.println("Opcion invalida. Ingrese una opcion del menu.");
                    break;
            }
            
        } while (opcion != 12);
        
        input.close();
    }
    
    public static void menu() {
        System.out.println("\nSISTEMA DE GESTION DE BIBLIOTECA");
        System.out.println("1. Registrar libro");
        System.out.println("2. Registrar usuario");
        System.out.println("3. Realizar prestamo");
        System.out.println("4. Devolver libro");
        System.out.println("5. Revertir operacion");
        System.out.println("6. Atender pendientes");
        System.out.println("7. Consultar libros registrados");
        System.out.println("8. Consultar usuarios registrados");
        System.out.println("9. Consultar monto total de prestamos");
        System.out.println("10. Buscar libros por autor");
        System.out.println("11. Usuarios con X o mas libros prestados");
        System.out.println("12. Salir");
        System.out.println("--------");
    }
}
