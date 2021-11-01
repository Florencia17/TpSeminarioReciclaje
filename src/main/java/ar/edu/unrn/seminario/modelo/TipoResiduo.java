package ar.edu.unrn.seminario.modelo;

import ModeloException.NullException;

public class TipoResiduo {

    private String nombre;

    private int idTipo;
    private int puntosResiduos;

    public TipoResiduo(String nombre) throws NullException {

        if (esVacio(nombre)) {
            throw new NullException("El nombre es vacio");
        }

        this.nombre = nombre;

    }

    public TipoResiduo(int idTipo, String nombre, int puntosResiduos) throws NullException {
        if (esVacio(nombre)) {
            throw new NullException("El nombre es vacio");
        }

        if (esNumeroVacio(idTipo)) {
            throw new NullException("El nombre es vacio");
        }
        if (esNumeroVacio(puntosResiduos)) {
            throw new NullException("El numero es vacio");
        }
        this.puntosResiduos = puntosResiduos;
        this.idTipo = idTipo;

        this.nombre = nombre;

    }

    private boolean esVacio(String dato) {
        return dato.isEmpty() || dato == "";
    }

    private boolean esNumeroVacio(int dato) {
        return dato == 0;
    }

    public int getId() {
        return this.idTipo;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getPuntosResiduos() {
        return puntosResiduos;
    }

    public void setPuntosResiduos(int puntosResiduos) {
        this.puntosResiduos = puntosResiduos;
    }

}
