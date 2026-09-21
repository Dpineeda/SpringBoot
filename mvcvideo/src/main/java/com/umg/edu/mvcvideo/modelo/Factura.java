package com.umg.edu.mvcvideo.modelo;

public class Factura {
    private int idFactura;
    private int IdCliente;
    private int idEmpleado;
    private String Fecha;
    private double total;

public Factura (int idFactura, int IdCliente, int idEmpleado, String Fecha, double total) {
    this.idFactura = idFactura;
    this.IdCliente = IdCliente;
    this.idEmpleado = idEmpleado;
    this.Fecha = Fecha;
    this.total = total;
}

public int getIdFactura() {
    return idFactura;
}
 
public void setIdFactura(int idFactura) {
    this.idFactura = idFactura;
}

public int getIdCliente() {
    return IdCliente;
}

public void setIdCliente(int IdCliente) {
    this.IdCliente = IdCliente;
}

public int getIdEmpleado() {
    return idEmpleado;
}

public void setIdEmpleado(int idEmpleado) {
    this.idEmpleado = idEmpleado;
}

public String getFecha () {
    return Fecha;
}

public void setFecha (String Fecha) {
    this.Fecha = Fecha;
}

public double getTotal() {
    return total;
}

public void setTotal(double total) {
    this.total = total;
}

}
