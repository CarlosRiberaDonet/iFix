/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.frames;

import controller.ClienteController;
import entity.Cliente;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import ui.components.clientes.CrearClienteJPanel;
import ui.components.clientes.ClientesTable;

/**
 *
 * @author Carlos Ribera
 */
/*public class NewClass {
    private void registrarClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        ClientePanel clientePanel = new ClientePanel();

        JDialog dialog = new JDialog(this, "Registrar Cliente", true);
        dialog.setContentPane(clientePanel);
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }                                                      
                                    

    private void salirButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        dispose();
    }                                           

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        String input = JOptionPane.showInputDialog(this, "Introduce el número de télefono");
        
        if(input != null && input.trim().matches("\\d{9}")){
            if(ClienteController.eliminarCliente(input)){
                JOptionPane.showMessageDialog(this, "Cliente eliminado correctamente");
            } else{
                JOptionPane.showMessageDialog(this, "Error al eliminar el cliente. Cliente no encontrado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        } else{
            JOptionPane.showMessageDialog(this, "El teléfono debe contener 9 dígitos sin espacios", "ERROR", JOptionPane.WARNING_MESSAGE);
        }
    }                                              

    private void listarButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        List<Cliente> clientesList = ClienteController.getClientesList();
        ClientesTableDialog tableDialog = new ClientesTableDialog(this);
                tableDialog.cargarClientes(clientesList);
                tableDialog.setVisible(true);
    }                                            

}*/
