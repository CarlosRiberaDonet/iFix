/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Carlos Ribera
 */
public class Dispositivo {
    private int id;
    private int imei;
    private int idModelo;
    private int idCliente;

    
    
    // CONSTRUCTOR
    public Dispositivo(int id, int imei, int idModelo, int idCliente) {
        this.id = id;
        this.imei = imei;
        this.idModelo = idModelo;
        this.idCliente = idCliente;
    }
    
    public Dispositivo(int imei, int idModelo, int idCliente){
        this.imei = imei;
        this.idModelo = idModelo;
        this.idCliente = idCliente;
    }
    
    public Dispositivo(){
        
    }
    
    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }
    
    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Dispositivo dispositivo = (Dispositivo) obj;
        return id == dispositivo.getId();
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
