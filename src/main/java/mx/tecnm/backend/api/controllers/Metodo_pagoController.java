package mx.tecnm.backend.api.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;  
import mx.tecnm.backend.api.models.Metodo_pago;
import mx.tecnm.backend.api.repository.Metoto_pagoDAO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/metodo_pago")
public class Metodo_pagoController {
     @Autowired
    private Metoto_pagoDAO repo;

    @GetMapping()
    public ResponseEntity<List<Metodo_pago>> obtenerMetodo_pago() {
        List<Metodo_pago> resultado = repo.obtenerMetodo_pago();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Metodo_pago>obtenerMetodo_pagoPorId(@PathVariable int id){
        System.out.println("ID recibido: " + id);
         Metodo_pago metodo_pago = repo.obtenerMetodo_pagoPorId(id);
         if (metodo_pago != null) {
            return ResponseEntity.ok(metodo_pago);            
         }else{
            return ResponseEntity.notFound().build();
         }
    }


      @PostMapping()
    public ResponseEntity<Metodo_pago> crearMetodo_pago (@RequestParam String nombre, @RequestParam double comision){
        Metodo_pago metodo_pagoCreada = repo.crearMetodo_pago(nombre, comision);
        return ResponseEntity.ok(metodo_pagoCreada);
    }

    @PutMapping("/{id}")
public ResponseEntity<Metodo_pago> actualizarMetodo_pago(
        @PathVariable int id,
        @RequestParam String nombreNuevo,
        @RequestParam double comisionNuevo,
        @RequestParam String estadoNuevo
) {
    Metodo_pago metodo_pagoActualizado = repo.actualizarMetodo_pago(id, nombreNuevo, comisionNuevo, estadoNuevo);
    if (metodo_pagoActualizado == null) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(metodo_pagoActualizado);
    }
}


    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarMetodo_pago(@PathVariable int id) {
        boolean eliminado = repo.eliminarMetodo_pago(id);
        if (eliminado) {
            return ResponseEntity.ok("Metodo pago desactivado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}