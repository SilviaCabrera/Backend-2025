package mx.tecnm.backend.api.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.sql.Timestamp;

import org.springframework.jdbc.core.RowMapper;

import mx.tecnm.backend.api.models.Usuario;

public class UsuarioRM implements RowMapper<Usuario> {
    @Override
    public Usuario mapRow(ResultSet rs, int rowNum) throws SQLException {
        // Convertir java.sql.Date a java.time.LocalDate
        Date sqlDate = rs.getDate("fecha_nacimiento");
        java.time.LocalDate fechaNacimiento = (sqlDate != null) ? sqlDate.toLocalDate() : null;
        
        // Convertir java.sql.Timestamp a java.time.LocalDateTime
        Timestamp sqlTimestamp = rs.getTimestamp("fecha_registro");
        java.time.LocalDateTime fechaRegistro = (sqlTimestamp != null) ? sqlTimestamp.toLocalDateTime() : null;
        
        return new Usuario(
            rs.getInt("id"),
            rs.getString("nombre"),
            rs.getString("email"),
            rs.getString("telefono"),
            rs.getString("sexo"),
            fechaNacimiento,
            rs.getString("contrasena"),
            fechaRegistro,                     // ✅ Ahora es LocalDateTime
            rs.getString("estado")
        );
    }
}