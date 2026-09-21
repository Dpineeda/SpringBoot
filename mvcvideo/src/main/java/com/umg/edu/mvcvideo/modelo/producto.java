package com.umg.edu.mvcvideo.modelo;

public class producto {
    private int idProducto;
    private int idMarca;
    private String nombre;
    private double precio;
    private int stock;

    public producto (int idProducto, int idMarca, String nombre, double precio, int stock) {
        this.idProducto = idProducto;
        this.idMarca = idMarca;
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
    }

public int getIdProducto() {
    return idProducto;
}

public void setIdProducto(int idProducto) {
    this.idProducto = idProducto;
}

public int getidMarca() {
    return idMarca;
}

public void setIdMarca(int idMarca) {
    this.idMarca = idMarca;
}

public String getNombre() {
    return nombre;
}

public void setNombre(String nombre) {
    this.nombre = nombre;
}

public double getPrecio() {
    return precio;
}

public void setPrecio(double precio) {
    this.precio = precio;
}

public int getStock() {
    return stock;
}

public void setStock(int stock) {
    this.stock = stock;
}

}
