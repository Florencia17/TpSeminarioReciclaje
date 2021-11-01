package ar.edu.unrn.seminario.gui;

import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.PropietarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;

public class ListadoVivienda extends JFrame {

    private JPanel contentPane;
    private JTable table;
    DefaultTableModel modelo;
    IApi api;
    JButton activarButton;
    JButton desactivarButton;
    private JButton btnNewButton;
    private Panel panel;
    private JTextField textField;
    private JTextField textField_1;
    private boolean encontro = false;

    /**
     * Create the frame.
     *
     * @throws NullException
     * @throws SQLException
     */
    public ListadoVivienda(IApi api) throws SQLException, NullException {
        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 5, 487, 231);
        contentPane.add(scrollPane);

        table = new JTable();
        String[] titulos = { labels.getString("listado.viviendas.titulos.vivienda.numeroVivienda"),
                labels.getString("listado.viviendas.titulos.vivienda.DNI"),
                labels.getString("listado.viviendas.titulos.vivienda.NOMBRE"),
                labels.getString("listado.viviendas.titulos.vivienda.APELLIDO"),
                labels.getString("listado.viviendas.titulos.vivienda.ALTURA"),
                labels.getString("listado.viviendas.titulos.vivienda.BARRIO"),
                labels.getString("listado.viviendas.titulos.vivienda.CALLE") };

        modelo = new DefaultTableModel(new Object[][] {}, titulos);

        // Obtiene la lista de usuarios a mostrar
        List<ViviendaDTO> viviendas = api.obtenerViviendas();
        // Agrega los usuarios en el model
        for (ViviendaDTO v : viviendas) {

            modelo.addRow(new Object[] { v.getNumeroVivienda(), v.getDnipropietarioDto(), v.getNombrePropietario(),
                    v.getApellidopropietarioDto(), v.getNumero(), v.getBarrio(), v.getCalle() });
        }

        table.setModel(modelo);
        scrollPane.setViewportView(table);

        panel = new Panel();
        panel.setBounds(5, 236, 790, 35);
        contentPane.add(panel);

        btnNewButton = new JButton(labels.getString("listado.viviendas.button.cerrar"));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                dispose();
            }
        });
        panel.add(btnNewButton);

        Panel panel_1 = new Panel();
        panel_1.setBounds(527, 5, 263, 231);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel lblBuscarPorapellido = new JLabel(labels.getString("listado.viviendas.filtrar.apellido"));
        lblBuscarPorapellido.setBounds(0, 7, 102, 15);
        panel_1.add(lblBuscarPorapellido);

        textField = new JTextField();
        textField.setBounds(120, 5, 114, 19);
        panel_1.add(textField);
        textField.setColumns(10);

        JLabel lblBuscarPorBarrio = new JLabel(labels.getString("listado.viviendas.filtrar.apellido"));
        lblBuscarPorBarrio.setBounds(0, 77, 70, 15);
        panel_1.add(lblBuscarPorBarrio);

        textField_1 = new JTextField();
        textField_1.setBounds(103, 75, 114, 19);
        panel_1.add(textField_1);
        textField_1.setColumns(10);

        JButton btnFiltrar = new JButton(labels.getString("listado.viviendas.filtrar.button"));
        btnFiltrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {

                    List<ViviendaDTO> viviendasFiltradas = api.obtenerFiltradoApellido(textField.getText());
                    if (modelo.getRowCount() > 0) {
                        modelo.setRowCount(0);
                        modelo.fireTableDataChanged();
                    }
                    for (ViviendaDTO v : viviendasFiltradas) {
                        modelo.addRow(new Object[] { v.getNumeroVivienda(), v.getDnipropietarioDto(),
                                v.getNombrePropietario(), v.getApellidopropietarioDto(), v.getNumero(), v.getBarrio(),
                                v.getCalle() });
                    }
                    modelo.fireTableDataChanged();
                    if (viviendasFiltradas.size() == 0) {
                        // arreglar esto no va aca, va en el objeto constructor

                    }
                } catch (NullException e1) {
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        });
        btnFiltrar.setBounds(117, 36, 117, 25);
        panel_1.add(btnFiltrar);

        JButton btnFiltrar_1 = new JButton(labels.getString("listado.viviendas.filtrar.button"));
        btnFiltrar_1.setBounds(103, 106, 117, 25);
        panel_1.add(btnFiltrar_1);

        JButton btnNewButton_1 = new JButton(labels.getString("listado.viviendas.Pedido.button"));
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {

                    int tablaElegida = table.getSelectedRow();
                    if (tablaElegida >= 0) {
                        int numeroVivienda = (int) table.getValueAt(tablaElegida, 0);

                        ViviendaDTO viviendaDto = api.obtenerVivienda(numeroVivienda);

                        RealizarPedido realizarPedido = new RealizarPedido(api, viviendaDto);

                        realizarPedido.setVisible(true);
                        System.out.println(viviendaDto.getNumeroVivienda());
                    } else {
                        throw new Exception("Ha ocurrido un error no se a elegido ninguna vivienda");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        btnNewButton_1.setBounds(32, 159, 188, 25);
        panel_1.add(btnNewButton_1);


        JButton ModificarNewButton = new JButton("Modificar");
        ModificarNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                try {

                    int tablaElegida = table.getSelectedRow();
                    if (tablaElegida >= 0) {
                        int numeroVivienda = (int) table.getValueAt(tablaElegida, 0);

                        ViviendaDTO viviendaDto = api.obtenerVivienda(numeroVivienda);

                        ModificarDatosVivienda modificarDatosVivienda= new ModificarDatosVivienda(api, viviendaDto);
                        modificarDatosVivienda.setVisible(true);
                    } else {
                        throw new Exception("Ha ocurrido un error no se ha elegido ninguna vivienda");
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }


            }
        });
        ModificarNewButton.setBounds(29, 195, 188, 25);
        panel_1.add(ModificarNewButton);

    }


}
