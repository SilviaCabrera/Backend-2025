package mx.tecnm.backend.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import mx.tecnm.backend.api.models.Detalles_carrito;

public class Detalles_carritoRM implements RowMapper<Detalles_carrito> {

    @Override
    public Detalles_carrito mapRow(ResultSet rs, int rowNum) throws SQLException {
        Detalles_carrito dc = new Detalles_carrito();

        dc.setId(rs.getInt("id"));
        dc.setCantidad(rs.getInt("cantidad"));
        dc.setPrecio(rs.getBigDecimal("precio"));
        dc.setproducto_id(rs.getInt("productos_id"));
        dc.setusuario_id(rs.getInt("usuarios_id"));
        dc.setnombre_producto(rs.getString("nombre_prod"));

        return dc; // ← ← ← AQUÍ devolvemos la fila convertida a objeto
    }

}