package mx.tecnm.backend.api.models;

public record Productos (int id, String nombre, double precio, String sku, String color, String marca, String descripcion,
    double peso, double alto, double ancho, double profundidad, int categorias_id, String estado){

}