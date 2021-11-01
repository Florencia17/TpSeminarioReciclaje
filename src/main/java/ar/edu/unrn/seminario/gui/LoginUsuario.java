package ar.edu.unrn.seminario.gui;


import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;

public class LoginUsuario extends JFrame {

    private JPanel contentPane;
    private JTextField ingresoContrasenia;
    private JTextField ingresoUsuario;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IApi api = new PersistenceApi();
                    LoginUsuario frame = new LoginUsuario(api);
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
    public LoginUsuario(IApi api) {
        setTitle("Ingresar cuenta");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        ingresoContrasenia = new JTextField();
        ingresoContrasenia.setBounds(172, 145, 114, 19);
        panel.add(ingresoContrasenia);
        ingresoContrasenia.setColumns(10);

        JLabel lblNewLabel = new JLabel(labels.getString("iniciar.sesion.label.password "));
        lblNewLabel.setBounds(74, 147, 70, 15);
        panel.add(lblNewLabel);

        JLabel lblUsername = new JLabel(labels.getString("iniciar.sesion.label.usuario "));
        lblUsername.setBounds(74, 81, 87, 15);
        panel.add(lblUsername);

        ingresoUsuario = new JTextField();
        ingresoUsuario.setBounds(172, 79, 114, 19);
        panel.add(ingresoUsuario);
        ingresoUsuario.setColumns(10);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 46, 440, 2);
        panel.add(separator);

        Button button = new Button(labels.getString("iniciar.sesion.titulo"));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Khmer OS System", Font.PLAIN, 20));
        button.setBackground(Color.BLACK);
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {
                    boolean existe = api.ingresarUsuario(ingresoUsuario.getText(), ingresoContrasenia.getText());
                    if (existe == true) {
                        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal(api);
                        ventanaPrincipal.setVisible(true);
                        JOptionPane.showInternalMessageDialog(null, "Se ha registrado correctamente");
                        ;
                    }
                }

                catch (NullException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    e.printStackTrace();
                }
            }
        });
        button.setBounds(194, 200, 86, 23);
        panel.add(button);

        JLabel lblRegistrarcuenta = new JLabel(labels.getString("iniciar.sesion.label.registrarse"));
        lblRegistrarcuenta.setForeground(Color.BLUE);
        lblRegistrarcuenta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                AltaUsuario altaUsuario = new AltaUsuario(api);
                altaUsuario.setVisible(true);

            }
        });
        lblRegistrarcuenta.setBounds(182, 175, 152, 19);
        panel.add(lblRegistrarcuenta);
    }
}