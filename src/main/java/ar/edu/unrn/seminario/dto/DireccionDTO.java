package ar.edu.unrn.seminario.dto;


import ModeloException.NullException;

public class DireccionDTO {
    private String calle;
    private int numero;
    private String barrio;
    private int idDireccion;

    public DireccionDTO(String calle, int numero, String barrio, int idDireccion) throws NullException {

        if (esDatoNulo(calle)) {
            throw new NullException("la calle es dato nulo");
        }
        if (esDatoNuloInt(numero)) {
            throw new NullException("el numero es dato nulo");
        }
        if (esDatoNulo(barrio)) {
            throw new NullException("el barrio es dato nulo");
        }
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
        this.idDireccion = idDireccion;
    }

    public DireccionDTO(String calle, int numero, String barrio) throws NullException {

        if (esDatoNulo(calle)) {
            throw new NullException("la calle es dato nulo");
        }
        if (esDatoNuloInt(numero)) {
            throw new NullException("el numero es dato nulo");
        }
        if (esDatoNulo(barrio)) {
            throw new NullException("el barrio es dato nulo");
        }
        this.calle = calle;
        this.numero = numero;
        this.barrio = barrio;
    }

    public String getCalle() {
        return calle;
    }

    public int getNumero() {
        return numero;
    }

    private boolean esDatoNuloInt(int dato) {
        if (dato == 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getBarrio() {
        return barrio;
    }

    private boolean esDatoNulo(String dato) {
        return dato == null | dato.isEmpty();
    }

    public int getIdDireccion() {
        return this.idDireccion;
    }

    public void setCalle(String text) {
        this.calle= calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}
