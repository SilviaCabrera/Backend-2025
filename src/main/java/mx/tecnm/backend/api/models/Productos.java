package mx.tecnm.backend.api.models;

public record Productos (int id, String nombre, int precio, String sku, String color, String marca, String descripcion,
    int peso, int alto, int ancho, int profundidad, int categoria_id, String estado){

}