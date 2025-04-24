/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Reparacion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionDao {
    
    private static final String SELECT_REPARACIONES = "SELECT r.fecha_entrada, "
                                                    + "r.fecha_salida, "
                                                    + "d.modelo AS dispositivo, "
                                                    + "r.precio_reparacion "
                                                    + "FROM reparacion r "
                                                    + "JOIN dispositivo d ON r.id_dispositivo = d.id "
                                                    + "WHERE r.id_cliente = ?";
    
    private static final String SELECT_REPARACIONES_CLIENTE = "SELECT r.fecha_entrada, "
                                                            + "r.fecha_salida, "
                                                            + "c.nombre AS cliente, "
                                                            + "d.modelo AS dispositivo, "
                                                            + "r.precio_reparacion "
                                                            + "FROM reparacion r "
                                                            + "JOIN cliente c ON r.id_cliente = c.id "
                                                            + "JOIN dispositivo d ON r.id_dispositivo = d.id "
                                                            + "WHERE telefono = ? AND "
                                                            + "fecha_salida = ? AND "
                                                            + "fecha_entrada = ?";
    
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
    
    public static List<Reparacion> getReparacionesbyTelefono(String telefono){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_REPARACIONES_CLIENTE)){
            stmt.setString(1, telefono);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Reparacion r = new Reparacion();
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setNombreCliente(rs.getString("cliente"));
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

    public static List<Reparacion> buscarReparaciones(String telefono, LocalDate fechaEntrada, LocalDate fechaSalida){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        String query = "SELECT r.fecha_entrada, r.fecha_salida, c.nombre AS cliente, d.modelo AS dispositivo, "
                     + "r.precio_reparacion AS precio "
                     + "FROM reparacion r "
                     + "JOIN cliente c ON r.id_cliente = c.id "
                     + "JOIN dispositivo d ON r.id_dispositivo = d.id "
                     + "WHERE 1=1";
        
        List<Object> parametros = new ArrayList<>();
        
        if(!telefono.trim().isEmpty()){
            query += "AND c.telefono = ? ";
            parametros.add(telefono.trim());
        }
        if(fechaEntrada != null && fechaSalida != null){
            query += "AND r.fecha_entrada >= ? AND r.fecha_salida <= ?";
            parametros.add(Date.valueOf(fechaEntrada));
            parametros.add(Date.valueOf(fechaSalida));
        }
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Reparacion r = new Reparacion();
            r.setFechaEntrada(rs.getDate("fecha_entrada"));
            r.setFechaSalida(rs.getDate("fecha_salida"));
            r.setNombreCliente(rs.getString("cliente"));
            r.setNombreDispositivo(rs.getString("dispositivo"));
            r.setPrecioReparacion(rs.getBigDecimal("precio"));
            reparacionesList.add(r);
        }
        }catch(SQLException e){
            System.out.println("Error al buscar reparaciones: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
}
