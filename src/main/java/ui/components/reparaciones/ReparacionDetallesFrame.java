package ui.components.reparaciones;

import controller.ReparacionController;
import entity.Dispositivo;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import utils.Utils;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */

/**
 *
 * @author Carlos Ribera
 */
public class ReparacionDetallesFrame extends javax.swing.JFrame {
    
    /**
     * Creates new form ReparacionDetallesDialog
     */
    
    private ReparacionController rc = new ReparacionController();
    private Reparacion reparacion;
    private Marca marca;
    private Modelo modelo;
    private TipoReparacion tipoReparacion;
    private static boolean modoEdicion;
    JComboBox<Marca> cmbMarca = new JComboBox<>();
    JComboBox<Modelo> cmbModelo = new JComboBox<>();
    JComboBox<TipoReparacion> cmbTipoReparacion = new JComboBox<>();
    
    public ReparacionDetallesFrame(Reparacion reparacion) {
        super("Detalles de Reparación");  // Título del frame
        initComponents();
        this.reparacion = reparacion;
        cargarComboBox();
        cargarReparacion(reparacion);
        setCamposEditable(false);
        modoEdicion = false;
        listenerComboBoxMarca();
        listenerComboBoxModelo();   
        listenerTipoReparacionComboBox();
    }
    
    // Cargar Marcas disponibles en marcaComboBox
    private void listenerComboBoxMarca(){
         // Añadir el ActionListener para escuchar los cambios en la selección de marca
         cmbMarca.addActionListener( e -> {
             Marca marcaSelect;
            // Obtengo la Marca seleccionada
            Object item = cmbMarca.getSelectedItem();
            if(item instanceof Marca){
                marcaSelect = (Marca) item;
                int idMarca = marcaSelect.getIdMarca();
                // Cargo modeloComboBox
                rc.llenarComboBoxModelo(idMarca, cmbModelo); 
            } else{
                // Preparo instancia para crear un nuevo tipo Modelo en la BD
                marcaSelect = new Marca();
                marcaSelect.setIdMarca(-1);
            }          
         });
    }
    
