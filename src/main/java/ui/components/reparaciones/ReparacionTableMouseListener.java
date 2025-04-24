/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.reparaciones;

import controller.ReparacionController;
import entity.Reparacion;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionTableMouseListener extends MouseAdapter {

    private JTable reparacionestable;

    public ReparacionTableMouseListener(JTable reparacionestable) {
        this.reparacionestable = reparacionestable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2 && reparacionestable.getSelectedRow() != -1) {
            int fila = reparacionestable.getSelectedRow();
            int id = (int) reparacionestable.getValueAt(fila, 0);
            Reparacion reparacion = ReparacionController.getReparacionById(id);

            if (reparacion == null) {
                JOptionPane.showMessageDialog(reparacionestable, "No se pudo cargar la reparación", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Crear y mostrar panel de detalles
            ReparacionDetails detallePanel = new ReparacionDetails();
            detallePanel.cargarReparaciones(reparacion);

            JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(reparacionestable), "Detalle de Reparación", true);
            dialog.setSize(800, 600);
            dialog.setLocationRelativeTo(reparacionestable);
            dialog.setLayout(new BorderLayout());
            dialog.add(detallePanel, BorderLayout.CENTER);
            dialog.setVisible(true);
        }
    }
}