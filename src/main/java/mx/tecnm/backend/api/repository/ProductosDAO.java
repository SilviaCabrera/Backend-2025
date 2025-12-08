package mx.tecnm.backend.api.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Productos;

import mx.tecnm.backend.api.repository.ProductosRM;

@Repository
public class ProductosDAO{
     @Autowired
    private JdbcClient jdbcClient;

     public List<Productos> obtenerProductos() {
        String sql = "SELECT id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id, estado FROM productos WHERE estado= 'Activo'";
        return jdbcClient.sql(sql).query(new ProductosRM()).list();
    }

     public Productos obtenerProductosPorId (int id){
        String sql = "SELECT  id, nombre, precio, sku, color, marca, descripcion, peso, alto, ancho, profundidad, categorias_id, estado FROM productos WHERE id = ? AND estado= 'Activo'";
        return jdbcClient.sql(sql).param(id).query(new ProductosRM()).single();
    }

   public Productos crearProductos(String nombre, double precio, String sku, String color, String marca, String descripcion,
                               double peso, double alto, double ancho, double profundidad, int categorias_id, String estado) {

    String sql = """      
    INSERT INTO productos
        (nombre, precio, sku, color, marca, descripcion, peso,
         alto, ancho, profundidad, categorias_id, estado)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
    RETURNING id, nombre, precio, sku, color, marca, descripcion,
              peso, alto, ancho, profundidad, categorias_id, estado
""";

return jdbcClient.sql(sql)
        .param(nombre)
        .param(precio)
        .param(sku)
        .param(color)
        .param(marca)
        .param(descripcion)
        .param(peso)
        .param(alto)
        .param(ancho)
        .param(profundidad)
        .param(categorias_id)
        .param(estado)
        .query(new ProductosRM())
        .optional()
        .orElse(null);

}


   public Productos actualizarProducto(int id, String nombreNuevo, double precioNuevo, String skuNuevo, String colorNuevo, String marcaNuevo,
                                    String descripcionNuevo, double pesoNuevo, double altoNuevo, double anchoNuevo, double profundidadNuevo,
                                    int categorias_idNuevo, String estadoNuevo) {

    String sql = """
        UPDATE productos
        SET nombre = ?,
            precio = ?,
            sku = ?,
            color = ?,
            marca = ?,
            descripcion = ?,
            peso = ?,
            alto = ?,
            ancho = ?,
            profundidad = ?,
            categorias_id = ?,
            estado = ?
        WHERE id = ?
        RETURNING id, nombre, precio, sku, color, marca, descripcion,
                  peso, alto, ancho, profundidad, categorias_id, estado
        """;

    return jdbcClient.sql(sql)
            .param(nombreNuevo)
            .param(precioNuevo)
            .param(skuNuevo)
            .param(colorNuevo)
            .param(marcaNuevo)
            .param(descripcionNuevo)
            .param(pesoNuevo)
            .param(altoNuevo)
            .param(anchoNuevo)
            .param(profundidadNuevo)
            .param(categorias_idNuevo)
            .param(estadoNuevo)
            .param(id)  
            .query(new ProductosRM())
            .optional()
            .orElse(null);
}

public boolean eliminaProductos(int id) {
        String sql = """
            UPDATE productos
            SET estado = 'Inactivo'
            WHERE id = ? AND estado = 'Activo'
        """;
        int filasActualizadas = jdbcClient.sql(sql).param(id).update();
        return filasActualizadas > 0; 
    }
}