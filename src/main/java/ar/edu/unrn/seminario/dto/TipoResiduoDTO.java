package ar.edu.unrn.seminario.dto;


import ModeloException.NullException;

public class TipoResiduoDTO {

    String nombre;

    int idTipo;
    int puntosResiduo;

    public TipoResiduoDTO(String nombre) throws NullException {

        if (esVacio(nombre)) {
            throw new NullException("El nombre es vacio");
        }

        this.nombre = nombre;

    }

    public TipoResiduoDTO(int idTipo, String nombre, int puntosResiduos) throws NullException {
        if (esVacio(nombre)) {
            throw new NullException("El nombre es vacio");
        }

        if (esNumeroVacio(idTipo)) {
            throw new NullException("El numero es vacio");
        }
        if (esNumeroVacio(puntosResiduos)) {
            throw new NullException("El numero es vacio");
        }
        this.idTipo = idTipo;
        this.puntosResiduo = puntosResiduos;
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

    public String toString() {
        return " tipo: " + this.nombre + " puntos: " + this.puntosResiduo;
    }

    public int getPuntosResiduo() {
        return puntosResiduo;
    }

    public void setPuntosResiduo(int puntosResiduo) {
        this.puntosResiduo = puntosResiduo;
    }

}