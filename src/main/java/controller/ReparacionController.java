/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReparacionDao;
import entity.Reparacion;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionController {
    
    public static void crearReparacion(Reparacion reparacion){
        
       
    }
    
    public static List<Reparacion> findReparacionesByIdCliente(int clienteId){
        
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesList(clienteId);
        return reparacionesList;
    }
    
    public static List<Reparacion> findReparacionesByTelefono(String telefono){
        
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesbyTelefono(telefono);
        return reparacionesList;
    }
    
    public static List<Reparacion> findReparaciones(String telefono, LocalDate fechaEntrada, LocalDate fechaSalida){
        return ReparacionDao.buscarReparaciones(telefono, fechaEntrada, fechaSalida);
    }
    
    public static Reparacion getReparacionById(int idReparacion){
        return ReparacionDao.getReparacionById(idReparacion);
    }
}
