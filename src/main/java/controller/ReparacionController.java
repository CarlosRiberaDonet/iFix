/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DispositivoDao;
import dao.ReparacionDao;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionController {
    
    public static List<Reparacion> getAllReparaciones(){
        return ReparacionDao.getAllReparacionesList();
    }
    
    public static boolean crearReparacion(Reparacion reparacion){
        
       return ReparacionDao.insertarReparacion(reparacion);
    }
    
    public static List<Reparacion> findReparacionesByIdCliente(int clienteId){
        
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesByClienteId(clienteId);
        return reparacionesList;
    }
    
    public static List<Reparacion> findReparacionesByTelefono(String telefono){
        
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesbyTelefono(telefono);
        return reparacionesList;
    }
    
    /*public static List<Reparacion> findReparaciones(String telefono, LocalDate fechaEntrada, LocalDate fechaSalida){
        return ReparacionDao.buscarReparaciones(telefono, fechaEntrada, fechaSalida);
    }*/
    
    public static Reparacion getReparacionById(int idReparacion){
        return ReparacionDao.getReparacionById(idReparacion);
    }
    
    public static Reparacion saveReparacion(Reparacion r){
        
        return null;
    }
    
    public static boolean modificarReparacion(Reparacion r){
        return ReparacionDao.updateReparacion(r);
    }
    
     // Cargar marcas disponibles en marcaComboBox
    public void llenarComboBoxMarca(JComboBox marcaComboBox){
                
        List<Marca> marcasList = DispositivoDao.getMarcas();
        for (Marca m : marcasList) {
            marcaComboBox.addItem(m.getMarca().toUpperCase()); 
        }
    }
    
     // Cargar modelos disponibles en modeloComboBox
    public void llenarComboBoxModelo(int idMarca, JComboBox modeloComboBox){
        
        ModeloController modeloController = new ModeloController(); 
        List<Modelo> modelosList = modeloController.filterModelosByMarca(idMarca);
        for(Modelo m : modelosList){
            modeloComboBox.addItem(m.getModelo().toUpperCase());
        }
    }
}
