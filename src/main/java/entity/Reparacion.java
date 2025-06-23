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
    private Dispositivo dispositivo;
    private TipoReparacion tipoReparacion;
    private BigDecimal precioReparacion;
    private boolean garantia;
    private String comentarios;
    private String estado;
    private Cliente cliente;
    
   // CONSTRUCTOR  
    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, Dispositivo dispositivo, TipoReparacion tipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, Cliente cliente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.dispositivo = dispositivo;
        this.tipoReparacion = tipoReparacion;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Reparacion(LocalDate fechaEntrada, LocalDate fechaSalida,Dispositivo dispositivo, Reparacion reparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado, Cliente cliente) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.dispositivo = dispositivo;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
        this.cliente = cliente;
    }

    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, Dispositivo dispositivo, int idTipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, String estado) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.dispositivo = dispositivo;
        this.tipoReparacion = tipoReparacion;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.estado = estado;
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

    public Dispositivo getDispositivo() {
        return dispositivo;
    }

    public void setIdMarca(Dispositivo dispositivo) {
        this.dispositivo = dispositivo;
    }

    public TipoReparacion getTipoReparacion() {
        return tipoReparacion;
    }

    public void setIdTipoReparacion(TipoReparacion tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
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
    
    public String getEstado(){
        return estado;
    }
    
    public void setEstado(String estado){
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setIdCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Reparacion{fechaEntrada=" + fechaEntrada +
                ", id=" + id +
                ", fechaSalida=" + fechaSalida +
                ", precioReparacion=" + precioReparacion +
                ", garantia=" + garantia +
                ", comentarios=" + comentarios +
                ", idMarca=" + dispositivo.getIdMarca() +
                ", idModelo=" + dispositivo.getIdModelo() +
                ", imei=" + dispositivo.getImei() +
                ", idTipoReparacion=" + tipoReparacion.getId() +
                ", estado=" + estado +
                ", idCliente=" + cliente.getId() +'}';
    }  
}
