/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Dispositivo;
import entity.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class DispositivoDao {
    
    private static final String INSERT_MODELO = "INSERT INTO dispositivo(imei, idModelo, idCliente) VALUES(?, ?, ?);";
    private static final String GET_IMEI = "SELECT * FROM dispositivo WHERE imei = ?;";
    
    public static boolean createDispositivo(Dispositivo nuevoDispositivo){
        
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(INSERT_MODELO)){
            stmt.setString(1, nuevoDispositivo.getImei());
            stmt.setInt(2, nuevoDispositivo.getId());
            stmt.setInt(3, nuevoDispositivo.getIdCliente().getId());
            stmt.executeUpdate();
            
        } catch(SQLException e){
            System.out.println("Error al insertar dispositivo");
            e.printStackTrace();
            return false;
        } finally{
            ConexionBD.close(conn);
        }     
       return true;
    }
    
    public static boolean getImei(String imei){
        
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(GET_IMEI)){
            stmt.setString(1, imei);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                return true; // Si existe un dispositivo con ese imei, devuelve true
            }
        } catch(SQLException e){
            System.out.println("Error al obtener dispositivo mediante imei");
            e.printStackTrace();
        }
        return false;
    }
    
//    public public static List<Modelo> getModelos(){
//        
//        List<Modelo> modelosList = new ArrayList<>();
//        
//        Connection conn = ConexionBD.connect();
//        try(PreparedStatement stmt = conn.prepareStatement(SELECT_MODELO);
//            ResultSet rs = stmt.executeQuery()){
//            
//            while(rs.next()){
//                
//                int id = rs.getInt("id");
//                String modelo = rs.getString("modelo");
//                int idMarca = rs.getInt("id_marca");
//                Modelo m = new Modelo(id, modelo, idMarca);
//                modelosList.add(m);
//                
//                
//            }
//        } catch(SQLException e){
//            System.out.println("Error al obener la lista de modelos");
//            e.printStackTrace();
//        } finally{
//            ConexionBD.close(conn);
//        }
//        return modelosList;
//    }
}
