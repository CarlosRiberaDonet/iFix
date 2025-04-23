/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.reparaciones;

import entity.Reparacion;
import java.awt.BorderLayout;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionesTable extends JPanel{
    private JTable tablaReparaciones;
    private DefaultTableModel modelo;
    private boolean mostrarNombreCliente;

    // booleano que identifica si la tabla es llamada desde ReparacionFrame
    public ReparacionesTable(boolean tableReparaciones) {
        this.mostrarNombreCliente = tableReparaciones;
        setLayout(new BorderLayout());
        
        // Si la tabla se llama desde ReparacionFrame, muestro estas columnas
        if (tableReparaciones) {
            modelo = new DefaultTableModel(new String[]{"Entrada", "Salida", "Cliente", "Dispositivo", "Importe"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }else{
            modelo = new DefaultTableModel(new String[]{"Entrada", "Salida", "Dispositivo", "Importe"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        }
        
        tablaReparaciones = new JTable(modelo);
        tablaReparaciones.getTableHeader().setReorderingAllowed(false);
        tablaReparaciones.setAutoCreateRowSorter(false);

        JScrollPane scroll = new JScrollPane(tablaReparaciones);
        add(scroll, BorderLayout.CENTER);
    }

     public void cargarReparaciones(List<Reparacion> lista) {
        modelo.setRowCount(0);
        for (Reparacion r : lista) {
            if (mostrarNombreCliente) {
                modelo.addRow(new Object[]{
                    r.getFechaEntrada(),
                    r.getFechaSalida(),
                    r.getNombreCliente(),
                    r.getNombreDispositivo(),
                    r.getPrecioReparacion()
                });
            } else {
                modelo.addRow(new Object[]{
                    r.getFechaEntrada(),
                    r.getFechaSalida(),
                    r.getNombreDispositivo(),
                    r.getPrecioReparacion()
                });
            }
        }
    }
}

