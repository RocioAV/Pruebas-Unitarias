package ar.edu.unju.fi.poo.manager;

import ar.edu.unju.fi.poo.constantes.Constantes;
import ar.edu.unju.fi.poo.dominio.Administrativo;
import ar.edu.unju.fi.poo.dominio.Empleado;
import ar.edu.unju.fi.poo.dominio.Profesional;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EmpresaManager {

    // Variable estática que se utiliza como ID auxiliar para nuevos empleados.
    private static int id_aux = 100;

    /**
     * Genera y agrega una lista predefinida de empleados a la lista proporcionada.
     * @param empleados Lista de empleados a la cual se agregarán los nuevos empleados.
     */
    public static void generarEmpleados(List<Empleado> empleados){
        empleados.add(new Profesional(1, "123", "Juan Pérez", 2, LocalDate.of(1985, 5, 15), 1, true));
        empleados.add(new Administrativo(2, "124", "María Gómez", 1, LocalDate.of(1990, 7, 22), 5, 3));
        empleados.add(new Profesional(3, "125", "Carlos López", 0, LocalDate.of(1995, 3, 10), 3, false));
        empleados.add(new Administrativo(4, "126", "Ana Fernández", 3, LocalDate.of(1988, 11, 30), 8, 5));
    }

    /**
     * Permite al usuario agregar un nuevo empleado a la lista proporcionada.
     * Solicita datos al usuario como tipo de empleado, legajo, nombre, cantidad de hijos,
     * fecha de nacimiento, y otros atributos específicos según el tipo de empleado.
     * @param empleados Lista de empleados a la cual se agregará el nuevo empleado.
     */
    public static void agregarEmpleado(List<Empleado> empleados){
        String legajo,titulo,tipo,nombre;
        int hijos,categoria;
        boolean tieneTitulo;

        Scanner sc = new Scanner(System.in);

        id_aux+=1;
        do{
            try {
                System.out.print("Es Profesional (P) o Administrativo (A): ");
                tipo = sc.nextLine().toLowerCase();
            } catch (Exception e){
                System.out.println("--Error: " + e.getMessage() + "--");
                tipo = "";
            }
        } while (!tipo.equals("p") && !tipo.equals("a"));

        do{
            System.out.print("Ingrese un legajo: ");
            legajo = sc.nextLine();
            if (legajo.isEmpty()) {
                System.out.println("--Debe ingresar un legajo."+"--");
            }
        } while (legajo.isEmpty());

        do{
            System.out.print("Ingrese un nombre: ");
            nombre = sc.nextLine();
            if (nombre.isEmpty()) {
                System.out.println("--Debe ingresar un nombre."+"--");
            }
        } while (nombre.isEmpty());

        do {
            try {
                System.out.print("Ingrese cantidad de hijos: ");
                hijos = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("--Error: " + e.getMessage() + "--");
                hijos = -1;
            }
        } while (hijos < 0);

        int dia, mes, anio;
        while (true){
            try {
                System.out.println("Ingrese fecha nacimiento");
                System.out.print("Dia: ");
                dia = Integer.parseInt(sc.nextLine());
                System.out.print("Mes: ");
                mes = Integer.parseInt(sc.nextLine());
                System.out.print("Anio: ");
                anio = Integer.parseInt(sc.nextLine());

                if (dia < 1 || dia > 31 || mes < 1 || mes > 12 || anio < 1950 || anio > 2006) {
                    System.out.println("--Fecha invalida."+"--");
                }else {
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("--Error: " + e.getMessage() + "--");;
            }
        }

        int antiguedad = 0; // Es nuevo, no tiene antiguedad
        switch (tipo) {
            case "p":
                do {
                    System.out.print("Tiene titulo (S/N): ");
                    titulo = sc.nextLine().toLowerCase();
                } while (!titulo.equals("s") && !titulo.equals("n"));


                tieneTitulo = titulo.equalsIgnoreCase("s");

                Profesional profesional = new Profesional(id_aux, legajo, nombre, hijos, LocalDate.of(anio, mes, dia),
                        antiguedad, tieneTitulo);
                empleados.add(profesional);
                System.out.println("Nuevo Empleado: "+ profesional);
                break;

            case "a":
                do {
                    try{
                        System.out.print("Ingrese categoria [1-20]: ");
                        categoria = Integer.parseInt(sc.nextLine());
                    } catch (NumberFormatException e){
                        System.out.println("--Error: " + e.getMessage() + "--");
                        categoria = -1;
                    }
                } while (categoria < 1 || categoria > 20);

                Administrativo administrativo = new Administrativo(id_aux, legajo, nombre, hijos,
                        LocalDate.of(anio, mes, dia), antiguedad,
                        categoria);
                empleados.add(administrativo);
                System.out.println("Nuevo empleado: "+administrativo);
                break;
            default:
                break;
        }

    }

    /**
     * Muestra la información de todos los empleados en la lista proporcionada.
     * @param empleados Lista de empleados a mostrar.
     */
    public static void mostrarEmpleados(List<Empleado> empleados){
        for (Empleado e : empleados){
            System.out.println(e);
        }
    }

    /**
     * Muestra los empleados con una antigüedad mayor a un número de años especificado,
     * y calcula el total acumulado de remunerativos bonificables, salario familiar,
     * descuentos e importe neto.
     * @param empleados Lista de empleados a filtrar y mostrar.
     * @param anios Número de años de antigüedad mínima para filtrar empleados.
     */
    public static void mostrarEmpleadosMayorAntiguedad(List<Empleado> empleados, int anios) {
        Double remunerativosBonificables=0.0;
        Double salario=0.0;
        Double descuentos=0.0;
        Double importeNeto=0.0;

        for (Empleado empleado : empleados) {
            if(empleado.getAntiguedad()>anios){
                System.out.println(empleado);
                salario+=empleado.calcularSalarioFamiliar();
                remunerativosBonificables+= empleado.calcularRemunerativosComunes()+ empleado.calcularAdicional();
                descuentos+= empleado.calcularDescuentos(remunerativosBonificables);
                importeNeto+=empleado.getSueldo();

            }

        }
        System.out.println("Total Remunerativos Bonificables: $" + remunerativosBonificables);
        System.out.println("Total Salario Familiar: $" + salario);
        System.out.println("Total Descuentos: $" + descuentos);
        System.out.println("Total Importe Neto: $" + importeNeto);
    }

    /**
     * Obtiene una lista de empleados cuya edad es mayor o igual a la edad especificada.
     * @param empleados Lista de empleados a filtrar.
     * @param edad Edad mínima para filtrar empleados.
     * @return Lista de empleados que cumplen con la condición de edad.
     */
    public static List<Empleado> obtenerEmpleadosConEdadMayorIgual(List<Empleado> empleados, int edad) {
        return empleados.stream()
                .filter(empleado -> empleado.getEdad() >= edad)
                .collect(Collectors.toList());
    }

    /**
     * Calcula el sueldo neto total de todos los empleados cuya edad es mayor o igual a la edad especificada.
     * @param empleados Lista de empleados a filtrar y calcular.
     * @param edad Edad mínima para filtrar empleados.
     * @return El total del sueldo neto de los empleados que cumplen con la condición de edad.
     */
    public static Double calcularSueldoNetoDeEmpleadosEdadMayorIgual(List<Empleado> empleados, int edad){
        Double totalNeto=0.0;
        for (Empleado empleado : empleados) {
            if (empleado.getEdad() >= edad) {
                totalNeto+=empleado.getSueldo();
            }
        }
        return totalNeto;
    }

    /**
     * Incrementa el sueldo básico de los empleados con una antigüedad menor o igual a 2 años.
     * El incremento es del 10% sobre el sueldo básico actual, y se recalculan las remuneraciones
     * y el sueldo neto en base al nuevo sueldo básico.
     * @param empleados Lista de empleados a los cuales se les incrementará el sueldo básico.
     */
    public static void incrementarSueldoBasico(List<Empleado> empleados ){
        Double sueldoActualizado;
        for (Empleado empleado : empleados) {
            if (empleado.getAntiguedad() <= 2) {
                // Aumento del 10%
                Double aumento = Constantes.SUELDO_BASICO * 1.10 - Constantes.SUELDO_BASICO;
                // Nuevo sueldo basico - BASICO+AUMENTO
                Double nuevoBasico = Constantes.SUELDO_BASICO * 1.10;
                // calculamos nuevamente el remunerativo bonificable
                Double nuevoRemunerativoBonificable = empleado.calcularRemunerativosComunes() - Constantes.SUELDO_BASICO + nuevoBasico + empleado.calcularAdicional();
                // Nuevo sueldo neto
                sueldoActualizado = nuevoRemunerativoBonificable + empleado.calcularSalarioFamiliar() - empleado.calcularDescuentos(nuevoRemunerativoBonificable);

                System.out.println(empleado.getNombre()+" con sueldo de: $"+empleado.getSueldo()+" Se actualiza a: $"+ sueldoActualizado);
                System.out.println("El nuevo sueldo basico es: $"+ (aumento+Constantes.SUELDO_BASICO));
            }
        }
    }
}
