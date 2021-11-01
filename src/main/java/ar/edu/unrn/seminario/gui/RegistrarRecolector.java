package ar.edu.unrn.seminario.gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;
import ModeloException.AppException;
import ModeloException.NullException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class RegistrarRecolector extends JFrame {

    private JPanel contentPane;
    private JTextField nombreField;
    private JTextField apellidoField;
    private JTextField emailField;
    private JTextField dniField;
    private JTextField legajoField;
    private IApi api;

    /**
     * Launch the application.
     */

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IApi api = new PersistenceApi();
                    RegistrarRecolector frame = new RegistrarRecolector(api);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public RegistrarRecolector(IApi api) {
        this.api= api;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        //ResourceBundle labels = ResourceBundle.getBundle("labels");
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel tituloLabel = new JLabel("Registrar un nuevo recolector:");
        tituloLabel.setBounds(10, 11, 149, 14);
        contentPane.add(tituloLabel);

        JLabel nombreLabel = new JLabel(labels.getString("registro.recolector.label.nombre"));
        nombreLabel.setBounds(25, 58, 60, 14);
        contentPane.add(nombreLabel);

        JLabel apellidoLabel = new JLabel(labels.getString("registro.recolector.label.apellido"));
        apellidoLabel.setBounds(25, 98, 50, 14);
        contentPane.add(apellidoLabel);

        JLabel emailLabel = new JLabel(labels.getString("registro.recolector.label.email"));
        emailLabel.setBounds(85, 138, 46, 14);
        contentPane.add(emailLabel);

        JButton registrarButton = new JButton(labels.getString("registro.recolector.button.registrar"));
        registrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                try {
                    api.registrarRecolector(nombreField.getText(), apellidoField.getText(), emailField.getText(),
                            dniField.getText(), legajoField.getText());
                    JOptionPane.showMessageDialog(null, "Recolector registrado con exito!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    //setVisible(false);
                    //dispose();
                }catch(NullException e1){
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

                catch (AppException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }

            }});

        registrarButton.setBounds(88, 227, 89, 23);
        contentPane.add(registrarButton);

        JButton cancelarButton = new JButton(labels.getString("registro.recolector.button.cancelar"));
        cancelarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        cancelarButton.setBounds(237, 227, 89, 23);
        contentPane.add(cancelarButton);

        nombreField = new JTextField();
        nombreField.setBounds(85, 55, 86, 20);
        contentPane.add(nombreField);
        nombreField.setColumns(10);

        apellidoField = new JTextField();
        apellidoField.setBounds(85, 95, 86, 20);
        contentPane.add(apellidoField);
        apellidoField.setColumns(10);

        emailField = new JTextField();
        emailField.setBounds(155, 135, 86, 20);
        contentPane.add(emailField);
        emailField.setColumns(10);

        JLabel dniLabel = new JLabel(labels.getString("registro.recolector.label.dni"));
        dniLabel.setBounds(202, 58, 46, 14);
        contentPane.add(dniLabel);

        dniField = new JTextField();
        dniField.setBounds(276, 55, 86, 20);
        contentPane.add(dniField);
        dniField.setColumns(10);

        JLabel legajoLabel = new JLabel(labels.getString("registro.recolector.label.legajo"));
        legajoLabel.setBounds(202, 98, 46, 14);
        contentPane.add(legajoLabel);

        legajoField = new JTextField();
        legajoField.setBounds(276, 95, 86, 20);
        contentPane.add(legajoField);
        legajoField.setColumns(10);
    }
}
