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
import ar.edu.unrn.seminario.modelo.Recolector;

public class RecolectorDAOJDBC implements RecolectorDao {

    @Override
    public void create(Recolector recolector) {
        // TODO Auto-generated method stub
        try {
            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("INSERT INTO recolector(nombre, apellido, email, dni, legajo)" +
                            "VALUES(?,?,?,?,?)");

            statement.setString(1, recolector.obtenerNombre());
            statement.setString(2, recolector.obtenerApellido());
            statement.setString(3, recolector.obtenerEmail());
            statement.setString(4, recolector.obtenerDni());
            statement.setString(5, recolector.obtenerLegajo());

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
            System.out.println("Error al insertar un recolector");
            // TODO: disparar Exception propia
        } finally {
            ConnectionManager.disconnect();
        }

    }

    @Override
    public void update(Recolector recolector) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void remove(Recolector recolector) {
        // TODO Auto-generated method stub

    }

    @Override
    public Recolector find(String legajo) throws AppException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Recolector> findAll() throws SQLException, NullException {
        // TODO Auto-generated method stub
        List<Recolector> recolectores= new ArrayList<Recolector>();
        Statement sentencia= null;
        ResultSet resultado= null;

        try {
            sentencia = ConnectionManager.getConnection().createStatement();
            resultado = sentencia.executeQuery("select r.nombre, r.apellido, r.email, r.dni, r.legajo from recolector r");
            while(resultado.next()) {
                Recolector recolector= new Recolector(resultado.getString(1),
                        resultado.getString(2),
                        resultado.getString(3),
                        resultado.getString(4),
                        resultado.getString(5));

                recolectores.add(recolector);

            }

        } catch (SQLException e) {
            System.out.println("Error de mySql\n" + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.disconnect();
        }

        return recolectores;

    }

}
