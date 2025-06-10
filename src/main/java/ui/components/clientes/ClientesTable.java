/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.clientes;

import entity.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Carlos Ribera
 */
public class ClientesTable extends JPanel {

    private JTable tablaClientes;
    private DefaultTableModel modelo;

    public ClientesTable() {
        setLayout(new BorderLayout());

        String[] columnas = {"ID", "APELLIDOS", "NOMBRE", "TELÉFONO", "DIRECCIÓN"};
        modelo = new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaClientes = new JTable(modelo);
        tablaClientes.getTableHeader().setReorderingAllowed(false);
        tablaClientes.setAutoCreateRowSorter(false);

        JScrollPane scroll = new JScrollPane(tablaClientes);
        add(scroll, BorderLayout.CENTER);
        
        // Ocultar la columna ID
        tablaClientes.getColumnModel().getColumn(0).setMinWidth(0);
        tablaClientes.getColumnModel().getColumn(0).setMaxWidth(0);
        tablaClientes.getColumnModel().getColumn(0).setWidth(0);
    }

    public void cargarClientes(List<Cliente> lista) {
        modelo.setRowCount(0);
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getApellidos().toUpperCase(),
                c.getNombre().toUpperCase(),
                c.getTelefono().toUpperCase(),
                c.getDireccion().toUpperCase()
            });
        }
    }

    public JTable getTablaClientes() {
        return tablaClientes;
    }
}

