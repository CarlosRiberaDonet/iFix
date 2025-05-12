/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Carlos
 */
public class Modelo {
    
    private int idModelo;
    private String modelo;
    private int idMarca;

    public Modelo(int id, String modelo, int id_marca) {
        this.idModelo = id;
        this.modelo = modelo;
        this.idMarca = id_marca;
    }
    
    public Modelo(){
        
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int id) {
        this.idModelo = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }
    
    @Override
    public String toString(){
        return this.modelo.toUpperCase();
    }
}
