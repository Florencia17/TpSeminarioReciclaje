package ar.edu.unrn.seminario.accesos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public class ResiduosDAOJDBC implements ResiduosDao {

    public ArrayList<TipoResiduo> findAll() throws NullException {
        ArrayList<TipoResiduo> tipoResiduo = new ArrayList<TipoResiduo>();
        Statement sentencia = null;
        ResultSet resultado = null;
        try {

            sentencia = ConnectionManager.getConnection().createStatement();
            resultado = sentencia.executeQuery("select id,tipo_residuo,puntos_kilo from Tipo_residuo");

            while (resultado.next()) {

                TipoResiduo residuo = new TipoResiduo(resultado.getInt("id"), resultado.getString("tipo_residuo"),
                        resultado.getInt("puntos_kilo"));
                tipoResiduo.add(residuo);
            }
        } catch (SQLException e) {
            System.out.println("Error de mySql\n" + e.toString());
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            ConnectionManager.disconnect();
        }

        return tipoResiduo;
    }

}
