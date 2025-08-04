/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DispositivoDao;

/**
 *
 * @author Carlos
 */
public class DispositivoController {
    
    
    public static MarcaModeloController addDispositivo(int idMarca, int idModelo, MarcaModeloController Dispositivo){
        return null;
    }
    
    public static boolean checkImei(int imei){
        return DispositivoDao.getImei(imei);
    }
}
