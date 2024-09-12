package ar.edu.unju.fi.poo.main;

import ar.edu.unju.fi.poo.manager.EmpresaManager;
import ar.edu.unju.fi.poo.dominio.Empleado;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Empleado> empleados = new ArrayList<>();
        //precarga empleados
        EmpresaManager.generarEmpleados(empleados);

        EmpresaManager.mostrarEmpleados(empleados);

        int op=-1;
        do{

            try {
                menu();
                System.out.print("Elija una opcion: ");
                op = sc.nextInt();
                menuReturn(empleados, op);
            } catch (InputMismatchException e) {
                System.out.println("--Error: Entrada inválida. Por favor, ingrese un número válido.--");
                sc.nextLine(); // Limpiar el buffer
                op = -1;
            }
        }while(op!=6);
        sc.close();
    }


    public static void menu(){

        System.out.println("1- Agregar empleado");
        System.out.println("2- Mostrar empleados mayor a X años de antigûedad");
        System.out.println("3- Mostrar empleados cuya edad sea mayora a");
        System.out.println("4- Calcular importe neto acumulado de empleados de edad mayor a");
        System.out.println("5- Incrementar 10% del salario basico a empleados con menos de 2 años de antigûedad");
        System.out.println("6- Salir:v");
        System.out.println("----------------------------------------------------------------------------------------");
    }
    public static void menuReturn(List<Empleado> empleados,int op){
        Scanner sc = new Scanner(System.in);
        int anios;
        try {
            switch (op) {
                case 1:
                    EmpresaManager.agregarEmpleado(empleados);
                    break;
                case 2:
                    // Mostrar empleados con antigüedad mayor a X años
                    System.out.print("Insertar antigüedad: ");
                    anios = sc.nextInt();
                    EmpresaManager.mostrarEmpleadosMayorAntiguedad(empleados, anios);
                    System.out.println("--------------------------------------------------------------------------------------------");
                    break;
                case 3:
                    // Mostrar los empleados cuya edad sea mayor o igual a un valor solicitado al usuario
                    System.out.print("Insertar edad: ");
                    anios = sc.nextInt();
                    EmpresaManager.mostrarEmpleados(EmpresaManager.obtenerEmpleadosConEdadMayorIgual(empleados, anios));
                    System.out.println("--------------------------------------------------------------------------------------------");
                    break;
                case 4:
                    // Calcular el importe neto acumulado de todos los empleados cuya edad sea mayor o igual a un valor solicitado al usuario
                    System.out.print("Insertar edad: ");
                    anios = sc.nextInt();
                    System.out.println("El Importe Neto total de los empleados mayor a " + anios + " años, es de: $" + EmpresaManager.calcularSueldoNetoDeEmpleadosEdadMayorIgual(empleados, anios));
                    System.out.println("--------------------------------------------------------------------------------------------");
                    break;
                case 5:
                    // Incrementar en un 10% el salario básico a todos los empleados cuya antigüedad sea menor o igual a 2 años
                    EmpresaManager.incrementarSueldoBasico(empleados);
                    System.out.println("--------------------------------------------------------------------------------------------");
                    break;
                case 6:
                    System.out.println("GRACIAS, VUELVA PRONTO");
                    break;
                default:
                    System.out.println("--Opción no válida. Por favor, elija una opción del menú.--");
                    break;
            }
        } catch (InputMismatchException e) {
            System.out.println("--Error: Entrada inválida. Por favor, ingrese un número válido.--");
            sc.nextLine(); // Limpiar el buffer
        }
    }


}