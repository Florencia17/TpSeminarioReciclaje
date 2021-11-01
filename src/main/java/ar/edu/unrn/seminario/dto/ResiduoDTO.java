package ar.edu.unrn.seminario.dto;

public class ResiduoDTO {
    private TipoResiduoDTO tipoResiduoDto;
    private ResiduoDTO residuoDto;
    private int id;
    private float peso;

    public ResiduoDTO(TipoResiduoDTO tipoResiduoDto, float peso) {
        this.tipoResiduoDto = tipoResiduoDto;

        this.peso = peso;

    }

    public ResiduoDTO(int peso) {

        this.peso = peso;

    }

    public TipoResiduoDTO getTipoResiduoDto() {
        return tipoResiduoDto;
    }

    public void setTipoResiduoDto(TipoResiduoDTO tipoResiduoDto) {
        this.tipoResiduoDto = tipoResiduoDto;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    @Override
    public String toString() {
        return tipoResiduoDto.toString() + " peso:" + peso;
    }

}
