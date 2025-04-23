/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Reparacion;
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
public class ReparacionDao {
    
    private static final String SELECT_REPARACIONES = "SELECT r.fecha_entrada, "
                                                    + "r.fecha_salida, "
                                                    + "d.nombre AS dispositivo, "
                                                    + "r.precio_reparacion "
                                                    + "FROM reparacion r "
                                                    + "JOIN dispositivo d ON r.id_dispositivo = d.id "
                                                    + "WHERE r.id_cliente = ?";
    
    public static List<Reparacion> getReparacionesList(int idCliente){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_REPARACIONES)){
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Reparacion r = new Reparacion();
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setNombreDispositivo(rs.getString("dispositivo"));
                r.setPrecioReparacion(rs.getBigDecimal("precio_reparacion"));
                reparacionesList.add(r);
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener las reparaciones: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
}
