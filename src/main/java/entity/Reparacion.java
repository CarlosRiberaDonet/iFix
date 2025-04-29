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
    private int idMarca;
    private String marca;
    private int idModelo;
    private String modelo;
    private int idTipoReparacion;
    private String tipoReparacion;
    private int idCliente;
    private String nombreCliente;
    
    // CONSTRUCTOR
    public Reparacion(Date fechaEntrada, Date fechaSalida, int idMarca, int idModelo, int idTipoReparacion, BigDecimal precioReparacion,
        boolean garantia, String comentarios, int idCliente){
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.idCliente = idCliente;
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

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    
    public int getIdTipoReparacion(){
        return idTipoReparacion;
    }
    
    public void setIdTipoReparacion(int idTipoReparacion){
        this.idTipoReparacion = idTipoReparacion;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String tipoReparacion) {
        this.tipoReparacion = tipoReparacion;
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

    @Override
    public String toString() {
        return "Reparacion{fechaEntrada=" + fechaEntrada + 
                ", fechaSalida=" + fechaSalida + 
                ", precioReparacion=" + precioReparacion + 
                ", garantia=" + garantia + 
                ", comentarios=" + comentarios + 
                ", idMarca=" + idMarca + 
                ", idModelo=" + idModelo + 
                ", idTipoReparacion=" + idTipoReparacion +
                ", idCliente=" + idCliente +'}';
    }
    
    
}
