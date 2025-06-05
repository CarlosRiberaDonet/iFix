/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package ui.components.reparaciones;

import controller.MarcaModeloController;
import controller.ReparacionController;
import controller.TipoReparacionController;
import entity.Cliente;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.awt.Window;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import ui.frames.ReparacionFrame;
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class CrearReparacion extends javax.swing.JPanel {

    /**
     * Creates new form CrearReparacion
     */
    private ReparacionController rc = new ReparacionController();
    
    private int idMarca;
    private int idModelo;
    private int idTipoReparacion;
    private Cliente cliente;
    private Modelo modeloSelect;
    private Marca marcaSelect;
    private TipoReparacion tipoReparacionSelect;
    
    private String fechaActual = Utils.fechaActualToString();
    JComboBox<Marca> cmbMarca  = new JComboBox<>();
    JComboBox<Modelo> cmbModelo = new JComboBox<>();
    JComboBox<TipoReparacion> cmbTipoReparacion = new JComboBox<>();

    
    

    public CrearReparacion(Cliente cliente) {
        this.cliente = cliente;
        initComponents();
        cargarDatosReparacion();
        cargarComboBox();
        listenerComboBoxMarca();
        listenerComboBoxModelo();   
        listenerTipoReparacionComboBox();
        
    }

    // Cargar Marcas disponibles en marcaComboBox
    public void listenerComboBoxMarca(){
         // Añadir el ActionListener para escuchar los cambios en la selección de marca
         cmbMarca.addActionListener(e -> {
            // Obtengo la Marca seleccionada
            Object item = cmbMarca.getSelectedItem();
            if(item instanceof Marca){
                marcaSelect = (Marca) item;
                idMarca = marcaSelect.getIdMarca();
                // Cargo modeloComboBox
                rc.llenarComboBoxModelo(idMarca, cmbModelo); 
            } else{
                // Preparo instancia para crear un nuevo tipo Modelo en la BD
                marcaSelect = new Marca();
                marcaSelect.setIdMarca(-1);
            }          
         });
    }
    
    public void listenerComboBoxModelo(){
        cmbModelo.addActionListener(e -> {
            
            /// Obtengo el modelo seleccionado
            Object item = cmbModelo.getSelectedItem();
            // Si se ha seleccionado un item del comboBox (No se ha añadido un modelo nuevo manualmente)
            if(item instanceof Modelo){
                modeloSelect = (Modelo) item;
            } else{
                // Preparo instancia para crear un nuevo tipo Modelo en la BD
                modeloSelect = new Modelo();
                modeloSelect.setIdModelo(-1);
            }
        });
    }
    
    public void listenerTipoReparacionComboBox(){
        cmbTipoReparacion.addActionListener(e -> {
            
            Object item = cmbTipoReparacion.getSelectedItem();
            if(item instanceof TipoReparacion){
                tipoReparacionSelect = (TipoReparacion) item;
            } else{
                tipoReparacionSelect = new TipoReparacion();
                tipoReparacionSelect.setId(-1);
            }
        });
    }
    
    public void cargarDatosReparacion(){
        nombreTextField.setText(cliente.getNombre().toUpperCase());
        apellidosTextField.setText(cliente.getApellidos().toUpperCase());
        telefonoTextField.setText(cliente.getTelefono().toUpperCase());
        direccionTextField.setText(cliente.getDireccion().toUpperCase());
        entradaTextField.setText(fechaActual);
        salidaTextField.setText(fechaActual);
    }
    // Lleno los comboBox
    public void cargarComboBox(){
        // LLeno el comboBox de tipo Marca y obtengo el id del primer valor
        cmbMarca = new JComboBox<>();
        rc.llenarComboBoxMarca(cmbMarca);
        marcaSelect = (Marca) cmbMarca.getSelectedItem();
        idMarca = marcaSelect.getIdMarca();
        // LLeno el comboBox de tipo Modelo y obtengo su id
        cmbModelo = rc.llenarComboBoxModelo(idMarca, cmbModelo);
        modeloSelect = (Modelo) cmbModelo.getSelectedItem();
        idModelo = (int) modeloSelect.getIdModelo();
        
        cmbMarca.setBounds(30, 20, 150, 30);
        cmbModelo.setBounds(190, 20, 150, 30);
        cmbTipoReparacion.setBounds(350, 20, 200, 30);
        jPanel1.add(cmbMarca);
        jPanel1.add(cmbModelo);
        jPanel1.add(cmbTipoReparacion);
        jPanel1.repaint();
        
        // LLeno el comboBox de tipo TipoReparacion y obtengo su id
        cmbTipoReparacion = rc.llenarComboBoxReparacion(cmbTipoReparacion);
        tipoReparacionSelect = (TipoReparacion) cmbTipoReparacion.getSelectedItem();
        idTipoReparacion = tipoReparacionSelect.getId();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jCheckBoxMenuItem1 = new javax.swing.JCheckBoxMenuItem();
        nombreTextField = new javax.swing.JTextField();
        comentariosLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        comentariosTextArea = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        apellidosTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        direccionTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        guardarButton = new javax.swing.JButton();
        reparacionPanel = new javax.swing.JPanel();
        entradaLabel = new javax.swing.JLabel();
        entradaTextField = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        salidaTextField = new javax.swing.JTextField();
        salidaLabel = new javax.swing.JLabel();
        importeLabel = new javax.swing.JLabel();
        importeTextField = new javax.swing.JTextField();
        garantiaCheckBox = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        jCheckBoxMenuItem1.setSelected(true);
        jCheckBoxMenuItem1.setText("jCheckBoxMenuItem1");

        setPreferredSize(new java.awt.Dimension(600, 800));

        nombreTextField.setEditable(false);

        comentariosLabel.setText("COMENTARIOS");
        comentariosLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comentariosTextArea.setColumns(20);
        comentariosTextArea.setRows(5);
        jScrollPane1.setViewportView(comentariosTextArea);

        jLabel3.setText("NOMBRE:");

        jLabel4.setText("APELLIDOS:");

        apellidosTextField.setEditable(false);

        jLabel5.setText("TELÉFONO:");

        telefonoTextField.setEditable(false);

        jLabel6.setText("DIRECCIÓN:");

        direccionTextField.setEditable(false);

        guardarButton.setText("GUARDAR");
        guardarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarButtonActionPerformed(evt);
            }
        });

        entradaLabel.setText("F.Entrada:");

        entradaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaTextFieldActionPerformed(evt);
            }
        });

        salidaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salidaTextFieldActionPerformed(evt);
            }
        });

        salidaLabel.setText("F.Salida:");

        importeLabel.setText("Importe");

        garantiaCheckBox.setText("Garantía");
        garantiaCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                garantiaCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reparacionPanelLayout = new javax.swing.GroupLayout(reparacionPanel);
        reparacionPanel.setLayout(reparacionPanelLayout);
        reparacionPanelLayout.setHorizontalGroup(
            reparacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reparacionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reparacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(reparacionPanelLayout.createSequentialGroup()
                        .addComponent(entradaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(entradaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(salidaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(salidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(importeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(garantiaCheckBox)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        reparacionPanelLayout.setVerticalGroup(
            reparacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reparacionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reparacionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entradaLabel)
                    .addComponent(salidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(entradaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salidaLabel)
                    .addComponent(importeLabel)
                    .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garantiaCheckBox))
                .addGap(83, 83, 83)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.setLayout(null);

        jLabel1.setText("Marca");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(80, 0, 50, 16);

        jLabel2.setText("Modelo");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(230, 0, 70, 16);

        jLabel7.setText("Reparación");
        jPanel1.add(jLabel7);
        jLabel7.setBounds(400, 0, 120, 16);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(70, 70, 70)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(telefonoTextField)
                                    .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(51, 51, 51)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(apellidosTextField)
                                    .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(274, 274, 274)
                                .addComponent(comentariosLabel))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(278, 278, 278)
                                .addComponent(guardarButton)))
                        .addGap(0, 10, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(reparacionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(apellidosTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nombreTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(reparacionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(comentariosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardarButton)
                .addContainerGap(100, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void garantiaCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_garantiaCheckBoxActionPerformed
        
    }//GEN-LAST:event_garantiaCheckBoxActionPerformed

    private void salidaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salidaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_salidaTextFieldActionPerformed

    private void guardarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarButtonActionPerformed
        
        // Obtengo los datos de la reparación y lso valido
        String fechaEntradaStr = entradaTextField.getText();
        LocalDate fechaEntrada = Utils.checkFecha(fechaEntradaStr);
        String fechaSalidaStr = salidaTextField.getText();
        LocalDate fechaSalida = Utils.checkFecha(fechaSalidaStr);
        String textoImporte = importeTextField.getText();
        BigDecimal importe = Utils.stringToBigDecimal(textoImporte);
        if(importe == null){
            JOptionPane.showMessageDialog(null, "Revise el importe", "ERROR", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean garantia = garantiaCheckBox.isSelected();
        String comentarios = comentariosTextArea.getText();
        int idCliente = cliente.getId();
        
        // Obtengo el id de la marca seleccionada
        idMarca = marcaSelect.getIdMarca();
        // Si la marca seleccionada ha sido introducida manualmente por el USR, (idMarca = -1)
        if(idMarca < 1){
            // Casteo a String el dato introducido por el USR en marcaCombobox
            String nuevaMarcaStr = (String) cmbMarca.getEditor().getItem();  
           // Creo la nueva Marca en la BD y la instancio en marcaSelect
            marcaSelect = MarcaModeloController.addMarca(nuevaMarcaStr);  
            // Obtengo el id de la nueva marca
            idMarca = marcaSelect.getIdMarca();
        }
        
        // Obtengo el id del modelo seleccionado
        idModelo = modeloSelect.getIdModelo();
        // Si el modelo seleccionado ha sido introducido manualmente por el USR, (idModelo = -1)
        if(idModelo < 1){
            // Casteo a String el dato introducido por el USR en modeloCombobox
            String nuevoModeloStr = (String) cmbModelo.getEditor().getItem();
            // Creo el nuevo Modelo en la BD y lo instancio en modeloSelect
            modeloSelect = MarcaModeloController.addModelo(nuevoModeloStr, idMarca);
            // Obtengo el id del nuevo modelo
            idModelo = modeloSelect.getIdModelo();
        }
        
        // Obtengo el id del tipoReparacion
        idTipoReparacion = tipoReparacionSelect.getId();
        if(idTipoReparacion < 1){
            String nuevotipoReparacionStr = (String) cmbTipoReparacion.getEditor().getItem();
            tipoReparacionSelect = TipoReparacionController.addTipoReparacion(nuevotipoReparacionStr);
            idTipoReparacion = tipoReparacionSelect.getId();
        }
        Reparacion nuevaReparacion = new Reparacion(fechaEntrada, fechaSalida, idMarca, idModelo, idTipoReparacion, importe, garantia, comentarios, idCliente);
        if (ReparacionController.crearReparacion(nuevaReparacion)) {
            JOptionPane.showMessageDialog(null, "Reparación guardada correctamente.");
        } else {
            JOptionPane.showMessageDialog(null, "Error al guardar la reparación.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        // Refreco la lista de reparacion y cierro el JPanel
        Window window = SwingUtilities.getWindowAncestor(this);
        if (window != null) {
            ReparacionFrame reparacionFrame = new ReparacionFrame();
            reparacionFrame.cargarTablaReparaciones();
            window.dispose();
        }
    }//GEN-LAST:event_guardarButtonActionPerformed

    private void entradaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_entradaTextFieldActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField apellidosTextField;
    private javax.swing.JLabel comentariosLabel;
    private javax.swing.JTextArea comentariosTextArea;
    private javax.swing.JTextField direccionTextField;
    private javax.swing.JLabel entradaLabel;
    private javax.swing.JTextField entradaTextField;
    private javax.swing.JCheckBox garantiaCheckBox;
    private javax.swing.JButton guardarButton;
    private javax.swing.JLabel importeLabel;
    private javax.swing.JTextField importeTextField;
    private javax.swing.JCheckBoxMenuItem jCheckBoxMenuItem1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextField nombreTextField;
    private javax.swing.JPanel reparacionPanel;
    private javax.swing.JLabel salidaLabel;
    private javax.swing.JTextField salidaTextField;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
