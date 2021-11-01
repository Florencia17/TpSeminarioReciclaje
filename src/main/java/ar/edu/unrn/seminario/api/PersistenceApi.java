package ar.edu.unrn.seminario.api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ModeloException.AppException;
import ModeloException.NullException;
import ar.edu.unrn.seminario.accesos.DireccionDAOJDBC;
import ar.edu.unrn.seminario.accesos.LoginUsuarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.LoginUsuarioDao;
import ar.edu.unrn.seminario.accesos.PedidoDAOJDBC;
import ar.edu.unrn.seminario.accesos.PedidoDao;
import ar.edu.unrn.seminario.accesos.PropietarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.RecolectorDAOJDBC;
import ar.edu.unrn.seminario.accesos.RecolectorDao;
import ar.edu.unrn.seminario.accesos.ResiduosDAOJDBC;
import ar.edu.unrn.seminario.accesos.ResiduosDao;
import ar.edu.unrn.seminario.accesos.RolDAOJDBC;
import ar.edu.unrn.seminario.accesos.RolDao;
import ar.edu.unrn.seminario.accesos.UsuarioDAOJDBC;
import ar.edu.unrn.seminario.accesos.UsuarioDao;
import ar.edu.unrn.seminario.accesos.ViviendaDAOJDBC;
import ar.edu.unrn.seminario.dto.DireccionDTO;
import ar.edu.unrn.seminario.dto.PedidoDTO;
import ar.edu.unrn.seminario.dto.PropietarioDTO;
import ar.edu.unrn.seminario.dto.RecolectorDTO;
import ar.edu.unrn.seminario.dto.ResiduoDTO;
import ar.edu.unrn.seminario.dto.RolDTO;
import ar.edu.unrn.seminario.dto.TipoResiduoDTO;
import ar.edu.unrn.seminario.dto.UsuarioDTO;
import ar.edu.unrn.seminario.dto.ViviendaDTO;
import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Recolector;
import ar.edu.unrn.seminario.modelo.Residuo;
import ar.edu.unrn.seminario.modelo.Rol;
import ar.edu.unrn.seminario.modelo.TipoResiduo;
import ar.edu.unrn.seminario.modelo.Usuario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public class PersistenceApi implements IApi {

    private RolDao rolDao;
    private UsuarioDao usuarioDao;
    private PropietarioDAOJDBC propietarioDao;
    private DireccionDAOJDBC direccionDao;
    private ViviendaDAOJDBC viviendaDao;
    private LoginUsuarioDao loginDao;
    private PedidoDao pedidoDao;
    private ResiduosDao residuoDao;
    private RecolectorDao recolectorDao;

    public PersistenceApi() {
        propietarioDao = new PropietarioDAOJDBC();
        direccionDao = new DireccionDAOJDBC();
        viviendaDao = new ViviendaDAOJDBC();
        rolDao = new RolDAOJDBC();
        usuarioDao = new UsuarioDAOJDBC();
        loginDao = new LoginUsuarioDAOJDBC();
        pedidoDao = new PedidoDAOJDBC();
        residuoDao = new ResiduosDAOJDBC();
        recolectorDao = new RecolectorDAOJDBC();
    }

    @Override
    public void registrarVivienda(String nombre, String apellido, String dni, String calle, int numeroCalle,
                                  String barrio) throws NullException, AppException {

        Propietario propietario = new Propietario(nombre, apellido, dni);
        Direccion direccion = new Direccion(calle, numeroCalle, barrio);

        Vivienda vivienda = new Vivienda(propietario, direccion);
        this.viviendaDao.create(vivienda, this.propietarioDao, this.direccionDao);

    }

    @Override
    public boolean ingresarUsuario(String username, String password) throws AppException, NullException {
        boolean existe = false;

        existe = loginDao.iniciarSesion(username, password);

        return existe;

    }

    @Override
    public void registrarUsuario(String username, String password, String email, Integer codigoRol) {
        Rol rol = rolDao.find(codigoRol);
        RolDTO rolDto = new RolDTO(rol);
        Usuario usuario = new Usuario(username, password, email, rol);
        this.usuarioDao.create(usuario);

    }

    @Override
    public void registrarPedido(ViviendaDTO viviendaDto, Date fecha, List<ResiduoDTO> residuosDto, boolean vehiculo,
                                String observacion) throws NullException {
        List<Residuo> residuos = new ArrayList<>();
        Propietario propietario = new Propietario(viviendaDto.getNombrePropietario(),
                viviendaDto.getApellidopropietarioDto(), viviendaDto.getDnipropietarioDto());
        Direccion direccion = new Direccion(viviendaDto.getCalle(), viviendaDto.getNumero(), viviendaDto.getBarrio(),
                viviendaDto.getDireccion().getIdDireccion());
        Vivienda vivienda = new Vivienda(viviendaDto.getNumeroVivienda(), propietario, direccion);
        for (ResiduoDTO r : residuosDto) {
            TipoResiduo tipoResiduo = new TipoResiduo(r.getTipoResiduoDto().getId(), r.getTipoResiduoDto().getNombre(),
                    r.getTipoResiduoDto().getPuntosResiduo());
            Residuo residuo = new Residuo(tipoResiduo, r.getPeso());
            residuos.add(residuo);
        }

        Pedido pedido = new Pedido(vivienda, fecha, residuos, vehiculo, observacion);

        this.pedidoDao.create(vivienda, pedido);

    }

    public ArrayList<TipoResiduoDTO> obtenerResiduos() throws NullException {
        ArrayList<TipoResiduoDTO> residuosDto = new ArrayList<>();
        List<TipoResiduo> residuos = residuoDao.findAll();
        for (TipoResiduo r : residuos) {
            System.out.println("los puntos son de :" + r.getPuntosResiduos());
            residuosDto.add(new TipoResiduoDTO(r.getId(), r.getNombre(), r.getPuntosResiduos()));
        }
        return residuosDto;
    }

    public List<PedidoDTO> obtenerPedidos() throws SQLException, NullException {
        List<PedidoDTO> dtos = new ArrayList<>();
        List<Pedido> pedidos = pedidoDao.findAll();
        List<ResiduoDTO> residuosDto = new ArrayList<>();
        for (Pedido p : pedidos) {
            // String nombre, String apellido, String dni
            PropietarioDTO propietario = new PropietarioDTO(p.getVivienda().getPropietario().getNombre(),
                    p.getVivienda().getPropietario().getApellido(), p.getVivienda().getPropietario().getDni());

            // String calle, int numero, String barrio,int idDireccion
            DireccionDTO direccion = new DireccionDTO(p.getVivienda().getDireccion().getCalle(),
                    p.getVivienda().getDireccion().getNumero(), p.getVivienda().getDireccion().getBarrio(),
                    p.getVivienda().getDireccion().getIdDireccion());

            // int numeroVivienda,PropietarioDTO propietarioDto, DireccionDTO direccionDto
            ViviendaDTO vivienda = new ViviendaDTO(p.getVivienda().getNumeroVivienda(), propietario, direccion);

            // ViviendaDTO vivienda, LocalDate fecha, int qresiduo, boolean vehiculo, String
            // observacion, int nro_pedido

            residuosDto = p.getResiduo().stream().map(r -> {
                try {
                    return new ResiduoDTO(new TipoResiduoDTO(r.getTipoResiduo().getId(), r.getTipoResiduo().getNombre(),
                            r.getTipoResiduo().getPuntosResiduos()), r.getPeso());

                } catch (NullException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                return null;
            }).collect(Collectors.toList());

            dtos.add(new PedidoDTO(vivienda, p.getFecha(), residuosDto, p.getVehiculo(), p.getObservacion(),
                    p.getidPedidoRetiro()));
        }

        return dtos;

    }

    public ViviendaDTO obtenerVivienda(int numeroVivienda) throws NullException {

        Vivienda vivienda = this.viviendaDao.find(numeroVivienda);
        PropietarioDTO propietarioDto = new PropietarioDTO(vivienda.getPropietario().getNombre(),
                vivienda.getPropietario().getApellido(), vivienda.getPropietario().getDni());
        DireccionDTO direccionDto = new DireccionDTO(vivienda.getDireccion().getCalle(),
                vivienda.getDireccion().getNumero(), vivienda.getDireccion().getBarrio());
        ViviendaDTO viviendaDto = new ViviendaDTO(vivienda.getNumeroVivienda(), propietarioDto, direccionDto);
        return viviendaDto;
    }

    @Override
    public List<UsuarioDTO> obtenerUsuarios() {
        List<UsuarioDTO> dtos = new ArrayList<>();
        List<Usuario> usuarios = usuarioDao.findAll();
        for (Usuario u : usuarios) {
            dtos.add(
                    new UsuarioDTO(u.getNombreUsuario(), u.getContrasenia(), u.getEmail(), u.getRol(), u.estaActivo()));
        }
        return dtos;

    }

    @Override
    public List<ViviendaDTO> obtenerViviendas() throws SQLException, NullException {
        List<ViviendaDTO> dtos = new ArrayList<>();
        List<Vivienda> viviendas = viviendaDao.findAll();
        for (Vivienda v : viviendas) {
            PropietarioDTO propietarioDto = new PropietarioDTO(v.getPropietario().getNombre(),
                    v.getPropietario().getApellido(), v.getPropietario().getDni());
            DireccionDTO direccionDto = new DireccionDTO(v.getDireccion().getCalle(), v.getDireccion().getNumero(),
                    v.getDireccion().getBarrio());
            dtos.add(new ViviendaDTO(v.getNumeroVivienda(), propietarioDto, direccionDto));
        }
        return dtos;
    }

    @Override
    public UsuarioDTO obtenerUsuario(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void eliminarUsuario(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<RolDTO> obtenerRoles() {
        List<Rol> roles = rolDao.findAll();
        List<RolDTO> rolesDTO = new ArrayList<>(0);
        for (Rol rol : roles) {
            rolesDTO.add(new RolDTO(rol.getNombre(), rol.getCodigo(), rol.estaActivo()));
        }
        return rolesDTO;

    }

    @Override
    public List<RolDTO> obtenerRolesActivos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RolDTO obtenerRolPorCodigo(Integer codigo) {
        Rol rol = rolDao.find(codigo);
        RolDTO rolDTO = new RolDTO(rol.getNombre(), rol.getCodigo(), rol.estaActivo());
        return rolDTO;

    }

    @Override
    public void activarRol(Integer codigo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void desactivarRol(Integer codigo) {
        // TODO Auto-generated method stub

    }

    @Override
    public void activarUsuario(String username) {
        // TODO Auto-generated method stub

    }

    @Override
    public void desactivarUsuario(String username) {
        // TODO Auto-generated method stub

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

    @Override
    public void guardarRol(String nombre, boolean estado) {
        Rol rol = new Rol(nombre, estado);
        this.rolDao.create(rol);

    }

    @Override
    public List<ViviendaDTO> obtenerFiltradoApellido(String apellido) throws SQLException, NullException {

        List<Vivienda> viviendas = viviendaDao.findAll();

        List<ViviendaDTO> viviendaDto = viviendas.stream()
                .filter(v -> apellido.isEmpty() || v.getPropietario().getApellido().equals(apellido)).map((v) -> {
                            ViviendaDTO viviendaDtoc = null;
                            try {
                                viviendaDtoc = new ViviendaDTO(
                                        new PropietarioDTO(v.getPropietario().getNombre(), v.getPropietario().getApellido(),
                                                v.getPropietario().getDni()),
                                        new DireccionDTO(v.getDireccion().getCalle(), v.getDireccion().getNumero(),
                                                v.getDireccion().getBarrio()));
                            } catch (NullException e) {

                                e.getMessage();
                            }
                            return viviendaDtoc;

                        }

                ).collect(Collectors.toList());

        return viviendaDto;
    }

    @Override
    public void registrarRecolector(String nombre, String apellido, String email, String dni, String legajo)
            throws NullException, AppException {

        Recolector recolector= new Recolector(nombre, apellido, email, dni, legajo);
        this.recolectorDao.create(recolector);

    }




    @Override
    public List<RecolectorDTO> obtenerRecolector() throws SQLException, NullException {

        List<Recolector> recolectores = recolectorDao.findAll();
        List<RecolectorDTO> dtos = new ArrayList<>();

        for(Recolector r: recolectores) {
            dtos.add(new RecolectorDTO(r.obtenerNombre(), r.obtenerApellido(),r.obtenerEmail(),r.obtenerDni(), r.obtenerLegajo()));
        }
        return dtos;
    }



    @Override
    public List<RecolectorDTO> obtenerFiltradoLegajo(String legajo) throws SQLException, NullException {


        List <Recolector> recolectores = recolectorDao.findAll();
        List <RecolectorDTO> recolectorDto = recolectores.stream().filter(r->legajo.isEmpty() || r.obtenerLegajo().equals(legajo))
                .map((r)->{
                    try {
                        return new RecolectorDTO(r.obtenerNombre(),r.obtenerApellido(),r.obtenerEmail(),r.obtenerDni(), r.obtenerLegajo());
                    } catch (NullException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    return null;
                }).collect(Collectors.toList());

        return recolectorDto;
    }

    public List<PropietarioDTO> obtenerPropietarios() throws NullException{
        List<Propietario> propietarios = propietarioDao.findAll();
        List<PropietarioDTO> dtos = new ArrayList<>();

        for(Propietario p: propietarios) {
            dtos.add(new PropietarioDTO(p.getNombre(), p.getApellido(), p.getDni()));
        }
        return dtos;

    }

    public PropietarioDTO obtenerPropietario(String numeroDNI) throws NullException, AppException {

       Propietario propietario = this.propietarioDao.find(numeroDNI);
        PropietarioDTO propietarioDto = new PropietarioDTO(propietario.getNombre(),
                propietario.getApellido(), propietario.getDni());

        return propietarioDto;
    }

    public void modificarPropietario( String nombre, String apellido, String dni) throws NullException, AppException {

        try {

            this.modificarPropietario(nombre, apellido, dni);

            Propietario p=propietarioDao.find(dni);
            p.setNombre(nombre);
            p.setApellido(apellido);
            p.setDni(dni);

           propietarioDao.update(p);

        }catch(NullException e) {
            //mensaje
        }catch (AppException a){
            //mensaje
        }

    }

    public void modificarDireccion(String calle, int numero, String barrio, int id) throws AppException {
        this.modificarDireccion(calle, numero, barrio, id);

        Direccion d= direccionDao.find(id);
        d.setCalle(calle);
        d.setNumero(numero);
        d.setBarrio(barrio);

        direccionDao.update(d);
    }


}