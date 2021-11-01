package ar.edu.unrn.seminario.gui;


import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.api.PersistenceApi;

public class VentanaPrincipal extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    IApi api = new PersistenceApi();
                    VentanaPrincipal frame = new VentanaPrincipal(api);
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
    public VentanaPrincipal(IApi api) {
        // ii8n
        // ResourceBundle labels=ResourceBundle.getBundle("labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        // ResourceBundle labels = ResourceBundle.getBundle("labels");
        JMenuBar menuBar = new JMenuBar();
        menuBar.setToolTipText("texto");
        setJMenuBar(menuBar);

        JMenu UsuariosNewMenu = new JMenu(labels.getString("ventana.principal.menu.usuarios"));
        menuBar.add(UsuariosNewMenu);

        JMenuItem AltaModificacionNewMenuItem = new JMenuItem(
                labels.getString("ventana.principal.menu.alta.modificacion"));
        AltaModificacionNewMenuItem.addActionListener(e -> new AltaUsuario(api).setVisible(true));

        UsuariosNewMenu.add(AltaModificacionNewMenuItem);

        JMenuItem ListadoNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.listado"));
        ListadoNewMenuItem.addActionListener(e -> new ListarUsuario(api).setVisible(true));
        /*
         * JMenuItem ListadoNewMenuItem = new
         * JMenuItem(labels.getString("ventana.principal.menu.item.listado"));
         * ListadoNewMenuItem.addActionListener(e->{ new
         * ListarUsuario(api).setVisible(true); new
         * ListarUsuario(api).setVisible(false);
         *
         *
         * });
         */
        UsuariosNewMenu.add(ListadoNewMenuItem);

        JMenu RolesNewMenu = new JMenu(labels.getString("ventana.principal.menu.rol"));
        menuBar.add(RolesNewMenu);

        JMenuItem AltaRolNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.rol"));
        AltaRolNewMenuItem.addActionListener(e -> new AltaRol(api).setVisible(true));

        RolesNewMenu.add(AltaRolNewMenuItem);

        JMenuItem ListarNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.lista.rol"));
        ListarNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        RolesNewMenu.add(ListarNewMenuItem);

        JMenu ViviendasNewMenu = new JMenu(labels.getString("ventana.principal.menu.viviendas"));
        menuBar.add(ViviendasNewMenu);

        JMenuItem RegistrarViviendaNewMenuItem = new JMenuItem(
                labels.getString("ventana.principal.menu.item.registrar.vivienda"));
        RegistrarViviendaNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                RegistrarVivienda registrarVivienda = new RegistrarVivienda(api);
                registrarVivienda.setVisible(true);
            }
        });
        ViviendasNewMenu.add(RegistrarViviendaNewMenuItem);

        JMenuItem ListarViviendaNewMenuItem = new JMenuItem(
                labels.getString("ventana.principal.menu.item.listar.vivienda"));
        ListarViviendaNewMenuItem.addActionListener(e -> {

            ListadoVivienda listado;
            try {
                listado = new ListadoVivienda(api);
                listado.setVisible(true);
            } catch (SQLException | NullException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

        });
        ViviendasNewMenu.add(ListarViviendaNewMenuItem);



        JMenuItem ListadoPropietariosMenuItem = new JMenuItem(
                labels.getString("ventana.principal.menu.item.listado.propietarios"));
        ListadoPropietariosMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ListadoPropietarios listadoPropietarios= new ListadoPropietarios(api);

                    listadoPropietarios.setVisible(true);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (NullException ex) {
                    ex.printStackTrace();
                }
            }
        });
        ViviendasNewMenu.add(ListadoPropietariosMenuItem);

        JMenu mnNewMenu = new JMenu(labels.getString("ventana.principal.menu.item.pedidos"));
        menuBar.add(mnNewMenu);

        JMenuItem mntmNewMenuItem = new JMenuItem(labels.getString("ventana.principal.menu.item.pedido.de.retiro")); //$NON-NLS-1$
        mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ListadoDePedidos listadoPedido;
                try {
                    listadoPedido = new ListadoDePedidos(api);
                    listadoPedido.setVisible(true);
                } catch (SQLException | NullException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        mnNewMenu.add(mntmNewMenuItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JButton SalirNewButton = new JButton(labels.getString("ventana.principal.menu.item.salir"));
        SalirNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        SalirNewButton.setBounds(179, 217, 89, 23);
        contentPane.add(SalirNewButton);

    }
}
