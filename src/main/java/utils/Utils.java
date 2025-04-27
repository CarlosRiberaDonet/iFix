/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Ribera
 */
public class Utils {
    
   public static boolean checkNombre(String input){
       
       if(input != null && !input.isBlank()){
           return true;
       } else{
           JOptionPane.showMessageDialog(null, "El campo nombre y/o apellidos no puede estar vacío.");
           return false;
       }
   }
   
   public static boolean checkTelefono(String telefono){
       
       if(telefono != null && telefono.matches("\\d{9}")){
           return true;
       } else{
           JOptionPane.showMessageDialog(null, "El teléfono no puede estar vacío y, debe contener 9 dígitos.");
           return false;
       } 
   }
   
   public static String checkTextField(String textField){
       
       if(textField == null || textField.isEmpty()){
           textField  = "";
       }
       return textField;
   }
 
   public static String fechaActualToString(){  
       DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
       LocalDate fechaActual = LocalDate.now();
       String fechaFormateada = formato.format(fechaActual);
       
       return fechaFormateada;
   }
   
   public static Date stringToDate(String fecha) {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fecha, formato);
        Date fechaDate = Date.valueOf(localDate);
        return fechaDate;
    }
}
