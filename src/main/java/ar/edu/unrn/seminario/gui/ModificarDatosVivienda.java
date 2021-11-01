package ar.edu.unrn.seminario.gui;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.DireccionDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ModificarDatosVivienda extends JFrame {

    private JPanel contentPane;
    private JTextField calleTextField;
    private JTextField numeroTextField_1;
    private JTextField barrioTextField_2;

    /**
     * Launch the application.
     */
    /*
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ModificarDatosVivienda frame = new ModificarDatosVivienda(IApi api);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }*/

    /**
     * Create the frame.
     */
    public ModificarDatosVivienda(IApi api, ViviendaDTO viviendaDTO) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));

        JLabel lblNewLabel = new JLabel(labels.getString("modificar.vivienda.selecione.datos.label.modificar"));
        lblNewLabel.setBounds(10, 11, 311, 14);
        contentPane.add(lblNewLabel);

        JCheckBox calleCheckBox = new JCheckBox(labels.getString("modificar.vivienda.calle.checkbox"));
        calleCheckBox.setBounds(6, 42, 97, 23);
        contentPane.add(calleCheckBox);

        JCheckBox numeroCheckBox_1 = new JCheckBox(labels.getString("modificar.vivienda.numero.checkbox"));
        numeroCheckBox_1.setBounds(6, 110, 97, 23);
        contentPane.add(numeroCheckBox_1);

        JCheckBox barrioCheckBox_2 = new JCheckBox(labels.getString("modificar.vivienda.barrio.checkbox"));
        barrioCheckBox_2.setBounds(6, 184, 97, 23);
        contentPane.add(barrioCheckBox_2);

        calleTextField = new JTextField();
        calleTextField.setBounds(108, 70, 86, 20);
        contentPane.add(calleTextField);
        calleTextField.setColumns(10);

        numeroTextField_1 = new JTextField();
        numeroTextField_1.setBounds(108, 147, 86, 20);
        contentPane.add(numeroTextField_1);
        numeroTextField_1.setColumns(10);

        barrioTextField_2 = new JTextField();
        barrioTextField_2.setBounds(108, 214, 86, 20);
        contentPane.add(barrioTextField_2);
        barrioTextField_2.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel(labels.getString("modificar.vivienda.nueva.calle.label"));
        lblNewLabel_1.setBounds(10, 72, 77, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(labels.getString("modificar.vivienda.nuevo.numero.label"));
        lblNewLabel_2.setBounds(10, 150, 93, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel(labels.getString("modificar.vivienda.nuevo.barrio.label"));
        lblNewLabel_3.setBounds(10, 217, 77, 14);
        contentPane.add(lblNewLabel_3);

        JButton modificarNewButton = new JButton(labels.getString("modificar.vivienda.button.modificar"));
        modificarNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                DireccionDTO direccionDTO= viviendaDTO.getDireccion();
                if (calleCheckBox.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar la calle de la vivienda '" + viviendaDTO.getCalle() + "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        direccionDTO.setCalle( calleTextField.getText());
                    }

                }

                if (numeroCheckBox_1.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar el numero de la vivienda '" + viviendaDTO.getNumero() + "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        direccionDTO.setNumero(Integer.parseInt(numeroTextField_1.getText()));
                    }

                }

                if (barrioCheckBox_2.isSelected()) {
                    int confirmar = JOptionPane.showConfirmDialog(null, "Esta seguro que desea modificar el barrio de la vivienda'" + viviendaDTO.getBarrio() + "'?", "Confirmar modificación.", JOptionPane.YES_NO_OPTION);
                    if (confirmar == JOptionPane.YES_OPTION) {

                        direccionDTO.setBarrio(barrioTextField_2.getText());
                    }

                }

                try {
                    api.modificarDireccion(direccionDTO.getCalle(), direccionDTO.getNumero(), direccionDTO.getBarrio(), direccionDTO.getIdDireccion());
                } catch (AppException a) {
                    JOptionPane.showMessageDialog(null, a.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        modificarNewButton.setBounds(10, 238, 290, 23);
        contentPane.add(modificarNewButton);

        JButton cancelarNewButton_1 = new JButton(labels.getString("modificar.vivienda.button.cancelar"));
        cancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                dispose();
            }
        });
        cancelarNewButton_1.setBounds(335, 238, 89, 23);
        contentPane.add(cancelarNewButton_1);
    }
}