/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Carlos Ribera
 */
public class ClienteDao {
    
    private static final String ADD_CLIENTE = "INSERT INTO cliente(nombre, apellidos, telefono, direccion) VALUES (?, ?, ?, ?);";
    private static final String FIND_CLIENTE = "SELECT * FROM cliente WHERE " +
                                                "LOWER(CONCAT(nombre, ' ', apellidos)) LIKE ? " +
                                                "OR telefono = ? ";
    private static final String DELETE_CLIENTE = "DELETE FROM cliente WHERE telefono = ?";
    
    public static boolean addCliente(Cliente cliente){
        
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(ADD_CLIENTE)){
            stmt.setString(1, cliente.getNombre());
            stmt.setString (2, cliente.getApellidos());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al añadir cliente a la BD");
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return true;
    }
   
    public static List<Cliente> getClientesList(String input){
        List<Cliente> clienteList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(FIND_CLIENTE)){
            String nombre = "%" + input.trim().toLowerCase() + "%";
            String telefono = input.trim();
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clienteList.add(cliente);
            }
        } catch(SQLException e){
            System.out.println("Error al buscar el cliente" + e.getMessage());
        } finally{
            ConexionBD.close(conn);
        }
        return clienteList;
    }
    
    public static boolean deleteCliente(String telefono){
        
        Connection conn = ConexionBD.connect();
        
        try{
            PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENTE);
            stmt.setString(1, telefono);
            int resultado = stmt.executeUpdate();
            if(resultado > 0){
            return true; 
            }
        } catch(SQLException e){
            System.out.println("Error al eliminar el cliente: " + e.getMessage());
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return false;
    }
}
