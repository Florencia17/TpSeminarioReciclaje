package ar.edu.unrn.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class ViviendaDAOJDBC {

    public void create(Vivienda vivienda, PropietarioDao propietarioDao, DireccionDao direccionDao)
            throws AppException {
        int ultimoValor = 0;
        try {
            // agregar insert de propietario y direccion
            propietarioDao.create(vivienda.getPropietario());
            direccionDao.create(vivienda.getDireccion());
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO Vivienda(idDireccion,dniPropietario) " + "VALUES (?, ?)");
            ResultSet rst = statement.executeQuery("SELECT MAX(id) AS id FROM Direccion");

            if (rst.next()) {
                ultimoValor = rst.getInt("id");
            }

            statement.setString(2, vivienda.getPropietario().getDni());
            statement.setInt(1, ultimoValor);

            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                // System.out.println("Modificando " + cantidad + " registros");
            } else {
                System.out.println("Error al actualizar");
                // TODO: disparar Exception propia
            }

        } catch (SQLException e) { // agregar AppException
            System.out.println("Error al procesar consulta");
            e.printStackTrace();
            // agregar el throw en la vista
            throw new AppException(e, e.getSQLState(), e.getMessage());

        }

        catch (Exception e) {
            System.out.println("Error al insertar una vivienda");
            throw new AppException(e, e.getCause().getMessage(), e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }
    }

    public List<Vivienda> findAll() throws SQLException, NullException {
        List<Vivienda> viviendas = new ArrayList<Vivienda>();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {

            sentencia = ConnectionManager.getConnection().createStatement();
            resultado = sentencia.executeQuery(
                    "SELECT Propietario.nombre,Propietario.apellido,Propietario.dni,Direccion.calle,Direccion.numero,Direccion.barrio,Vivienda.numero_vivienda\n"
                            + "FROM Vivienda JOIN Propietario on Propietario.dni=Vivienda.dniPropietario join Direccion on Vivienda.idDireccion=Direccion.id");

            while (resultado.next()) {
                Propietario propietario = new Propietario(resultado.getString("nombre"),
                        resultado.getString("apellido"), resultado.getString("dni"));
                Direccion direccion = new Direccion(resultado.getString("calle"), resultado.getInt("numero"),
                        resultado.getString("barrio"));
                Vivienda vivienda = new Vivienda(resultado.getInt("numero_vivienda"), propietario, direccion);

                viviendas.add(vivienda);
            }
        } catch (SQLException e) {
            System.out.println("Error de mySql\n" + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.disconnect();
        }

        return viviendas;
    }

    public Vivienda find(int numeroVivienda) {
        Statement sentencia = null;
        Vivienda vivienda = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement resultado = conn.prepareStatement(
                    "SELECT * FROM `Vivienda`  JOIN Propietario on Propietario.dni=Vivienda.dniPropietario join Direccion on Vivienda.idDireccion=Direccion.id where numero_vivienda=?");

            resultado.setInt(1, numeroVivienda);

            ResultSet rs = resultado.executeQuery();
            if (rs.next()) {
                Propietario propietario = new Propietario(rs.getString("nombre"), rs.getString("apellido"),
                        rs.getString("dni"));
                Direccion direccion = new Direccion(rs.getString("calle"), rs.getInt("numero"), rs.getString("barrio"),
                        rs.getInt("id"));
                vivienda = new Vivienda(rs.getInt("numero_vivienda"), propietario, direccion);

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

        return vivienda;
    }

}
