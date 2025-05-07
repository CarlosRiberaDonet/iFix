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

    // booleano que identifica si la tabla es llamada desde ReparacionFrame
    public ReparacionesTable() {
        setLayout(new BorderLayout());
        // Si la tabla se llama desde ReparacionFrame, muestro estas columnas
            modelo = new DefaultTableModel(new String[]{"ID", " Entrada", "Salida", "Cliente", "Dispositivo", "Importe"}, 0) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        
        
        tablaReparaciones = new JTable(modelo);
        tablaReparaciones.getTableHeader().setReorderingAllowed(false);
        tablaReparaciones.setAutoCreateRowSorter(false);

        JScrollPane scroll = new JScrollPane(tablaReparaciones);
        add(scroll, BorderLayout.CENTER);
    }

    public void cargarReparaciones(List<Reparacion> lista) {
        modelo.setRowCount(0);
        for (Reparacion r : lista) {
                modelo.addRow(new Object[]{
                    r.getId(),
                    r.getFechaEntrada(),
                    r.getFechaSalida(),
                    r.getCliente().getNombre() + " " + r.getCliente().getApellidos(),
                    r.getModelo().getModelo(),
                    r.getPrecioReparacion(),
                });
        }
    }
     
     public JTable getTablaReparaciones(){
         return tablaReparaciones;
     }
}

