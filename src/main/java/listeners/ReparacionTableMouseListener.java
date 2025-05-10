/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;

import ui.components.reparaciones.ReparacionDetallesDialog;
import entity.Reparacion;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionTableMouseListener extends MouseAdapter {

    private JTable reparacionesTable;
    private List<Reparacion> reparacionesList;

    public ReparacionTableMouseListener(JTable reparacionesTable, List<Reparacion> reparacionesList) {
        this.reparacionesTable = reparacionesTable;
        this.reparacionesList = reparacionesList;
    }

    public void setReparacionesList(List<Reparacion> reparacionesList) {
        this.reparacionesList = reparacionesList;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.getClickCount() == 2 && reparacionesTable.getSelectedRow() != -1) {
            int fila = reparacionesTable.getSelectedRow();

            if (fila < 0 || fila >= reparacionesList.size()) return;

            Reparacion reparacion = reparacionesList.get(fila);
            System.out.println("reparacion seleccionada: " + reparacion.getId());
            if (reparacion == null) {
                JOptionPane.showMessageDialog(reparacionesTable, "No se pudo cargar la reparación", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Obtener el Frame principal
            Frame parent = (Frame) SwingUtilities.getWindowAncestor(reparacionesTable);
            ReparacionDetallesDialog dialog = new ReparacionDetallesDialog(parent, reparacion, true);
            dialog.setLocationRelativeTo(parent);
            dialog.setVisible(true);
       
        }
    }
}
