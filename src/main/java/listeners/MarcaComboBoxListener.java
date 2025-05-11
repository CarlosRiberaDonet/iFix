/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package listeners;

import controller.MarcaModeloController;
import dao.MarcaModeloDao;
import entity.Marca;
import entity.Modelo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;

/**
 *
 * @author Carlos Ribera
 */
public class MarcaComboBoxListener implements ActionListener{
    
    private final JComboBox<String> marcaComboBox;
    private final JComboBox<String> modeloComboBox;
    
    public MarcaComboBoxListener(JComboBox<String> marcaComboBox, JComboBox<String> modeloComboBox) {
        this.marcaComboBox = marcaComboBox;
        this.modeloComboBox = modeloComboBox;
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {
        String seleccion = (String) marcaComboBox.getSelectedItem();

        if ("OTROS".equalsIgnoreCase(seleccion)) {
            modeloComboBox.removeAllItems();
            modeloComboBox.setEditable(true);
        } else {
            modeloComboBox.setEditable(false);
            modeloComboBox.removeAllItems();

            List<Marca> marcas = MarcaModeloDao.getMarcas();
            int idMarca = 0;
            for (Marca m : marcas) {
                if (m.getMarca().equalsIgnoreCase(seleccion)) {
                    idMarca = m.getIdMarca();
                    break;
                }
            }

            MarcaModeloController modeloController = new MarcaModeloController();
            List<Modelo> modelos = modeloController.filterModelosByMarca(idMarca);
            for (Modelo m : modelos) {
                modeloComboBox.addItem(m.getModelo().toUpperCase());
            }
        }
    }
}
