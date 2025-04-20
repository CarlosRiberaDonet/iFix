/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import entity.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Carlos Ribera
 */
public class ClienteDao {
    
    private static final String ADD_CLIENTE = "INSERT INTO cliente(nombre, telefono, direccion) VALUES (?,?,?);";
    
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
}
