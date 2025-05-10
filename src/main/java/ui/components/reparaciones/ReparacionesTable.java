/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.reparaciones;

import entity.Reparacion;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import listeners.ReparacionTableMouseListener;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionesTable extends JPanel{
    
    private JTable tablaReparaciones;
    private DefaultTableModel reparacionesTable;

    public ReparacionesTable(List<Reparacion> reparacionesList) {
        
        String[] columnas = {"ID", "Entrada", "Salida", "Cliente", "Dispositivo", "Reparación", "Importe", "Garantía"};
        reparacionesTable = new DefaultTableModel(columnas, 0){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaReparaciones = new JTable(reparacionesTable);
        JScrollPane scrollPane = new JScrollPane(tablaReparaciones);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(scrollPane);

        tablaReparaciones.addMouseListener(new ReparacionTableMouseListener(tablaReparaciones));
        setReparaciones(reparacionesList);
    }
    
   public void setReparaciones(List<Reparacion> reparacionesList){
        reparacionesTable.setRowCount(0);
        for (Reparacion r : reparacionesList) {
            Object[] fila = {
                r.getId(),
                r.getFechaEntrada(),
                r.getFechaSalida(),
                r.getCliente().getNombre() + " " + r.getCliente().getApellidos(),
                r.getModelo().getModelo(),
                r.getTipoReparacion().getReparacion(),
                r.getPrecioReparacion(),
                r.isGarantia()
            };
            reparacionesTable.addRow(fila);
        }
   }
   
   public JTable getTablaReparaciones(){
       return tablaReparaciones;
   }
}

