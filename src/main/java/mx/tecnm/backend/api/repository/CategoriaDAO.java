package mx.tecnm.backend.api.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Categoria;

import mx.tecnm.backend.api.repository.CategoriaRM;


@Repository
public class CategoriaDAO{
    @Autowired
    private JdbcClient jdbcClient;

    public List<Categoria> obtenerCategorias() {
        String sql = "SELECT id, nombre, estado FROM categorias WHERE estado= 'Activo'";
        return jdbcClient.sql(sql).query(new CategoriaRM()).list();
    }


    public Categoria obtenerCategoriasPorId (int id){
        String sql = "SELECT id, nombre, estado FROM categorias WHERE id = ? AND estado= 'Activo'";
        return jdbcClient.sql(sql).param(id).query(new CategoriaRM()).single();
    }


    public Categoria crearCategoria (String nuevaCategoria){
        String sql = "Insert into categorias (nombre, estado) values (?,?) returning id, nombre, estado";
        return jdbcClient.sql(sql).param(nuevaCategoria).param("Activo").query(new CategoriaRM()).optional()
            .orElse(null);
    }


    public Categoria actualizarCategoria(int id, String nombreNuevo) {
    String sql = """
        UPDATE categorias
        SET nombre = ?
        WHERE id = ? AND estado= 'Activo'
        RETURNING id, nombre, estado
    """;
    return jdbcClient.sql(sql).param(nombreNuevo).param(id).query(new CategoriaRM()).optional().orElse(null);
}


 public boolean eliminarCategoria(int id) {
        String sql = """
            UPDATE categorias
            SET estado = 'Inactivo'
            WHERE id = ? AND estado = 'Activo'
        """;
        int filasActualizadas = jdbcClient.sql(sql).param(id).update();
        return filasActualizadas > 0; // Retorna true si se actualizó algo
    }

     public boolean reactivarCategoria(int id) {
        String sql = """
            UPDATE categorias
            SET estado = 'Activo'
            WHERE id = ? AND estado = 'Inactivo'
        """;
        int filasActualizadas = jdbcClient.sql(sql).param(id).update();
        return filasActualizadas > 0;
    }

}