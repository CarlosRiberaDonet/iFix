/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Carlos
 */
public class Marca {
    
    private int idMarca;
    private String marca;

    public Marca(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.marca = marca;
    }
    
    public Marca(){
        
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    @Override
    public String toString(){
        return this.marca.toUpperCase();
    }
}
