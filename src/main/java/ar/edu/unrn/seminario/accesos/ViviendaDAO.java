package ar.edu.unrn.seminario.accesos;


import java.util.List;

import ar.edu.unrn.seminario.modelo.Direccion;
import ar.edu.unrn.seminario.modelo.Propietario;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface ViviendaDAO {


    void create(Propietario propietario,Direccion direccion);
    List<Vivienda> findAll();
    public Vivienda find(String username);
}