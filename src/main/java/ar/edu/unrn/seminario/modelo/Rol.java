package ar.edu.unrn.seminario.modelo;

import java.util.Objects;

public class Rol {

    private Integer codigo;
    private  String nombre;
    private boolean estado;

    public Rol() {

    }

    public Rol(Integer codigo, String nombre){
        //agregar exepciones

        this.codigo=codigo;
        this.nombre=nombre;
    }

    public Rol(String nombre, boolean estado){
        //agregar exepciones

        this.nombre=nombre;
        this.estado= estado;
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

    public void activar() {
        //agregar excepcion
        this.estado=true;
    }
    public void setActivo(boolean activo) {
        this.estado = activo;
    }
    public void desactivar() {
        //agregar excepcion
        this.estado=false;
    }
    public boolean estaActivo() {
        return this.estado;
    }

    @Override
    public String toString() {
        return "Rol{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return estado == rol.estado && Objects.equals(codigo, rol.codigo) && Objects.equals(nombre, rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, nombre, estado);
    }
}
