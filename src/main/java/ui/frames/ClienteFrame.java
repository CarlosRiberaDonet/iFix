/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.frames;

import controller.ClienteController;
import entity.Cliente;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import ui.components.clientes.CrearClientePanel;
import listeners.ClienteTableMouseListener;
import ui.components.clientes.ClientesTable;

/**
 *
 * @author Carlos Ribera
 */

public class ClienteFrame extends JFrame {

    private JTable tablaClientes;
    private ClientesTable tablePanel;
    private JTextField nombreTextField;
    private JTextField apellidosTextField;
    private JTextField telefonoTextField;
    private List<Cliente> clientesList = new ArrayList<>();
    

    public ClienteFrame() {
        setTitle("CLIENTES");
        setSize(900, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        initUI();
        tablaClientes.addMouseListener(new ClienteTableMouseListener(tablaClientes, clientesList));
    }

    private void initUI() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        nombreTextField = new JTextField(10);
        apellidosTextField = new JTextField(10);
        telefonoTextField = new JTextField(10);

        JButton buscarButton = new JButton("Buscar");
        JButton crearClienteButton = new JButton("Crear");
        JButton salirButton = new JButton("Atrás");

        buscarButton.addActionListener(e -> buscarCliente());
        crearClienteButton.addActionListener(e -> abrirCrearCliente());
        salirButton.addActionListener(e -> dispose());

        topPanel.add(new JLabel("Nombre:"));
        topPanel.add(nombreTextField);
        topPanel.add(new JLabel("Apellidos:"));
        topPanel.add(apellidosTextField);
        topPanel.add(new JLabel("Teléfono:"));
        topPanel.add(telefonoTextField);
        topPanel.add(buscarButton);
        topPanel.add(crearClienteButton);
        topPanel.add(salirButton);

        add(topPanel, BorderLayout.NORTH);
        tablePanel = new ClientesTable();
        tablaClientes = tablePanel.getTablaClientes(); 
        add(tablePanel, BorderLayout.CENTER);
        
    }

    private void buscarCliente(){    
        String nombre = nombreTextField.getText();
        String apellidos = apellidosTextField.getText();
        String telefono = telefonoTextField.getText();
        
        clientesList.clear();

        if(nombreTextField.getText().isEmpty() && apellidosTextField.getText().isEmpty() && telefonoTextField.getText().isEmpty()){
           clientesList.addAll(ClienteController.getAllClientes());
        }
        else{
            clientesList.addAll(ClienteController.findCliente(nombre, apellidos, telefono)); 
        }
        tablePanel.cargarClientes(clientesList);
    }

    private void abrirCrearCliente() {
        JDialog dialog = new JDialog(this, "CREAR CLIENTE", true);
        dialog.setSize(400, 300);
        dialog.setLocationRelativeTo(this);
        dialog.setLayout(new BorderLayout());
        dialog.add(new CrearClientePanel(), BorderLayout.CENTER);
        dialog.setVisible(true);
    }
}
