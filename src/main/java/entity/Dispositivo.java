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
    private int idCliente;
    private int idMarca;
    
    
    // CONSTRUCTOR
    public Dispositivo(int id, int idMarca, int idModelo, int imei) {
        this.id = id;
        this.idCliente = idMarca;
        this.idMarca = idModelo;
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

    public int getIdMarca() {
        return idCliente;
    }

    public void setIdMarca(int idMarca) {
        this.idCliente = idMarca;
    }

    public int getIdModelo() {
        return idMarca;
    }

    public void setIdModelo(int idModelo) {
        this.idMarca = idModelo;
    }

    public int getImei() {
        return imei;
    }

    public void setImei(int imei) {
        this.imei = imei;
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
