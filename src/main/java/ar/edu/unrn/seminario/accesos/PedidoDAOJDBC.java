package ar.edu.unrn.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PedidoDAOJDBC implements PedidoDao {

    public void create(Vivienda vivienda, Pedido pedido) {
        Connection conn = ConnectionManager.getConnection();
        try {
            /*
             * PreparedStatement statement2 = conn.prepareStatement(null,
             * PreparedStatement.RETURN_GENERATED_KEYS); conn.setAutoCommit(false); if
             * (pedido.getResiduo() != null) {
             *
             * statement2.setInt(1, pedido.getResiduo().getPuntosKilo());
             *
             * statement2.addBatch(); } int[] updateCounts = statement2.executeBatch(); if
             * (updateCounts.length >= 1) { System.out.println("se a creado correctamente");
             * } ResultSet rsResiduo = statement2.getGeneratedKeys(); rsResiduo.next(); int
             * idResiduo = rsResiduo.getInt(1);
             *
             * rsResiduo.close();
             *
             * if (updateCounts.length > 0) {
             * System.out.println("se a creado correctamente el resiudo"); } else {
             * System.out.println("Error al actualizar"); // TODO: disparar Exception propia
             * }
             */

            PreparedStatement statement = conn.prepareStatement(
                    "INSERT INTO Pedido_retiro(fecha_del_pedido,observacion,vehiculo_pesado,numero_vivienda) "// falta
                            // id_residuos,numero_vivienda
                            + "VALUES (?,?,?,?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setObject(1, pedido.getFecha());
            statement.setObject(2, pedido.getObservacion());
            statement.setObject(3, pedido.getVehiculo());
            statement.setObject(4, vivienda.getNumeroVivienda());
            int cantidad = statement.executeUpdate();
            ResultSet rsPedido = statement.getGeneratedKeys();
            rsPedido.next();
            int idPedido = rsPedido.getInt(1);
            if (cantidad == 1) {
                System.out.println("se a creado correctamente");
            }

            PreparedStatement statement3 = conn
                    .prepareStatement("INSERT INTO Residuo(id_pedido,id_tipo_residuo,peso) " + "VALUES (?,?,?)");
            conn.setAutoCommit(false);
            for (Residuo pedidoTipo : pedido.getResiduo()) {

                statement3.setInt(1, idPedido);
                statement3.setInt(2, pedidoTipo.getTipoResiduo().getId());
                statement3.setFloat(3, pedidoTipo.getPeso());
                statement3.addBatch();
            }
            int[] updateCountsPedidoResiduoTipo = statement3.executeBatch();
            if (updateCountsPedidoResiduoTipo.length == 1) {
                System.out.println("se a creado correctamente el tipoResiduo");
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            e.printStackTrace();
            // TODO: disparar Exception propia
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }

    }

    @Override
    public List<Pedido> findAll() throws NullException {
        List<Pedido> pedidos = new ArrayList<Pedido>();
        Statement sentencia = null;
        ResultSet rs = null;
        try {
            sentencia = ConnectionManager.getConnection().createStatement();

            rs = sentencia.executeQuery(
                    "SELECT  Vivienda.numero_vivienda,Pedido_retiro.fecha_del_pedido, Pedido_retiro.observacion,Pedido_retiro.id_pedido,  Pedido_retiro.vehiculo_pesado,\n"
                            + " Direccion.barrio,Direccion.calle,Direccion.numero,\n"
                            + "Propietario.nombre,Propietario.apellido,Propietario.dni\n"
                            + " from Vivienda join Pedido_retiro  on Vivienda.numero_vivienda=Pedido_retiro.numero_vivienda  \n"
                            + " join Direccion on Vivienda.idDireccion=Direccion.id\n"
                            + " join Propietario on Propietario.dni=Vivienda.dniPropietario");

            while (rs.next()) {
                Direccion direccion = new Direccion(rs.getString("barrio"), rs.getInt("numero"), rs.getString("calle"));

                // String nombre, String apellido, String dni
                Propietario propietario = new Propietario(rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("dni"));

                // int numeroVivienda,Propietario propietario, Direccion direccion
                Vivienda vivienda = new Vivienda(rs.getInt("numero_vivienda"), propietario, direccion);

                // String tipoResiduo,int puntosKilo

                // Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean
                // vehiculo, String observacion

                List<Residuo> residuos = new ArrayList<>();
                Statement sentenciaResiduos = ConnectionManager.getConnection().createStatement();
                ResultSet rsResiduos = sentenciaResiduos.executeQuery(
                        "SELECT Residuo.peso,Pedido_retiro.id_pedido,Pedido_retiro.fecha_del_pedido,Residuo.id,Tipo_residuo.id,Tipo_residuo.tipo_residuo,Tipo_residuo.puntos_kilo\n"
                                + "from Residuo join Pedido_retiro on Residuo.id_pedido=Pedido_retiro.id_pedido join\n"
                                + " Tipo_residuo on Residuo.id_tipo_residuo=Tipo_residuo.id\n"
                                + "where Residuo.id_pedido=" + rs.getInt("Pedido_retiro.id_pedido"));

                while (rsResiduos.next()) {
                    TipoResiduo tipoResiduo = new TipoResiduo(rsResiduos.getInt("Tipo_residuo.id"),
                            rsResiduos.getString("Tipo_residuo.tipo_residuo"),
                            rsResiduos.getInt("Tipo_residuo.puntos_kilo"));
                    Residuo residuo = new Residuo(rsResiduos.getInt("Residuo.id"), tipoResiduo,
                            rsResiduos.getFloat("peso"));
                    residuos.add(residuo);
                }

                rsResiduos.close();

                // Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean
                // vehiculo, String observacion
                // LocalTime por Date en fecha
                Pedido pedido = new Pedido(rs.getInt("Pedido_retiro.id_pedido"), vivienda,
                        rs.getDate("fecha_del_pedido"), residuos, rs.getBoolean("vehiculo_pesado"),
                        rs.getString("observacion"));

                pedidos.add(pedido);

            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error de mySql\n" + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.disconnect();
        }

        try {
            rs.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pedidos;
    }

    @Override
    public Pedido find(int idPedido, Vivienda vivienda) {// fecha_del_pedido,observacion,vehiculo_pesado,numero_vivienda,id_residuos

        Statement sentencia = null;
        ResultSet rs = null;
        Pedido pedido = null;
        try {
            sentencia = ConnectionManager.getConnection().createStatement();

            rs = sentencia.executeQuery("SELECT * FROM `Pedido_retiro` WHERE Pedido_retiro.id_pedido =" + idPedido);

            if (rs.next()) {

                List<Residuo> residuos = new ArrayList<>();
                Statement sentenciaResiduos = ConnectionManager.getConnection().createStatement();
                ResultSet rsResiduos = sentenciaResiduos.executeQuery(
                        "SELECT Residuo.id, Residuo.peso,Tipo_residuo.id,Tipo_residuo.tipo_residuo,Tipo_residuo.puntos_kilo\n"
                                + "from Residuo join Pedido_retiro on Residuo.id_pedido=Pedido_retiro.id_pedido join\n"
                                + " Tipo_residuo on Residuo.id_tipo_residuo=Tipo_residuo.id\n"
                                + " where Residuo.id_pedido=" + idPedido);
                while (rsResiduos.next()) {
                    TipoResiduo tipoResiduo = new TipoResiduo(rsResiduos.getInt("Tipo_residuo.id"),
                            rsResiduos.getString("Tipo_residuo.tipo_residuo"),
                            rsResiduos.getInt("Tipo_residuo.puntos_kilo"));
                    Residuo residuo = new Residuo(rsResiduos.getInt("Residuo.id"), tipoResiduo,
                            rsResiduos.getFloat("peso"));
                    residuos.add(residuo);
                }
                rsResiduos.close();
                // Vivienda vivienda, LocalDate fecha, ArrayList<Residuo> residuos, boolean
                // vehiculo, String observacion
                // LocalTime por Date en fecha
                pedido = new Pedido(rs.getInt("Pedido_retiro.id_pedido"), vivienda, rs.getDate("fecha_del_pedido"),
                        residuos, rs.getBoolean("vehiculo_pesado"), rs.getString("observacion"));

            }

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            // TODO: disparar Exception propia
            // throw new AppException(e, e.getSQLState(), e.getMessage());
        } catch (Exception e) {
            // TODO: disparar Exception propia
            // throw new AppException(e, e.getCause().getMessage(), e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }

        return pedido;
    }

}