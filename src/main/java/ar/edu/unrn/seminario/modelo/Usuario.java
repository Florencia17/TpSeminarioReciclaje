package ar.edu.unrn.seminario.modelo;


import java.util.Objects;

import ar.edu.unrn.seminario.dto.RolDTO;

public class Usuario {
    private String username;
    private String email;
    private String password;
    private RolDTO rol;
    private boolean estado;



    public Usuario(String username, String password, String email,
                   RolDTO rol){
        //agregar excepciones
        this.username= username;
        this.password=password;
        this.email= email;
        this.rol= rol;


    }

    public String getNombreUsuario() {
        return username;
    }

    public String getContrasenia() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public RolDTO getRol() {
        return this.rol;
    }

    public void activar() {
        //agregar excepcion
        estado=true;
    }
    public void desactivar() {
        //agregar excepcion
        estado=false;
    }
    public boolean estaActivo() {
        return this.estado;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombreUsuario='" + username + '\'' +
                ", email='" + email + '\'' +
                ", contrasenia='" + password + '\'' +
                ", rol=" + rol +
                ", estado=" + estado +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return estado == usuario.estado && Objects.equals(username, usuario.username) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(rol, usuario.rol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, email, password, rol, estado);
    }
}
