/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components;

import entity.Reparacion;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionesTableDialog extends JDialog{
    private JTable tablaReparaciones;
    private DefaultTableModel modelo;

    public ReparacionesTableDialog(Frame parent) {
        super(parent, "Resultados de búsqueda", true);
        initUI();
    }

    private void initUI() {
        setLayout(new BorderLayout());

        // Crear la tabla con columnas
        String[] columnas = {"ID", "Nombre", "Apellidos", "Teléfono", "Dirección"};
        modelo = new DefaultTableModel(columnas, 0);
        tablaReparaciones = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tablaReparaciones);

        add(scroll, BorderLayout.CENTER);

        // Tamaño y cierre
        setSize(600, 300);
        setLocationRelativeTo(getParent());
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public void cargarClientes(List<Reparacion> lista) {
        modelo.setRowCount(0);
        for (Reparacion r : lista) {
            modelo.addRow(new Object[]{
                r.getId(),
                r.getFechaEntrada(),
                r.getFechaSalida(),
                r.getIdtipoReparacion(),
                r.getPrecioReparacion(),
                r.getComentarios()
            });
        }
    }
    
    public void cargarCliente(Reparacion reparacion){
         modelo.setRowCount(0);
            modelo.addRow(new Object[]{
                reparacion.getId(),
                reparacion.getFechaEntrada(),
                reparacion.getFechaSalida(),
                reparacion.getIdtipoReparacion(),
                reparacion.getPrecioReparacion(),
                reparacion.getComentarios()
            });
    }
}

