/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Marca;
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
public class MarcaModeloDao {
    private static final String MAX_ID_MODELO = "SELECT MAX(id) FROM modelo";
    
    // QUERYS para tabla marca
    private static final String GET_ID_MARCA = "SELECT id FROM marca WHERE marca = ?";
    private static final String SELECT_MARCA = "SELECT * FROM marca ";
    private static final String INSERT_MARCA = "INSERT INTO marca (marca) VALUES (?)";
    
    // QUERYS para tabla modelo
    private static final String GET_ID_MODELO = "SELECT id FROM modelo WHERE modelo = ?";
    private static final String SELECT_MODELO = "SELECT * FROM modelo";
    private static final String INSERT_MODELO = "INSERT INTO modelo (modelo, id_marca) VALUES (?, ?)";
 
    public static int getMarcaId(String marca) {
        int idMarca = -1;
        Connection conn = ConexionBD.connect();
        try {
            PreparedStatement stmt = conn.prepareStatement(GET_ID_MARCA);
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idMarca = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el id de la marca: " + e.getMessage());
        } finally {
            ConexionBD.close(conn);
        }
        return idMarca;
    }
    
    public static List<Marca> getMarcas(){
        
        List<Marca> marcas = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_MARCA);
            ResultSet rs = stmt.executeQuery()){   

            while (rs.next()) {
            int id = rs.getInt("id");
            String marca = rs.getString("marca");
            Marca m = new Marca(id, marca);
            marcas.add(m);
        }
        } catch(SQLException e){
            System.out.println("Error al obtener marcas: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
       
        return marcas;
    }
    
    public static int insertMarcaByString(String nuevaMarca){
        
        int idMarca = -1;
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(INSERT_MARCA)){
            stmt.setString(1, nuevaMarca);
            int insert = stmt.executeUpdate();
            if(insert > 0){
                idMarca = getMarcaId(nuevaMarca);
            }
            
        } catch(SQLException e){
            System.out.println("Error al agregar la marca a la BD: " + e.getMessage());
            e.printStackTrace();
        }finally{
            ConexionBD.close(conn);
        }
        return idMarca;
    }
    
     public static int getModeloId(String modelo){
        
        int idModelo = -1;
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(GET_ID_MODELO)){
            stmt.setString(1, modelo);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){          
                idModelo = rs.getInt("id");
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener el modelo: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return idModelo;
    }
     
     public static List<Modelo> getModelos(){
        
        List<Modelo> modelosList = new ArrayList<>();
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_MODELO);
            ResultSet rs = stmt.executeQuery()){
            
            while(rs.next()){
                
                int id = rs.getInt("id");
                String modelo = rs.getString("modelo");
                int idMarca = rs.getInt("id_marca");
                Modelo m = new Modelo(id, modelo, idMarca);
                modelosList.add(m);
                
                
            }
        } catch(SQLException e){
            System.out.println("Error al obener la lista de modelos");
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return modelosList;
    }
    
    public static int insertModeloByString(String nuevoModelo, int idMarca){
        
        int idModelo = -1;
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(INSERT_MODELO)){
            stmt.setString(1, nuevoModelo);
            stmt.setInt(2, idMarca);
            int insert = stmt.executeUpdate();
            if(insert > 0){
                idModelo = getModeloId(nuevoModelo);
            }
        } catch(SQLException e){
            System.out.println("Eror al agregar el modelo a la BD: " + e.getMessage());
            e.printStackTrace();
        }finally{
            ConexionBD.close(conn);
        }
        return idModelo;
    }
     
    

    /*public static int modeloMaxId(){
        
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
    }*/
}
