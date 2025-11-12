package Helper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;



public class Helper {
	
	public static String SiOrNo(String mensaje,Scanner input) {
		while(true) {
			String opcion=validarString(mensaje, input);
			if(opcion.equalsIgnoreCase("si") || opcion.equalsIgnoreCase("no")) {
				return opcion;
			}
			System.out.println("ERROR!! Debe ingresar si o no");
		}
	}
	
	public static String validarEspecialidad(String mensaje,Scanner input) {
		while(true) {
			String especialidad=validarString(mensaje, input);
			if(especialidad.equalsIgnoreCase("cirujano") || especialidad.equalsIgnoreCase("clinica general")) {
				return especialidad;
			}
			System.out.println("ERROR!! La especialidad tiene que ser cirujano o clinica general");
		}
	}
	
	
	public static double validarDouble(String mensaje,Scanner input) {
		double numero = 0;
		String valorIngresado;
		while (true) {
			try {
				System.out.println(mensaje);
				valorIngresado = input.nextLine();
				numero= Double.parseDouble(valorIngresado);
				break;
			}catch(Exception e) {
				System.out.println("Error...!!! debe ingresar un numero");
			}
		}
		return numero;
	}
	
	public static String validarString(String mensaje,Scanner input) {
		String aux;
		while(true) {
			System.out.println(mensaje);
			aux=input.nextLine();
			if(!aux.equals(" ") && !aux.equals("")) {
				return aux;
			}
			System.out.println("No puede ingresar una cadena vacia!!!");
		}
	}
	
	public static int validarEnteroPositivo(String mensaje,Scanner input) {
		int numero;
		while(true){
			try{
				System.out.println(mensaje);
				String aux=input.nextLine();
				numero=Integer.parseInt(aux);
				if(numero>0) {
					break;
				}
				System.out.println("ERROR¡¡¡ El numero debe ser mayor que cero");
			}
			catch(Exception e) {
				System.out.println("ERROR!! Debe ingresar un numero");
			}
		}
		return numero;	
	}
	
	public static int validarEntero(String mensaje,Scanner input) {
		int numero;
		while(true){
			try{
				System.out.println(mensaje);
				//String aux=input.nextLine();
				numero=Integer.parseInt(input.nextLine());
				break;
			}
			catch(Exception e) {
				System.out.println("ERROR!! Debe ingresar un numero");
			}
		}
		return numero;	
	}
	
	public static LocalDate validarFecha(Scanner input) {
		int dia,mes,anio ;
		while(true) {
			dia=Helper.validarEntero("Ingrese el dia : ", input);
			if(dia>=1 && dia<=31) {
				break;
			}
			System.out.println("ERROR¡¡  el dia tiene que estar entre 1 y 31");
		}
		while(true) {
			mes=Helper.validarEntero("Ingrese el mes : ", input);
			if(mes>=1 && mes<=12) {
				break;
			}
			System.out.println("ERROR¡¡  el mes tiene que estar entre 1 y 12");
		}
		while(true) {
			anio=Helper.validarEntero("Ingrese el año: ", input);
			if(anio<=2024) {
				break;
			}
			System.out.println("ERROR¡¡  el año no puede ser mayor que 2024");
		}
		LocalDate aux=LocalDate.of(anio, mes, dia);
		return aux;
	}
	
	public static int validarNumeroEnRango(String mensaje,Scanner input,int inicio,int fin) {
		int opcion;
		while(true) {
			opcion=validarEntero(mensaje, input);
			if(opcion>=inicio && opcion<=fin) {
				return opcion;
			}
			else if(fin<=inicio) {
				throw new RuntimeException("Parametros invalidos");
			}
			else {
				System.out.println("ERROR¡¡ el numero debe estar entre "+inicio+" y "+fin);
			}
		}
	
	}
	
	public static boolean verdaderoFalso(String mensaje) {
		Scanner input=new Scanner(System.in);
		char aux;
		while(true) {
			try {
				System.out.println(mensaje+" ingrese 'S'/si o 'N'/no  ");
				aux=Character.toUpperCase(input.nextLine().charAt(0));
				if(aux=='S' || aux=='N') {
					if(aux=='S') {
						return true;
					}
					else {
						return false;
					}
				}
				System.out.println("ERROR¡¡ debe ingresar 'S'/si o 'N'/no");
			}
			catch(Exception e) {
				System.out.println("ERROR¡¡ debe ingresar 'S'/si o 'N'/no");
			}
		}
		
	}
	
	public static LocalTime verificarHora() {
		Scanner input=new Scanner(System.in);
		int hora,minuto;
		while(true) {
			hora=validarEntero("Ingrese la hora:", input);
			if(hora<=23) {
				break;
			}
			System.out.println("ERROR¡¡ la hora debe ser menor a 24");
		}
		while(true) {
			minuto=validarEntero("Ingrese minutos: ", input);
			if(minuto<60) {
				break;
			}
			System.out.println("ERROR¡¡ los minutos debe ser menor a 60");
			
		}
		LocalTime aux=LocalTime.of(hora, minuto);
		return aux;
	}

}
