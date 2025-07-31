/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MarcaModeloDao;
import dao.ReparacionDao;
import dao.TipoReparacionDao;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.time.LocalDate;
import java.util.ArrayList;
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
    
    public static boolean crearReparacion(Reparacion r){
        return ReparacionDao.insertarReparacion(r);
    }
    
    public static List<Reparacion> findReparacionesByIdCliente(int clienteId){ 
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesByClienteId(clienteId);
        return reparacionesList;
    }
    
    // Buscar reparación por número de teléfono
    public static List<Reparacion> findReparacionesByPhone(String telefono){
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionesbyTelefono(telefono);
        return reparacionesList;
    }
    
    public static List<Reparacion> findReparacionByDate(LocalDate fechaEntrada, LocalDate fechaSalida){
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionByDate(fechaEntrada, fechaSalida);
        return reparacionesList;
    }
    
    public static List<Reparacion> findReparacionByPhoneAndDate(String telefono, LocalDate fechaEntrada, LocalDate fechaSalida){
        List<Reparacion> reparacionesList = ReparacionDao.getReparacionByPhoneAndDate(telefono, fechaEntrada, fechaSalida);
        return reparacionesList;
    }
    
    public static Reparacion getReparacionById(int idReparacion){
        return ReparacionDao.getReparacionById(idReparacion);
    }
    
    public static boolean modificarReparacion(Reparacion r){
        return ReparacionDao.updateReparacion(r);
    }
    
    // Cargar marcas disponibles en marcaComboBox
    public void llenarComboBoxMarca(JComboBox marcaComboBox){
                
        List<Marca> marcasList = MarcaModeloDao.getMarcas();
        
        DefaultComboBoxModel<Marca> model = new DefaultComboBoxModel<>();
        for (Marca m : marcasList) {
            model.addElement(m);
            System.out.println("Marca: " + m.getMarca());
        }
        marcaComboBox.setModel(model);
    }
    
    // Cargar modelos disponibles en modeloComboBox
    public JComboBox llenarComboBoxModelo(int idMarca, JComboBox modeloComboBox){
        
        Dispositivo modeloController = new Dispositivo(); 
        List<Modelo> modelosList = modeloController.filterModelosByMarca(idMarca);
        
        DefaultComboBoxModel<Modelo> model = new DefaultComboBoxModel<>();
        for(Modelo m : modelosList){
            model.addElement(m);
        }
        modeloComboBox.setModel(model);
        return modeloComboBox;  
    }
    
    public void seleccionarModeloPorId(JComboBox<Modelo> modeloComboBox, int idModelo){
        
        for(int i = 0; i < modeloComboBox.getItemCount(); i++){
            Modelo m = modeloComboBox.getItemAt(i);
            if(m.getIdModelo() == idModelo){
                modeloComboBox.setSelectedIndex(i);
                break;
            }
        }
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
    
    public void seleccionarTipoReparacionPorId(JComboBox<TipoReparacion> tipoReparacionComboBox, int idTipo){
       
        for(int i = 0; i < tipoReparacionComboBox.getItemCount(); i++){
            TipoReparacion tr = tipoReparacionComboBox.getItemAt(i);
            if(tr.getId() == idTipo){
                tipoReparacionComboBox.setSelectedItem(tr);
            }
        }      
    }
    
    public static boolean eliminarReparacion(int idReparacion){
        return ReparacionDao.deleteReparacion(idReparacion);
    }
    
    public static void checkGarantia(){
        // Fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Lista original de la BD
        List<Reparacion> reparacionesList = ReparacionDao.getAllReparacionesList();
        // Lista para guardar las reparaciones con el campo garantía modificado
        List<Reparacion> reparacionesModificadas = new ArrayList<>();

        for(Reparacion r : reparacionesList){
            if(r.isGarantia() == true){
                if(fechaActual.isAfter(r.getFechaSalida().plusMonths(3))){
                    r.setGarantia(false);
                    reparacionesModificadas.add(r);
                }
            }
        } 
        ReparacionDao.updateGarantia(reparacionesModificadas);
    }
}
