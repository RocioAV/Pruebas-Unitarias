package ar.edu.unju.fi.poo.dominio;

import ar.edu.unju.fi.poo.constantes.Constantes;

import java.time.LocalDate;

public class Profesional extends Empleado {
    private Boolean tieneTitulo;

    //CONSTRUCTORES
    public Profesional(Integer id, String legajo, String nombre, Integer cantHijos, LocalDate fechaNacimiento, Integer antiguedad, Boolean tieneTitulo) {
        super(id, legajo, nombre, cantHijos, fechaNacimiento, antiguedad);
        this.tieneTitulo = tieneTitulo;
    }

    //METODOS

    /**
     * Calcula el adicional específico para un empleado profesional.
     * En este caso, si el empleado tiene un título, se le asigna un adicional.
     *
     * @return El valor del adicional por título si el empleado lo tiene, de lo contrario, retorna 0.
     */
    @Override
    public Double calcularAdicional() {
        return (getTieneTitulo() ? Constantes.ADICIONAL_TITULO : 0);
    }

    /**
     * Calcula el sueldo neto del empleado profesional.
     * El cálculo incluye los siguientes componentes:
     * - Remunerativos bonificables comunes.
     * - Adicional específico para el empleado profesional.
     * - Salario familiar basado en la cantidad de hijos.
     * - Descuentos aplicables (15% de los remunerativos bonificables).
     *
     * @return El sueldo neto del empleado después de sumar los remunerativos y adicionales,
     *         agregar el salario familiar y restar los descuentos.
     */
    @Override
    public Double getSueldo(){
        Double remunerativosBonificables = super.calcularRemunerativosComunes() + calcularAdicional();
        Double salarioFamiliar = super.calcularSalarioFamiliar();
        Double descuentos = super.calcularDescuentos(remunerativosBonificables);
        return remunerativosBonificables + salarioFamiliar - descuentos;
    }

    //GETTERS AND SETTERS
    public Boolean getTieneTitulo() {
        return tieneTitulo;
    }

    public void setTieneTitulo(Boolean tieneTitulo) {
        this.tieneTitulo = tieneTitulo;
    }
}
