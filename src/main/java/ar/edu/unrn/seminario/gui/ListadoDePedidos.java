package ar.edu.unrn.seminario.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import ModeloException.NullException;
import ar.edu.unrn.seminario.api.IApi;
import ar.edu.unrn.seminario.dto.PedidoDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;

public class ListadoDePedidos extends JFrame {

    private JPanel contentPane;
    private JTable table;
    DefaultTableModel modelo;
    IApi api;
    private JTextField textField;
    private JTextField textField_1;
    private JTable table_1;
    private JTextField textField_2;
    private JButton btnNewButton;

    /**
     * Launch the application.
     *
     * public static void main(String[] args) { EventQueue.invokeLater(new
     * Runnable() { public void run() { try { ListadoDePedidos frame = new
     * ListadoDePedidos(); frame.setVisible(true); } catch (Exception e) {
     * e.printStackTrace(); } } }); }
     *
     * @throws NullException
     * @throws SQLException
     *
     *
     */
    public ListadoDePedidos(IApi api) throws SQLException, NullException {

        this.api = api;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1065, 325);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        ResourceBundle labels = ResourceBundle.getBundle("labels", new Locale("es"));
        contentPane.setLayout(null);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 0, 764, 270);
        contentPane.add(scrollPane);

        table_1 = new JTable();

        String[] titulos = { labels.getString("listado.pedidos.titulos.id.vivienda"),
                labels.getString("listado.pedidos.titulos.nro.pedido"),
                labels.getString("listado.pedidos.titulos.fecha"), labels.getString("listado.pedidos.titulos.vehiculo"),
                labels.getString("listado.pedidos.titulos.observacion"),
                labels.getString("listado.pedidos.titulos.residuo") };
        modelo = new DefaultTableModel(new Object[][] {}, titulos);
        modelo.isCellEditable(5, 5);
        List<PedidoDTO> pedidos = api.obtenerPedidos();

        for (PedidoDTO p : pedidos) {
            String residuo = "";
            for (ResiduoDTO r : p.getResiduoDto()) {
                System.out.println("el nombre es:" + r.getTipoResiduoDto().getNombre() + "el id es:"
                        + r.getTipoResiduoDto().getId());
                residuo += r.getTipoResiduoDto().toString();
            }
            modelo.addRow(new Object[] { p.obtenerVivienda().getNumeroVivienda(), p.getNumeroPedido(), p.getFecha(),
                    p.getVehiculo(), p.getObservacion(), residuo });

        }

        table_1.setModel(modelo);

        scrollPane.setViewportView(table_1);

        JPanel panel = new JPanel();
        panel.setBounds(878, 0, 175, 300);
        contentPane.add(panel);
        panel.setLayout(null);

        textField_2 = new JTextField();
        textField_2.setBounds(37, 58, 86, 20);
        panel.add(textField_2);
        textField_2.setColumns(10);

        btnNewButton = new JButton("Mostrar residuo");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                VentanaResiduos ventana = new VentanaResiduos(api);
                ventana.setVisible(true);
            }
        });
        btnNewButton.setBounds(37, 107, 89, 23);
        panel.add(btnNewButton);

    }

    private void reloadGrid() throws SQLException, NullException {

        // Obtiene el model del table
        DefaultTableModel modelo = (DefaultTableModel) table_1.getModel();

        // Obtiene la lista de usuarios a mostrar
        List<PedidoDTO> pedidos = api.obtenerPedidos();
        try {
            pedidos = api.obtenerPedidos();
            // Resetea el model
            modelo.setRowCount(0);

            // Agrega los usuarios en el model
            for (PedidoDTO p : pedidos) {
                modelo.addRow(new Object[] { p.obtenerVivienda().getNumero(), p.getFecha(), p.getResiduoDto(),
                        p.getVehiculo(), p.getObservacion(), p.getObservacion() });
            }
        } catch (SQLException | NullException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}

