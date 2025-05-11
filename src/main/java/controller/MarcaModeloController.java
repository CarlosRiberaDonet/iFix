/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MarcaModeloDao;
import entity.Modelo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos
 */
public class MarcaModeloController {
    
    
    public static List<Modelo> getModelosList(){
        
        List<Modelo> modelosList = MarcaModeloDao.getModelos();
        
        return modelosList;
    }
    
    public List<Modelo> filterModelosByMarca(int idMarca){
        
        List<Modelo> modelosList = getModelosList();       
        List<Modelo> modelosFiltrados = new ArrayList<>();
        
        for(Modelo m : modelosList){
            if(m.getIdMarca() == idMarca){
                modelosFiltrados.add(m);
            }
        }
        return modelosFiltrados;
    }
    
    // Inserta la nueva marca en la BD y obtengo el id 
    public static int addMarca(String nuevaMarca){
        return MarcaModeloDao.insertMarcaByString(nuevaMarca);
    }   
    
    // Inserta el nuevo modelo en la BD y obtengo el id 
    public static int addModelo(String nuevoModelo, int idMarca){
        return MarcaModeloDao.insertModeloByString(nuevoModelo, idMarca);
    } 
}
