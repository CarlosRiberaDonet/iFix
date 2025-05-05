/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DispositivoDao;
import entity.Modelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class ModeloController {
    
    
    public static List<Modelo> getModelosList(){
        
        List<Modelo> modelosList = DispositivoDao.getModelos();
        
        return modelosList;
    }
    
    public List<Modelo> filterModelosByMarca(int idMarca){
        
        List<Modelo> modelosList = getModelosList();       
        List<Modelo> modelosFiltrados = new ArrayList<>();
        
        for(Modelo m : modelosList){
            if(m.getId_marca() == idMarca){
                modelosFiltrados.add(m);
            }
        }
        return modelosFiltrados;
    }
    
}
