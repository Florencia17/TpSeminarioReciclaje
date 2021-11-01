package ar.edu.unrn.seminario.api;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.exception.StateException;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class MemoryApi implements IApi {

    private List<Vivienda> viviendas = new ArrayList<>();
    private List<Rol> roles = new ArrayList<>();
    private List<Usuario> usuarios = new ArrayList<>();

    // VIVIENDA

    @Override
    public void registrarVivienda(String nombre, String apellido, String dni, String calle, int numeroCalle,
                                  String barrio) throws NullException {
        Propietario propietario = new Propietario(nombre, apellido, dni);
        Direccion direccion = new Direccion(calle, numeroCalle, barrio);
        Vivienda vivienda = new Vivienda(propietario, direccion);

        viviendas.add(vivienda);
    }

    @Override
    public List<ViviendaDTO> obtenerViviendas() {
        List<ViviendaDTO> dtos = new ArrayList<>();
        for (Vivienda v : this.viviendas) {
            System.out.println("el dni:" + v.getPropietario().getDni());
            dtos.add(new ViviendaDTO(v.getPropietario(), v.getDireccion()));
        }
        return dtos;
    }
    /*
     * @Override public void emilinarVivienda(String direccion) {
     *
     * }
     */

    // USUARIOS
    @Override
    public void registrarUsuario(String username, String password, String email, String nombre, Integer rol) {

    }

    @Override
    public UsuarioDTO obtenerUsuario(String username) {
        return null;
    }

    @Override
    public void eliminarUsuario(String username) {

    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        return null;
    }

    // ROLES
    @Override
    public List<RolDTO> obtenerRoles() {
        List<RolDTO> dtos = new ArrayList<>();
        for (Rol r : roles) {
            dtos.add(new RolDTO(r.getNombre(), r.estaActivo()));
        }
        return dtos;
    }

    @Override
    public List<RolDTO> obtenerRolesActivos() {
        return null;
    }

    @Override
    public void guardarRol(String nombre, boolean estado) {

        Rol rol = new Rol(nombre, estado);
        roles.add(rol);

    }

    @Override
    public RolDTO obtenerRolPorCodigo(Integer codigo) {
        return null;
    }

    @Override
    public void activarRol(Integer codigo) {

    }

    @Override
    public void desactivarRol(Integer codigo) {

    }

    @Override
    public ViviendaDTO obtenerVivienda() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void emilinarVivienda() {
        // TODO Auto-generated method stub

    }

    public void activarUsuario(String usuario) throws StateException {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario))
                u.activar();
            // enviar mail
            // ..
        }

    }

    @Override
    public void desactivarUsuario(String usuario) throws StateException {
        for (Usuario u : usuarios) {
            if (u.getNombreUsuario().equals(usuario))
                u.desactivar();

        }
    }

    @Override
    public List<String> obtenerFiltradoApellido(String apellido) throws SQLException, NullException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean ingresarUsuario(String username, String password) throws AppException, NullException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void registrarUsuario(String username, String password, String email, Integer codigoRol) {
        // TODO Auto-generated method stub

    }
}
