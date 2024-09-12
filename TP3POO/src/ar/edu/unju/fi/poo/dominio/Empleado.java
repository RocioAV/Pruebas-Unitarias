package ar.edu.unju.fi.poo.dominio;

import ar.edu.unju.fi.poo.constantes.Constantes;

import java.time.LocalDate;
import java.time.Period;

public abstract class Empleado {
    private Integer id;
    private String legajo;
    private String nombre;
    private Integer cantHijos;
    private LocalDate fechaNacimiento;
    private Integer antiguedad;


    //CONSTRUCTORES
    public Empleado() {
    }

    public Empleado(Integer id, String legajo, String nombre, Integer cantHijos, LocalDate fechaNacimiento, Integer antiguedad) {
        this.id = id;
        this.legajo = legajo;
        this.nombre = nombre;
        this.cantHijos = cantHijos;
        this.fechaNacimiento = fechaNacimiento;
        this.antiguedad = antiguedad;
    }
    //METODOS

    /**
     * Metodo abstracto para obtener el sueldo del empleado.
     * Este metodo deberá ser implementado por las subclases.
     *
     * @return El sueldo del empleado.
     */
    public abstract Double getSueldo();

    /**
     * Calcula la edad del empleado en base a su fecha de nacimiento.
     *
     * @return La edad del empleado en años.
     */
    public int getEdad() {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * Metodo para calcular los Remunerativos Bonificables comunes
     * @return retorna la suma del sueldo basico mas el salario por antiguedad
     */
    public Double calcularRemunerativosComunes() {
        return Constantes.SUELDO_BASICO + getAntiguedad() * Constantes.VALOR_ANTIGUEDAD;
    }

    /**
     * Metodo para calcular el salario familiar basado en la cantidad de hijos
     * @return retorna el valor total del adicional por hijo
     */
    public Double calcularSalarioFamiliar() {
        return getCantHijos() * Constantes.VALOR_HIJO;
    }

    /**
     * Calcula los descuentos aplicables al empleado, que son el 15%
     * de los remunerativos bonificables.
     *
     * @param remunerativosBonificables Monto total de los remunerativos bonificables.
     * @return El monto de los descuentos.
     */
    public Double calcularDescuentos(double remunerativosBonificables) {
        return remunerativosBonificables * Constantes.PORCENTAJE_DESCUENTOS;
    }

    /**
     * Metodo abstracto para calcular el adicional personalizado del empleado.
     * Este metodo deberá ser implementado por las subclases.
     *
     * @return El valor del adicional específico del empleado.
     */
    public abstract Double calcularAdicional();

    // GETTER AND SETTERS

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantHijos() {
        return cantHijos;
    }

    public void setCantHijos(Integer cantHijos) {
        this.cantHijos = cantHijos;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(Integer antiguedad) {
        this.antiguedad = antiguedad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "id=" + id +
                ", legajo='" + legajo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", cantHijos=" + cantHijos +
                ", fechaNacimiento=" + fechaNacimiento +
                ", antiguedad=" + antiguedad +
                '}';
    }
}
