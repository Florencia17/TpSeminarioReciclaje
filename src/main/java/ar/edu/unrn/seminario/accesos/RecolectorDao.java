package ar.edu.unrn.seminario.accesos;


import java.sql.SQLException;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.Recolector;

public interface RecolectorDao {

    void create (Recolector recolector);

    void update (Recolector recolector);

    void remove(Long id);

    void remove (Recolector recolector);

    Recolector find(String legajo) throws AppException;

    List<Recolector> findAll()throws SQLException, NullException;
}
