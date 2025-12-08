package mx.tecnm.backend.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.backend.api.models.Productos;

public class ProductosRM implements RowMapper<Productos> {
    @Override
    public Productos mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Productos(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getDouble("precio"),
            rs.getString("sku"),
            rs.getString("color"),
            rs.getString("marca"),
            rs.getString("descripcion"),
            rs.getDouble("peso"),
            rs.getDouble("alto"),
            rs.getDouble("ancho"),                   
            rs.getDouble("profundidad"),
            rs.getInt("categorias_id"),
            rs.getString("estado")
        );
        
    }
}