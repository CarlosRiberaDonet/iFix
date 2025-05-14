/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.frames;

import com.toedter.calendar.JDateChooser;
import controller.ReparacionController;
import entity.Reparacion;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import listeners.ReparacionTableMouseListener;
import ui.components.reparaciones.ReparacionesTable;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionFrame extends JFrame {
    
    private JTable tablaReparaciones;
    private ReparacionesTable tablePanel;
    private JTextField telefonoTextField;
    private JButton buscarButton;
    private JButton deleteButton;
    private JButton volverButton;
    private JDateChooser fechaEntradaChooser;
    private JDateChooser fechaSalidaChooser;
    private static List<Reparacion> reparacionesList  = new ArrayList<>();
    
    public ReparacionFrame(){
        setTitle("REPARACIONES");
        setSize(830, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
    }
    
    private void initUI(){
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JLabel clienteLabel = new JLabel("Teléfono cliente");
        telefonoTextField = new JTextField(10);
        
        buscarButton = new JButton("Buscar");
        deleteButton = new JButton("Eliminar");
        volverButton = new JButton("Volver");
        
        fechaEntradaChooser = new JDateChooser();
        fechaEntradaChooser.setDateFormatString("dd/MM/yyyy");
        fechaSalidaChooser = new JDateChooser();
        fechaSalidaChooser.setDateFormatString("dd/MM/yyyy");
        
        topPanel.add(clienteLabel);
        topPanel.add(telefonoTextField);
        topPanel.add(new JLabel("Fecha Inicio"));
        topPanel.add(fechaEntradaChooser);
        topPanel.add(new JLabel("Fecha Fin"));
        topPanel.add(fechaSalidaChooser);
        topPanel.add(buscarButton);
        topPanel.add(deleteButton);
        topPanel.add(volverButton);
        
        add(topPanel, BorderLayout.NORTH);
        tablePanel = new ReparacionesTable(reparacionesList);
        tablaReparaciones = tablePanel.getTablaReparaciones();
        cargarTablaReparaciones();

        tablaReparaciones.addMouseListener(new ReparacionTableMouseListener(tablaReparaciones, reparacionesList));
        add(tablePanel, BorderLayout.CENTER);
        
        buscarButton.addActionListener( e -> listarReparacionButton());
        deleteButton.addActionListener( e -> eliminarReparacion());
        volverButton.addActionListener( e -> dispose());
    }
    
    public void cargarTablaReparaciones(){
        
        reparacionesList.clear();
        reparacionesList.addAll(ReparacionController.getAllReparaciones());
        tablePanel.setReparaciones(reparacionesList);
    }
    
     private void listarReparacionButton(){
        
        

    }
     
     private void eliminarReparacion(){
        int filaSelect = tablaReparaciones.getSelectedRow();
        if(filaSelect >= 0){
            int idReparacion = (int) tablaReparaciones.getValueAt(filaSelect, 0);
            if(ReparacionController.eliminarReparacion(idReparacion)){
                JOptionPane.showMessageDialog(this, "Reparación eliminada correctamente.","ÉXITO",  JOptionPane.INFORMATION_MESSAGE);
                // Recargo la lista de reparaciones en la tabla
                cargarTablaReparaciones();
            }else{
               JOptionPane.showMessageDialog(this, "No se ha podido eliminar la reparación.","ERROR",  JOptionPane.ERROR_MESSAGE);
            }
        }
     }
     
     /*public static LocalDate dateToLocalDate(Date fechaDate) {
        if (fechaDate != null) {
            return fechaDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
        }
        return null;
    }*/
}
