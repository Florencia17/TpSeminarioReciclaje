package ar.edu.unrn.seminario.modelo;
import ModeloException.NullException;

public class Direccion {

    private String calle;
    private int numero;
    private String barrio;
    private int idDireccion;

    // latitud y longitud?

    public Direccion(String calle, int numero, String barrio) throws NullException {

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

    public Direccion(String calle, int numero, String barrio, int idDireccion) throws NullException {

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

    // to string? equals?
    public int getIdDireccion() {
        return this.idDireccion;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }
}