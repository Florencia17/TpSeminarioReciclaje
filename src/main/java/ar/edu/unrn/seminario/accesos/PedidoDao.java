package ar.edu.unrn.seminario.accesos;


import java.util.List;

import ModeloException.NullException;
import ar.edu.unrn.seminario.modelo.Pedido;
import ar.edu.unrn.seminario.modelo.Vivienda;

public interface PedidoDao {
    public void create(Vivienda vivienda, Pedido pedido);

    List<Pedido> findAll() throws NullException;

    Pedido find(int id_pedido, Vivienda vivienda);

}

