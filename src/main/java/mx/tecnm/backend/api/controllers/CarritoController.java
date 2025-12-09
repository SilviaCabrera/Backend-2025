package mx.tecnm.backend.api.controllers;

import mx.tecnm.backend.api.models.Detalles_carrito;
import mx.tecnm.backend.api.repository.CarritoDAO;
import mx.tecnm.backend.api.repository.PedidosDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/carrito")
public class CarritoController {

    @Autowired
    private CarritoDAO carritoRepo;

    @Autowired
    private PedidosDAO pedidoRepo;

    // YA NO USAMOS EL ID FIJO
    // private final int USUARIO_TEST_ID = 1; <--- BORRADO

    // 1. VER CARRITO
    // URL: GET .../carrito?usuarios_id=1
    @GetMapping
    public List<Detalles_carrito> verCarrito(@RequestParam int usuarios_id) {
        return carritoRepo.obtenerCarrito(usuarios_id);
    }

    // 2. AGREGAR AL CARRITO
    // URL: POST .../carrito?usuarios_id=1&productos_id=5&cantidad=2
    @PostMapping
    public ResponseEntity<String> agregar(
            @RequestParam int usuarios_id,
            @RequestParam int productos_id,
            @RequestParam int cantidad) {

        carritoRepo.agregarProducto(usuarios_id, productos_id, cantidad);
        return ResponseEntity.ok("Producto agregado al carrito");
    }

    // 3. ELIMINAR UN ITEM
    // URL: DELETE .../carrito/20?usuarios_id=1 (Donde 20 es el producto)
    @DeleteMapping("/borrar/{productos_id}")
    public ResponseEntity<String> eliminarProducto(
            @PathVariable("productos_id") int productosId,
            @RequestParam("usuarios_id") int usuariosId) {

        boolean eliminado = carritoRepo.eliminarProducto(usuariosId, productosId);

        if (eliminado)
            return ResponseEntity.ok("Producto eliminado del carrito");

        return ResponseEntity.notFound().build();
    }

    // 4. LIMPIAR TODO EL CARRITO
    // URL: DELETE .../carrito?usuarios_id=1
    @DeleteMapping
    public ResponseEntity<String> limpiar(@RequestParam int usuarios_id) {
        carritoRepo.limpiarCarrito(usuarios_id);
        return ResponseEntity.ok("Carrito vaciado");
    }

    // 5. GENERAR PEDIDO (COMPRAR)
    // URL: POST .../carrito/comprar?usuarios_id=1&metodoPagoId=2
    @PostMapping("/comprar")
    public ResponseEntity<String> realizarCompra(
            @RequestParam int usuarios_id,
            @RequestParam int metodoPagoId) {
        try {
            BigDecimal envio = new BigDecimal("50.00");
            pedidoRepo.generarPedido(usuarios_id, metodoPagoId, envio);
            return ResponseEntity.ok("¡Compra exitosa! Pedido generado y carrito vacío.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}