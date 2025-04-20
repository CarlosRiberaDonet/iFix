/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Ribera
 */
public class Utils {
    
   public static boolean checkNombre(String nombre){
       
       if(nombre != null && !nombre.isBlank()){
           return true;
       } else{
           JOptionPane.showMessageDialog(null, "No puede dejar el campo nombre en blanco");
           return false;
       }
   }
   
   public static boolean checkTelefono(String telefono){
       
       if(telefono != null && telefono.matches("\\d{9}")){
           return true;
       } else{
           JOptionPane.showMessageDialog(null, "El teléfono debe contener 9 digitos");
           return false;
       } 
   }
}
