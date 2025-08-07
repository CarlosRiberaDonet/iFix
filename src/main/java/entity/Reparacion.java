/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class Reparacion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento en BD
    private int id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private BigDecimal precioReparacion;
    private boolean garantia;
    private String comentarios;
    private String estado;
    @ManyToOne
    @JoinColumn(name = "id_dispositivo")
    private Dispositivo dispositivo;
    private int idDispositivo;
    
    @ManyToMany
    @JoinTable(
        name = "reparacion_tipo",
        joinColumns = @JoinColumn(name = "id_reparacion"),
        inverseJoinColumns = @JoinColumn(name = "id_tipo_reparacion")
    )
    private List<TipoReparacion> tipoReparacion;
    
   // CONSTRUCTOR  
    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, Dispositivo dispositivo) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
    }

    public Reparacion(LocalDate fechaEntrada, LocalDate fechaSalida, Reparacion reparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, List<TipoReparacion> tipoReparacion) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
        this.tipoReparacion = tipoReparacion;
    }

    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, Dispositivo dispositivo, int idTipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
    }

    public Reparacion(LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, Dispositivo dispositivo) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
        this.dispositivo = dispositivo;
    }
    
    public Reparacion(){
        
    }
    
    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(LocalDate fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public BigDecimal getPrecioReparacion() {
        return precioReparacion;
    }

    public void setPrecioReparacion(BigDecimal precioReparacion) {
        this.precioReparacion = precioReparacion;
    }

    public boolean isGarantia() {
        return garantia;
    }

    public void setGarantia(boolean garantia) {
        this.garantia = garantia;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setDispositivo(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public List<TipoReparacion> getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(List<TipoReparacion> tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
    }

    @Override
    public String toString() {
        return "Reparacion{" + "id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", precioReparacion=" + precioReparacion + ", garantia=" + garantia + ", comentarios=" + comentarios + ", estado=" + estado + ", dispositivo=" + dispositivo + '}';
    }
}
