/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDao;
import entity.Cliente;
import java.util.List;
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class ClienteController {
    
    
    public static boolean nuevoCliente(String nombre, String apellidos, String telefono, String direccion){
        
        if(Utils.checkNombre(nombre) && Utils.checkNombre(apellidos) && Utils.checkTelefono(telefono)){
            Cliente nuevoCliente = new Cliente(nombre, apellidos, telefono, direccion);
            return ClienteDao.addCliente(nuevoCliente);
        } else{
            return false;
        } 
    }
    
    public static List<Cliente> getAllClientes(){
        return ClienteDao.selectAllClientes();
    }
    
    public static List<Cliente> getClienteByNombre(){
        return ClienteDao.selectClienteByName();
    }
    
    public static List<Cliente> findClientes(String nombre, String apellidos, String telefono){
        
        nombre = Utils.checkTextField(nombre);
        apellidos = Utils.checkTextField(apellidos);
        telefono = Utils.checkTextField(telefono);
        return ClienteDao.findCliente(nombre, apellidos, telefono);
    }
    
    public static boolean eliminarCliente(String telefono){
        return ClienteDao.deleteCliente(telefono);
    }
}


