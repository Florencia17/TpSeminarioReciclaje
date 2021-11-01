package ar.edu.unrn.seminario.modelo;


import java.util.Date;

public class Visita {
    private Date diaConcurrido;
    private String observacion;
    private int cantResiduo;
    private String tipoResiduo;

    public Visita(Date diaConcurrido, int cantResiduo, String tipoResiduo) {

        this.diaConcurrido = diaConcurrido;
        this.cantResiduo= cantResiduo;
        this.tipoResiduo = tipoResiduo;
    }

    public void agregarObservacion(String observacion) {
        this.observacion = observacion;
    }
}

