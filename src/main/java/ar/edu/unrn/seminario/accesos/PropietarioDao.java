package ar.edu.unrn.seminario.accesos;


import java.util.List;

import ModeloException.AppException;
import ar.edu.unrn.seminario.modelo.Propietario;


public interface PropietarioDao {

    void create(Propietario propietario);

    void update(Propietario propietario) throws AppException;

    void remove(Long id);

    void remove(Propietario propietario);

    Propietario find(String dni) throws AppException;

    List<Propietario> findAll();
}
