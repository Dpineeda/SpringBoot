package com.umg.edu.mvcvideo.modelo;

public class DetalleFactura {
    private int idDetalle;
    private int idFactura;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public DetalleFactura(int idDetalle, int idFactura, int idProducto, int cantidad, double precioUnitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idFactura = idFactura;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }

    public int getIdDetalle() {
        return idDetalle;
    }

public void setIdDetalle(int idDetalle) {
    this.idDetalle = idDetalle;
}

    public int getIdFactura() {
        return idFactura;
    }
    
    public void setIdFactura(int idFactura){
        this.idFactura = idFactura;
    }

    public int getIdProducto() {
    return idProducto;
}

    public void setIdProductos(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getcantidad() {
        return cantidad;
    }

public void setcantidad(int cantidad) {
    this.cantidad = cantidad;
}

    public double getprecioUnitario() {
        return precioUnitario;
    }

public void setprecioUnitario(double precioUnitario) {
    this.precioUnitario = precioUnitario;
}

public double getsubtotal (){
    return subtotal;
}

public void setsubtotal (double subtotal) {
    this.subtotal = subtotal;
}


}
