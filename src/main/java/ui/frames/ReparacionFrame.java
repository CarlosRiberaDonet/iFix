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
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ui.components.reparaciones.ReparacionesTable;
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionFrame extends JFrame {
    
    private ReparacionesTable tablePanel;
    private JTextField telefonoTextField;
    private JButton buscarButton;
    JDateChooser fechaEntradaChooser;
    JDateChooser fechaSalidaChooser;
    
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
        
        add(topPanel, BorderLayout.NORTH);
        
        tablePanel = new ReparacionesTable(true);
        add(tablePanel, BorderLayout.CENTER);
        
       buscarButton.addActionListener( e -> buscarButton());
    }
    
     private void buscarButton(){
          
        String telefono = telefonoTextField.getText().trim();
        LocalDate fechaEntrada = Utils.dateToLocalDate(fechaEntradaChooser.getDate());
        LocalDate fechaSalida = Utils.dateToLocalDate(fechaSalidaChooser.getDate());
        
        List<Reparacion> reparacionesList = ReparacionController.buscarReparaciones(telefono, fechaEntrada, fechaSalida);
        tablePanel.cargarReparaciones(reparacionesList);
    }
}
