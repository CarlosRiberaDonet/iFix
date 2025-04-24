/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.frames;

import controller.ClienteController;
import entity.Cliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import ui.components.clientes.CrearClientePanel;
import ui.components.clientes.ClienteTableMouseListener;
import ui.components.clientes.ClientesTable;

/**
 *
 * @author Carlos Ribera
 */

    public class ClienteFrame extends JFrame{
        
        private ClientesTable tablePanel;
        private JTextField nombreTextField;
        private JTextField apellidosTextField;
        private JTextField telefonoTextField;
        private JButton buscarButton;
        private JButton crearClienteButton;
        private JButton salirButton;
        private String nombre;
        private String apellidos;
        private String telefono;

        public ClienteFrame(){
            setTitle("CLIENTES");
            setSize(800, 500);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLocationRelativeTo(null);
            initUI();
        }

       private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel nombreLabel = new JLabel("Nombre:");
        nombreTextField = new JTextField(10);

        JLabel apellidosLabel = new JLabel("Apellidos:");
        apellidosTextField = new JTextField(10);

        JLabel telefonoLabel = new JLabel("Teléfono:");
        telefonoTextField = new JTextField(10);

        buscarButton = new JButton("Buscar");
        crearClienteButton = new JButton("Crear");
        salirButton = new JButton("Atrás");

        topPanel.add(nombreLabel);
        topPanel.add(nombreTextField);
        topPanel.add(apellidosLabel);
        topPanel.add(apellidosTextField);
        topPanel.add(telefonoLabel);
        topPanel.add(telefonoTextField);
        topPanel.add(buscarButton);
        topPanel.add(crearClienteButton);
        topPanel.add(salirButton);

        add(topPanel, BorderLayout.NORTH);
        
        tablePanel = new ClientesTable();
        add(tablePanel, BorderLayout.CENTER);
        
        buscarButton.addActionListener( e -> buscarClienteButton());
        crearClienteButton.addActionListener( e -> crearClienteButton());
        salirButton.addActionListener( e -> dispose());
    }
       
    private void buscarClienteButton(){
        
        nombre = nombreTextField.getText();
        apellidos = apellidosTextField.getText();
        telefono = telefonoTextField.getText();
        
        List<Cliente> clientesList = ClienteController.findClientes(nombre, apellidos, telefono);
        tablePanel.cargarClientes(clientesList);
        
        tablePanel.getTablaClientes().addMouseListener(new ClienteTableMouseListener(clientesList, tablePanel.getTablaClientes())
        );
    }
    
    private void crearClienteButton(){
        JDialog dialog = new JDialog(this, "CREAR CLIENTE", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        
        CrearClientePanel panel = new CrearClientePanel();
        dialog.add(panel, BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
