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
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import listeners.ReparacionTableMouseListener;
import ui.components.reparaciones.ReparacionesTable;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionFrame extends JFrame {
    
    private ReparacionesTable tablePanel;
    private JTextField telefonoTextField;
    private JButton buscarButton;
    private JButton volverButton;
    private JDateChooser fechaEntradaChooser;
    private JDateChooser fechaSalidaChooser;
    
    public ReparacionFrame(){
        setTitle("REPARACIONES");
        setSize(800, 600);
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
        topPanel.add(volverButton);
        
        add(topPanel, BorderLayout.NORTH);
        
        tablePanel = new ReparacionesTable(true);
        add(tablePanel, BorderLayout.CENTER);
        
       buscarButton.addActionListener( e -> buscarReparacionButton());
       volverButton.addActionListener( e -> dispose());
       
       tablePanel.getTablaReparaciones().addMouseListener(
       new ReparacionTableMouseListener(tablePanel.getTablaReparaciones()));
    }
    
     private void buscarReparacionButton(){
          
        String telefono = telefonoTextField.getText().trim();
        LocalDate fechaEntrada = dateToLocalDate(fechaEntradaChooser.getDate());
        LocalDate fechaSalida = dateToLocalDate(fechaSalidaChooser.getDate());
        
        List<Reparacion> reparacionesList = ReparacionController.findReparaciones(telefono, fechaEntrada, fechaSalida);
        if(reparacionesList != null && !reparacionesList.isEmpty()){
            tablePanel.cargarReparaciones(reparacionesList);
        } else{
             JOptionPane.showMessageDialog(null, "No hay reparaciones en la base de datos.", "INFO", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
     
     public static LocalDate dateToLocalDate(Date fechaDate) {
        if (fechaDate != null) {
            return fechaDate.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate();
        }
        return null;
    }
}
