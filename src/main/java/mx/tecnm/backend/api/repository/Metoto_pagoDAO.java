package mx.tecnm.backend.api.repository;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import mx.tecnm.backend.api.models.Metodo_pago;

import mx.tecnm.backend.api.repository.Metoto_pagoRM;

@Repository
public class Metoto_pagoDAO {
      @Autowired
    private JdbcClient jdbcClient;

     public List<Metodo_pago> obtenerMetodo_pago() {
        String sql = "SELECT id, nombre, comision, estado FROM metodos_pago WHERE estado= 'Activo'";
        return jdbcClient.sql(sql).query(new Metoto_pagoRM()).list();
    }

    public Metodo_pago obtenerMetodo_pagoPorId (int id){
        String sql = "SELECT  id, nombre, comision, estado FROM metodos_pago WHERE id = ? AND estado= 'Activo'";
        return jdbcClient.sql(sql).param(id).query(new Metoto_pagoRM()).single();
    }

    public Metodo_pago crearMetodo_pago (String nombre, double comision){
        String sql = "Insert into metodos_pago (nombre, comision, estado) values (?,?,?) returning id, nombre, comision, estado";
        return jdbcClient.sql(sql).param(nombre).param(comision).param("Activo").query(new Metoto_pagoRM()).optional()
            .orElse(null);
    }

    public Metodo_pago actualizarMetodo_pago(int id, String nombreNuevo, double comisionNuevo, String estadoNuevo) {
    String sql = """
        UPDATE metodos_pago
        SET nombre = ?, comision = ?, estado = ?
        WHERE id = ? AND estado = 'Activo'
        RETURNING id, nombre, comision, estado
    """;
    return jdbcClient
        .sql(sql)
        .param(nombreNuevo)
        .param(comisionNuevo)
        .param(estadoNuevo)
        .param(id)
        .query(new Metoto_pagoRM())
        .optional()
        .orElse(null);
}


    public boolean eliminarMetodo_pago(int id) {
        String sql = """
            UPDATE metodos_pago
            SET estado = 'Inactivo'
            WHERE id = ? AND estado = 'Activo'
        """;
        int filasActualizadas = jdbcClient.sql(sql).param(id).update();
        return filasActualizadas > 0; // Retorna true si se actualizó algo
    }

}
