package mx.tecnm.backend.api.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Usuario;

import mx.tecnm.backend.api.repository.UsuarioRM;

@Repository
public class UsuarioDAO{
     @Autowired
    private JdbcClient jdbcClient;

     public List<Usuario> obtenerUsuario() {
        String sql = "SELECT id, nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro, estado FROM usuarios WHERE estado= 'Activo'";
        return jdbcClient.sql(sql).query(new UsuarioRM()).list();
    }

     public Usuario obtenerUsuarioPorId (int id){
        String sql = "SELECT id, nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro, estado FROM usuarios WHERE id = ? AND estado= 'Activo'";
        return jdbcClient.sql(sql).param(id).query(new UsuarioRM()).single();
    }

   public Usuario crearUsuario(String nombre, String email, String telefono, String sexo,
                            LocalDate fechaNacimiento, String contrasena) {

    String sql = """
        INSERT INTO usuarios
            (nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro, estado)
        VALUES (?, ?, ?, ?, ?, ?, NOW(), 'Activo')
        RETURNING id, nombre, email, telefono, sexo, fecha_nacimiento, contrasena, fecha_registro, estado
    """;

    return jdbcClient.sql(sql)
        .param(nombre)
        .param(email)
        .param(telefono)
        .param(sexo)
        .param(fechaNacimiento)
        .param(contrasena)
        .query(new UsuarioRM())
        .optional()
        .orElse(null);
}

     public Usuario actualizarUsuario(int id, String nombreNuevo, String emailNuevo, String telefonoNuevo, String sexoNuevo, 
                                      LocalDate fechaNacimientoNueva, String contrasenaNueva) {
    String sql = """
        UPDATE usuarios
        SET nombre = ?, 
            email = ?, 
            telefono = ?, 
            sexo = ?, 
            fecha_nacimiento = ?, 
            contrasena = ?
        WHERE id = ? AND estado = 'Activo'
        RETURNING id, nombre, email, telefono, sexo, fecha_nacimiento, 
                  contrasena, fecha_registro, estado
    """;    
    return jdbcClient.sql(sql)
        .param(nombreNuevo)
        .param(emailNuevo)
        .param(telefonoNuevo)
        .param(sexoNuevo)
        .param(fechaNacimientoNueva)
        .param(contrasenaNueva)
        .param(id)
        .query(new UsuarioRM())
        .optional()
        .orElse(null);
}

public boolean eliminarUsuario(int id) {
        String sql = """
            UPDATE usuarios
            SET estado = 'Inactivo'
            WHERE id = ? AND estado = 'Activo'
        """;
        int filasActualizadas = jdbcClient.sql(sql).param(id).update();
        return filasActualizadas > 0; 
    }
}