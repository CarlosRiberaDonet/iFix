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
                                                            + "t.tipo AS reparacion, "
                                                            + "r.precio_reparacion "
                                                            + "FROM reparacion r "
                                                            + "JOIN cliente c ON r.id_cliente = c.id "
                                                            + "JOIN dispositivo d ON r.id_dispositivo = d.id "
                                                            + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
                                                            + "WHERE telefono = ? AND "
                                                            + "fecha_salida = ? AND "
                                                            + "fecha_entrada = ?";
    
    private static final String SELECT_ID_REPARACION = "SELECT r.id AS idReparacion, "
            + "c.nombre AS cliente, "
            + "r.fecha_entrada, "
            + "r.fecha_salida, "
            + "d.marca AS fabricante, "
            + "d.modelo AS dispositivo, "
            + "t.tipo AS reparacion, "
            + "r.precio_reparacion AS importe, "
            + "r.garantia, "
            + "r.comentarios AS comentarios "
            + "FROM reparacion r "
            + "JOIN dispositivo d ON r.id_dispositivo = d.id "
            + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
            + "JOIN cliente c ON r.id_cliente = c.id "
            + "WHERE r.id = ?";
    
    
    private static final String INSERT_REPARACION = " INSERT INTO reparacion (fecha_entrada, fecha_salida, id_marca, id_modelo, id_tipo_reparacion, "
            + "precio, garantia, comentarios, id_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String GET_ID_MARCA = "SELECT id FROM marca WHERE marca = ?";
    private static final String GET_ID_TIPO_REPARACION = "SELECT id FROM tipo_reparacion WHERE UPPER(reparacion) = ?";
    
    
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
                r.setModelo(rs.getString("dispositivo"));
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
                r.setModelo(rs.getString("dispositivo"));
                r.setTipoReparacion(rs.getString("reparacion"));
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
        
        String query = "SELECT r.id AS idReparacion, "
                + "r.fecha_entrada, "
                + "r.fecha_salida, "
                + "r.precio AS importe, "
                + "r.garantia, "
                + "r.comentarios, "
                + "c.nombre AS cliente, "
                + "m.marca, "
                + "mo.modelo, "
                + "t.reparacion "
                + "FROM reparacion r "
                + "JOIN cliente c ON r.id_cliente = c.id "
                + "JOIN marca m ON r.id_marca = m.id "
                + "JOIN modelo mo ON r.id_modelo = mo.id "
                + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
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
            r.setId(rs.getInt("idReparacion"));
            r.setFechaEntrada(rs.getDate("fecha_entrada"));
            r.setFechaSalida(rs.getDate("fecha_salida"));
            r.setPrecioReparacion(rs.getBigDecimal("importe"));
            r.setGarantia(rs.getBoolean("garantia"));
            r.setComentarios(rs.getString("comentarios"));
            r.setNombreCliente(rs.getString("cliente"));
            r.setMarca(rs.getString("marca"));
            r.setModelo(rs.getString("modelo"));
            r.setTipoReparacion(rs.getString("reparacion"));

            reparacionesList.add(r);
        }
        }catch(SQLException e){
            System.out.println("Error al buscar reparaciones: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
    
    public static Reparacion getReparacionById(int id){
        
        Reparacion reparacion = null;
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(SELECT_ID_REPARACION);
            stmt.setInt(1, id);
            System.out.println("ID reparacion: " + id);
            ResultSet rs = stmt.executeQuery();
            
            
            if(rs.next()){
                reparacion = new Reparacion();
                reparacion.setId(rs.getInt("idReparacion"));
                reparacion.setNombreCliente(rs.getString("cliente"));
                reparacion.setFechaEntrada(rs.getDate("fecha_entrada"));
                reparacion.setFechaSalida(rs.getDate("fecha_salida"));
                reparacion.setMarca(rs.getString("fabricante"));
                reparacion.setModelo(rs.getString("dispositivo"));
                reparacion.setTipoReparacion(rs.getString("reparacion"));
                reparacion.setPrecioReparacion(rs.getBigDecimal("importe"));
                reparacion.setGarantia(rs.getBoolean("garantia"));
                reparacion.setComentarios(rs.getString("comentarios"));
            }    
        } catch(SQLException e){
            System.out.println("Error al buscar la reparacion: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return reparacion;
    }
    
    public static boolean insertarReparacion(Reparacion r){
        
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(INSERT_REPARACION );
            stmt.setDate(1, new java.sql.Date(r.getFechaEntrada().getTime()));
            stmt.setDate(2, new java.sql.Date(r.getFechaSalida().getTime()));
            stmt.setInt(3, r.getIdMarca());
            stmt.setInt(4, r.getIdModelo());          
            stmt.setInt(5, r.getIdTipoReparacion());
            stmt.setBigDecimal(6, r.getPrecioReparacion());
            stmt.setBoolean(7, r.isGarantia());
            stmt.setString(8, r.getComentarios());
            stmt.setInt(9, r.getIdCliente());
            // System.out.println("Insertando con id_tipo_reparacion: " + r.getIdTipoReparacion());

            
            int filasAfectadas = stmt.executeUpdate();
            
            if(filasAfectadas > 0){
                return true;
            } 
            
        } catch(SQLException e){
            System.out.println("Error al insertar reparacion: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return false;
    }
    
    public static int getMarcaId(String marca){
        
        int idMarca = -1;
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(GET_ID_MARCA);
            stmt.setString(1, marca);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                idMarca = rs.getInt("id");
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener el id de la marca: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return idMarca;
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
