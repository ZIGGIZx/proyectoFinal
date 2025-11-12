package libreria.gestion;

import java.util.Random;
import java.util.Scanner;

import Helper.Helper;
import libreria.modelo.Usuario;
import libreria.registro.ResultadoRegistroUsuario;

public class GestionUsuarios {

    // registra nuevos usuarios en el sistema con numeros generados aleatoriamente
    public static ResultadoRegistroUsuario registrarUsuarios(Usuario[] arregloUsuarios, Scanner input) {
        Random random = new Random();
        int cantidad = Helper.validarEntero("Cuantos usuarios desea agregar: ", input);

        // calcular cuantos usuarios habia antes
        int usuariosAnteriores = 0;
        if (arregloUsuarios != null) {
            usuariosAnteriores = arregloUsuarios.length;
        }

        // crear nuevo arreglo con mas espacio
        Usuario[] nuevoArreglo = new Usuario[usuariosAnteriores + cantidad];

        // copiar los usuarios viejos si hay
        if (arregloUsuarios != null) {
            for (int i = 0; i < arregloUsuarios.length; i++) {
                nuevoArreglo[i] = arregloUsuarios[i];
            }
        }

        // cargar los nuevos usuarios
        for (int i = 0; i < cantidad; i++) {
            System.out.println("\nUsuario " + (i + 1) + " de " + cantidad);

            // generar numero de usuario unico
            int numeroUsuario = 0;
            boolean numeroValido = false;
            while (!numeroValido) {
                numeroUsuario = random.nextInt(100) + 1;
                numeroValido = true;

                // verificar que no se repita
                if (arregloUsuarios != null) {
                    for (Usuario usuario : arregloUsuarios) {
                        if (usuario.getNumeroDeUsuario() == numeroUsuario) {
                            numeroValido = false;
                            break;
                        }
                    }
                }
                for (int j = 0; j < i; j++) {
                    if (nuevoArreglo[usuariosAnteriores + j].getNumeroDeUsuario() == numeroUsuario) {
                        numeroValido = false;
                        break;
                    }
                }
            }

            System.out.println("Numero de usuario generado: " + numeroUsuario);

            // pedir dni unico
            int dni = 0;
            boolean dniValido = false;
            while (!dniValido) {
                dni = Helper.validarEntero("Ingrese el DNI: ", input);
                dniValido = true;

                if (arregloUsuarios != null) {
                    for (Usuario usuario : arregloUsuarios) {
                        if (usuario.getDni() == dni) {
                            System.out.println("Error: " + dni + " ya esta registrado. Intente con otro.");
                            dniValido = false;
                            break;
                        }
                    }
                }
                for (int j = 0; j < i; j++) {
                    if (nuevoArreglo[usuariosAnteriores + j].getDni() == dni) {
                        System.out.println("Error: " + dni + " ya esta registrado. Intente con otro.");
                        dniValido = false;
                        break;
                    }
                }
            }

            String nombre = Helper.validarString("Ingrese el nombre: ", input);
            String direccion = Helper.validarString("Ingrese la direccion: ", input);
            int telefono = Helper.validarEntero("Ingrese el telefono: ", input);

            Usuario usuario = new Usuario(numeroUsuario, dni, nombre, direccion, telefono, 0);
            nuevoArreglo[usuariosAnteriores + i] = usuario;

            System.out.println("Usuario registrado exitosamente.");
        }

        // devolver el arreglo actualizado
        return new ResultadoRegistroUsuario(nuevoArreglo, cantidad);
    }

    // muestra todos los usuarios registrados
    public static void consultarUsuarios(Usuario[] arregloUsuarios) {
        if (arregloUsuarios == null || arregloUsuarios.length == 0) {
            System.out.println("No hay usuarios registrados en el sistema.");
            return;
        }

        System.out.println("\nLISTA DE USUARIOS REGISTRADOS");
        System.out.println("Total de usuarios: " + arregloUsuarios.length);
        System.out.println("------------------------------------------");

        for (Usuario usuario : arregloUsuarios) {
            System.out.println(usuario);
        }
    }
}
