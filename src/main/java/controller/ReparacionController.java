/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.ReparacionDao;
import entity.Reparacion;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionController {
    
    public static void crearReparacion(Reparacion reparacion){
        
       ReparacionDao.insertarReparacion(reparacion);
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
    
    /*public static int getIdReparacion(String tipoReparacion){
        
        int idTipoReparacion = -1;
        
        switch(tipoReparacion){
            case "ALTAVOZ":{
                idTipoReparacion = 1;
                break;
            }
            case "BATERIA":{
                 idTipoReparacion = 2;
                break;
            }
            case "CAMARA":{
                idTipoReparacion = 3;
                break;
            }
            case "MICROFONO":{
                 idTipoReparacion = 4;
                break;
            }
            case "PANTALLA":{
                idTipoReparacion = 5;
                break;
            }
            case "PC":{
                 idTipoReparacion = 6;
                break;
            }
            case "SOFTWARE":{
                 idTipoReparacion = 7;
                break;
            }
            case "SOLDADURA":{
                 idTipoReparacion = 8;
                break;
            }
        }
        return idTipoReparacion;
    }*/
}
