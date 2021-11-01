package ar.edu.unrn.seminario.accesos;

import java.util.List;

import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.TipoResiduo;

public interface ResiduosDao {

    List<TipoResiduo> findAll() throws NullException;

}
