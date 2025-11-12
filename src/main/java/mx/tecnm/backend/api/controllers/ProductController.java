package mx.tecnm.backend.api;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@GetMapping("/")
public String home() {
    return "¡Bienvenida a mi API!";
}

@RestController
@RequestMapping("/test")
public class ProductController {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola API Rest";
    }

}