/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class Cliente {
    
    private int id;
    private String nombre;
    private String apellidos;
    private String telefono;
    private String direccion;
    private List<Reparacion> reparacionesList = new ArrayList<>();
    
    
    // CONSTRUCTOR
    public Cliente(int id, String nombre, String apellidos, String telefono, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public Cliente(String nombre, String apellidos, String telefono, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
    }
    
    public Cliente(int id, String nombre, String apellidos, String telefono, String direccion, List<Reparacion> reparacionesList){
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.direccion = direccion;
        this.reparacionesList = reparacionesList;
    }

    public Cliente() {
    }
    
    // GETTERS Y SETTERS

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    private List<Reparacion> getReparacionesList(){
        return reparacionesList;
    }
    
    private void setReparacionesList(List<Reparacion> reparacionesList){
        this.reparacionesList = reparacionesList;
    }
    
    @Override
    public String toString(){
            return 
                "ID: " + id + "\n" + 
                "NOMBRE: " + nombre.toUpperCase() + "\n" +
                "APELLIDOS: " + apellidos.toUpperCase() + "\n" +
                "TELEFONO: " + telefono + "\n" +
                "DIRECCION: " + direccion.toUpperCase() + "\n"
                ;
    }
}
