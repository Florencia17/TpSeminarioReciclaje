package ar.edu.unrn.seminario.dto;


import java.time.LocalDateTime;

import java.util.Calendar;
import java.util.Date;


import ar.edu.unrn.seminario.herramienta.Fecha;
import ar.edu.unrn.seminario.modelo.Direccion;


public class ViviendaDTO {

    private PropietarioDTO propietarioDto;
    private DireccionDTO direccionDto;
    private LocalDateTime fechaRegistro;
    private int numeroVivienda;

    public ViviendaDTO(int numeroVivienda,PropietarioDTO propietarioDto, DireccionDTO direccionDto){
        super(); //de que?
        this.propietarioDto= propietarioDto;
        this.direccionDto=direccionDto;
        this.numeroVivienda=numeroVivienda;

    }
    public ViviendaDTO(PropietarioDTO propietarioDto, DireccionDTO direccionDto){
        super(); //de que?
        this.propietarioDto= propietarioDto;
        this.direccionDto=direccionDto;


    }


    public int getNumeroVivienda() {
        return this.numeroVivienda;
    }
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public PropietarioDTO getPropietario() {
        return propietarioDto;
    }

    public DireccionDTO getDireccion() {
        return direccionDto;
    }


    //es necesario????????
    public String getCalle(){
        return this.direccionDto.getCalle();
    }

    public int getNumero(){
        return this.direccionDto.getNumero();
    }

    public String getBarrio(){
        return this.direccionDto.getBarrio();
    }

    public String getNombrePropietario(){
        return this.propietarioDto.getNombre();
    }

    public String getApellidopropietarioDto(){
        return this.propietarioDto.getApellido();
    }

    public String getDnipropietarioDto(){
        return this.propietarioDto.getDni();
    }


}
