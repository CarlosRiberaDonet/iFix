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
}
