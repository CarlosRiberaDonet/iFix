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
    
    public static List<Cliente> findCliente(String nombre, String apellidos, String telefono){
        return ClienteDao.selectCliente(nombre, apellidos, telefono);
    }

    public static boolean eliminarCliente(String telefono){
        return ClienteDao.deleteCliente(telefono);
    }
}


