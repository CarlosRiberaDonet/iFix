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
    // NO ENCUENTRA EL CLIENTE INTRODUCIENDO EL NÚMERO DE TELEFONO
    //
    //
    //
    public static List<Cliente> getClientesList(String input){
        List<Cliente> clienteList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(FIND_CLIENTE)){
            String busqueda = "%" + input.trim().toLowerCase() + "%";
            stmt.setString(1, busqueda);
            stmt.setString(2, busqueda);
            
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
}
