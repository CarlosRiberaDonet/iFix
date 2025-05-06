/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JTextField;


/**
 *
 * @author Carlos Ribera
 */
public class ButtonListeners implements ActionListener{
    
    private final JButton boton;
    private final JTextField[] campos;
    private boolean modoEdicion = false;

    // Constructor: recibe el botón que cambia de estado y un array de campos que se habilitan/deshabilitan.
    public ButtonListeners(JButton boton, JTextField... campos) {
        this.boton = boton;
        this.campos = campos;
    }
    
    // Al pulsar el botón, cambia el texto y habilita o deshabilita los campos.
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!modoEdicion) {
            // Cambia a modo edición
            boton.setText("Guardar");
            for (JTextField campo : campos) {
                campo.setEditable(true);
            }
            modoEdicion = true;
        } else {
            // Guarda y vuelve a modo normal
            boton.setText("Modificar");
            for (JTextField campo : campos) {
                campo.setEditable(false);
            }
            // Aquí podrías llamar a un método guardarCambios()
            modoEdicion = false;
        }
    }   
}
