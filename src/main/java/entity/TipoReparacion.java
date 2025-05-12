/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

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
    public String toString(){
        return tipoReparacion.toUpperCase();
    }
}
