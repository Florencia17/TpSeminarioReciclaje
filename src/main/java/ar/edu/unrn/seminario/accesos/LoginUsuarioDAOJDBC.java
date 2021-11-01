package ar.edu.unrn.seminario.accesos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModeloException.AppException;
import ModeloException.NullException;

public class LoginUsuarioDAOJDBC implements LoginUsuarioDao {

    @Override
    public boolean iniciarSesion(String username, String password) throws NullException, AppException {

        boolean seEncuentra = false;
        try {

            Connection conn = ConnectionManager.getConnection();
            PreparedStatement statement = conn
                    .prepareStatement("SELECT * FROM cuenta " + " WHERE (username=? AND contrasena =?)");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                if (rs.getString("username").equals(username) && (rs.getString("password").equals(password))) {
                    seEncuentra = true;
                }

            }
            if (seEncuentra == false) {
                System.out.println("no se encuentra");
                throw new NullException("No se encuentra ningua cuenta con lo registrado");
            }
        } catch (SQLException e) {
            System.out.println("Error al procesar consulta");
            // TODO: disparar Exception propia
        }

        finally {
            ConnectionManager.disconnect();
        }
        return seEncuentra;
    }

}
