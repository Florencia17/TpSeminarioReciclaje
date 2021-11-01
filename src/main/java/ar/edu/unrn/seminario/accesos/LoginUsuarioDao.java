package ar.edu.unrn.seminario.accesos;

import ModeloException.AppException;
import ModeloException.NullException;

public interface LoginUsuarioDao {

    boolean iniciarSesion(String username, String password) throws NullException, AppException;
}
