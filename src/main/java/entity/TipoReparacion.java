/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Carlos Ribera
 */
public class TipoReparacion {
    
    private int id;
    private String tipo;

    // CONSTRUCTOR
    public TipoReparacion(int id, String tipo) {
        this.id = id;
        this.tipo = tipo;
    }

    public TipoReparacion() {
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
    
    
}
