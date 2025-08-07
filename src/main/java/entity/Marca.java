/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.*;
/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "marca")
public class Marca {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMarca;
    private String nombre;

    public Marca(int idMarca, String marca) {
        this.idMarca = idMarca;
        this.nombre = marca;
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
        return nombre;
    }

    public void setMarca(String marca) {
        this.nombre = marca;
    }
    
    @Override
    public boolean equals(Object object){
        if(this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Marca other = (Marca) object;
        return this.idMarca == other.idMarca;
    }
    
    @Override
    public String toString(){
        return this.nombre.toUpperCase();
    }
}
