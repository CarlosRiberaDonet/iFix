/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cliente;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
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
    
    private static final String SELECT_ALL_REPARACIONES = "SELECT r.id, " 
            + "r.fecha_entrada, "
            + "r.fecha_salida, "
            + "r.precio, "
            + "r.garantia, "
            + "r.comentarios, "
            + "r.id_marca AS idMarca, "
            + "r.id_modelo AS idModelo, "
            + "r.id_tipo_reparacion AS idReparacion, "
            + "r.id_cliente, "
            + "m.marca, "
            + "mo.modelo, "
            + "t.reparacion, "
            + "c.nombre, "
            + "c.apellidos, "
            + "c.telefono, "
            + "c.direccion "
            + "FROM reparacion r "
            + "JOIN marca m ON r.id_marca = m.id "
            + "JOIN modelo mo ON r.id_modelo = mo.id "
            + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
            + "JOIN cliente c ON r.id_cliente = c.id ";
    
    private static final String SELECT_REPARACIONES_BY_CLIENTE_ID = "SELECT r.id, " 
            + "r.fecha_entrada, "
            + "r.fecha_salida, "
            + "r.precio, "
            + "r.garantia, "
            + "r.comentarios, "
            + "r.id_marca AS idMarca, "
            + "r.id_modelo AS idModelo, "
            + "r.id_tipo_reparacion AS idReparacion, "
            + "r.id_cliente, "
            + "m.marca, "
            + "mo.modelo, "
            + "t.reparacion, "
            + "c.nombre, "
            + "c.apellidos, "
            + "c.telefono, "
            + "c.direccion "
            + "FROM reparacion r "
            + "JOIN marca m ON r.id_marca = m.id "
            + "JOIN modelo mo ON r.id_modelo = mo.id "
            + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
            + "JOIN cliente c ON r.id_cliente = c.id "
            + "WHERE r.id_cliente = ?";
    
    private static final String SELECT_REPARACIONES_BY_TELEFONO = "SELECT r.fecha_entrada, "
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
    
    private static final String SELECT_REPARACION_BY_ID = "SELECT r.id, " 
            + "r.fecha_entrada, "
            + "r.fecha_salida, "
            + "r.precio, "
            + "r.garantia, "
            + "r.comentarios, "
            + "r.id_marca AS idMarca, "
            + "r.id_modelo AS idModelo, "
            + "r.id_tipo_reparacion AS idReparacion, "
            + "r.id_cliente, "
            + "m.marca, "
            + "mo.modelo, "
            + "t.reparacion, "
            + "c.nombre, "
            + "c.apellidos, "
            + "c.telefono, "
            + "c.direccion "
            + "FROM reparacion r "
            + "JOIN marca m ON r.id_marca = m.id "
            + "JOIN modelo mo ON r.id_modelo = mo.id "
            + "JOIN tipo_reparacion t ON r.id_tipo_reparacion = t.id "
            + "JOIN cliente c ON r.id_cliente = c.id "
            + "WHERE r.id = ?";
    
    private static final String MODIFICAR_REPARACION = "UPDATE reparacion SET "
            + "fecha_entrada = ?, "
            + "fecha_salida = ?, "
            + "precio = ?, "
            + "garantia = ?, "
            + "comentarios = ?, "
            + "id_marca = ?, "
            + "id_modelo = ?, "
            + "id_tipo_reparacion = ? "
            + "WHERE id = ?";
    
    
    private static final String INSERT_REPARACION = " INSERT INTO reparacion (fecha_entrada, fecha_salida, id_marca, id_modelo, id_tipo_reparacion, "
            + "precio, garantia, comentarios, id_cliente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    
    private static final String DELETE_REPARACION = "DELETE FROM reparacion WHERE id = ?";
    
    // Obtener toda la lista de reparaciones
    public static List<Reparacion> getAllReparacionesList(){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_REPARACIONES)){
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                
                // Creo el objeto Marca con los datos obtenidos
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("idMarca"));
                m.setMarca(rs.getString("marca"));
                
                // Creo el objeto Modelo con los datos obtenidos
                Modelo mo = new Modelo();
                mo.setIdModelo(rs.getInt("idModelo"));
                mo.setModelo(rs.getString("modelo"));
                mo.setIdMarca(m.getIdMarca());
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("id"));
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setIdMarca(m.getIdMarca());
                r.setIdModelo(mo.getIdModelo());
                r.setIdTipoReparacion(t.getId());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setIdCliente(c.getId());
                r.setCliente(c);
                r.setMarca(m);
                r.setModelo(mo);
                r.setTipoReparacion(t);
                
                reparacionesList.add(r);
                System.out.println("Reparacion: " + r.toString());
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener la lista de reparaciones de la BD.");
        } finally{
            ConexionBD.close(conn);
        }
        
        return reparacionesList;
    }
    
    // Obtener reparación mediante el idReparacion
    public static Reparacion getReparacionById(int id){
        
        Reparacion reparacion = null;
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_REPARACION_BY_ID)){
            stmt.setInt(1, id);
            
            // MENSAJE DE DEPURACIÓN
            System.out.println("ID reparacion: " + id);
            
            ResultSet rs = stmt.executeQuery();
                      
            if(rs.next()){
                // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos("apellidos");
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                
                // Creo el objeto Marca con los datos obtenidos
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("idMarca"));
                m.setMarca(rs.getString("marca"));
                
                // Creo el objeto Modelo con los datos obtenidos
                Modelo mo = new Modelo();
                mo.setIdModelo(rs.getInt("idModelo"));
                mo.setModelo(rs.getString("modelo"));
                mo.setIdMarca(m.getIdMarca());
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("id"));
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setIdMarca(m.getIdMarca());
                r.setIdModelo(mo.getIdModelo());
                r.setIdTipoReparacion(t.getId());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setIdCliente(c.getId());
                r.setCliente(c);
                r.setMarca(m);
                r.setModelo(mo);
                r.setTipoReparacion(t);
                
                reparacion = r;   
            }    
        } catch(SQLException e){
            System.out.println("Error al buscar la reparacion: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return reparacion;
    }

    // Obtener reparación mediante idCliente
    public static List<Reparacion> getReparacionesByClienteId(int idCliente){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_REPARACIONES_BY_CLIENTE_ID)){
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                  // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                
                // Creo el objeto Marca con los datos obtenidos
                Marca m = new Marca();
                m.setIdMarca(rs.getInt("idMarca"));
                m.setMarca(rs.getString("marca"));
                
                // Creo el objeto Modelo con los datos obtenidos
                Modelo mo = new Modelo();
                mo.setIdModelo(rs.getInt("idModelo"));
                mo.setModelo(rs.getString("modelo"));
                mo.setIdMarca(m.getIdMarca());
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("id"));
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setIdMarca(m.getIdMarca());
                r.setIdModelo(mo.getIdModelo());
                r.setIdTipoReparacion(t.getId());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setIdCliente(c.getId());
                r.setCliente(c);
                r.setMarca(m);
                r.setModelo(mo);
                r.setTipoReparacion(t);
                
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
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_REPARACIONES_BY_TELEFONO)){
            stmt.setString(1, telefono);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Reparacion r = new Reparacion();
                r.setFechaEntrada(rs.getDate("fecha_entrada"));
                r.setFechaSalida(rs.getDate("fecha_salida"));
                r.setIdCliente(rs.getInt("idcliente"));
                r.setIdModelo(rs.getInt("idModelo"));
                r.setIdTipoReparacion(rs.getInt("id_tipo_reparacion"));
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
    
    public static boolean updateReparacion(Reparacion r){
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(MODIFICAR_REPARACION)){
            
            stmt.setDate(1, r.getFechaEntrada());
            stmt.setDate(2, r.getFechaSalida());
            stmt.setBigDecimal(3, r.getPrecioReparacion());
            stmt.setBoolean(4, r.isGarantia());
            stmt.setString(5, r.getComentarios());
            stmt.setInt(6, r.getIdMarca());
            stmt.setInt(7, r.getIdModelo());
            stmt.setInt(8, r.getIdTipoReparacion());
            stmt.setInt(9, r.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Reparación actualizada correctamente.");
            }
            
        } catch(SQLException e){
            System.out.println("Error al modificar la reparacion" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return true;
    }
    
    public static boolean deleteReparacion(int idReparacion){
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(DELETE_REPARACION)){
            stmt.setInt(1, idReparacion);
            return stmt.executeUpdate() > 0;
            
        } catch(SQLException e){
            System.out.println("Error al eliminar la reparacion con id: " + idReparacion + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        } 
        return false;
    }
}
