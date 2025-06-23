/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDao;
import entity.Cliente;
import java.util.List;
/**
 *
 * @author Carlos Ribera
 */
public class ClienteController {
    
    public static boolean nuevoCliente(Cliente nuevoCliente, String telefono){
        List<Cliente> clientesList = ClienteDao.selectAllClientes();
        for(Cliente c : clientesList){
            if(c.getTelefono().equals(telefono)){
                System.out.println("el telefono ya existe");
                return false;
            }
        }
        return ClienteDao.addCliente(nuevoCliente);
    }
    
    public static List<Cliente> getAllClientes(){
        return ClienteDao.selectAllClientes();
    }
    
    public static List<Cliente> findCliente(String nombre, String apellidos, String telefono){
        return ClienteDao.selectCliente(nombre, apellidos, telefono);
    }

    public static boolean eliminarCliente(int idCliente){
        return ClienteDao.deleteCliente(idCliente);
    }
}


