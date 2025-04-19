/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Carlos Ribera
 */
public class ConexionBD {
    
   private static final String URL = "jdbc:mysql://localhost:3306/reparaciones?useSSL=false&serverTimezone=UTC";
   private static final String USER = "root";
   private static final String PASS = "1234";
   
   
   
   
   public static Connection connect(){
       Connection conn = null;
       try{
            conn = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion exitosa");
                
       }catch(SQLException e){
           System.out.println("No se ha podido establecer la conexion con la BD" + e.getMessage());
       }    
       return conn;   
   }
   
   public static void close(Connection conn){
       
       try{
           if(conn != null){
               conn.close();
           }
       } catch(SQLException e){
           System.out.println("Error al cerrar conexion");
       }
   }
}
