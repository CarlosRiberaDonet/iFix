/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 *
 * @author Carlos Ribera
 */
public class Reparacion {
    
    private int id;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private BigDecimal precioReparacion;
    private boolean garantia;
    private String comentarios;
    private String estado;
    private Dispositivo dispositivo;
    private int idDispositivo;
    
   // CONSTRUCTOR  
    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, int idDispositivo) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
    }

    public Reparacion(LocalDate fechaEntrada, LocalDate fechaSalida, Reparacion reparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
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
    
    public int getIdDispositivo(){
        return idDispositivo;
    }
    
    public void setIdDispositivo(int idDispositivo){
        this.idDispositivo = idDispositivo;
    }

    @Override
    public String toString() {
        return "Reparacion{" + "id=" + id + ", fechaEntrada=" + fechaEntrada + ", fechaSalida=" + fechaSalida + ", precioReparacion=" + precioReparacion + ", garantia=" + garantia + ", comentarios=" + comentarios + ", estado=" + estado + ", dispositivo=" + dispositivo + '}';
    }
}
