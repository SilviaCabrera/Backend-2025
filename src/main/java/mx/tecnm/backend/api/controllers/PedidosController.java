package mx.tecnm.backend.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mx.tecnm.backend.api.models.Pedidos;
import mx.tecnm.backend.api.repository.PedidosDAO;

@RestController
@RequestMapping("/pedidos")
public class PedidosController {

    @Autowired
    private PedidosDAO repo;

    // GET: Mostrar TODOS los pedidos
    @GetMapping
    public List<Pedidos> todos() {
        return repo.obtenerTodos();
    }

    // GET: Mostrar pedidos por usuario específico
    @GetMapping("/usuario/{idUsuario}")
    public List<Pedidos> pedidosPorUsuario(@PathVariable int idUsuario) {
        return repo.obtenerPedidosPorUsuario(idUsuario);
    }

    // GET: Ver detalle de un pedido por ID
    @GetMapping("/{id}")
    public ResponseEntity<Pedidos> verPedido(@PathVariable int id) {
        try {
            Pedidos pedidos = repo.obtenerPedidoPorId(id);
            return ResponseEntity.ok(pedidos);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}
