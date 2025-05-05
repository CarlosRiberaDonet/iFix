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
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import ui.components.reparaciones.ReparacionesTable;
import ui.components.reparaciones.CrearReparacion;


/**
 *
 * @author Carlos Ribera
 */
public class ClientePanel extends JPanel{
    
    private JFrame mainFrame;
    private Cliente cliente;
    public void mostrarReparacionesCliente(Cliente clienteSelect){

        cliente = clienteSelect;
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
        btnAñadir.addActionListener( e -> abrirCrearReparacion());
        dialog.setVisible(true);
    }
    
    private void abrirCrearReparacion() {
        JDialog crearDialog = new JDialog(mainFrame, "Crear Reparación", true);
        crearDialog.setSize(700, 720);
        crearDialog.setLocationRelativeTo(mainFrame);
        crearDialog.setLayout(new BorderLayout());

        ui.components.reparaciones.CrearReparacion crearReparacionPanel = new CrearReparacion(cliente);
        crearDialog.add(crearReparacionPanel, BorderLayout.CENTER);

        crearDialog.setVisible(true);
    }
}
