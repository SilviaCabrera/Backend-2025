package mx.tecnm.backend.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.backend.api.models.Metodo_pago;

public class Metoto_pagoRM implements RowMapper<Metodo_pago>{
    @Override
    public Metodo_pago mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Metodo_pago(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getInt("comision"),
            rs.getString("estado")
        );
        
    }
}