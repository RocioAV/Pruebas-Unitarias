package ar.edu.unju.fi.poo.dominio;

import ar.edu.unju.fi.poo.constantes.Constantes;

import java.time.LocalDate;

public class Administrativo extends Empleado {
    private Integer categoria;

    //CONSTRUCTORES
    public Administrativo(Integer id, String legajo, String nombre, Integer cantHijos, LocalDate fechaNacimiento, Integer antiguedad, Integer categoria) {
        super(id, legajo, nombre, cantHijos, fechaNacimiento, antiguedad);
        this.categoria = categoria;
    }

    //METODOS
    @Override
    public Double calcularAdicional() {
       return Constantes.getValorCategoria(getCategoria());
    }

    @Override
    public Double getSueldo(){
        double remunerativosBonificables = super.calcularRemunerativosComunes()+ calcularAdicional();
        double salarioFamiliar = super.calcularSalarioFamiliar();
        double descuentos = super.calcularDescuentos(remunerativosBonificables);
        return remunerativosBonificables + salarioFamiliar - descuentos;
    }


    //GETTER AND SETTER

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(int categoria) {
        this.categoria = categoria;
    }
}
