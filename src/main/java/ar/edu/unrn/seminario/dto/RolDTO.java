package ar.edu.unrn.seminario.dto;


import ar.edu.unrn.seminario.modelo.Rol;

public class RolDTO {
    private Integer codigo;
    private String nombre;
    private boolean estado;

    public RolDTO(String nombre, Integer codigo) {

        this.codigo = codigo;
        this.nombre = nombre;
    }
    public RolDTO(String nombre, Integer codigo, boolean estado) {
        this(nombre, codigo);
        this.estado=estado;
    }

    public RolDTO(String nombre, boolean estado) {

        this.nombre = nombre;
        this.estado= estado;
    }
    public RolDTO(Rol rol) {

        this.codigo=rol.getCodigo();
        this.nombre=rol.getNombre();
        this.estado=rol.estaActivo();
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public boolean estaActivo() {
        return this.estado;
    }
    public void activar() {
        this.estado=true;
    }
    public void desactivar() {
        this.estado=false;
    }
    public String toString() {
        return (this.nombre);
    }
    public void setEstado() {
        if(this.estado) {
            this.desactivar();
        }else {
            this.activar();
        }
    }
}

