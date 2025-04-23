/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.clientes;

import controller.ReparacionController;
import entity.Cliente;
import entity.Reparacion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
            mostrarReparacionesCliente(clienteSeleccionado);
            
        }
    }
    
    private void mostrarReparacionesCliente(Cliente clienteSelect){
        
        List<Reparacion> reparacionesList = ReparacionController.findReparacionesByIdCliente(clienteSelect.getId());

        ReparacionesTable panel = new ReparacionesTable(false);
        panel.cargarReparaciones(reparacionesList);

        // Crear botones
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAñadir = new JButton("Añadir");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        menuPanel.add(btnAñadir);
        menuPanel.add(btnEliminar);
        menuPanel.add(btnBuscar);
        
        // Añadir espaciado interno
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        // Crear y mostrar dialog
        JDialog dialog = new JDialog(mainFrame, "Reparaciones de " + clienteSelect.getNombre(), true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setLayout(new BorderLayout());
        dialog.add(menuPanel, BorderLayout.NORTH);
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}

