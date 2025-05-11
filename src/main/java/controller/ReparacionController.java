/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DispositivoDao;
import dao.ReparacionDao;
import dao.TipoReparacionDao;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
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
    public JComboBox llenarComboBoxMarca(JComboBox marcaComboBox){
                
        List<Marca> marcasList = DispositivoDao.getMarcas();
        
        DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<>();
        for (Marca m : marcasList) {
            model.addElement(m);
        }
        marcaComboBox.setModel(model);
        return marcaComboBox;
    }
    
    // Cargar modelos disponibles en modeloComboBox
    public JComboBox llenarComboBoxModelo(int idMarca, JComboBox modeloComboBox){
        modeloComboBox.removeAllItems();
        
        ModeloController modeloController = new ModeloController(); 
        List<Modelo> modelosList = modeloController.filterModelosByMarca(idMarca);
        
        DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>();
        for(Modelo m : modelosList){
            model.addElement(m);
        }
        modeloComboBox.setModel(model);
        return modeloComboBox;  
    }
    
    // Cargar reparaciones disponibles en reparacionComboBox
    public JComboBox llenarComboBoxReparacion(JComboBox reparacionComboBox){
        
        TipoReparacionDao tr = new TipoReparacionDao();
        List<TipoReparacion> tipoReparacionList = tr.getTipoReparacionList();
         
        DefaultComboBoxModel<TipoReparacion> model = new DefaultComboBoxModel<>();
        for (TipoReparacion t : tipoReparacionList) {
           model.addElement(t);
        }
        
        reparacionComboBox.setModel(model);
        return reparacionComboBox;
    }
}
