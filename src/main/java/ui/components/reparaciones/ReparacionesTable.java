/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.reparaciones;

import controller.TipoReparacionController;
import entity.Reparacion;
import entity.TipoReparacion;
import java.util.List;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import utils.Utils;
import java.util.stream.Collectors;


/**
 *
 * @author Carlos Ribera
 */
public class ReparacionesTable extends JPanel{
    
    private JTable tablaReparaciones = new JTable();
    private DefaultTableModel reparacionesTable = new DefaultTableModel();

    public ReparacionesTable(List<Reparacion> reparacionesList, List<TipoReparacion> tipoReparacionList) {
        
        String[] columnas = {"ID", "ENTRADA", "SALIDA", "CLIENTE", "DISPOSITIVO", "REPARACION", "ESTADO", "IMPORTE"};
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
        setReparaciones(reparacionesList, tipoReparacionList);
    }
    
   public void setReparaciones(List<Reparacion> reparacionesList, List<TipoReparacion> tipoReparacionList){
        reparacionesTable.setRowCount(0);
        for (Reparacion r : reparacionesList) {
            String fechaEntrada = Utils.localDateToString(r.getFechaEntrada());
            String fechaSalida = Utils.localDateToString(r.getFechaEntrada());
            // Llenar la lista de tipos de reparación
            r.setTipoReparacion(TipoReparacionController.getTipoReparacionList(r.getId()));
            String tiposReparacion = r.getTipoReparacion().stream()
                .map(TipoReparacion::getTipoReparacion) // Extrae el nombre
                .collect(Collectors.joining(", "));     // Une con comas

            Object[] fila = {
                r.getId(),
                fechaEntrada,
                fechaSalida,
                r.getDispositivo().getIdCliente() ,
                r.getDispositivo().getIdModelo(),
                tiposReparacion,
                r.getEstado(),
                r.getPrecioReparacion()             
            };
            reparacionesTable.addRow(fila);
            System.out.println("ESTADO REPARACION: " + r.getEstado());
        }
   }
   
   public JTable getTablaReparaciones(){
       return tablaReparaciones;
   }
}

