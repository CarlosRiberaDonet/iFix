/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.TipoReparacionDao;

/**
 *
 * @author Carlos Ribera
 */
public class TipoReparacionController {
    
    
    // Inserta nuevo tipo de reparación en la BD y obtengo el id 
    public static int addTipoReparacion(String nuevoTipoReparacion){  
       return TipoReparacionDao.insertTipoReparacion(nuevoTipoReparacion);
    }
}
