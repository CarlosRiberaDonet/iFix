/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;

/**
 *
 * @author Carlos Ribera
 */
public class Utils {
    
    public static boolean checkNombre(String input){

        if(input == null && !input.trim().isEmpty()){
            JOptionPane.showMessageDialog(null, "El campo nombre y/o apellidos no puede estar vacío.");
            return false;
        } else{
            return true;
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

    public static LocalDate stringToLocalDate(String fecha) {    
        try{
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            return LocalDate.parse(fecha, formato);
        } catch(Exception e){
            JOptionPane.showMessageDialog(null, "Formato fecha no válido. Formato correcto dd/MM/yyyy");
            return null;
        }
     }

    public static String localDateToString(LocalDate fecha) {
        if(fecha == null ){
            return null;
        }
         DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
         return fecha.format(formato);
     }
    
    public static java.sql.Date LocalDateToSqlDate(LocalDate localDate) {
        if (localDate == null) return null;
        return java.sql.Date.valueOf(localDate);
    }

    public static BigDecimal stringToBigDecimal(String valor) {
       try{
           return new BigDecimal(valor.trim());
       } catch(NumberFormatException e){
           System.out.println("Error en el formato importe");
           return null;
       }
     }

    public static LocalDate checkFecha(String fechaStr){
       if(fechaStr != null || fechaStr.trim().isEmpty()){
         return stringToLocalDate(fechaStr);
       } else{
           return null;
       }      
     }

    public static boolean confirmationMessage(String titulo, String texto){
      
      int option = JOptionPane.showConfirmDialog(
              null,
              texto,
              titulo,
              JOptionPane.YES_NO_OPTION,
              JOptionPane.WARNING_MESSAGE
      );
      return option == JOptionPane.YES_OPTION; 
  }
  
}
