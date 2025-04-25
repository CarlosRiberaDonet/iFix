/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Dispositivo;
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
    
    private static final String MARCA = "SELECT DISTINCT marca FROM dispositivo ";
    private static final String SELECT_DISPOSITIVO = "SELECT * FROM dispositivo WHERE marca = ? ";

    public static List<String> getMarcasUnicas(){
        List<String> marcas = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try{
            PreparedStatement stmt = conn.prepareStatement(MARCA);
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

    public static List<Dispositivo> getModelosPorMarca(String marca){
        List<Dispositivo> dispositivos = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try{
             PreparedStatement stmt = conn.prepareStatement(SELECT_DISPOSITIVO);
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            dispositivos.add(new Dispositivo(
                rs.getInt("id"),
                rs.getString("marca"),
                rs.getString("modelo")
            ));
        }
        } catch(SQLException e){
            System.out.println("Error al obtener dispositivos: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return dispositivos;
    }
}
