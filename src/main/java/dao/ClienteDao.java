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
    
    private static final String ADD_CLIENTE = "INSERT INTO cliente(nombre, telefono, direccion) VALUES (?,?,?);";
    private static final String FIND_CLIENTE = "SELECT * FROM cliente WHERE nombre LIKE ? OR apellidos LIKE ? OR CONCAT(nombre, ' ', apellidos) LIKE ?";
    
    public static boolean addCliente(Cliente cliente){
        
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(ADD_CLIENTE)){
            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getTelefono());
            stmt.setString(3, cliente.getDireccion());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al añadir cliente a la BD");
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return true;
    }
    
    public static void findCliente(String input){
        List<Cliente> clienteList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(FIND_CLIENTE)){
            String busqueda = "%" + input.trim() + "%";
            stmt.setString(1, busqueda);
            stmt.setString(2, busqueda);
            stmt.setString(3, busqueda);
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
            }
        } catch(SQLException e){
            System.out.println("Error al buscar el cliente");
        } finally{
            ConexionBD.close(conn);
        }
    }
}
