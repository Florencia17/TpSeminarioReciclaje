package ar.edu.unrn.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ModeloException.AppException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;

public class DireccionDAOJDBC implements DireccionDao {

    public void create(Direccion direccion) {
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO Direccion(calle, numero, barrio) "
                            + "VALUES (?, ?, ?)");

            statement.setString(1, direccion.getCalle());
            statement.setInt(2, direccion.getNumero());
            statement.setString(3, direccion.getBarrio());


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
    public Direccion find(int id) {
        Direccion direccion=null;
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn.prepareStatement(
                    "SELECT Direccion.barrio,Direccion.numero,Direccion.calle,Direccion.id "
                            + " WHERE Direccion.id = ?");

            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                direccion=new Direccion(rs.getString("barrio"),rs.getInt("numero"),rs.getString("calle"));
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

        return direccion;
    }
    @Override
    public void update(Direccion direccion) throws AppException {

        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement consulta=conn.prepareStatement("UPDATE direccion SET calle = ?, numero = ?, barrio = ? WHERE id = ?");
            consulta.setString(1, direccion.getCalle());
            consulta.setInt(2, direccion.getNumero());
            consulta.setString(3, direccion.getBarrio());
            consulta.setLong(4, direccion.getIdDireccion());
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
    public void remove(Direccion direccion) {
        // TODO Auto-generated method stub

    }



    @Override
    public List<Direccion> findAll() {
        // TODO Auto-generated method stub
        return null;
    }


}
