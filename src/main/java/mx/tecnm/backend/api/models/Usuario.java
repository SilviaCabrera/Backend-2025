package mx.tecnm.backend.api.models;
import java.time.LocalDateTime;
import java.time.LocalDate;

public record Usuario (int id, String nombre, String email, String telefono, String sexo, LocalDate fecha_nacimiento, 
    String contrasena, LocalDateTime fecha_registro, String estado){

}