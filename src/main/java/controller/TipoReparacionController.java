/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TipoReparacionDao;
import entity.TipoReparacion;

/**
 *
 * @author Carlos Ribera
 */
public class TipoReparacionController {
    
    
     // Inserta una nuevo tipo de reparación en la BD y devuelve el objeto TipoReparacion con el ID generado
    public static TipoReparacion addTipoReparacion(String nuevotipoReparacionStr){  
       return TipoReparacionDao.insertTipoReparacion(nuevotipoReparacionStr);
    }
}
