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
    
    private static final String SELECT_ALL_CLIENTES = "SELECT * FROM cliente";
    private static final String SELECT_CLIENTE = "SELECT * FROM cliente WHERE nombre LIKE ? AND apellidos LIKE ? AND telefono LIKE ?";
    private static final String ADD_CLIENTE = "INSERT INTO cliente(nombre, apellidos, telefono, direccion) VALUES (?, ?, ?, ?);";
    private static final String DELETE_CLIENTE = "DELETE FROM cliente WHERE id = ?";
    
    public static List<Cliente> selectAllClientes(){
        
        List<Cliente> clientesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_ALL_CLIENTES)){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                
                clientesList.add(c);
            }
        } catch(SQLException e){
            System.out.println("Error al obtener la lista de clientes" + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return clientesList;
    }
    
    public static List<Cliente> selectCliente(String nombre, String apellidos, String telefono){
        
        List<Cliente> clientesList = new ArrayList<>();
        Connection conn = ConexionBD.connect();
        try(PreparedStatement stmt = conn.prepareStatement(SELECT_CLIENTE)){
            stmt.setString(1, "%" + nombre + "%");
            stmt.setString(2, "%" + apellidos + "%");
            stmt.setString(3, "%" + telefono + "%");
            
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Cliente c = new Cliente();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setApellidos(rs.getString("apellidos"));
                c.setTelefono(rs.getString("telefono"));
                c.setDireccion(rs.getString("direccion"));
                clientesList.add(c);
            }
        }catch(SQLException e){
            System.out.println("Error al buscar al cliente por nombre: " + e.getMessage());
            e.printStackTrace();
        } finally{
            ConexionBD.close(conn);
        }
        return clientesList;
    }
    
    public static boolean addCliente(Cliente cliente){
        
        Connection conn = ConexionBD.connect();
        
        try(PreparedStatement stmt = conn.prepareStatement(ADD_CLIENTE)){
            stmt.setString(1, cliente.getNombre());
            stmt.setString (2, cliente.getApellidos());
            stmt.setString(3, cliente.getTelefono());
            stmt.setString(4, cliente.getDireccion());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al añadir cliente a la BD" + e.getMessage());
            e.printStackTrace();
            return false;
        } finally{
            ConexionBD.close(conn);
        }
        return true;
    }
   
    public static List<Cliente> findCliente(String nombre, String apellidos, String telefono) {
        List<Cliente> clienteList = new ArrayList<>();
        Connection conn = ConexionBD.connect();

        try {
            // Construcción dinámica de la consulta
            String sql = "SELECT * FROM cliente WHERE 1=1";
            List<String> parametros = new ArrayList<>();

            // Si el usuario escribe algo en nombre
            if (!nombre.trim().isEmpty()) {
                sql += " AND LOWER(nombre) LIKE ?";
                parametros.add(nombre.trim().toLowerCase() + "%");
            }

            // Si el usuario escribe algo en apellidos
            if (!apellidos.trim().isEmpty()) {
                sql += " AND LOWER(apellidos) LIKE ?";
                parametros.add(apellidos.trim().toLowerCase() + "%");
            }

            // Si el usuario escribe algo en teléfono
            if (!telefono.trim().isEmpty()) {
                sql += " AND telefono LIKE ?";
                parametros.add(telefono.trim() + "%");
            }

            // Preparamos la consulta con los filtros aplicados
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Insertamos los valores en orden en el PreparedStatement
            for (int i = 0; i < parametros.size(); i++) {
                stmt.setString(i + 1, parametros.get(i));
            }

            // Ejecutamos la consulta
            ResultSet rs = stmt.executeQuery();

            // Recorremos el resultado y creamos objetos Cliente
            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("direccion")
                );
                clienteList.add(cliente);
            }

        } catch (SQLException e) {
            System.out.println("Error al buscar el cliente: " + e.getMessage());
        } finally {
            ConexionBD.close(conn);
        }

        return clienteList;
    }

    public static boolean deleteCliente(int idCliente){
        Connection conn = ConexionBD.connect();     
        try{
            PreparedStatement stmt = conn.prepareStatement(DELETE_CLIENTE);
            stmt.setInt(1, idCliente);
            System.out.println("eliminando cliente con id: " + idCliente);
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
