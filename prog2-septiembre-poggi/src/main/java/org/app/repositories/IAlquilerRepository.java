package org.app.repositories;

import org.app.models.Alquiler;
import org.app.models.EnumEstado;

import java.util.Date;
import java.util.List;

public interface IAlquilerRepository {

    Alquiler crearAlquiler(Alquiler alquiler);
    Alquiler actualizarAlquiler(Alquiler alquiler);
    Alquiler obtenerPorID(Integer id);
    List<Alquiler> buscarAlquileresConFiltros(Date fechaDesde, Date fechaHasta, String tipoVehiculo, EnumEstado estado);
    List<Object[]> contarPorTipoVehiculo();
    List<Object[]> contarPorEstado();
}