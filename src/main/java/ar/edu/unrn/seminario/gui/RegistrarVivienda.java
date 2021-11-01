package ar.edu.unrn.seminario.gui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;

public class RegistrarVivienda extends JFrame {

    private JPanel contentPane;
    private JTextField nrotextField;
    private JTextField calletextField_1;
    private JTextField barriotextField_2;
    private JTextField nombretextField_3;
    private JTextField apellidotextField_4;
    private JTextField emailtextField_5;
    private JTextField dnitextField;

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) { EventQueue.invokeLater(new
     * Runnable() { public void run() { try { RegistrarVivienda frame = new
     * RegistrarVivienda(); frame.setVisible(true); } catch (Exception e) {
     * e.printStackTrace(); } } }); }
     */

    /**
     * Create the frame.
     */
    public RegistrarVivienda(IApi api) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        JLabel lblNewLabel = new JLabel(labels.getString("registro.viviendas.titulo"));
        lblNewLabel.setBounds(10, 11, 166, 14);
        contentPane.add(lblNewLabel);

        JLabel direccionNewLabel_3 = new JLabel(labels.getString("registro.viviendas.label.direccion"));
        direccionNewLabel_3.setBounds(10, 47, 86, 22);
        contentPane.add(direccionNewLabel_3);

        JLabel calleNewLabel_4 = new JLabel(labels.getString("registro.viviendas.label.calle"));
        calleNewLabel_4.setBounds(10, 80, 46, 14);
        contentPane.add(calleNewLabel_4);

        JLabel nroNewLabel_1 = new JLabel(labels.getString("registro.viviendas.label.numero"));
        nroNewLabel_1.setBounds(150, 80, 46, 14);
        contentPane.add(nroNewLabel_1);

        nrotextField = new JTextField();
        nrotextField.setBounds(178, 77, 46, 20);
        contentPane.add(nrotextField);
        nrotextField.setColumns(10);

        calletextField_1 = new JTextField();
        calletextField_1.setBounds(54, 81, 86, 20);
        contentPane.add(calletextField_1);
        calletextField_1.setColumns(10);

        JLabel barrioNewLabel_2 = new JLabel(labels.getString("registro.viviendas.label.barrio"));
        barrioNewLabel_2.setBounds(244, 80, 46, 14);
        contentPane.add(barrioNewLabel_2);

        barriotextField_2 = new JTextField();
        barriotextField_2.setBounds(280, 77, 86, 20);
        contentPane.add(barriotextField_2);
        barriotextField_2.setColumns(10);

        JLabel lblNewLabel_5 = new JLabel(labels.getString("registro.viviendas.label.datospropietario"));
        lblNewLabel_5.setBounds(10, 153, 130, 14);
        contentPane.add(lblNewLabel_5);

        JLabel nombreNewLabel_6 = new JLabel(labels.getString("registro.viviendas.label.nombre"));
        nombreNewLabel_6.setBounds(10, 178, 93, 14);
        contentPane.add(nombreNewLabel_6);

        nombretextField_3 = new JTextField();
        nombretextField_3.setBounds(79, 178, 86, 20);
        contentPane.add(nombretextField_3);
        nombretextField_3.setColumns(10);

        JLabel dniNewLabel_7 = new JLabel(labels.getString("registro.viviendas.label.dni"));
        dniNewLabel_7.setBounds(219, 178, 46, 14);
        contentPane.add(dniNewLabel_7);

        apellidotextField_4 = new JTextField();
        apellidotextField_4.setBounds(79, 206, 86, 20);
        contentPane.add(apellidotextField_4);
        apellidotextField_4.setColumns(10);

        JLabel emailNewLabel_9 = new JLabel(labels.getString("registro.viviendas.label.correo"));
        emailNewLabel_9.setBounds(219, 209, 46, 14);
        contentPane.add(emailNewLabel_9);

        emailtextField_5 = new JTextField();
        emailtextField_5.setBounds(280, 206, 86, 20);
        contentPane.add(emailtextField_5);
        emailtextField_5.setColumns(10);

        JButton btnNewButton = new JButton(labels.getString("registro.viviendas.button.aceptar"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    api.registrarVivienda(nombretextField_3.getText(), apellidotextField_4.getText(),
                            dnitextField.getText(), calletextField_1.getText(),
                            Integer.parseInt(nrotextField.getText()), barriotextField_2.getText());

                    JOptionPane.showMessageDialog(null, labels.getString("registro.viviendas.mensaje.carga.correcta"),
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                    setVisible(false);
                    dispose();
                } catch (NullException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                catch (AppException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }
        });
        btnNewButton.setBounds(201, 237, 89, 23);
        contentPane.add(btnNewButton);

        JButton btnNewButton_1 = new JButton(labels.getString("registro.viviendas.button.cancelar"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        btnNewButton_1.setBounds(300, 237, 89, 23);
        contentPane.add(btnNewButton_1);

        JLabel apellidoNewLabel_1 = new JLabel(labels.getString("registro.viviendas.label.apellido"));
        apellidoNewLabel_1.setBounds(10, 209, 46, 14);
        contentPane.add(apellidoNewLabel_1);

        dnitextField = new JTextField();
        dnitextField.setBounds(280, 175, 86, 20);
        contentPane.add(dnitextField);
        dnitextField.setColumns(10);
    }
}

