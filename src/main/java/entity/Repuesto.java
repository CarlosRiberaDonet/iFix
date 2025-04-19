/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.math.BigDecimal;

/**
 *
 * @author Carlos Ribera
 */
public class Repuesto {
    
    private int id;
    private String tipo;
    private BigDecimal precio;
    private int idModeloDispositivo;
    
    // CONSTRUCTOR
    public Repuesto(int id, String tipo, BigDecimal precio, int idModeloDispositivo) {
        this.id = id;
        this.tipo = tipo;
        this.precio = precio;
        this.idModeloDispositivo = idModeloDispositivo;
    }

    public Repuesto() {
    }
    
    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getIdModeloDispositivo() {
        return idModeloDispositivo;
    }

    public void setIdModeloDispositivo(int idModeloDispositivo) {
        this.idModeloDispositivo = idModeloDispositivo;
    }
       
}