    private void listenerComboBoxModelo(){
        cmbModelo.addActionListener( e -> {
            Modelo modeloSelect;
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
    
    private void listenerTipoReparacionComboBox(){
        cmbTipoReparacion.addActionListener(e -> {
            TipoReparacion tipoReparacionSelect;
            Object item = cmbTipoReparacion.getSelectedItem();
            if(item instanceof TipoReparacion){
                tipoReparacionSelect = (TipoReparacion) item;
            } else{
                tipoReparacionSelect = new TipoReparacion();
                tipoReparacionSelect.setId(-1);
            }
        });
    }
    
    private void setCamposEditable(boolean editable) {
        fechaEntradaTextField.setEditable(editable);
        fechaSalidaTextField.setEditable(editable);
        importeTextField.setEditable(editable);
        garantiaCheckBox.setEnabled(editable);
        comentariosTextArea.setEditable(editable);
        cmbMarca.setEnabled(editable);
        cmbModelo.setEnabled(editable);
        imeiTextField.setEditable(editable);
        cmbTipoReparacion.setEnabled(editable);
        cmbEstado.setEnabled(editable);
    }
     
    // Lleno los comboBox
    private void cargarComboBox(){
        rc.llenarComboBoxMarca(cmbMarca);
        rc.llenarComboBoxModelo(reparacion.getIdMarca(), cmbModelo);
        rc.llenarComboBoxReparacion(cmbTipoReparacion);       
        // Dibujo los ComboBox
        cmbMarca.setBounds(30, 20, 150, 30);
        cmbModelo.setBounds(190, 20, 150, 30);
        cmbTipoReparacion.setBounds(350, 20, 200, 30);
        // Agrego los ComboBox al jPanel1
        jPanel1.add(cmbMarca);
        jPanel1.add(cmbModelo);
        jPanel1.add(cmbTipoReparacion);
        jPanel1.repaint();
    }
    
    private void cargarReparacion(Reparacion reparacion){
              
        fechaEntradaTextField.setText(Utils.localDateToString(reparacion.getFechaEntrada()));
        fechaSalidaTextField.setText(Utils.localDateToString(reparacion.getFechaSalida()));
        cmbMarca.setSelectedItem(reparacion.getMarca());
        rc.seleccionarModeloPorId(cmbModelo, reparacion.getIdModelo());
        imeiTextField.setText(String.valueOf(reparacion.getImei()));
        rc.seleccionarTipoReparacionPorId(cmbTipoReparacion, reparacion.getTipoReparacion().getId());
        importeTextField.setText(reparacion.getPrecioReparacion().toString());
        garantiaCheckBox.setSelected(reparacion.isGarantia());
        comentariosTextArea.setText(reparacion.getComentarios());
        cmbEstado.setSelectedItem(reparacion.getEstado());
        clienteTextField.setText(reparacion.getCliente().getNombre().toUpperCase() + " " + reparacion.getCliente().getApellidos().toUpperCase());
        telefonoTextField.setText(reparacion.getCliente().getTelefono());
    }
    
    public void guardarReparacion(Reparacion reparacion){
        
        int idReparacion = reparacion.getId();
        LocalDate fechaEntrada = Utils.stringToLocalDate(fechaEntradaTextField.getText());
        LocalDate fechaSalida =  Utils.stringToLocalDate(fechaSalidaTextField.getText());
        marca = (Marca) cmbMarca.getSelectedItem();
        int idMarca = marca.getIdMarca();
        modelo = (Modelo) cmbModelo.getSelectedItem();
        int idModelo = modelo.getIdModelo();
        int imei = Integer.parseInt(imeiTextField.getText());
        tipoReparacion = (TipoReparacion) cmbTipoReparacion.getSelectedItem();
        int idTipoReparacion = tipoReparacion.getId();
        BigDecimal importe = Utils.stringToBigDecimal(importeTextField.getText());
        boolean garantia = garantiaCheckBox.isSelected();
        String comentarios = comentariosTextArea.getText();
        String estado = (String) cmbEstado.getSelectedItem();
        
        Reparacion r = new Reparacion(idReparacion, fechaEntrada, fechaSalida, idMarca, idModelo, imei, idTipoReparacion, importe, garantia, comentarios, estado);
        ReparacionController.modificarReparacion(r);
        
        JOptionPane.showMessageDialog(null, "Reparación modificada", "ÉXITO", JOptionPane.INFORMATION_MESSAGE);
        this.dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        comentariosLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        comentariosTextArea = new javax.swing.JTextArea();
        jSeparator2 = new javax.swing.JSeparator();
        modificarButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        telefonoTextField = new javax.swing.JTextField();
        clienteLabel = new javax.swing.JLabel();
        clienteTextField = new javax.swing.JTextField();
        entradaLabel = new javax.swing.JLabel();
        fechaEntradaTextField = new javax.swing.JTextField();
        salidaLabel = new javax.swing.JLabel();
        fechaSalidaTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        eliminarButton = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        importeLabel = new javax.swing.JLabel();
        importeTextField = new javax.swing.JTextField();
        garantiaCheckBox = new javax.swing.JCheckBox();
        cmbEstado = new javax.swing.JComboBox<>();
        imeiTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DETALLES REPARACION");
        setResizable(false);

        comentariosLabel.setText("COMENTARIOS");
        comentariosLabel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        comentariosTextArea.setEditable(false);
        comentariosTextArea.setColumns(20);
        comentariosTextArea.setRows(5);
        jScrollPane1.setViewportView(comentariosTextArea);

        modificarButton.setText("Modificar Reparación");
        modificarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("Teléfono:");

        telefonoTextField.setEditable(false);

        clienteLabel.setText("Cliente:");

        clienteTextField.setEditable(false);

        entradaLabel.setText("F.Entrada:");

        fechaEntradaTextField.setEditable(false);

        salidaLabel.setText("F.Salida:");

        fechaSalidaTextField.setEditable(false);

        eliminarButton.setText("Eliminar Reparación");
        eliminarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarButtonActionPerformed(evt);
            }
        });

        jButton2.setText("Menú Reparaciones");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 645, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        importeLabel.setText("Importe:");

        importeTextField.setEditable(false);

        garantiaCheckBox.setText("Garantía");

        cmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Presupuesto", "Repuesto Pedido", "En Reparación", "Reparado" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(32, 32, 32)
                                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(52, 52, 52))
                            .addComponent(jSeparator2)
                            .addComponent(jSeparator1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(85, 85, 85)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 534, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(220, 220, 220)
                                        .addComponent(comentariosLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(130, 130, 130)
                                .addComponent(modificarButton)
                                .addGap(18, 18, 18)
                                .addComponent(eliminarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton2))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(38, 38, 38)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(clienteLabel)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(clienteTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                                            .addComponent(cmbEstado, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(entradaLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addGap(9, 9, 9)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(telefonoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                                    .addComponent(fechaEntradaTextField))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(salidaLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(fechaSalidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(importeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(garantiaCheckBox, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(imeiTextField))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(clienteLabel)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(salidaLabel)
                            .addComponent(fechaSalidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(fechaEntradaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(entradaLabel)
                            .addComponent(imeiTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(importeLabel)
                            .addComponent(garantiaCheckBox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(cmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(8, 8, 8)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comentariosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modificarButton)
                    .addComponent(jButton2)
                    .addComponent(eliminarButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modificarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarButtonActionPerformed
        
        if(!modoEdicion){
            modoEdicion = true;
            setCamposEditable(true);
            modificarButton.setText("GUARDAR");
        } else{
            modoEdicion = false;
            setCamposEditable(false);
            guardarReparacion(reparacion);
            modificarButton.setText("MODIFICAR");
        }
    }//GEN-LAST:event_modificarButtonActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void eliminarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarButtonActionPerformed
        if(Utils.confirmationMessage("ELIMINAR", "ELIMINAR REPARACIÓN")){ 
            if(ReparacionController.eliminarReparacion(reparacion.getId())){
                JOptionPane.showMessageDialog(this, "Reparación eliminada correctamente.","ÉXITO",  JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else{
                JOptionPane.showMessageDialog(this, "No se ha podido eliminar la reparación.","ERROR",  JOptionPane.ERROR_MESSAGE);
                dispose();
            }
        }   
    }//GEN-LAST:event_eliminarButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JTextField clienteTextField;
    private javax.swing.JComboBox<String> cmbEstado;
    private javax.swing.JLabel comentariosLabel;
    private javax.swing.JTextArea comentariosTextArea;
    private javax.swing.JButton eliminarButton;
    private javax.swing.JLabel entradaLabel;
    private javax.swing.JTextField fechaEntradaTextField;
    private javax.swing.JTextField fechaSalidaTextField;
    private javax.swing.JCheckBox garantiaCheckBox;
    private javax.swing.JTextField imeiTextField;
    private javax.swing.JLabel importeLabel;
    private javax.swing.JTextField importeTextField;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JButton modificarButton;
    private javax.swing.JLabel salidaLabel;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
