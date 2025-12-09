package mx.tecnm.backend.api.models;

import java.sql.Timestamp; // Usamos Timestamp para fecha y hora exacta
import java.math.BigDecimal;

public class Pedidos {
    private int id;
    private Timestamp fecha;
    private String numero; // UUID
    private BigDecimal importe_productos;
    private BigDecimal importe_envio;
    private int usuarios_id;
    private int metodos_pago_id;
    private Timestamp fecha_hora_pago;
    private BigDecimal importe_iva; // Generado por BD
    private BigDecimal total;      // Generado por BD

    public Pedidos() {}

    // Constructor completo
    public Pedidos(int id, Timestamp fecha, String numero, BigDecimal importe_productos, 
                  BigDecimal importe_envio, int usuarios_id, int metodos_pago_id, 
                  Timestamp fecha_hora_pago, BigDecimal importe_iva, BigDecimal total) {
        this.id = id;
        this.fecha = fecha;
        this.numero = numero;
        this.importe_productos = importe_productos;
        this.importe_envio = importe_envio;
        this.usuarios_id = usuarios_id;
        this.metodos_pago_id = metodos_pago_id;
        this.fecha_hora_pago = fecha_hora_pago;
        this.importe_iva = importe_iva;
        this.total = total;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public Timestamp getFecha() { return fecha; }
    public void setFecha(Timestamp fecha) { this.fecha = fecha; }

    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }

    public BigDecimal getimporte_productos() { return importe_productos; }
    public void setimporte_productos(BigDecimal importe_productos) { this.importe_productos = importe_productos; }

    public BigDecimal getimporte_envio() { return importe_envio; }
    public void setimporte_envio(BigDecimal importe_envio) { this.importe_envio = importe_envio; }

    public int getusuarios_id() { return usuarios_id; }
    public void setusuarios_id(int usuarios_id) { this.usuarios_id = usuarios_id; }

    public int getmetodos_pago_id() { return metodos_pago_id; }
    public void setmetodos_pago_id(int metodos_pago_id) { this.metodos_pago_id = metodos_pago_id; }

    public Timestamp getfecha_hora_pago() { return fecha_hora_pago; }
    public void setfecha_hora_pago(Timestamp fecha_hora_pago) { this.fecha_hora_pago = fecha_hora_pago; }

    public BigDecimal getimporte_iva() { return importe_iva; }
    public void setimporte_iva(BigDecimal importe_iva) { this.importe_iva = importe_iva; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }
}