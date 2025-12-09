package mx.tecnm.backend.api.models;

import java.math.BigDecimal;

public class Detalles_carrito {
    private int id;
    private int cantidad;
    private BigDecimal precio; // Precio al momento de agregar
    private int producto_id;
    private int usuario_id;
     private String nombre_producto; // Dato extra para mostrar en el JSON

    public Detalles_carrito() {}

    public Detalles_carrito(int id, int cantidad, BigDecimal precio, int producto_id, String nombre_producto, int usuario_id) {
        this.id = id;
        this.cantidad = cantidad;
        this.precio = precio;
        this.producto_id = producto_id;
        this.usuario_id = usuario_id;
        this.nombre_producto = nombre_producto;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }
    public int getproducto_id() { return producto_id; }
    public void setproducto_id(int producto_id) { this.producto_id = producto_id; }
    public int getusuario_id() { return usuario_id; }
    public void setusuario_id(int usuario_id) { this.usuario_id = usuario_id; }
    public String getnombre_producto() { return nombre_producto; }
    public void setnombre_producto(String nombre_producto) { this.nombre_producto = nombre_producto; }
}