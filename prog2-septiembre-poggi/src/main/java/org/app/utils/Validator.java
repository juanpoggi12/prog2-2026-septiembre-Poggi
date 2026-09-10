package org.app.utils;

import org.app.dto.AlquilerDTO;
import org.app.dto.ClienteDTO;

public class Validator {
    public static void validarCliente(ClienteDTO dto) {
        if (dto == null){
            throw new IllegalArgumentException("El cliente no puede ser nulo");
        }
        if (dto.getNombre() == null || dto.getNombre().isBlank()){
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        if(dto.getDni() == null || dto.getDni().isBlank()){
            throw new IllegalArgumentException("El dni es obligatorio");
        }
        if(dto.getLicenciaConducir() == null || dto.getLicenciaConducir().isBlank()){
            throw new IllegalArgumentException("El licencia conducir es obligatoria");
        }
    }

    public static void validarAlquiler(AlquilerDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("El alquiler no puede ser nulo");
        }
        if(dto.getIdCliente() == null) {
            throw new IllegalArgumentException("Wl cliente es obligatorio");
        }
        if(dto.getFechaHoraInicio() == null) {
            throw new IllegalArgumentException("Fecha de inicio es obligatora");
        }
        if(dto.getVehiculo() == null || dto.getVehiculo().isBlank()){
            throw new IllegalArgumentException("El vehiculo es obligatorio");
        }
        if (dto.getEstado() == null){
            throw new IllegalArgumentException("El estado es obligatorio");
        }
    }

}
