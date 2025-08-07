/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cliente;
import entity.Dispositivo;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import utils.QuerySQL;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionDao {
    
    
    // Obtener toda la lista de reparaciones
    public static List<Reparacion> getAllReparacionesList(){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELECT_ALL_REPARACIONES)){
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                
                // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
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
                mo.setNombreModelo(rs.getString("modelo"));
                mo.setIdMarca(m.getIdMarca());
                
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("idDispositivo"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idTipoReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("idReparacion"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql.toLocalDate());
                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql.toLocalDate());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setDispositivo(d.getId());
                reparacionesList.add(r);
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener la lista de reparaciones de la BD getAllReparacionesList.");
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        
        return reparacionesList;
    }
    
    // Obtener reparación mediante el idReparacion
    public static Reparacion getReparacionById(int id){
        
        Reparacion reparacion = null;
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELECT_REPARACION_BY_ID)){
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
                
                // Creo el objeto Dispositivo con los datos obtenidos
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("id"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql.toLocalDate());
                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql.toLocalDate());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setIdDispositivo(d.getId());
                
                reparacion = r;   
            }    
        } catch(SQLException e){
            System.out.println("Error al buscar la reparacion getReparacionById: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return reparacion;
    }

    // Obtener reparaciones mediante idCliente
    public static List<Reparacion> getReparacionesByClienteId(int idCliente){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELEC_REPARACION_BY_CLIENTE_ID)){
            stmt.setInt(1, idCliente);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
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
                
                // Creo el objeto Dispositivo con los datos obtenidos
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("idDispositivo"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idTipoReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("idReparacion"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql != null ? fechaEntradaSql.toLocalDate() : null);

                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql != null ? fechaSalidaSql.toLocalDate() : null);
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setIdDispositivo(d.getId());
                
                reparacionesList.add(r);
            }
            
        } catch(SQLException e){
            System.out.println("Error al obtener las reparaciones getReparacionesByClienteId: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
    
    // Obtener reparaciones mediante teléfono
    public static List<Reparacion> getReparacionesbyTelefono(String telefono){
        
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELECT_REPARACIONES)){
            stmt.setString(1, telefono);
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
                
                // Creo el objeto Dispositivo con los datos obtenidos
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("id"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql.toLocalDate());
                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql.toLocalDate());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setIdDispositivo(d.getId());
                
                reparacionesList.add(r);
            }
        } catch(SQLException e){
            System.out.println("Error al obtener las reparaciones getReparacionesbyTelefono: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
    
    // Obtener reparaciones mediante rango de fechas
    public static List<Reparacion> getReparacionByDate(LocalDate fechaEntrada, LocalDate fechaSalida){
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELECT_REPARACION_BY_DATE)){
            stmt.setDate(1, java.sql.Date.valueOf(fechaEntrada));
            stmt.setDate(2, java.sql.Date.valueOf(fechaSalida));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                // Creo el objeto Cliente con los datos obtenidos
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
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
                            
                // Creo el objeto Dispositivo con los datos obtenidos
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("idReparacion"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql.toLocalDate());
                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql.toLocalDate());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setIdDispositivo(d.getId());
                
                reparacionesList.add(r);
            }
        } catch(SQLException e){
            System.out.println("Error al obtener las reparaciones getReparacionByDate: " + e.getMessage());
        }finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }
    
    // Obtener reparaciones mediante combinación de teléfono y rango de fechas
    public static List<Reparacion> getReparacionByPhoneAndDate(String telefono, LocalDate fechaEntrada, LocalDate fechaSalida){
        List<Reparacion> reparacionesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.SELECT_REPARACION_BY_PHONE_DATE)){
            stmt.setString(1, telefono);
            stmt.setDate(2, java.sql.Date.valueOf(fechaSalida));
            stmt.setDate(3, java.sql.Date.valueOf(fechaSalida));
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("idCliente"));
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
                
                // Creo el objeto Dispositivo con los datos obtenidos
                Dispositivo d = new Dispositivo();
                d.setId(rs.getInt("id"));
                d.setImei(rs.getString("imei"));
                
                // Creo el objeto TipoReparacion con los datos obtenidos  
                TipoReparacion t = new TipoReparacion();
                t.setId(rs.getInt("idReparacion"));
                t.setTipoReparacion(rs.getString("reparacion"));
                
                // Creo el objeto Reparacion con los datos obtenidos  
                Reparacion r = new Reparacion();
                r.setId(rs.getInt("idReparacion"));
                java.sql.Date fechaEntradaSql = rs.getDate("fecha_entrada");
                r.setFechaEntrada(fechaEntradaSql.toLocalDate());
                java.sql.Date fechaSalidaSql = rs.getDate("fecha_salida");
                r.setFechaSalida(fechaSalidaSql.toLocalDate());
                r.setPrecioReparacion(rs.getBigDecimal("precio"));
                r.setGarantia(rs.getBoolean("garantia"));
                r.setComentarios(rs.getString("comentarios"));
                r.setEstado(rs.getString("estado"));
                r.setIdDispositivo(d.getId());
                
                reparacionesList.add(r);
            }
        } catch(SQLException e){
             System.out.println("Error al obtener las reparaciones getReparacionByPhoneAndDate: " + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return reparacionesList;
    }

    // Crear nueva reparación
    public static boolean insertarReparacion(Reparacion r){
        
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(QuerySQL.INSERT_REPARACION);
            LocalDate hoy = LocalDate.now();
            stmt.setDate(1, java.sql.Date.valueOf(hoy));
            stmt.setDate(2, java.sql.Date.valueOf(hoy));
            stmt.setBigDecimal(3, r.getPrecioReparacion());
            stmt.setBoolean(4, r.isGarantia());
            stmt.setString(5, r.getComentarios());
            stmt.setInt(6, r.getIdDispositivo());
            stmt.setString(7, r.getEstado());
            
            int filasAfectadas = stmt.executeUpdate();
            
            if(filasAfectadas > 0){
                return true;
            } 
            
        } catch(SQLException e){
            System.out.println("Error al insertar reparacion insertarReparacion: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return false;
    }
    
    // Actualizar reparación
    public static boolean updateReparacion(Reparacion r){
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.MODIFICAR_REPARACION)){
            stmt.setDate(1, java.sql.Date.valueOf(r.getFechaEntrada()));
            stmt.setDate(2, java.sql.Date.valueOf(r.getFechaSalida()));
            stmt.setBigDecimal(3, r.getPrecioReparacion());
            stmt.setBoolean(4, r.isGarantia());
            stmt.setString(5, r.getComentarios());
            stmt.setString(6, r.getEstado());
            stmt.setInt(7, r.getIdDispositivo());
            stmt.setInt(8, r.getId());
            
            int filasAfectadas = stmt.executeUpdate();
            if(filasAfectadas > 0){
                System.out.println("Reparación actualizada correctamente.");
            }
            
        } catch(SQLException e){
            System.out.println("Error al modificar la reparacion updateReparacion" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return true;
    }
    
    // Actualizar el estado garantía de las reparaciones
    public static void updateGarantia(List<Reparacion> reparacionesModificadas){
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.UPDATE_GARANTIA_REPARACION)){
            for(Reparacion r : reparacionesModificadas){
                stmt.setBoolean(1, r.isGarantia());
                stmt.setInt(2, r.getId());
                int filasAfectadas = stmt.executeUpdate();
                if(filasAfectadas > 0){
                    System.out.println("Reparación actualizada correctamente.");
                }
            }
        } catch(SQLException e){
            System.out.println("Error al actulizar el estado garantia de la reparacion updateGarantia: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
    }
    // Eliminar reparación
    public static boolean deleteReparacion(int idReparacion){
        
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(QuerySQL.DELETE_REPARACION)){
            stmt.setInt(1, idReparacion);
            return stmt.executeUpdate() > 0;
            
        } catch(SQLException e){
            System.out.println("Error al eliminar la reparacion con id deleteReparacion: " + idReparacion + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        } 
        return false;
    }
}
