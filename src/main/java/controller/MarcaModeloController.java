/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.MarcaModeloDao;
import entity.Marca;
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
    
    // Inserta la nueva marca en la BD y devuelve el objeto Marca con el ID generado
    public static Marca addMarca(String nuevaMarca){
        return MarcaModeloDao.insertMarcaByString(nuevaMarca); 
    }
    
    // Inserta el nuevo modelo en la BD y devuelve el objeto Modelo con el ID generado
    public static Modelo addModelo(String nuevoModeloStr, int idNuevaMarca){
        return MarcaModeloDao.insertModeloByString(nuevoModeloStr, idNuevaMarca);
    } 
}
