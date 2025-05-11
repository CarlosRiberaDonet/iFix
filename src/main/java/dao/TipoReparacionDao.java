/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.TipoReparacion;
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
public class TipoReparacionDao {
    
    private static final String SELECT_TIPO_REPARACION = "SELECT * FROM tipo_reparacion";
    private static final String GET_ID_TIPO_REPARACION = "SELECT id FROM tipo_reparacion WHERE reparacion = ?";
    private static final String INSERT_TIPO_REPARACION = "INSERT INTO tipo_reparacion (reparacion) VALUES(?)";
    
    public List<TipoReparacion> getTipoReparacionList(){
        
        List<TipoReparacion> tipoReparacionesList = new ArrayList<>();
        
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(SELECT_TIPO_REPARACION);
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("id"));
                t.setReparacion(rs.getString("reparacion"));             
                tipoReparacionesList.add(t);
            }
        } catch (SQLException e){ 
     }
        return tipoReparacionesList;
    }
    
    public static int insertTipoReparacion(String nuevoTipoReparacion){
        
        int idTipoReparacion = -1;
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(INSERT_TIPO_REPARACION)){
            stmt.setString(1, nuevoTipoReparacion);
           int insert = stmt.executeUpdate();
            if(insert > 0){
                idTipoReparacion = getTipoReparacionId(nuevoTipoReparacion);
            }
        } catch(SQLException e){
            System.out.println("Error al agregar el tipo de reparacion en la BD: " + e.getMessage());
            e.printStackTrace();
        }finally{
            ConexionBD.close(conn);
        }
        return idTipoReparacion;
    }
    
    
      public static int getTipoReparacionId(String tipo){
        
        int idTipo = -1;
        
        Connection conn = ConexionBD.connect();
        try( PreparedStatement stmt = conn.prepareStatement(GET_ID_TIPO_REPARACION)){
           
            stmt.setString(1, tipo.toUpperCase());
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idTipo = rs.getInt("id");
            }
            stmt.close();
        } catch(SQLException e){
            System.out.println("Error al obtener el id de la tabla tipo_reparacion: " + e.getMessage());
        } finally{
           ConexionBD.close(conn);
        }
        if (idTipo == -1) {
            throw new IllegalArgumentException("No se encontró el tipo de reparación: " + tipo);
        }
        return idTipo;
    }
}
