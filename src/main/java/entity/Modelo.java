/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 *
 * @author Carlos
 */
@Entity
@Table(name = "modelo")
public class Modelo {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idModelo;
    private String nombreModelo;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;
    private int idMarca;

    public Modelo(int id, String nombreModelo, Marca marca) {
        this.idModelo = id;
        this.nombreModelo = nombreModelo;
        this.marca = marca;
    }
    
    public Modelo(int id, String nombreModelo, int idMarca) {
        this.idModelo = id;
        this.nombreModelo = nombreModelo;
        this.marca = marca;
    }
    
    public Modelo(){
        
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int id) {
        this.idModelo = id;
    }

    public String getnombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public Marca getMarca() {
        return marca;
    }

    public void setMarca(Marca marca) {
        this.marca = marca;
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
        return Objects.hash(idModelo);
    }

    @Override
    public String toString(){
        return nombreModelo.toUpperCase();
    }
}
