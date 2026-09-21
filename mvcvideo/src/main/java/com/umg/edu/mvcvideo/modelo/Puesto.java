package com.umg.edu.mvcvideo.modelo;

public class Puesto {
    private int idPuesto;
    private String nombre;
    private double salarioBase;

    public Puesto(int idPuesto, String nombre, double salarioBase) {
        this.idPuesto = idPuesto;
        this.nombre = nombre;
        this.salarioBase = salarioBase;
    }

public int getIdPuesto() {
    return idPuesto;
}

public void setIdPuesto (int idPuesto) {
    this.idPuesto = idPuesto;
}

public String getNombre () {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public double getsalarioBase () {
    return salarioBase;

}

public void setsalarioBase (double salarioBase) {
    this.salarioBase = salarioBase;
}

}
