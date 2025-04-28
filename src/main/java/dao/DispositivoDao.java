/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class DispositivoDao {
    
    private static final String SELECT_MARCA = "SELECT marca FROM marca ";
    private static final String SELECT_MODELO = "SELECT * FROM modelo WHERE modelo = ? AND id_marca = ?";
    private static final String MAX_ID_MODELO = "SELECT MAX(id) FROM modelo";

    public static List<String> getMarcas(){
        List<String> marcas = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try{
            PreparedStatement stmt = conn.prepareStatement(SELECT_MARCA);
            ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            marcas.add(rs.getString("marca"));
        }
        } catch(SQLException e){
            System.out.println("Error al obtener marcas: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
       
        return marcas;
    }

    public static Modelo checkModelo(int idMarca, String modelo){
        
        Connection conn = ConexionBD.connect();
        
        
        try{
            PreparedStatement stmt = conn.prepareStatement(SELECT_MODELO);
            stmt.setInt(1, idMarca);
            stmt.setString(2, modelo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){          
                int idModelo = rs.getInt("id");
                modelo = rs.getString("modelo");
                idMarca = rs.getInt("id_marca");
                Modelo modeloExistente = new Modelo(idModelo, modelo, idMarca);
                return modeloExistente;
            }
            
            
        } catch(SQLException e){
            System.out.println("Error al obtener el modelo: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return null;
    }
    
    public static int modeloMaxId(){
        
        int maxId = 0;
        
        Connection conn = ConexionBD.connect();
        try{
            PreparedStatement stmt = conn.prepareStatement(MAX_ID_MODELO);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {   // Siempre mover el cursor antes de leer datos
                maxId = rs.getInt(1);  // La primera columna es MAX(id)
            }
        } catch(SQLException e){
            System.out.println("Error al obtener el max. id de modelo: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return maxId;
    }
}
