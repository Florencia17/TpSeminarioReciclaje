package ar.edu.unrn.seminario.dto;

import ar.edu.unrn.seminario.modelo.Rol;

public class UsuarioDTO {

    private String username;
    private String password;
    private String nombre;
    private String email;
    private RolDTO rol;
    private boolean estado;

    public UsuarioDTO(String username, String password, String email, RolDTO rol,
                      boolean estado) {
        super();
        this.username = username;
        this.password = password;

        this.email = email;
        this.rol = rol;
        this.estado = estado;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public RolDTO getRol() {
        return rol;
    }


    public void setRol(RolDTO rol) {
        this.rol = rol;
    }

    public void activar() {
        estado=true;
    }
    public void desactivar() {
        estado=false;
    }
    public boolean estaActivo() {
        return this.estado;
    }




}
