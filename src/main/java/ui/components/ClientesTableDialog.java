/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components;

import entity.Cliente;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 *
 * @author Carlos Ribera
 */
public class ClientesTableDialog extends JDialog {

    private JTable tablaClientes;
    private DefaultTableModel modelo;

    public ClientesTableDialog(Frame parent) {
        super(parent, "Resultados de búsqueda", true);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Crear la tabla con columnas
        String[] columnas = {"ID", "Nombre", "Apellidos", "Teléfono", "Dirección"};
        modelo = new DefaultTableModel(columnas, 0);
        tablaClientes = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaClientes);

        add(scroll, BorderLayout.CENTER);

        // Tamaño y cierre
        setSize(600, 300);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void cargarClientes(List<Cliente> lista) {
        modelo.setRowCount(0);
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getId(),
                c.getNombre(),
                c.getApellidos(),
                c.getTelefono(),
                c.getDireccion()
            });
        }
    }
}
