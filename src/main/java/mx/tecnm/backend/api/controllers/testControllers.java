package mx.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class testControllers {

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hola API Rest";
    }

}