package ar.edu.unrn.seminario.modelo;

public class Residuo {
    private TipoResiduo tipoResiduo;
    private int id;
    private float peso;

    public Residuo(TipoResiduo tipoResiduo, float peso) {
        this.tipoResiduo = tipoResiduo;

        this.peso = peso;

    }

    public Residuo(int id, TipoResiduo tipoResiduo, float peso) {
        this.tipoResiduo = tipoResiduo;
        this.id = id;
        this.peso = peso;

    }

    public TipoResiduo getTipoResiduo() {
        return tipoResiduo;
    }

    public void setTipoResiduo(TipoResiduo tipoResiduo) {
        this.tipoResiduo = tipoResiduo;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

}
