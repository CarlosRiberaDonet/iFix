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
    private int idMarca;
    private Marca marca;
    private int idModelo;
    private Modelo modelo;
    private int idTipoReparacion;
    private TipoReparacion tipoReparacion;
    private BigDecimal precioReparacion;
    private boolean garantia;
    private String comentarios;
    private int idCliente;
    private Cliente cliente;
    
   // CONSTRUCTOR  
    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, int idMarca, int idModelo, int idTipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, int idCliente) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.idTipoReparacion = idTipoReparacion;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.idCliente = idCliente;
    }

    public Reparacion(LocalDate fechaEntrada, LocalDate fechaSalida, int idMarca, int idModelo, int idTipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios, int idCliente) {
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.idTipoReparacion = idTipoReparacion;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
        this.idCliente = idCliente;
    }

    public Reparacion(int id, LocalDate fechaEntrada, LocalDate fechaSalida, int idMarca, int idModelo, int idTipoReparacion, BigDecimal precioReparacion, boolean garantia, String comentarios) {
        this.id = id;
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
        this.idMarca = idMarca;
        this.idModelo = idModelo;
        this.idTipoReparacion = idTipoReparacion;
        this.precioReparacion = precioReparacion;
        this.garantia = garantia;
        this.comentarios = comentarios;
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

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public Modelo getModelo() {
        return modelo;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public int getIdTipoReparacion() {
        return idTipoReparacion;
    }

    public void setIdTipoReparacion(int idTipoReparacion) {
        this.idTipoReparacion = idTipoReparacion;
    }

    public TipoReparacion getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(TipoReparacion tipoReparacion) {
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
