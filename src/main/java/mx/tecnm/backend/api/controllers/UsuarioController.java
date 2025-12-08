package mx.tecnm.backend.api.controllers;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import mx.tecnm.backend.api.models.Usuario;
import mx.tecnm.backend.api.repository.UsuarioDAO;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    private UsuarioDAO repo;

    @GetMapping()
    public ResponseEntity<List<Usuario>> obtenerUsuario() {
        List<Usuario> resultado = repo.obtenerUsuario();
        return ResponseEntity.ok(resultado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        System.out.println("ID recibido: " + id);
        Usuario usuario = repo.obtenerUsuarioPorId(id);
        if (usuario != null) {
            return ResponseEntity.ok(usuario);            
        } else {
            return ResponseEntity.notFound().build();
        }
    }

   @PostMapping()
public ResponseEntity<Usuario> crearUsuario(
        @RequestParam String nombre,
        @RequestParam String email,
        @RequestParam String telefono,
        @RequestParam String sexo,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimiento,
        @RequestParam String contrasena) {

    Usuario usuarioCreado = repo.crearUsuario(
            nombre, email, telefono, sexo, fechaNacimiento, contrasena
    );

    return ResponseEntity.ok(usuarioCreado);
}


    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(
            @PathVariable int id,
            @RequestParam String nombreNuevo,
            @RequestParam String emailNuevo,
            @RequestParam String telefonoNuevo,
            @RequestParam String sexoNuevo,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaNacimientoNueva,
            @RequestParam String contrasenaNueva) {
        
        Usuario usuarioActualizado = repo.actualizarUsuario(
            id, nombreNuevo, emailNuevo, telefonoNuevo, 
            sexoNuevo, fechaNacimientoNueva, contrasenaNueva
        );
        
        if (usuarioActualizado == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(usuarioActualizado);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarUsuario(@PathVariable int id) {
        boolean eliminado = repo.eliminarUsuario(id);
        if (eliminado) {
            return ResponseEntity.ok("Usuario desactivado correctamente");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}