/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Carlos Ribera
 */
public class Reparacion {
    
    private int id;
    private Date fechaEntrada;
    private Date fechaSalida;
    private BigDecimal precioReparacion;
    private boolean garantia;
    private String comentarios;
    private int idDispositivo;
    String nombreDispositivo;
    private int idtipoReparacion;
    private int idCliente;
    String nombreCliente;
    
    // CONSTRUCTOR
    public Reparacion(int id, Date fechaEntrada, Date fechaSalida, BigDecimal precioReparacion, boolean garantia, String comentarios, int idDispositivo, int idtipoReparacion, int idCliente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.idDispositivo = idDispositivo;
        this.idtipoReparacion = idtipoReparacion;
        this.idCliente = idCliente;
    }
    
    public Reparacion(Date fechaEntrada, Date fechaSalida, String nombreCliente, String nombreDispositivo, BigDecimal precioReparacion){
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.nombreCliente = nombreCliente;
        this.nombreDispositivo = nombreDispositivo;
        this.precioReparacion = precioReparacion;
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

    public Date getFechaEntrada() {
        return fechaEntrada;
    }

    public void setFechaEntrada(Date fechaEntrada) {
        this.fechaEntrada = fechaEntrada;
    }

    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
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

    public int getIdDispositivo() {
        return idDispositivo;
    }

    public void setIdDispositivo(int idDispositivo) {
        this.idDispositivo = idDispositivo;
    }

    public String getNombreDispositivo() {
        return nombreDispositivo;
    }

    public void setNombreDispositivo(String nombreDispositivo) {
        this.nombreDispositivo = nombreDispositivo;
    }

    public int getIdtipoReparacion() {
        return idtipoReparacion;
    }

    public void setIdtipoReparacion(int idtipoReparacion) {
        this.idtipoReparacion = idtipoReparacion;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
}
