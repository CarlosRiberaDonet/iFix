/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.reparaciones;

import entity.Reparacion;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionTableMouseListener {
    
    private List<Reparacion> reparacionesList;
    private JTable reparacionestable;
    ReparacionPanel reparacionPanel = new ReparacionPanel(); 

    public ReparacionTableMouseListener(List<Reparacion> reparacionesList, JTable reparacionestable) {
        this.reparacionesList = reparacionesList;
        this.reparacionestable = reparacionestable;
    }
    
    /*@Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2 && reparacionestable.getSelectedRow() != -1) {  
            int fila = reparacionestable.getSelectedRow();
            Reparacion reparacionSeleccionada = reparacionesList.get(fila);
            reparacionPanel.mostrarReparacionesCliente(reparacionSeleccionada);    
        }
    }*/
}
