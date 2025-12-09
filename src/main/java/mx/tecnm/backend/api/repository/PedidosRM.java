package mx.tecnm.backend.api.repository;

import java.sql.ResultSet;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import mx.tecnm.backend.api.models.Pedidos;

public class PedidosRM implements RowMapper<Pedidos> {

    @Override
    public Pedidos mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Pedidos(
            rs.getInt("id"),
            rs.getTimestamp("fecha"),
            rs.getString("numero"), // UUID viene como String
            rs.getBigDecimal("importe_productos"),
            rs.getBigDecimal("importe_envio"),
            rs.getInt("usuarios_id"),
            rs.getInt("metodos_pago_id"),
            rs.getTimestamp("fecha_hora_pago"),
            rs.getBigDecimal("importe_iva"), //se genera 
            rs.getBigDecimal("total") //se genera
        );
    }
}