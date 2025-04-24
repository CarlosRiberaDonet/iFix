/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui.components.clientes;

import entity.Cliente;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.JTable;


/**
 *
 * @author Carlos Ribera
 */
public class ClienteTableMouseListener extends MouseAdapter {

    private List<Cliente> clientesList;
    private JTable clienteTable;
    ClientePanel clientePanel = new ClientePanel();

    public ClienteTableMouseListener(List<Cliente> clientesList, JTable clienteTable) {
        this.clientesList = clientesList;
        this.clienteTable = clienteTable;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if (e.getClickCount() == 2 && clienteTable.getSelectedRow() != -1) {  
            int fila = clienteTable.getSelectedRow();
            Cliente clienteSeleccionado = clientesList.get(fila);
            clientePanel.mostrarReparacionesCliente(clienteSeleccionado);    
        }
    }
}

