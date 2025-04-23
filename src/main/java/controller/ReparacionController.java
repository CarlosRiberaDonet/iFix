/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReparacionDao;
import entity.Reparacion;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionController {
    
    public static void crearReparacion(Reparacion reparacion){
        
    }
    
    public static List<Reparacion> findReparacionesByIdCliente(int clienteId){
        
        System.out.println("ID CLIENTE: " + clienteId);
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesList(clienteId);
        return reparacionesList;
    }
}
