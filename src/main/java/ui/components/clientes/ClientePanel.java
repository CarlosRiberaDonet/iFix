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
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import ui.components.reparaciones.ReparacionesTable;
import ui.components.reparaciones.CrearReparacion;
import ui.components.reparaciones.ReparacionDetails;


/**
 *
 * @author Carlos Ribera
 */
public class ClientePanel extends JPanel {

    private JFrame mainFrame;
    private Cliente cliente;
    private List<Reparacion> reparacionesList = new ArrayList<>();

    public void mostrarReparacionesCliente(Cliente clienteSelect) {
        this.cliente = clienteSelect;
        this.reparacionesList = ReparacionController.findReparacionesByIdCliente(clienteSelect.getId());

        ReparacionesTable tablePanel = new ReparacionesTable(reparacionesList);
        JTable tablaReparaciones = tablePanel.getTablaReparaciones();

        // MouseListener configurado para abrir detalles desde esta lista
        tablaReparaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2 && tablaReparaciones.getSelectedRow() != -1) {
                    int fila = tablaReparaciones.getSelectedRow();
                    Reparacion reparacion = reparacionesList.get(fila);

                    // Cargar detalle con instancia actualizada desde DB
                    Reparacion recargada = ReparacionController.getReparacionById(reparacion.getId());
                    if (recargada == null) return;

                    ReparacionDetails detalle = new ReparacionDetails(recargada);
                    detalle.setVisible(true);
                }
            }
        });

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnAñadir = new JButton("Añadir");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnBuscar = new JButton("Buscar");
        menuPanel.add(btnAñadir);
        menuPanel.add(btnEliminar);
        menuPanel.add(btnBuscar);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        JDialog dialog = new JDialog(mainFrame, "Reparaciones de " + clienteSelect.getNombre(), true);
        dialog.setSize(800, 600);
        dialog.setLocationRelativeTo(mainFrame);
        dialog.setLayout(new BorderLayout());
        dialog.add(menuPanel, BorderLayout.NORTH);
        dialog.add(tablePanel, BorderLayout.CENTER);
        btnAñadir.addActionListener(e -> abrirCrearReparacion());
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
