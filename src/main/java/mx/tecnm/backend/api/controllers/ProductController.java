package mx.tecnm.backend.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import mx.tecnm.backend.api.models.Product;  // 👈 Importamos la clase Product

import java.util.List;   // Para devolver listas

@RestController
@RequestMapping("/test")  // la ruta base será /products
public class ProductController {

    // Endpoint de bienvenida
    @GetMapping("/")
    public String home() {
        return "¡Bienvenida a mi API de productos!";
    }

    @GetMapping("/hello") 
    public String helloWorld() { 
        return "Hola API Rest"; 
        }
        

    // Endpoint que devuelve una lista de productos
    @GetMapping("/productos")
    public List<Product> getAllProducts() {
        // Creamos algunos productos de ejemplo
        Product p1 = new Product("Manzana", "ABC123", 12.5);
        Product p2 = new Product("Plátano", "DEF456", 8.9);
        Product p3 = new Product("Naranja", "GHI789", 10.0);

        // Los devolvemos como lista
        return List.of(p1, p2, p3);
    }


    @GetMapping("/productos/{id}")
public ResponseEntity<Product> getProductoById(@PathVariable int id) {
    if (id < 0 || id > 2) {
        return ResponseEntity.notFound().build();
    }

    Product p1 = new Product("Manzana", "ABC123", 12.5);
    Product p2 = new Product("Plátano", "DEF456", 8.9);
    Product p3 = new Product("Naranja", "GHI789", 10.0);

    Product[] productos = {p1, p2, p3};

    return ResponseEntity.ok(productos[id]);
}


    
}
