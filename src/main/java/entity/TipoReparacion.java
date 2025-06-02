/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author Carlos
 */
public class TipoReparacion {
    
    private int id;
    private String tipoReparacion;
    
    // CONSTRUCTOR

    public TipoReparacion(int id, String reparacion) {
        this.id = id;
        this.tipoReparacion = reparacion;
    }
    
    public TipoReparacion(){
        
    }
    
    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoReparacion() {
        return tipoReparacion;
    }

    public void setTipoReparacion(String reparacion) {
        this.tipoReparacion = reparacion;
    }
    
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if(object == null || getClass() != object.getClass()) return false;
        TipoReparacion tipoReparacion = (TipoReparacion) object;
        return id == tipoReparacion.getId();
    }
    
    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
    
    @Override
    public String toString(){
        return tipoReparacion.toUpperCase();
    }
}
