package ar.edu.unrn.seminario.gui;

import java.awt.Button;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.TipoResiduoDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.herramienta.Fecha;

public class RealizarPedido extends JFrame {

    private JPanel contentPane;
    private JTextField observacionestextField_4;
    private JTextField textField;
    private ViviendaDTO vivienda;
    DefaultTableModel modelo;
    private JTable table;

    /**
     * Launch the application.
     */
    /*
     * public static void main(String[] args) { EventQueue.invokeLater(new
     * Runnable() { public void run() { try { RealizarPedido frame = new
     * RealizarPedido(IApi api); frame.setVisible(true); } catch (Exception e) {
     * e.printStackTrace(); } } }); }
     */
    /**
     * Create the frame.
     */
    public RealizarPedido(IApi api, ViviendaDTO viviendaDto) {
        this.vivienda = viviendaDto;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 926, 315);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        JLabel lblNewLabel = new JLabel(labels.getString("realizarpedido.titulo.label"));
        lblNewLabel.setToolTipText("sadasd");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 11));
        lblNewLabel.setBounds(10, 0, 524, 46);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel(labels.getString("realizarpedido.numerovivienda"));
        lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 20));
        lblNewLabel_1.setBounds(20, 41, 342, 67);
        contentPane.add(lblNewLabel_1);

        JLabel kilaje = new JLabel(labels.getString("realizarpedido.kilo"));
        kilaje.setBounds(456, 127, 218, 16);
        contentPane.add(kilaje);
        kilaje.setVisible(false);

        JCheckBox vehiculoNewCheckBox = new JCheckBox(labels.getString("realizarpedido.vehiculo"));
        vehiculoNewCheckBox.setBounds(18, 94, 300, 23);
        contentPane.add(vehiculoNewCheckBox);

        JLabel lblNewLabel_6 = new JLabel(labels.getString("realizarpedido.observacion"));
        lblNewLabel_6.setBounds(12, 165, 116, 14);
        contentPane.add(lblNewLabel_6);

        observacionestextField_4 = new JTextField();
        observacionestextField_4.setBounds(132, 146, 158, 54);
        contentPane.add(observacionestextField_4);
        observacionestextField_4.setColumns(10);
        JLabel label = new JLabel("");
        label.setFont(new Font("Dialog", Font.BOLD, 20));
        label.setBounds(364, 53, 104, 46);
        contentPane.add(label);

        textField = new JTextField();
        textField.setBounds(466, 155, 114, 19);
        contentPane.add(textField);
        textField.setColumns(10);
        textField.setVisible(false);

        JButton cancelarNewButton_1 = new JButton(labels.getString("realizarpedido.botton.cancelar"));
        cancelarNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        cancelarNewButton_1.setBounds(207, 256, 126, 23);
        contentPane.add(cancelarNewButton_1);

        Button button = new Button("agregar ");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            }
        });
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(692, 56, 198, 189);
        contentPane.add(scrollPane);

        table = new JTable();
        scrollPane.setColumnHeaderView(table);
        String[] titulos = { labels.getString("realizarpedido.titulo.table.tipo"),
                labels.getString("realizarpedido.titulo.table.numero"),
                labels.getString("realizarpedido.titulo.table.kilo"),
                labels.getString("realizarpedido.titulo.table.puntos") };

        modelo = new DefaultTableModel(new Object[][] {}, titulos);
        ArrayList<TipoResiduoDTO> tipoResiduos;
        try {
            tipoResiduos = api.obtenerResiduos();
            for (TipoResiduoDTO r : tipoResiduos) {

                modelo.addRow(new Object[] { r.getId(), r.getNombre(), null, r.getPuntosResiduo() });
            }
        } catch (NullException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Agrega los usuarios en el model

        table.setModel(modelo);
        scrollPane.setViewportView(table);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int tablaElegida = table.getSelectedRow();

                System.out.println("tablaElegida es:" + tablaElegida);

                String nombreResiduo = (String) table.getValueAt(tablaElegida, 1);

                textField.setVisible(true);

                kilaje.setVisible(true);
                kilaje.setText(labels.getString("realizarpedido.kilo"));
                String nombreLabelKilo = kilaje.getText().concat(nombreResiduo + "?");
                kilaje.setText(nombreLabelKilo);

            }
        });

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                try {
                    System.out.println(textField.getText());

                    table.setValueAt(Integer.parseInt(textField.getText()), table.getSelectedRow(), 2);

                    // residuoDto.add(new ResiduoDTO(table.get))

                    // residuo = new ResiduoDTO(textField_1.getText(),
                    // Integer.parseInt(textField.getText()));

                    // residuos.add(residuo);
                    // list.add(textField_1.getText());
                    // list.add(textField.getText());
                } catch (NumberFormatException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        JButton pedidoNewButton = new JButton(labels.getString("realizarpedido.botton.realizarpedido"));

        pedidoNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {

                List<ResiduoDTO> residuosDto = new ArrayList<>();

                try {

                    for (int j = 0; j < table.getColumnCount(); j++) {

                        if (table.getValueAt(j, 2) != null) {
                            int idTipoResiduo = (int) table.getValueAt(j, 0);

                            String nombreTipo = (String) table.getValueAt(j, 1);
                            int peso = (int) table.getValueAt(j, 2);
                            int puntos = (int) table.getValueAt(j, 3);
                            residuosDto
                                    .add(new ResiduoDTO(new TipoResiduoDTO(idTipoResiduo, nombreTipo, puntos), peso));

                        }
                    }

                    if (residuosDto.size() == 0) {
                        throw new NullException("No se agrego ningun residuo");
                    } else {
                        api.registrarPedido(vivienda, Fecha.fechaHoy(), residuosDto, vehiculoNewCheckBox.isSelected(),
                                observacionestextField_4.getText());
                        JOptionPane.showMessageDialog(null, "pedido realizado con exito");
                    }

                } catch (NullException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        pedidoNewButton.setBounds(12, 256, 168, 23);
        contentPane.add(pedidoNewButton);

        String numeroVivienda = Integer.toString(viviendaDto.getNumeroVivienda());
        label.setText(numeroVivienda);
        button.setBounds(591, 256, 86, 23);
        contentPane.add(button);

    }
}
