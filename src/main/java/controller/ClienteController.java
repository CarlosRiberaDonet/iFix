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
    
    public static List<Cliente> findClientes(String input){
        
        return ClienteDao.getClientesList(input);
    }
}


