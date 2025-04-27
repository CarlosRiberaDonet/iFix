/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package entity;

/**
 *
 * @author Carlos Ribera
 */
public enum TipoReparacion {
    ALTAVOZ,
    BATERIA,
    CAMARA,
    MICROFONO,
    PANTALLA,
    PC,
    SOFTWARE,
    SOLDADURA;
    
    @Override
    public String toString() {
        // Que se vea la primera letra en mayúscula y el resto en minúscula
        String lowercase = name().toLowerCase();
        return lowercase.substring(0,1).toUpperCase() + lowercase.substring(1);
    }
}
