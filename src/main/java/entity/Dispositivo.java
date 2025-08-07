/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import jakarta.persistence.*;
import java.util.Objects;

/**
 *
 * @author Carlos Ribera
 */
@Entity
@Table(name = "dispositivo")
public class Dispositivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incremento en BD
    private int id;
    private String imei;
    @ManyToOne
    @JoinColumn(name = "id_modelo", nullable = false)
    private Modelo modelo;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    // CONSTRUCTOR
    public Dispositivo(int id, String imei, Modelo modelo, Cliente cliente) {
        this.id = id;
        this.imei = imei;
        this.modelo = modelo;
        this.cliente = cliente;
    }
    
    public Dispositivo(String imei, Modelo modelo, Cliente cliente) {
        this.imei = imei;
        this.modelo = modelo;
        this.cliente = cliente;
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
    
    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public Modelo getIdModelo() {
        return modelo;
    }

    public void setIdModelo(Modelo idModelo) {
        this.modelo = modelo;
    }

    public Cliente getIdCliente() {
        return cliente;
    }

    public void setIdCliente(Cliente cliente) {
        this.cliente = cliente;
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
