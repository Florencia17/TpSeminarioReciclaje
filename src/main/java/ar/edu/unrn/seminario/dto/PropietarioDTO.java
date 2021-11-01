package ar.edu.unrn.seminario.dto;


import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.Usuario;

public class PropietarioDTO {
    private String nombre;
    private String apellido;
    private String dni;
    private Usuario usuario;
    private int puntosAcumulados;
    // private PedidoRetiro pedidoRetiro;

    public PropietarioDTO(String nombre, String apellido, String dni) throws NullException {
        // agregar expeciones
        if (esDatoNulo(nombre))
            throw new NullException("nombre");
        if (esDatoNulo(apellido))
            throw new NullException("apellido");
        if (esDatoNulo(dni))
            throw new NullException("dni");
        this.apellido = apellido;
        this.dni = dni;
        this.nombre = nombre;

    }

    private boolean esDatoNulo(String dato) {
        return dato == null | dato.isEmpty();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }
}

