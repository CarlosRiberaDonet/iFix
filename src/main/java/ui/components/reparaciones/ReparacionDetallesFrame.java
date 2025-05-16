package ui.components.reparaciones;

import controller.ReparacionController;
import entity.Marca;
import entity.Modelo;
import entity.Reparacion;
import entity.TipoReparacion;
import java.math.BigDecimal;
import java.sql.Date;
import javax.swing.JComboBox;
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
        cmbTipoReparacion.setEnabled(editable);
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
              
        fechaEntradaTextField.setText(Utils.dateToString(reparacion.getFechaEntrada()));
        fechaSalidaTextField.setText(Utils.dateToString(reparacion.getFechaSalida()));
        cmbMarca.setSelectedItem(reparacion.getMarca());
        cmbModelo.setSelectedItem(reparacion.getModelo());
        cmbTipoReparacion.setSelectedItem(reparacion.getTipoReparacion());
        importeTextField.setText(reparacion.getPrecioReparacion().toString());
        garantiaCheckBox.setSelected(reparacion.isGarantia());
        comentariosTextArea.setText(reparacion.getComentarios());
        direccionTextField.setText(reparacion.getCliente().getDireccion());
        clienteTextField.setText(reparacion.getCliente().getNombre());
        telefonoTextField.setText(reparacion.getCliente().getTelefono());
    }
    
    public void guardarReparacion(Reparacion reparacion){
        
        int idReparacion = reparacion.getId();
        Date fechaEntrada = Utils.stringToDate(fechaEntradaTextField.getText());
        Date fechaSalida =  Utils.stringToDate(fechaSalidaTextField.getText());
        marca = (Marca) cmbMarca.getSelectedItem();
        int idMarca = marca.getIdMarca();
        modelo = (Modelo) cmbModelo.getSelectedItem();
        int idModelo = modelo.getIdModelo();
        tipoReparacion = (TipoReparacion) cmbTipoReparacion.getSelectedItem();
        int idTipoReparacion = tipoReparacion.getId();
        BigDecimal importe = Utils.stringToBigDecimal(importeTextField.getText());
        boolean garantia = garantiaCheckBox.isSelected();
        String comentarios = comentariosTextArea.getText();
        
        Reparacion r = new Reparacion(idReparacion, fechaEntrada, fechaSalida, idMarca, idModelo, idTipoReparacion, importe, garantia, comentarios);
        ReparacionController.modificarReparacion(r);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        importeLabel = new javax.swing.JLabel();
        direccionTextField = new javax.swing.JTextField();
        importeTextField = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
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
        modeloLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        garantiaCheckBox = new javax.swing.JCheckBox();
        marcaLabel = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("DETALLES REPARACION");
        setResizable(false);

        importeLabel.setText("Importe:");

        direccionTextField.setEditable(false);

        importeTextField.setEditable(false);

        jLabel3.setText("Reparación:");

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

        modeloLabel.setText("Modelo:");

        jLabel2.setText("Dirección:");

        garantiaCheckBox.setText("Garantía");

        marcaLabel.setText("Marca:");

        jButton1.setText("Eliminar Reparación");

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
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator2)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(340, 340, 340)
                                        .addComponent(comentariosLabel))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(43, 43, 43)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(77, 77, 77)
                                                .addComponent(modificarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(clienteLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(direccionTextField)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(marcaLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(entradaLabel)
                                .addGap(13, 13, 13)))
                        .addComponent(fechaEntradaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(modeloLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(salidaLabel)
                                .addGap(2, 2, 2)))
                        .addComponent(fechaSalidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(importeLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(garantiaCheckBox))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jLabel3)
                                .addGap(161, 161, 161))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(clienteLabel)
                    .addComponent(clienteTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(direccionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(telefonoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(entradaLabel)
                    .addComponent(fechaEntradaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(salidaLabel)
                    .addComponent(fechaSalidaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(importeLabel)
                    .addComponent(importeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(garantiaCheckBox))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(marcaLabel)
                    .addComponent(modeloLabel)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comentariosLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(modificarButton)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addGap(140, 140, 140))
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clienteLabel;
    private javax.swing.JTextField clienteTextField;
    private javax.swing.JLabel comentariosLabel;
    private javax.swing.JTextArea comentariosTextArea;
    private javax.swing.JTextField direccionTextField;
    private javax.swing.JLabel entradaLabel;
    private javax.swing.JTextField fechaEntradaTextField;
    private javax.swing.JTextField fechaSalidaTextField;
    private javax.swing.JCheckBox garantiaCheckBox;
    private javax.swing.JLabel importeLabel;
    private javax.swing.JTextField importeTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel marcaLabel;
    private javax.swing.JLabel modeloLabel;
    private javax.swing.JButton modificarButton;
    private javax.swing.JLabel salidaLabel;
    private javax.swing.JTextField telefonoTextField;
    // End of variables declaration//GEN-END:variables
}
