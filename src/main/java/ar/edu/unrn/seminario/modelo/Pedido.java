package ar.edu.unrn.seminario.modelo;
import java.util.Date;
import java.util.List;

import ModeloException.NullException;

public class Pedido {

    private Vivienda vivienda; // se obtiene mediante el numero de vivienda
    private Date fecha;
    private List<Residuo> residuo;
    private boolean vehículo; // true: se requiere vehículo de carga pesa, tratar con checkbox en vista
    private String observacion;
    private int idPedidoRetiro;

    public Pedido(Vivienda vivienda, Date fecha, List<Residuo> residuo, boolean vehiculo, String observacion)
            throws NullException {

        if (esDatoNulo(vivienda))
            throw new NullException("vivienda");
        else
            this.vivienda = vivienda;
        if (esDatoNulo(fecha))
            throw new NullException("fecha");
        else
            this.fecha = fecha;
        if (esDatoNulo(residuo))
            throw new NullException("residuo");
        else
            this.residuo = residuo;

        if (esDatoNulo(vehiculo))
            throw new NullException("vehiculo");
        else
            this.vehículo = vehiculo;
        if (esDatoNulo(observacion))
            throw new NullException("observacion");
        else
            this.observacion = observacion;

    }

    public Pedido(int idPedidoRetiro, Vivienda vivienda, Date fecha, List<Residuo> residuo, boolean vehiculo,
                  String observacion) throws NullException {
        if (esDatoNulo(idPedidoRetiro))
            throw new NullException("idpedido");
        else
            this.idPedidoRetiro = idPedidoRetiro;
        if (esDatoNulo(vivienda))
            throw new NullException("vivienda");
        else
            this.vivienda = vivienda;
        if (esDatoNulo(fecha))
            throw new NullException("fecha");
        else
            this.fecha = fecha;
        if (esDatoNulo(residuo))
            throw new NullException("residuo");
        else
            this.residuo = residuo;

        if (esDatoNulo(vehiculo))
            throw new NullException("vehiculo");
        else
            this.vehículo = vehiculo;
        if (esDatoNulo(observacion))
            throw new NullException("observacion");
        else
            this.observacion = observacion;

    }

    private boolean esDatoVacio(String dato) {
        return dato.equals("");
    }

    private boolean esDatoNulo(Object dato) {
        return dato == null;
    }

    public Date getFecha() {
        return this.fecha;
    }

    public List<Residuo> getResiduo() {
        return this.residuo;
    }

    public String getObservacion() {
        return observacion;
    }

    public boolean getVehiculo() {
        return this.vehículo;
    }

    public Vivienda getVivienda() {
        // TODO Auto-generated method stub
        return this.vivienda;
    }

    public int getidPedidoRetiro() {
        // TODO Auto-generated method stub
        return this.idPedidoRetiro;
    }
}

