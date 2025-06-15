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
import java.time.LocalDate;
import java.time.ZoneId;
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
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionFrame extends JFrame {
    
    private JTable tablaReparaciones;
    private ReparacionesTable tablePanel;
    private JTextField telefonoTextField = null;
    private JButton buscarButton;
    private JButton deleteButton;
    private JButton volverButton;
    private JDateChooser fechaEntradaChooser = null;
    private JDateChooser fechaSalidaChooser = null;
    private List<Reparacion> reparacionesList  = new ArrayList<>();
    
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
        
        buscarButton.addActionListener( e -> cargarTablaReparaciones());
        deleteButton.addActionListener( e -> eliminarReparacion());
        volverButton.addActionListener( e -> dispose());
    }
    
    public void cargarTablaReparaciones(){      
        reparacionesList.clear();
        
        boolean buscarTelefono = !telefonoTextField.getText().isEmpty();
        boolean buscarFecha = fechaEntradaChooser.getDate() != null && fechaSalidaChooser.getDate() != null;
        
        // Busqueda por teléfono y fecha
        if(buscarTelefono && buscarFecha){
            reparacionesList.addAll(findByPhoneAndDate());
        }
        
        // Busqueda solo por teléfono
        else if(buscarTelefono){
            // Compruebo que el campo teléfono está rellenado correctamente
            if(Utils.checkTelefono(telefonoTextField.getText())){
                reparacionesList.addAll(findByPhone());
            }
        }
        
        // Busqueda solo por fecha
        else if(buscarFecha){
            reparacionesList.addAll(findByDate());
        }
        
        // Si los campos de busqueda están en blanco, lista todas las reparaciones
        else{
            reparacionesList.addAll(ReparacionController.getAllReparaciones());
        }
        tablePanel.setReparaciones(reparacionesList);
    }
    
    // Busqueda por teléfono
    private List<Reparacion> findByPhone(){
        String telefono = telefonoTextField.getText();   
        return ReparacionController.findReparacionesByPhone(telefono);
    }
    
    // Busqueda por fecha
    private List<Reparacion> findByDate(){
        // Si la busqueda es por rango de fechas
        LocalDate fechaEntrada = fechaEntradaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaSalida = fechaSalidaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ReparacionController.findReparacionByDate(fechaEntrada, fechaSalida);
    }
    
    private List<Reparacion> findByPhoneAndDate(){
        String telefono = telefonoTextField.getText();
        LocalDate fechaEntrada = fechaEntradaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate fechaSalida = fechaSalidaChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return ReparacionController.findReparacionByPhoneAndDate(telefono, fechaEntrada, fechaSalida);
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
}
