package ar.edu.unrn.seminario.api;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.dto.*;
import ar.edu.unrn.seminario.exception.StateException;

public interface IApi {

    // VIVIENDA

    void registrarVivienda(String nombre, String apellido, String dni, String calle, int numeroCalle, String barrio)
            throws NullException, AppException;

    public ViviendaDTO obtenerVivienda(int numeroVivienda) throws NullException;

    void emilinarVivienda();

    public List<ViviendaDTO> obtenerViviendas() throws SQLException, NullException; // recupera todas las viviendas

    public boolean ingresarUsuario(String username, String password) throws AppException, NullException;

    // USUARIO
    void registrarUsuario(String username, String password, String email, Integer codigoRol);

    UsuarioDTO obtenerUsuario(String username);

    void eliminarUsuario(String username);

    List<UsuarioDTO> obtenerUsuarios(); // recuperar todos los usuarios

    void activarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el
    // comportamiento de estado.

    void desactivarUsuario(String username) throws StateException; // recuperar el objeto Usuario, implementar el
    // comportamiento de estado.

    // ROLES
    List<RolDTO> obtenerRoles();

    List<RolDTO> obtenerRolesActivos();

    void guardarRol(String nombre, boolean estado); // crear el objeto de dominio â€œRolâ€�

    RolDTO obtenerRolPorCodigo(Integer codigo); // recuperar el rol almacenado

    void activarRol(Integer codigo); // recuperar el objeto Rol, implementar el comportamiento de estado.

    void desactivarRol(Integer codigo); // recuperar el objeto Rol, imp

    List<ViviendaDTO> obtenerFiltradoApellido(String apellido) throws SQLException, NullException;

    ViviendaDTO obtenerVivienda();

    List<PedidoDTO> obtenerPedidos() throws SQLException, NullException;

    ArrayList<TipoResiduoDTO> obtenerResiduos() throws NullException;

    void registrarPedido(ViviendaDTO viviendaDto, Date fecha, List<ResiduoDTO> residuosDto, boolean vehiculo,
                         String observacion) throws NullException;

    //RECOLECTORES

    void registrarRecolector(String nombre, String apellido, String email, String dni, String legajo)throws NullException, AppException;
    public List<RecolectorDTO> obtenerRecolector()throws SQLException, NullException;
    public List<RecolectorDTO> obtenerFiltradoLegajo(String legajo)throws SQLException, NullException;

    List<PropietarioDTO> obtenerPropietarios() throws NullException;
    PropietarioDTO obtenerPropietario(String numeroDNI) throws NullException, AppException;
    void modificarPropietario( String nombre, String apellido, String dni) throws NullException, AppException;

    void modificarDireccion(String calle, int numero, String barrio, int idDireccion) throws AppException;
}
