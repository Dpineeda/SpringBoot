package com.umg.edu.mvcvideo.modelo;

import java.time.LocalDate;

public class Empleado extends Persona {
    private int idEmpleado;
    private int idPuesto;
    private LocalDate fechaContratacion;

    public Empleado() {
        super("", "");
    }

    public Empleado(int idEmpleado, String nombre, String apellidos, LocalDate fechaContratacion, int idPuesto) {
        super(nombre, apellidos);
        this.idEmpleado = idEmpleado;
        this.idPuesto = idPuesto;
        this.fechaContratacion = fechaContratacion;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdPuesto() {
        return idPuesto;
    }

    public void setIdPuesto(int idPuesto) {
        this.idPuesto = idPuesto;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
    }
}
