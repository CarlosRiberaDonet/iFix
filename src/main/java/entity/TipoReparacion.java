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
    private String reparacion;
    
    // CONSTRUCTOR

    public TipoReparacion(int id, String reparacion) {
        this.id = id;
        this.reparacion = reparacion;
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

    public String getReparacion() {
        return reparacion;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }
    
    @Override
    public String toString(){
        return reparacion.toUpperCase();
    }
}
