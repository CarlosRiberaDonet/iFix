/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DispositivoDao;
import entity.Dispositivo;

/**
 *
 * @author Carlos
 */
public class DispositivoController {
    
    
    public static boolean addDispositivo(Dispositivo nuevoDispositivo){
        return DispositivoDao.createDispositivo(nuevoDispositivo);
    }
    
    public static boolean checkImei(int imei){
        return DispositivoDao.getImei(imei);
    }
}
