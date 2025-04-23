/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.clientes;

import controller.ReparacionController;
import entity.Cliente;
import entity.Reparacion;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import ui.components.reparaciones.ReparacionesTable;

/**
 *
 * @author Carlos Ribera
 */
public class ClienteTableMouseListener extends MouseAdapter {

    private List<Cliente> clientesList;
    private JTable clienteTable;
    private JFrame mainFrame;

    public ClienteTableMouseListener(List<Cliente> clientesList, JTable clienteTable, JFrame mainFrame) {
        this.clientesList = clientesList;
        this.clienteTable = clienteTable;
        this.mainFrame = mainFrame;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2 && clienteTable.getSelectedRow() != -1) {
            
            int fila = clienteTable.getSelectedRow();
            Cliente clienteSeleccionado = clientesList.get(fila);
            
            // Obtener reparaciones del cliente
            List<Reparacion> reparacionesList = ReparacionController.findReparacionesByIdCliente(clienteSeleccionado.getId());
            
            // Crear el panel y cargar las reparaciones
            ReparacionesTable panel = new ReparacionesTable(false);
            panel.cargarReparaciones(reparacionesList);
            
            // Mostrar el dialog
            JDialog dialog = new JDialog(mainFrame, "Reparaciones de " + clienteSeleccionado.getNombre(), true);
            dialog.setSize(800, 600);
            dialog.setLocationRelativeTo(mainFrame);
            dialog.add(panel);
            dialog.setVisible(true);
        }
    }
}

