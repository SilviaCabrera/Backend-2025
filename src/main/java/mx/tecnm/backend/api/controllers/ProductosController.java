package mx.tecnm.backend.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Productos;
import mx.tecnm.backend.api.repository.ProductosDAO;

@RestController
@RequestMapping("/productos")
public class ProductosController {

    @Autowired
    private ProductosDAO repo;

    // =============================
    // GET ALL
    // =============================
    @GetMapping()
    public ResponseEntity<List<Productos>> obtenerProductos() {
        List<Productos> resultado = repo.obtenerProductos();
        return ResponseEntity.ok(resultado);
    }

    // =============================
    // GET BY ID
    // =============================
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductosPorId(@PathVariable int id) {
        Productos productos = repo.obtenerProductosPorId(id);
        if (productos != null) {
            return ResponseEntity.ok(productos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // =============================
    // CREATE
    // =============================
    @PostMapping()
public ResponseEntity<Productos> crearProductos(
       @RequestParam String nombre,
        @RequestParam double precio,
        @RequestParam String sku,
        @RequestParam String color,
        @RequestParam String marca,
        @RequestParam String descripcion,
        @RequestParam double peso,
        @RequestParam double alto,
        @RequestParam double ancho,
        @RequestParam double profundidad,
        @RequestParam int categorias_id,
        @RequestParam String estado){

    Productos productoCreado = repo.crearProductos(
            nombre, precio, sku, color, marca, descripcion,
            peso, alto, ancho, profundidad, categorias_id, estado
    );

    return ResponseEntity.ok(productoCreado);
}


    // =============================
    // UPDATE
    // =============================
   @PutMapping("/{id}")
public ResponseEntity<Productos> actualizarProducto(
        @PathVariable int id,
        @RequestParam String nombreNuevo,
        @RequestParam double precioNuevo,
        @RequestParam String skuNuevo,
        @RequestParam String colorNuevo,
        @RequestParam String marcaNuevo,
        @RequestParam String descripcionNuevo,
        @RequestParam double pesoNuevo,
        @RequestParam double altoNuevo,
        @RequestParam double anchoNuevo,
        @RequestParam double profundidadNuevo,
        @RequestParam int categorias_idNuevo,
        @RequestParam String estadoNuevo
    ) {

    Productos productoActualizado = repo.actualizarProducto(
        id, nombreNuevo, precioNuevo, skuNuevo, colorNuevo,
        marcaNuevo, descripcionNuevo, pesoNuevo, altoNuevo,
        anchoNuevo, profundidadNuevo, categorias_idNuevo, estadoNuevo
    );

    if (productoActualizado == null) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(productoActualizado);
    }
}


    // =============================
    // DELETE
    // =============================
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarProductos(@PathVariable int id) {
        boolean eliminado = repo.eliminaProductos(id);
        if (eliminado) {
            return ResponseEntity.ok("Producto desactivado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
