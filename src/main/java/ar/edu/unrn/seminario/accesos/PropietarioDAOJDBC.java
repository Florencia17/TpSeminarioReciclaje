package ar.edu.unrn.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import ModeloException.AppException;
import ar.edu.unrn.seminario.modelo.Propietario;

public class PropietarioDAOJDBC implements PropietarioDao{
    public void create(Propietario propietario) {
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO Propietario(nombre, apellido, dni) "
                            + "VALUES (?, ?, ?)");



            statement.setString(1, propietario.getNombre());
            statement.setString(2, propietario.getApellido());
            statement.setString(3, propietario.getDni());



            int cantidad = statement.executeUpdate();
            if (cantidad > 0) {
                // System.out.println("Modificando " + cantidad + " registros");
            } else {
                System.out.println("Error al actualizar");
                // TODO: disparar Exception propia
            }

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            e.printStackTrace();
            // TODO: disparar Exception propia
        } catch (Exception e) {
            System.out.println("Error al insertar un usuario");
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }

    }

    @Override
    public void update(Propietario propietario) throws AppException {


        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement consulta=conn.prepareStatement("UPDATE propietarios SET nombre = ?, apellido = ?, dni = ? WHERE dni = ?");
            consulta.setString(1,propietario.getNombre());
            consulta.setString(2,propietario.getApellido());
            consulta.setString(3, propietario.getDni());
            if(consulta.executeUpdate()>0) {

            }
            consulta.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");

            throw new AppException(e, e.getSQLState(), e.getMessage());
        } catch (Exception e) {

            throw new AppException(e, e.getCause().getMessage(), e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }


    }

    @Override
    public void remove(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Propietario propietario) {
        // TODO Auto-generated method stub

    }

    @Override
    public Propietario find(String dni) throws AppException {
        Propietario propietario = null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT propietario.dni,  propietario.nombre,propietario.apellido"
                            +" WHERE propietario.dni = ?");

            statement.setString(1, dni);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                propietario = new Propietario(rs.getString("dni"), rs.getString("propietario.nombre"),rs.getString("propietario.apellido"));
            }

        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");

            throw new AppException(e, e.getSQLState(), e.getMessage());
        } catch (Exception e) {

            throw new AppException(e, e.getCause().getMessage(), e.getMessage());
        } finally {
            ConnectionManager.disconnect();
        }

        return propietario;
    }

    @Override
    public List<Propietario> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
}
