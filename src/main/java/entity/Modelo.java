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
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Modelo modelo = (Modelo) obj;
        return idModelo == modelo.getIdModelo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelo); // usa el campo identificador
    }

    @Override
    public String toString(){
        return modelo;
    }
}
