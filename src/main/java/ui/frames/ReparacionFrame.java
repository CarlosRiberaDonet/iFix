/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.frames;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ui.components.reparaciones.ReparacionesTable;

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionFrame extends JFrame {
    
    private ReparacionesTable tablePanel;
    private JTextField telefonoTextField;
    private JButton buscarButton;
    
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
        
        JLabel clienteLabel = new JLabel("Número cliente");
        telefonoTextField = new JTextField(10);
        
        buscarButton = new JButton("Buscar");
        
        topPanel.add(clienteLabel);
        topPanel.add(telefonoTextField);
        topPanel.add(buscarButton);
        
        add(topPanel, BorderLayout.CENTER);
        
        tablePanel = new ReparacionesTable(true);
        add(tablePanel, BorderLayout.CENTER);
        
       //buscarButton.addActionListener( e -> buscarButton());
    }
    
     private void buscarButton(){
          
        String telefono = telefonoTextField.getText();
        // List<Reparacion> reparacionesList = obtener lista repareciones
    }
}
