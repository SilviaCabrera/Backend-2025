package mx.tecnm.backend.api.repository;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.math.BigDecimal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import mx.tecnm.backend.api.models.Pedidos;
import mx.tecnm.backend.api.models.Productos;

@Repository
public class PedidosDAO {

    @Autowired
    private JdbcClient jdbcClient;

    // 1. LISTAR PEDIDOS DE UN USUARIO (Historial)
    public List<Pedidos> obtenerPedidosPorUsuario(int usuarios_id) {
        String sql = "SELECT * FROM pedidos WHERE usuarios_id = ? ORDER BY fecha DESC";
        return jdbcClient.sql(sql).param(usuarios_id).query(new PedidosRM()).list();
    }

    // 2. OBTENER UN PEDIDO POR ID
    public Pedidos obtenerPedidoPorId(int id) {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        return jdbcClient.sql(sql).param(id).query(new PedidosRM()).single();
    }

    // 3. GENERAR PEDIDO (La joya de la corona: Carrito -> Pedido)
    @Transactional
    public void generarPedido(int usuarios_id, int metodos_pago_id, BigDecimal importe_envio) {
        
        // A) Traer items del carrito
        String sqlCarrito = "SELECT productos_id, cantidad, precio FROM detalles_carrito WHERE usuarios_id = ?";
        List<Map<String, Object>> items = jdbcClient.sql(sqlCarrito).param(usuarios_id).query().listOfRows();

        if (items.isEmpty()) {
            throw new RuntimeException("El carrito está vacío.");
        }

        // B) Calcular suma de productos
        BigDecimal importe_productos = BigDecimal.ZERO;
        for (Map<String, Object> item : items) {
            BigDecimal precio = (BigDecimal) item.get("precio");
            int cantidad = (Integer) item.get("cantidad");
            importe_productos = importe_productos.add(precio.multiply(new BigDecimal(cantidad)));
        }

        // C) Insertar el PEDIDO (CORREGIDO: Agregamos fecha_hora_pago)
        UUID folio = UUID.randomUUID();
        
        // AQUÍ ESTÁ EL CAMBIO: Agregamos CURRENT_TIMESTAMP
        String sqlInsertPedido = """
            INSERT INTO pedidos (numero, importe_productos, importe_envio, usuarios_id, metodos_pago_id, fecha_hora_pago)
            VALUES (?, ?, ?, ?, ?, CURRENT_TIMESTAMP) 
            RETURNING id
        """;
        
        int pedidoId = jdbcClient.sql(sqlInsertPedido)
                .param(folio)
                .param(importe_productos)
                .param(importe_envio)
                .param(usuarios_id)
                .param(metodos_pago_id)
                .query(Integer.class)
                .single();

        // D) Mover items a 'detalles_pedido'
        String sqlDetalle = "INSERT INTO detalles_pedido (cantidad, precio, productos_id, pedidos_id) VALUES (?, ?, ?, ?)";
        for (Map<String, Object> item : items) {
            jdbcClient.sql(sqlDetalle)
                    .param(item.get("cantidad"))
                    .param(item.get("precio"))
                    .param(item.get("productos_id"))
                    .param(pedidoId)
                    .update();
        }

        // E) Vaciar Carrito
        String sqlClean = "DELETE FROM detalles_carrito WHERE usuarios_id = ?";
        jdbcClient.sql(sqlClean).param(usuarios_id).update();
    }

    public List<Pedidos> obtenerTodos() {
    String sql = "SELECT * FROM pedidos ORDER BY fecha DESC";
    return jdbcClient.sql(sql).query(new PedidosRM()).list();
}

}