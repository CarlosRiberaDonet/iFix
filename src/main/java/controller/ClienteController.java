/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ClienteDao;
import entity.Cliente;
import entity.Reparacion;
import java.util.List;
/**
 *
 * @author Carlos Ribera
 */
public class ClienteController {
    
    public static boolean nuevoCliente(Cliente nuevoCliente){
        return ClienteDao.addCliente(nuevoCliente);
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
    
//    // Llenar List de reparaciones
//    public static List<Reparacion> cargarReparacionesList(int idCliente){      
//        List<Reparacion> reparacionesList = ReparacionController.findReparacionesByIdCliente(idCliente);
//        return reparacionesList;
//    }
}


